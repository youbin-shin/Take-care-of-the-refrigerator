<template>
  <div class="container" style="margin-top:30px">
    <h1>{{ userData.nickname}}님의 마이 페이지</h1>

    <div class="header">
      <div class="box" style="background: #BDBDBD;">
        <img
          class="profile"
          src="https://img1.daumcdn.net/thumb/R720x0/?fname=https://t1.daumcdn.net/news/201904/19/moneytoday/20190419141606693hahz.jpg"
        />
      </div>
      <div class="introduce">
        <h3 style="text-align:left">팔로잉 18 명 / 팔로우 25 명</h3>
        <h2 style="text-align:left">자기소개</h2>
        <p
          class="text-intro"
          style="text-align:left"
        >안녕하세요~ 수지입니다. 요즘 요리에 취미를 갖고 있어서 좋은 꿀팁 레시피 생기면 공유할께요!!</p>
      </div>
    </div>

    <div class="middle">
      <h1>나의 냉장고</h1>
      <div>
        <v-row class="m-2 inputBlank" variant="danger">
          <v-text-field
            label="직접 추가하기"
            v-model="addText"
            hide-details="auto"
            v-on:keyup.enter="plusFood()"
          ></v-text-field>
          <v-icon large @click="plusFood()">mdi-plus</v-icon>
        </v-row>
        <v-chip
          class="m-1"
          v-for="tag in chips"
          close
          @click:close="closeChip(tag);"
          :key="tag"
        >{{ tag }}</v-chip>
        <div v-if="emptyChip">요리할 재료를 입력해주세요.</div>
      </div>
      <p>조기 김치 설탕 소금 간장 식용유 계란 소고기 맛술 사과 배 캐비어</p>
    </div>
    <div class="interest">
      <h1>관심 레시피</h1>
      <p>연어 킹의 연어 덮밥</p>
      <p>진주새럼의 진주비빔밥</p>
    </div>
    <div class="interest">
      <h1>내가 작성한 레시피 목록</h1>
      <p>김치가 필요없는 김치볶음밥</p>
      <p>손쉽게 만드는 폭탄주먹밥</p>
    </div>
    <div class="white--text">
      <b-button class="bottom-button mr-2" @click="moveCreatePost">레시피 작성하기</b-button>
      <b-button class="a_tag_modal bottom-button mr-2" @click="modalShow = !modalShow">수정하기</b-button>
      <b-button class="bottom-button mr-2" variant="danger">탈퇴하기</b-button>
    </div>

    <b-modal v-model="modalShow" hide-footer hide-header>
      <h1 class="text-center">{{ userData.uid }}개인 정보 수정하기</h1>
      <div>
        <div class="div_item">
          <span class="item_100px">닉네임</span>
          <input
            :value="userData.nickname"
            @input="userData.nickname = $event.target.value"
            id="email"
            type="text"
          />
        </div>
        <div class="div_item">
          <span class="item_100px">비밀번호</span>
          <input
            @input="userData.password = $event.target.value"
            placeholder="새로운 비밀번호를 입력해주세요."
            id="password"
            type="password"
          />
        </div>
      </div>
      <b-button class="mt-3" block @click="updateData">저장하기</b-button>
    </b-modal>
  </div>
</template>

<script>
import axios from "axios";

const BACK_URL = "http://i3a305.p.ssafy.io:8399/api/";

export default {
  data() {
    return {
      chips: [
        // 마이페이지에 입력한 나의 냉장고 데이터를 넣기
        "간장",
        "김치",
        "돼지고기",
        "새우",
      ],
      modalShow: false,
      addText: "",
      emptyChip: false,

      userData: {
        email: "",
        password: "",
        nickname: "",
      },
    };
  },
  created() {
    axios
      .get(`${BACK_URL}/users/info`, {
        headers: { "jwt-auth-token": this.$cookies.get("token") },
      })
      .then((response) => {
        console.log(response);
        this.userData.email = response.data.email;
        this.userData.nickname = response.data.nickname;
      });
  },
  methods: {
    updateData() {
      axios
        .put(`${BACK_URL}/users/info`, {
          headers: { "jwt-auth-token": this.$cookies.get("token") },
          params: {
            nickname: this.userData.nickname,
          },
        })
        .then((response) => {
          console.log(response);
          if (response.data == "success") {
            alert("회원정보가 수정되었습니다!");
            this.$router.push("/user/mypage");
          } else {
            alert("회원정보 수정이 실패했습니다");
          }
        });
    },
    switchModal() {
      this.modalShow = !this.modalShow;
    },
    remove(item) {
      this.chips.splice(this.chips.indexOf(item), 1);
      this.chips = [...this.chips];
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
      if (this.chips.length === 0) {
        this.emptyChip = true;
      }
    },
    moveCreatePost() {
      console.log("움직여");
      // this.$router.push("/create");
    },
  },
};
</script>

<style>
.text-intro {
  border: 1px dotted black;
  padding: 5px;
  height: 60px;
}
.interest {
  margin-top: 30px;
  text-align: left;
  padding: 10px;
}
.middle {
  text-align: left;
  margin-top: 50px;
  background-color: skyblue;
  border-radius: 1%;
  padding: 10px;
}
.header {
  margin-top: 50px;
  width: 100%;
  overflow: auto;
}

.box {
  display: inline-block;
  width: 15%;
  height: 150px;
  float: left;
  border-radius: 30%;
  overflow: hidden;
}
.introduce {
  display: inline-block;
  width: 85%;
  padding-left: 55px;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.bottom-button {
  margin-bottom: 50px;
}
</style>
