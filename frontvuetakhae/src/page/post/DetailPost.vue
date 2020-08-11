<template>
  <!-- <div> -->
  <div class="container">
    <div class="detailHeader">
      <h1 class="mt-5 mb-2">
        {{ detailData.title }}
        <b-badge class="mr-2" variant="success">난이도 {{ detailData.grade }}</b-badge>
        <b-badge variant="secondary">소요시간 {{ detailData.cookingTime }}시간</b-badge>
      </h1>
      <div @click="changeEasy" class="icon">
        <!-- 좋아요 수 {{ likenum }} -->
        <div v-if="easy">
          <b-icon icon="emoji-smile" class="mr-1" scale="2" variant="warning"></b-icon>
          <p class="caption mb-0 mt-1">easy</p>
        </div>
        <div v-else>
          <b-icon icon="emoji-frown" class="mr-1" scale="2" variant="secondary"></b-icon>
          <p class="caption mb-0 mt-1">hard</p>
        </div>
      </div>
      <b-row>
        <b-col>
          <b-avatar class="mt-2"></b-avatar>
        </b-col>
        <b-col cols="10">
          <p>{{ detailData.userId }}</p>
          <p>{{ detailData.createAt }}</p>
        </b-col>
        <b-col>댓글 {{ detailData.commentsNum }}</b-col>
      </b-row>
      <!-- <p>댓글수{{detailData.commentsNum}} {{detail.comments}}</p> -->
    </div>
    <hr />
    <div class="detailContent">
      <h4 class="detailContentItem">필요한 재료</h4>
      <p>{{ detailData.ingredient }}</p>

      <h4 class="detailContentItem">과정</h4>

      <ul v-for="step in detailData.steps" :key="step">
        <li>{{ step.image }} : {{step.description}}</li>
      </ul>
      <hr />
      <b-row>
        <b-col>
          <h4 class="detailContentItem">후기</h4>
        </b-col>
        <b-col cols="10">{{ detailData.content }}</b-col>
      </b-row>
      <hr />
      <div class="comments">
        <h4>댓글</h4>
        <b-row v-for="comment in detailData.comments" :key="comment">
          <b-col>
            <b-avatar variant="primary" class="m-2 auto" text="프로필"></b-avatar>
            <p>{{ comment.nickname }}</p>
          </b-col>
          <b-col cols="10">
            <p>{{ comment.commentContent }}</p>
            <p>{{ comment.createAt }}</p>
          </b-col>
        </b-row>

        <b-row>
          <b-col>
            <b-avatar variant="success" icon="people-fill"></b-avatar>
            <p>{{ }}</p>
          </b-col>
          <b-col cols="10">
            <b-input-group>
              <b-form-input label="댓글을 입력해주세요." v-model="commentInput"></b-form-input>
              <b-button variant="secondary" @click="createComment">등록</b-button>
            </b-input-group>
          </b-col>
        </b-row>
        <div></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const BACK_URL = "http://i3a305.p.ssafy.io:8399/api";

export default {
  name: "DetailPost",
  props: {
    // back 연결되면 사용할 데이터
    // detailData: {
    //   type: Object,
    // },
  },
  created() {
    // this.backData = this.dummyData[this.$route.params.no - 1];
    let boardurlId = this.$route.params.no;
    axios.get(`${BACK_URL}/boards/${boardurlId}`).then((response) => {
      console.log(response);
      this.detailData.boardId = response.data.board.boardId;
      this.detailData.title = response.data.board.title;
      this.detailData.userId = response.data.board.userId;
      this.detailData.ingredient = response.data.board.ingredient;
      this.detailData.content = response.data.board.content;
      this.detailData.createAt = response.data.board.createAt;
      this.detailData.updateAt = response.data.board.updateAt;
      this.detailData.grade = response.data.board.grade;
      this.detailData.cookingTime = response.data.board.cookingTime;
      this.detailData.thumbailImage = response.data.board.crethumbailImageateAt;
      this.detailData.steps = response.data.board.steps;
      this.detailData.tags = response.data.board.tags;
      this.detailData.comments = response.data.board.comments;
      this.commentsNum = response.data.board.comments.length;
      console.log(this.detailData);
    });
  },
  data() {
    return {
      easy: true,
      detailData: {
        boardId: "",
        title: "",
        userId: "",
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
        commentsNum: null,
      },
      commentInput: null,
    };
  },
  methods: {
    createComment() {
      axios
        .post(
          `${BACK_URL}/boards/comments`,
          {
            boardId: this.detailData.boardId,
            commentContent: this.commentInput,
          },
          {
            headers: { "jwt-auth-token": this.$cookies.get("token") },
          }
        )
        .then((response) => {
          console.log(response);
          if (response.status === 200) {
            alert("댓글이 작성되었습니다!");
            this.$router.go();
          }
        })
        .catch((error) => {
          alert(error);
        });
    },
    changeEasy() {
      if (this.easy) {
        this.easy = false;
      } else {
        this.easy = true;
      }
    },
  },
};
</script>

<style>
.detailHeader {
  text-align: left;
  vertical-align: center;
}
.detailContent {
  text-align: left;
}
.detailContentItem {
  margin-bottom: 20px;
}
.icon {
  text-align: right;
  cursor: pointer;
}
</style>
