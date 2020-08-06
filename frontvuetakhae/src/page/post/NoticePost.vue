<template>
  <div class="container">
    <template>
      <h1 class="m-3">공지사항</h1>
      <v-expansion-panels focusable>
        <v-expansion-panel v-for="notice in notices" :key="notice.title">
          <v-expansion-panel-header>{{ notice.title }}</v-expansion-panel-header>
          <v-expansion-panel-content>{{ notice.date }}</v-expansion-panel-content>
          <v-expansion-panel-content>{{ notice.content }}</v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </template>
  </div>
</template>

<script>
export default {
  name: "NoticePost",
  data() {
    return {
      notices: [
        {
          title: "제목1",
          content: "내용1",
          date: "2020.08.05",
        },
        {
          title: "제목2",
          content: "내용2",
          date: "2020.08.05",
        },
        {
          title: "제목3",
          content: "내용3",
          date: "2020.08.05",
        },
        {
          title: "제목4",
          content: "내용4",
          date: "2020.08.05",
        },
        {
          title: "제목5",
          content: "내용5",
          date: "2020.08.05",
        },
      ],
    };
  },
};
</script>

<style>
</style>