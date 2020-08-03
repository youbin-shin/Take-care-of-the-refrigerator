<template>
  <div class="container">
    <v-card class="p-1" color="grey lighten-1">
      <h2 class="m-5 0 4">나의 냉장고</h2>
      <v-combobox
        v-model="chips"
        :items="items"
        chips
        clearable
        label="요리하고 싶은 재료를 입력해주세요."
        multiple
        solo
        class="m-3"
      >
        <template v-slot:selection="{ attrs, item, select, selected }">
          <v-chip
            v-bind="attrs"
            :input-value="selected"
            close
            @click="select"
            @click:close="remove(item)"
          >
            <strong>{{ item }}</strong>&nbsp;
          </v-chip>
        </template>
      </v-combobox>
    </v-card>
    <h2 class="m-5 0 4">지금 당장 가능한 요리 레시피</h2>
    <div class="row row-cols-3">
      <ul v-for="backData in backDatas" :key="backData.title">
        <div class="col postcard" @click="goDetail(backData.boardId)">
          <b-card
            v-bind:img-src="backData.thumbnailImage"
            img-width="100%"
            img-height="200px"
            img-alt="Image"
            img-top
            tag="article"
            style="max-width: 20rem;"
            class="mb-2"
          >
            <div class="contents">
              <h3>{{ backData.title }}</h3>
              <!-- <p class="content">{{ backData.content }}</p> -->

              <span class="date">{{ backData.date }}</span>
              <br />
              <a>작성자 : {{ backData.nickname }}</a>
              <br />
              <a>작성 시간 : {{ backData.createAt }}</a>
              <br />

              <span class="comment">소요시간 : {{ backData.cookingTime }}시간</span>
            </div>
            <div class="writer-wrap">
              <hr />
              <div>
                난이도
                <v-rating v-model="backData.grade "></v-rating>
              </div>
            </div>
          </b-card>
        </div>
      </ul>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "SearchPost",
  data() {
    return {
      limit: 0,
      backDatas: [],
      chips: [
        // 마이페이지에 입력한 나의 냉장고 데이터를 넣기
        "간장",
        "김치",
        "돼지고기",
        "새우",
      ],
    };
  },
  created() {
    axios.get("http://i3a305.p.ssafy.io:8399/api/boards").then((response) => {
      console.log(response.data);
      this.backDatas = response.data.boards;
    });
  },
  methods: {
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
    },
    goDetail(boardId) {
      this.$router.push("/detail/" + boardId);
    },
  },
};
</script>