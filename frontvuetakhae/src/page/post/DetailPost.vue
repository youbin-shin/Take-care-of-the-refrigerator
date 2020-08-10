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
            <p>수지</p>
          </b-col>
          <b-col cols="10">
            <b-input-group>
              <b-form-input></b-form-input>
              <b-button variant="secondary">등록</b-button>
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
    let boardId = this.$route.params.no;
    axios.get(`${BACK_URL}/boards/${boardId}`).then((response) => {
      console.log(response);
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

      // dummyData: [
      //   {
      //     title: "간단한 연어덮밥",
      //     content: "30분안에 끝나는 연어덮밥 요리 방법",
      //     image: "https://i.imgur.com/Ztp9hAN.jpg",
      //     materials: "연어 간장 와사비 밥 양파",
      //     date: "2020.07.30",
      //     comment: 3,
      //     nickname: "연어가좋아",
      //     like: 3,
      //     steps: [
      //       "연어를 자른다",
      //       "밥 위에 연어를 올린다.",
      //       "양파와 와사비로 고명을 해준다.",
      //     ],
      //   },
      //   {
      //     title: "쇠고기 미역국",
      //     content: "거의 밥도둑!",
      //     image: "https://i.imgur.com/O1cQ8N7.jpg",
      //     materials: "소고기 소금 미역 간장 참기름",
      //     date: "2020.07.30",
      //     comment: 3,
      //     nickname: "간장게장",
      //     like: 3,
      //     steps: [
      //       "물을 끓인다",
      //       "미역을 넣고 끓인다.",
      //       "소고기를 넣고 간장으로 간을 맞춰준다.",
      //       "참기름으로 감칠맛을 더한다.",
      //     ],
      //   },
      //   {
      //     title: "김치볶음밥",
      //     content: "백종원 선생님의 황금레시피",
      //     image: "https://i.imgur.com/7pNI9BA.jpg",
      //     materials: "김치 간장 스팸 베이컨 밥 계란",
      //     date: "2020.07.30",
      //     comment: 3,
      //     nickname: "골목식당",
      //     like: 3,
      //     steps: [
      //       "김치를 식용유에 볶는다.",
      //       "스팸을 넣어 볶다가 밥을 넣는다.",
      //       "간장을 살짝 태워 밥에 섞는다.",
      //       "계란을 넣고 마무리한다.",
      //     ],
      //   },
      //   {
      //     title: "전주비빔밥",
      //     content: "계속 생각나는 그 맛",
      //     image: "https://i.imgur.com/oDHwKwP.jpg",
      //     materials: "계란 소고기 당근 콩나물 간장 고추장 밥 오이 호박",
      //     date: "2020.07.30",
      //     comment: 3,
      //     nickname: "콩나물국밥",
      //     like: 3,
      //     steps: ["밥을 한다", "나물을 준비한다.", "나물은 얹고 섞어준다."],
      //   },
      //   {
      //     title: "김치전",
      //     content: "비오는 날 생각나는",
      //     image: "https://i.imgur.com/2nMSgb8.jpg",
      //     date: "2020.07.30",
      //     materials: "김치 소금 간장 다진마늘 양파 밀가루 물",
      //     comment: 3,
      //     nickname: "비가오는날엔",
      //     like: 3,
      //     steps: [
      //       "밀가루와 김치를 넣고 반죽을 만든다.",
      //       "반죽을 굽는다.",
      //       "맛있게 먹는다.",
      //     ],
      //   },
      // ],
      // likenum: 5,
    };
  },
  methods: {
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
