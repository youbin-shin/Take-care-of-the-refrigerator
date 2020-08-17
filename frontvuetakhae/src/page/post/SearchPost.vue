<template>
  <div class="searchPost">
    <div class="container">
      <v-card class="p-3" color="grey lighten-1">
        <h2 class="m-3 0 4">나의 냉장고</h2>
        <h3 v-if="emptyChip" class="white--text">요리할 재료를 입력해주세요. 냉뷰가 기다리고 있습니다.</h3>

        <div>
          <div class="left d-sm-inline-flex pa-2">
            <v-row class="m-2 inputBlank" variant="danger">
              <v-text-field
                label="직접 추가하기"
                v-model="addText"
                hide-details="auto"
                v-on:keyup.enter="
                  plusFood();
                  check();
                "
              ></v-text-field>
              <v-icon
                large
                @click="
                  plusFood();
                  check();
                "
              >mdi-plus</v-icon>
            </v-row>
          </div>
          <div>
            <v-chip
              class="m-1"
              v-for="tag in chips"
              close
              @click:close="closeChip(tag)"
              :key="tag"
            >{{ tag }}</v-chip>
            <div v-if="emptyChip">요리할 재료를 입력해주세요.</div>
          </div>
        </div>
      </v-card>
      <h2 class="m-5 white--text">지금 당장 가능한 요리 레시피</h2>
      <div class="row row-cols-3 searchPostContent">
        <ul v-for="apiboard in searchData.apiboards" :key="apiboard.title">
          <v-hover v-slot:default="{ hover }" open-delay="200">
            <v-card max-width="344" class="mx-auto" :elevation="hover ? 16 : 2">
              <v-list-item @click="goApiDetail(apiboard.boardId)">
                <v-list-item-avatar color="grey"></v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title class="headline">
                    {{
                    apiboard.title
                    }}
                  </v-list-item-title>
                  <v-list-item-subtitle style="text-align:right;">작성자 : {{ apiboard.nickname }}</v-list-item-subtitle>
                  <small style="text-align:right;">
                    {{
                    apiboard.createAt
                    }}
                  </small>
                </v-list-item-content>
              </v-list-item>

              <v-img
                :src="apiboard.thumbnailImage"
                height="194"
                @click="goApiDetail(apiboard.boardId)"
              ></v-img>

              <v-card-text @click="goApiDetail(apiboard.boardId)">
                <p class="caption">소요시간 : {{ apiboard.cookingTime }}시간</p>난이도
                <v-rating
                  class="p-0"
                  small
                  v-model="apiboard.grade"
                  background-color="orange lighten-3"
                  color="orange"
                ></v-rating>
              </v-card-text>
              <v-card-actions>
                <v-btn
                  @click="goApiDetail(apiboard.boardId)"
                  text
                  color="deep-purple accent-4"
                >자세히 보기</v-btn>
                <v-btn text color="deep-purple accent-4">즐겨찾기</v-btn>
                <v-btn icon>
                  <v-icon>mdi-heart</v-icon>
                </v-btn>

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
      <div class="row row-cols-3 searchPostContent">
        <ul v-for="board in searchData.boards" :key="board.title">
          <v-hover v-slot:default="{ hover }" open-delay="200">
            <v-card max-width="344" class="mx-auto" :elevation="hover ? 16 : 2">
              <v-list-item @click="goDetail(board.boardId)">
                <v-list-item-avatar color="grey"></v-list-item-avatar>
                <v-list-item-content>
                  <v-list-item-title class="headline">
                    {{
                    board.title
                    }}
                  </v-list-item-title>
                  <v-list-item-subtitle style="text-align:right;">작성자 : {{ board.nickname }}</v-list-item-subtitle>
                  <small style="text-align:right;">
                    {{
                    board.createAt
                    }}
                  </small>
                </v-list-item-content>
              </v-list-item>

              <v-img :src="board.thumbnailImage" height="194" @click="goDetail(board.boardId)"></v-img>

              <v-card-text @click="goDetail(board.boardId)">
                <p class="caption">소요시간 : {{ board.cookingTime }}시간</p>난이도
                <v-rating
                  class="p-0"
                  small
                  v-model="board.grade"
                  background-color="orange lighten-3"
                  color="orange"
                ></v-rating>
              </v-card-text>
              <v-card-actions>
                <v-btn @click="goDetail(board.boardId)" text color="deep-purple accent-4">자세히 보기</v-btn>
                <v-btn text color="deep-purple accent-4">즐겨찾기</v-btn>
                <v-btn icon>
                  <v-icon>mdi-heart</v-icon>
                </v-btn>

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
    </div>
  </div>
