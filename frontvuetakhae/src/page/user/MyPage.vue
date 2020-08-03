<template>
  <div class="container" style="margin-top:30px">
    <h1>수지님의 마이 페이지</h1>
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
        <p class="text-intro" style="text-align:left">안녕하세요~ 수지입니다. 요즘 요리에 취미를 갖고 있어서 좋은 꿀팁 레시피 생기면 공유할께요!!</p>
      </div>
    </div>

    <div class="middle">
      <h1>나의 냉장고</h1>
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
    <b-button class="bottom-button">작성하기</b-button>
    <b-button class="bottom-button">수정하기</b-button>
  </div>
</template>

<script>
export default {};
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
