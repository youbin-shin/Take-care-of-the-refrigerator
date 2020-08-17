<template>
  <div>
    <h1>apiDetail</h1>
    {{detailApiData}}
  </div>
</template>

<script>
import axios from "axios";
// import Comment from "@/page/postItem/Comment.vue";
const BACK_URL = "http://i3a305.p.ssafy.io:8399/api";

export default {
  name: "ApiDetailPost",
  created() {
    let boardurlId = this.$route.params.no;
    axios
      .get(`${BACK_URL}/boards/foodsafe/recipes/${boardurlId}`)
      .then((response) => {
        console.log(response);
        this.detailData.boardId = response.data.recipes[0].rcpSeq;
        this.detailData.title = response.data.recipes[0].rcpNm;
        this.detailData.nickname = "식품안전나라";
        this.detailData.ingredient = response.data.recipes[0].rcpPartsDtls;
        this.detailData.createAt = response.data.recipes[0].createAt;
        this.detailData.updateAt = response.data.recipes[0].updateAt;
        this.detailData.difficultyCount =
          response.data.recipes[0].difficultyCount;
        this.detailData.easyCount = response.data.recipes[0].easyCount;
        this.detailData.grade = response.data.recipes[0].grade;
        this.detailData.cookingTime = response.data.recipes[0].cookingTime;
        this.detailData.thumbailImage = response.data.recipes[0].attFileNoMain;
        this.detailData.steps = response.data.recipes[0].manuals;
        this.detailData.tags = response.data.recipes[0].tags;
        this.detailData.comments = response.data.recipes[0].comments;
        console.log(this.detailData);
      });
  },
  data() {
    return {
      detailApiData: {
        boardId: "",
        title: "",
        nickname: "",
        ingredients: "",
        content: "",
        createAt: "",
        updateAt: null,
        grade: "",
        cookingTime: "",
        thumbailImage: null,
        steps: [],
        tags: [],
        comments: [],
        difficultyCount: 0,
        easyCount: 0,
      },
    };
  },
};
</script>

<style>
</style>