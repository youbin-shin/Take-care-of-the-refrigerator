<template>
  <div class="search-bar">
    <h1 class="search-bar-title">{{ introduceTitle }}</h1>
    <v-row class="search-input m-auto">
      <v-col cols="4" sm="2" style="padding: 0px;">
        <v-overflow-btn
          :items="searchType"
          label="제목"
          v-model="searchSelectNum"
          target="#dropdown-example"
        ></v-overflow-btn>
      </v-col>
      <v-col cols="6">
        <input
          class="search-input-text"
          type="text"
          placeholder="관심있는 요리를 검색하세요."
          v-model="searchBar"
          v-on:keyup.enter="searchInput()"
        />
      </v-col>
      <v-col cols="4">
        <div class="search-img mt-2" @click="searchInput">
          <svg
            width="1.5em"
            height="1.5em"
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
      </v-col>
    </v-row>
  </div>
</template>

<script>
import _ from "lodash";

export default {
  name: "SearchHomePost",
  created() {
    this.introduceTitle = _.sample(this.introduceList);
  },
  data() {
    return {
      introduceTitle: "",
      introduceList: [
        "음식으로 못 고치는 병은 의사도 못 고친다.",
        "나를 살게 하는 것은 충분한 음식이지 훌륭한 말이 아니다.",
        "음식에 대한 사랑처럼 진실된 사랑은 없다.",
        "배가 비어 있으면 정신도 빌 수 밖에 없다.",
        "배고픈 자는 음식을 가리지 않는다.",
        "잘못된 음식이란 것은 없다.",
        "식욕 이상으로 진실된 집념은 없다.",
        "요리가 없다면 예술도 지성도 사라질 것이다.",
        "새로운 요리의 발견이 새로운 별의 발견보다 인간을 더욱 행복하게 만든다.",
      ],
      searchBar: "",
      searchType: [
        { text: "제목", value: 1 },
        { text: "작성자", value: 2 },
      ],
      searchSelectNum: 1,
    };
  },
  methods: {
    searchInput() {
      if (this.searchBar.trim()) {
        this.$emit("search-input", this.searchBar, this.searchSelectNum);
        this.searchBar = "";
      }
    },
  },
};
</script>

<style scoped>
.search-bar-title {
  font-family: "MapoGoldenPier";
}
</style>
