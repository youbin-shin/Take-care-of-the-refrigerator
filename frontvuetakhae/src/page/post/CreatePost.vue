<template>
  <div
    class="container"
    style="margin-top: 30px;
"
  >
    <b-form-group label-cols="4" label-cols-lg="2" label-size="lg" label="제목" label-for="input-lg" autofocus>
      <b-form-input v-model="postData.title" id="input-lg" size="lg"></b-form-input>
    </b-form-group>
    <hr />
    <h4>재료 입력</h4>
    <p>드래그앤드롭으로 쉽게 요리에 필요한 재료를 입력하세요.</p>
    <b-container class="bv-example-row">
      <b-row>
        <b-col class="bg-my-gredient">
          <p class="text-white">요리에 필요한 재료</p>
          <div>
            <draggable tag="span" v-model="postData.content.ingredients" v-bind="dragOptions" @start="drag = true" @end="drag = false">
              <transition-group name="no" class="list-group" tag="ul">
                <li class="list-group-item" v-for="tag in postData.content.ingredients" :key="tag">
                  <i aria-hidden="true"></i>
                  {{ tag }}
                </li>
              </transition-group>
            </draggable>
          </div>
        </b-col>
        <b-col class="bg-my-plus">
          직접 추가하기
          <b-form-input type="text" v-model="addText" />
          <button class="btn btn-info" @click="plusFood">추가</button>
        </b-col>
        <b-col class="bg-my-box">
          나의 냉장고
          <div>
            <draggable class="list-group" tag="ul" v-model="list" v-bind="dragOptions" @start="drag = true" @end="drag = false">
              <transition-group type="transition" :name="'flip-list'">
                <li class="list-group-item" v-for="tag in list" :key="tag">
                  <i aria-hidden="true"></i>
                  {{ tag }}
                </li>
              </transition-group>
            </draggable>
          </div>
        </b-col>
      </b-row>
    </b-container>
    <hr />
    <h4>요리 과정 입력하기</h4>
    <b-container class="bv-example-row">
      <b-row>
        <b-col class="bg-my-step">
          과정 순서
          <draggable class="list-group" tag="ul" v-model="postData.content.steps" v-bind="dragOptions" @start="drag = true" @end="drag = false">
            <transition-group type="transition" :name="'flip-list'">
              <li class="list-group-item" v-for="tag in postData.content.steps" :key="tag.description">
                <i aria-hidden="true"></i>
                {{ tag.description }}
                <button class="btn btn-info" style="background-color:red" @click="deleleStep(tag.description)">
                  삭제
                </button>
              </li>
            </transition-group>
          </draggable>
        </b-col>
        <b-col class="bg-plus-step">
          과정 입력
          <b-form-input type="text" placeholder="요리 과정을 입력해주세요." v-model="postData.content.process" />
          <button class="btn btn-info" @click="plusStep">과정추가</button>
        </b-col>
      </b-row>
    </b-container>

    <hr />
    <p class="titles">난이도</p>
    <b-form-input type="text" placeholder="숫자가 클수록 난이도가 높습니다." v-model="postData.difficulty" />
    <br />

    <p class="titles">소요 시간</p>

    <b-form-input type="text" v-model="postData.time" />
    <br />
    <p class="titles">후기</p>

    <b-form-input type="text" v-model="postData.review" />

    <br />
    <div class="titles">썸네일 사진 넣기</div>
    <br />
    <br />
    <input type="file" name="file" id="imageFileOpenInput" accept="image/*" style="float:left" />
    <br />

    <div>
      <button class="btn btn-primary" @click="createPost">작성 완료</button>
    </div>
  </div>
</template>
<script>
// import axios from "axios";
import draggable from "vuedraggable";
import axios from "axios";

// const message = ["소금", "밥", "간장", "돼지고기", "오이", "김치", "감자", "설탕"];

export default {
  name: "CreatePost",
  components: {
    draggable,
  },
  data() {
    return {
      postData: {
        title: null,
        content: {
          ingredients: [],
          steps: [],
          process: null,
        },
        difficulty: null,
        time: null,
        review: null,
        thumbnail: null,
      },
      // list : 나의 냉장고의 데이터를 저장할 변수
      list: ["소금", "밥", "간장", "돼지고기", "오이", "김치", "감자", "설탕"],

      isDragging: false,
      delayedDragging: false,
      addText: "",
    };
  },
  created() {
    // if (!this.$cookies.isKey("auth-token")) {
    //   return this.$router.push("users/signin");
    // }
  },
  methods: {
    deleleStep(title) {
      const idx = this.postData.content.steps.findIndex(function(item) {
        return item.description === title;
      });
      this.postData.content.steps.splice(idx, 1);
    },
    plusFood() {
      if (this.addText === "") {
        return;
      }
      this.postData.content.ingredients.push(this.addText);
      this.addText = "";
    },
    plusStep() {
      if (this.postData.content.process === "") {
        return;
      }
      this.postData.content.steps.push({
        description: this.postData.content.process,
        image: "",
        stepNumber: 1,
      });
      this.postData.content.process = "";
    },
    createPost() {
      console.log(this.postData);

      const requestHeader = {
        headers: {
          "jwt-auth-token": this.$cookies.get("token"),
        },
      };
      var ingreString = this.postData.content.ingredients.join(" ");
      console.log(ingreString);
      axios
        .post(
          "http://i3a305.p.ssafy.io:8399/api/boards/create/",
          {
            content: this.postData.review,
            cookingTime: this.postData.time,
            grade: this.postData.difficulty,
            ingredient: ingreString,
            steps: this.postData.content.steps,
            thumbnailImage: "no image",
            title: this.postData.title,
          },
          requestHeader
        )
        .then((response) => {
          console.log(response);
          alert("게시글이 성공적으로 작성됐습니다!");
          this.$router.push("/");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  computed: {
    dragOptions() {
      return {
        animation: 0,
        group: "description",
      };
    },
  },
  watch: {
    isDragging(newValue) {
      if (newValue) {
        this.delayedDragging = true;
        return;
      }
      this.$nextTick(() => {
        this.delayedDragging = false;
      });
    },
  },
};
</script>

<style>
@import url(https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css);

@font-face {
  font-family: naBrush;
  src: url("NanumBarunpenB.ttf");
}
* {
  font-family: "NanumSquare", sans-serif;
}
.titles {
  float: left;
}
.flip-list-move {
  transition: transform 0.5s;
}

.list-group {
  min-height: 20px;
  width: 100%;
}

.list-group-item {
  cursor: move;
}

.list-group-item i {
  cursor: pointer;
}
.bg-my-gredient {
  background-color: burlywood;
  padding: 20px;
  border-radius: 5%;
}
.bg-my-box {
  background-color: skyblue;
  padding: 20px;
  border-radius: 5%;
}
.bg-my-step {
  background-color: khaki;
  padding: 20px;
  border-radius: 2%;
}
.bg-plus-step {
  background-color: lightsteelblue;
  padding: 20px;
  border-radius: 2%;
}
</style>
