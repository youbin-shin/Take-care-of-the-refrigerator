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
    <h2 class="m-5">지금 당장 가능한 요리 레시피</h2>
    <div class="row row-cols-3">
      <ul v-for="backData in backDatas" :key="backData.title">
        <v-card max-width="344" class="mx-auto" @click="goDetail(backData.boardId)">
          <v-list-item>
            <v-list-item-avatar color="grey"></v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title class="headline">{{ backData.title }}</v-list-item-title>
              <v-list-item-subtitle style="text-align:right;">작성자 : {{ backData.nickname }}</v-list-item-subtitle>
              <small style="text-align:right;">{{ backData.createAt }}</small>
            </v-list-item-content>
          </v-list-item>

          <v-img :src="backData.thumbnailImage" height="194"></v-img>

          <v-card-text>
            소요시간 : {{ backData.cookingTime }}시간
            난이도
            <v-rating v-model="backData.grade "></v-rating>
          </v-card-text>
          <v-card-actions>
            <v-btn text color="deep-purple accent-4">자세히 보기</v-btn>
            <v-btn text color="deep-purple accent-4">관심레시피</v-btn>
            <v-spacer></v-spacer>
            <v-btn icon>
              <v-icon>mdi-heart</v-icon>
            </v-btn>
            <v-btn icon>
              <v-icon>mdi-share-variant</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>
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