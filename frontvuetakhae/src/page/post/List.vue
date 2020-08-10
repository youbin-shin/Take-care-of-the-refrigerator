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
              <v-list-item @click="goDetail(backData.boardId)">
                <v-list-item-avatar color="grey"></v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title class="headline">{{ backData.title }}</v-list-item-title>
                  <v-list-item-subtitle style="text-align:right;">작성자 : {{ backData.nickname }}</v-list-item-subtitle>
                  <small style="text-align:right;">{{ backData.createAt }}</small>
                </v-list-item-content>
              </v-list-item>

              <v-img
                :src="backData.thumbnailImage"
                height="194"
                @click="goDetail(backData.boardId)"
              ></v-img>

              <v-card-text @click="goDetail(backData.boardId)">
                <p class="caption">소요시간 : {{ backData.cookingTime }}시간</p>난이도
                <v-rating
                  class="p-0"
                  small
                  v-model="backData.grade"
                  background-color="orange lighten-3"
                  color="orange"
                ></v-rating>
              </v-card-text>
              <v-card-actions>
                <v-btn @click="goDetail(backData.boardId)" text color="deep-purple accent-4">자세히</v-btn>
                <v-btn text color="deep-purple accent-4">즐겨찾기</v-btn>
                <v-spacer></v-spacer>
                <v-btn icon @click="changeEasy">
                  <div v-if="easy">
                    <b-icon icon="emoji-smile" scale="2" variant="warning"></b-icon>
                    <p class="caption mb-0 mt-1">easy</p>
                  </div>
                  <div v-else>
                    <b-icon icon="emoji-frown" scale="2" variant="secondary"></b-icon>
                    <p class="caption mb-0 mt-1">hard</p>
                  </div>
                </v-btn>
                <v-btn icon>
                  <v-icon>mdi-share-variant</v-icon>
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

<script>
import axios from "axios";
const BACK_URL = "http://i3a305.p.ssafy.io:8399/api";

export default {
  name: "Post",
  components: {
    // DetailPost,
  },
  watch: {},
  created() {
    axios.get(`${BACK_URL}/boards`).then((response) => {
      console.log(response.data);
      this.backDatas = response.data.boards;
    });
  },
  methods: {
    goDetail(boardId) {
      this.$router.push("/detail/" + boardId);
    },
    changeEasy() {
      if (this.easy) {
        this.easy = false;
      } else {
        this.easy = true;
      }
    },
  },
  data: () => {
    return {
      limit: 0,
      backDatas: [],
      easy: true,
    };
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
  background-size: 100%;
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
/* .tag-list-wrap {
  position: fixed;
  left: 90%;
  top: 600px;
} */
.input:focus {
  outline: none;
}
.v-card {
  cursor: pointer;
}
/* .tag-list-wrap h4 {
  font-size: 1em;
  padding-bottom: 5px;
  border-bottom: 1px solid #000;
  margin-bottom: 20px;
  font-weight: 600;
}

.tag-list-wrap ul.tag-list {
  width: 100%;
  float: left;
  padding-right: 30px;
  list-style: none;
}

.tag-list-wrap ul.tag-list li {
  cursor: pointer;
  margin-bottom: 15px;
} */
</style>
