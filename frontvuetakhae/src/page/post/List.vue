<template>
  <div class="post">
    <div class="search-bar">
      <h1 class="search-bar-title">Happy cooking time</h1>
      <div class="search-input">
        <input class="search-input-text" type="text" placeholder="관심있는 요리를 검색하세요." />
        <div class="search-img">
          <svg
            width="1em"
            height="1em"
            viewBox="0 0 16 16"
            class="bi bi-search"
            fill="currentColor"
            xmlns="http://www.w3.org/2000/svg"
          >
            <path
              fill-rule="evenodd"
              d="M10.442 10.442a1 1 0 0 1 1.415 0l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1 1 0 0 1 0-1.415z"
            />
            <path
              fill-rule="evenodd"
              d="M6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11zM13 6.5a6.5 6.5 0 1 1-13 0 6.5 6.5 0 0 1 13 0z"
            />
          </svg>
        </div>
      </div>
    </div>

    <br />
    <div class="container">
      <h2 class="m-5 0 4">인기순 레시피</h2>
      <div class="row row-cols-3">
        <ul v-for="backData in backDatas" :key="backData.title">
          <v-hover v-slot:default="{ hover }" open-delay="200">
            <v-card max-width="344" class="mx-auto" :elevation="hover ? 16 : 2">
              <v-list-item>
                <v-list-item-avatar color="grey"></v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="headline"
                    @click="goDetail(backData.boardId)"
                  >{{ backData.title }}</v-list-item-title>
                  <v-list-item-subtitle
                    style="text-align: right;"
                    @click="goOtherpage(backData.nickname)"
                  >작성자 : {{ backData.nickname }}</v-list-item-subtitle>
                  <small style="text-align: right;">
                    {{
                    backData.createAt
                    }}
                  </small>
                </v-list-item-content>
              </v-list-item>

              <v-img
                :src="backData.thumbnailImage"
                height="194"
                @click="goDetail(backData.boardId)"
              ></v-img>

              <v-card-text @click="goDetail(backData.boardId)" style="text-align: left;">
                <p class="m-0">소요시간 {{ backData.cookingTime }}시간</p>난이도
                <v-rating
                  class="d-inline-flex pa-2"
                  small
                  v-model="backData.grade"
                  background-color="orange lighten-3"
                  color="orange"
                ></v-rating>
              </v-card-text>
              <v-card-actions>
                <v-btn @click="goDetail(backData.boardId)" text color="deep-purple accent-4">자세히</v-btn>
                <span @click="heartRecipe">
                  <span v-if="backData.favorite">
                    <v-bottom-navigation
                      class="elevation-0"
                      :value="backData.favorite"
                      style="width: 60px"
                      color="deep-purple"
                    >
                      <v-btn>
                        <span>즐겨찾기</span>
                        <v-icon>mdi-heart</v-icon>
                      </v-btn>
                    </v-bottom-navigation>
                  </span>
                  <span v-else>
                    <v-bottom-navigation
                      class="elevation-0"
                      :value="backData.favorite"
                      style="width: 60px"
                    >
                      <v-btn>
                        <span>즐겨찾기</span>
                        <v-icon>mdi-heart</v-icon>
                      </v-btn>
                    </v-bottom-navigation>
                  </span>
                </span>
                <v-spacer></v-spacer>

                <v-btn icon>
                  <img
                    @click="
                      kakaoShare(
                        backData.title,
                        backData.boardId,
                        backData.thumbnailImage,
                        backData.nickname
                      )
                    "
                    src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png"
                    width="40"
                  />

                  <!-- <v-icon>mdi-share-variant</v-icon> -->
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-hover>
        </ul>
      </div>

      <!-- <div class="tag-list-wrap">
        <h4>인기태그</h4>
        <ul class="tag-list">
          <li>#태그1 (8)</li>
          <li>#태그2 (5)</li>
          <li>#태그3 (2)</li>
        </ul>
      </div>-->
    </div>
  </div>
</template>
<script
  type="text/JavaScript"
  src="https://developers.kakao.com/sdk/js/kakao.min.js"
></script>
<script>
import axios from "axios";
const BACK_URL = "http://i3a305.p.ssafy.io:8399/api";

export default {
  name: "Post",

  data: () => {
    return {
      limit: 0,
      backDatas: [],
      userData: {
        nickname: "",
      },
    };
  },
  created() {
    axios.get(`${BACK_URL}/boards`).then((response) => {
      console.log(response.data);
      this.backDatas = response.data.boards;
    });
    Kakao.init("bed1ac3b578a5c6daea9bcc807fdc6d8");
  },
  methods: {
    heartRecipe() {
      // 즐겨찾기 눌렀을 경우 사용자 데이터에 추가하기
    },
    kakaoShare(title, boardId, imgUrl, nickName) {
      Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: title, // 콘텐츠의 타이틀
          description: "작성자 : " + nickName, // 콘텐츠 상세설명
          imageUrl: imgUrl, // 썸네일 이미지
          link: {
            mobileWebUrl: "http://i3a305.p.ssafy.io/#/detail/" + boardId, // 모바일 카카오톡에서 사용하는 웹 링크 URL
            webUrl: "http://i3a305.p.ssafy.io/#/detail/" + boardId, // PC버전 카카오톡에서 사용하는 웹 링크 URL
          },
        },
        social: {
          likeCount: 0, // LIKE 개수
          commentCount: 0, // 댓글 개수
          sharedCount: 0, // 공유 회수
        },
        buttons: [
          {
            title: "게시글 확인", // 버튼 제목
            link: {
              mobileWebUrl: "http://i3a305.p.ssafy.io/#/detail/" + boardId, // 모바일 카카오톡에서 사용하는 웹 링크 URL
              webUrl: "http://i3a305.p.ssafy.io/#/detail/" + boardId, // PC버전 카카오톡에서 사용하는 웹 링크 URL
            },
          },
        ],
      });
    },
    goDetail(boardId) {
      this.$router.push("/detail/" + boardId);
    },
    goOtherpage(nickname) {
      axios
        .get(`${BACK_URL}/users/mypage`, {
          headers: { "jwt-auth-token": this.$cookies.get("token") },
        })
        .then((response) => {
          this.userData.nickname = response.data.mypage.nickname;
          if (this.userData.nickname === nickname) {
            this.$router.push("/user/mypage");
          } else {
            this.$router.push("/users/otherpage/" + nickname);
          }
        })
        .catch(() => {
          alert("로그인 후 이용 가능합니다.");
        });
    },
  },
};
</script>

<style>
.search-img {
  float: right;
  margin-right: 10px;
  cursor: pointer;
}
.search-input {
  width: 500px;
  height: 50px;
  border: 1px solid black;
  border-radius: 100px;
  padding: 10px;
  background-color: white;
  margin: 0 auto;
}
.search-input-text {
  width: 300px;
  size: 30px;
  font-size: 20px;
  border: none;
  border-right: 0px;
  border-top: 0px;
  border-left: 0px;
  border-bottom: 0px;
}
.search-bar {
  background-image: url("https://user-images.githubusercontent.com/60081201/88775069-3799b380-d1bf-11ea-8473-8e1e322c1923.jpg");
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  height: 400px;
}
.search-bar-title {
  font-weight: bold;
  margin-bottom: 90px;
  padding-top: 90px;
  color: purple;
}
.search-bar-input {
  width: 100px;
}
.postcard {
  cursor: pointer;
}
.input:focus {
  outline: none;
}
.v-card {
  cursor: pointer;
}
.element.style {
  width: 60px;
}
</style>
