<template>
  <div class="post">
    <SearchHomePost @search-input="searchInput" />
    <div v-if="searchDatas" class="container">
      <h2 class="m-5 0 4">검색한 레시피</h2>

      <div class="row row-cols-3">
        <ul v-for="searchData in searchDatas" :key="searchData.title">
          <v-hover v-slot:default="{ hover }" open-delay="200">
            <v-card max-width="344" class="mx-auto" :elevation="hover ? 16 : 2">
              <v-list-item>
                <v-list-item-avatar color="grey"></v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title
                    class="headline"
                    @click="goDetail(searchData.boardId)"
                    >{{ searchData.title }}</v-list-item-title
                  >
                  <v-list-item-subtitle
                    style="text-align: right;"
                    @click="goOtherpage(searchData.nickname)"
                    >작성자 : {{ searchData.nickname }}</v-list-item-subtitle
                  >
                  <small style="text-align: right;">{{
                    searchData.createAt
                  }}</small>
                </v-list-item-content>
              </v-list-item>

              <v-img
                :src="searchData.thumbnailImage"
                height="194"
                @click="goDetail(searchData.boardId)"
              ></v-img>

              <v-card-text
                @click="goDetail(searchData.boardId)"
                style="text-align: left;"
              >
                <p class="m-0">소요시간 {{ searchData.cookingTime }}시간</p>
                난이도
                <v-rating
                  class="d-inline-flex pa-2"
                  small
                  v-model="searchData.grade"
                  background-color="orange lighten-3"
                  color="orange"
                ></v-rating>
              </v-card-text>
              <v-card-actions>
                <v-btn
                  @click="goDetail(searchData.boardId)"
                  text
                  color="deep-purple accent-4"
                  >자세히</v-btn
                >
                <span @click="heartRecipe(searchData.boardId)">
                  <span v-if="searchData.favorite">
                    <v-bottom-navigation
                      class="elevation-0"
                      :value="searchData.favorite"
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
                      :value="searchData.favorite"
                      style="width: 60px"
                      color="secondary lighten-2"
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
                        searchData.title,
                        searchData.boardId,
                        searchData.thumbnailImage,
                        searchData.nickname
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
                <v-list-item-content class="row">
                  <v-list-item-title
                    class="headline text-left col-9"
                    @click="goDetail(backData.boardId)"
                    >{{ backData.title }}</v-list-item-title
                  >
                  <div class="col-3" @click="heartRecipe(backData.boardId)">
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
                        color="secondary lighten-2"
                      >
                        <v-btn>
                          <span>즐겨찾기</span>
                          <v-icon>mdi-heart</v-icon>
                        </v-btn>
                      </v-bottom-navigation>
                    </span>
                  </div>
                </v-list-item-content>
              </v-list-item>

              <v-img
                :src="backData.thumbnailImage"
                height="194"
                @click="goDetail(backData.boardId)"
              ></v-img>

              <v-card-text
                @click="goDetail(backData.boardId)"
                style="text-align: left;"
              >
                <v-list-item-subtitle
                  class="mb-2"
                  @click="goOtherpage(backData.nickname)"
                >
                  작성자 : {{ backData.nickname }}
                  <small style="float:right">
                    {{ backData.createAt }}
                  </small>
                </v-list-item-subtitle>

                <p class="m-0">소요시간 {{ backData.cookingTime }}시간</p>
                난이도
                <v-rating
                  class="d-inline-flex pa-2"
                  small
                  v-model="backData.grade"
                  background-color="orange lighten-3"
                  color="orange"
                ></v-rating>
                <v-btn icon style="float:right">
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
                </v-btn>
              </v-card-text>
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
import SearchHomePost from "@/page/postItem/SearchHomePost.vue";

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
      searchDatas: null,
    };
  },
  components: {
    SearchHomePost,
  },
  created() {
    axios
      .get(`${BACK_URL}/boards`, {
        headers: { "jwt-auth-token": this.$cookies.get("token") },
      })
      .then((response) => {
        console.log(response.data);
        this.backDatas = response.data.boards;
      });
    Kakao.init("bed1ac3b578a5c6daea9bcc807fdc6d8");
  },
  methods: {
    searchInput(input, typeNum) {
      console.log(input, typeNum);
      axios
        .post(
          `${BACK_URL}/boards/search`,
          {
            keyword: input,
            type: typeNum,
          },
          {
            headers: { "jwt-auth-token": this.$cookies.get("token") },
          }
        )
        .then((response) => {
          console.log(response);
          if (response.status === 200) {
            this.searchDatas = response.data.boards;
          }
        })
        .catch((error) => {
          alert(error);
        });
    },
    heartRecipe(boardId) {
      console.log(boardId);
      // 즐겨찾기 눌렀을 경우 사용자 데이터에 추가하기
      axios
        .post(
          `${BACK_URL}/boards/favorite`,
          {
            boardId: boardId,
          },
          {
            headers: { "jwt-auth-token": this.$cookies.get("token") },
          }
        )
        .then((response) => {
          console.log(response);
          if (response.status === 200) {
            alert("관심레시피에 등록되었습니다.");
            // this.$router.go();
          }
        })
        .catch((error) => {
          alert(error);
        });
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
.search-img {
  float: right;
  margin-right: 10px;
  cursor: pointer;
}
.search-input {
  width: 600px;
  height: 50px;
  border: 1px solid black;
  border-radius: 100px;
  background-color: white;
}
.search-input-text {
  width: 300px;
  size: 30px;
  font-size: 20px;
  border: none;
  margin-right: 100px;
  letter-spacing: 2px;
  padding-left: 5px;
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
  padding: 90px;
  /* margin-bottom: 90px; */
  /* padding-top: 90px; */
}
.search-bar-input {
  width: 100px;
}
.v-application .headline {
  font-family: "TmoneyRoundWindExtraBold" !important;
}
</style>