</template>

<script>
import axios from "axios";
const BACK_URL = "http://i3a305.p.ssafy.io:8399/api";

export default {
  name: "SearchPost",
  data() {
    return {
      searchData: {
        boards: [],
        apiboards: [],
      },
      easy: true,
      limit: 0,
      addText: "",
      emptyChip: false,
      showDatas: [],
      frag: 0,
      chips: [
        // 마이페이지에 입력한 나의 냉장고 데이터를 넣기
      ],
    };
  },

  created() {
    axios
      .get(`${BACK_URL}/users/mypage/box`, {
        headers: { "jwt-auth-token": this.$cookies.get("token") },
      })
      .then((response) => {
        console.log(response.data);
        this.chips = response.data.box;
        if (this.chips.length === 0) {
          this.emptyChip = true;
        }
        axios
          .post(`${BACK_URL}/boards/foodsafe/recipes/ingredient`, {
            ingredient: this.chips,
          })
          .then((response) => {
            console.log(response);
            this.searchData.apiboards = response.data.recipes;
            // console.log(this.searchData.boards);
          });
      });
    axios
      .post(`${BACK_URL}/boards/foodList`, { foodList: this.chips })
      .then((response) => {
        this.searchData.boards = response.data.boards;
      });
  },
  methods: {
    changeEasy() {
      if (this.easy) {
        this.easy = false;
      } else {
        this.easy = true;
      }
    },
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    goDetail(boardId) {
      this.$router.push("/detail/" + boardId);
    },
    goApiDetail(boardId) {
      this.$router.push("/foodsafe/detail/" + boardId);
    },
    plusFood() {
      // 빈값일 경우 추가 안되도록 한다.
      if (this.addText === "") {
        return;
      }
      // 중복되는 데이터일 경우 추가 안되도록 한다.
      for (var i = 0; i < this.chips.length; i++) {
        if (this.addText === this.chips[i]) {
          this.addText = "";
          return;
        }
      }
      // 위의 경우가 아니라면 추가한다.
      this.chips.push(this.addText);
      this.addText = "";
      this.emptyChip = false;
    },
    closeChip(tag) {
      // close 버튼 누를 경우 실행되는 메소드 (리스트에서 삭제)
      this.chips.splice(this.chips.indexOf(tag), 1);
      axios
        .post(`${BACK_URL}/boards/foodsafe/recipes/ingredient`, {
          ingredient: this.chips,
        })
        .then((response) => {
          console.log(response);
          this.searchData.apiboards = response.data.recipes;
          // console.log(this.searchData.boards);
        });
      axios
        .post(`${BACK_URL}/boards/foodList`, { foodList: this.chips })
        .then((response) => {
          this.searchData.boards = response.data.boards;
          console.log(this.searchData.boards);
        });
      if (this.chips.length === 0) {
        this.emptyChip = true;
        this.searchData.boards = [];
      }
    },
    check() {
      axios
        .post(`${BACK_URL}/boards/foodList`, { foodList: this.chips })
        .then((response) => {
          this.searchData.boards = response.data.boards;
          console.log(this.searchData.boards);
        });
      axios
        .post(`${BACK_URL}/boards/foodsafe/recipes/ingredient`, {
          ingredient: this.chips,
        })
        .then((response) => {
          console.log(response);
          this.searchData.apiboards = response.data.recipes;
          // console.log(this.searchData.boards);
        });
      if (this.chips.length === 0) {
        this.emptyChip = true;
      }
    },
  },
};
</script>

<style>
.searchPost {
  background-image: url("https://user-images.githubusercontent.com/60081201/89270362-33b1d980-d676-11ea-8b50-bee693e1ee36.jpg");
  -webkit-background-size: cover;
  -moz-background-size: cover;
  -o-background-size: cover;
  background-size: cover;
  min-height: 100%;
}
.searchPostContent {
  min-height: 400px;
}
</style>
