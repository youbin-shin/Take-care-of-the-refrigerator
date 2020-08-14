<template>
  <b-row>
    <b-col>
      <b-avatar variant="success" icon="people-fill"></b-avatar>
      <!-- <p>{{}}</p> -->
    </b-col>
    <b-col cols="10">
      <b-input-group>
        <b-form-input
          label="댓글을 입력해주세요."
          v-model="commentInput"
        ></b-form-input>
        <v-btn color="red lighten-2" class="white--text" @click="completeInput"
          >등록</v-btn
        >
      </b-input-group>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "Comment",
  data() {
    return {
      commentInput: "",
    };
  },
  methods: {
    completeInput() {
      if (this.commentInput.trim()) {
        this.$emit("create-comment", this.commentInput);
        this.commentInput = "";
      }
    },
  },
};
</script>

<style></style>
