import Vue from "vue";
import Router from "vue-router";

import constants from "../lib/constants";

// 유저
import MyPage from "../page/user/MyPage.vue";
import OtherPage from "../page/user/OtherPage.vue";
// 포스트
import List from "../page/post/List.vue";
import CreatePost from "../page/post/CreatePost.vue";
import DetailPost from "../page/post/DetailPost.vue";
import SearchPost from "../page/post/SearchPost.vue";
import NoticePost from "../page/post/NoticePost.vue";
import ApiDetailPost from "../page/post/ApiDetailPost.vue";
// 에디터
// import VMdEditor from "@kangc/v-md-editor";
// import "@kangc/v-md-editor/lib/style/base-editor.css";
// import githubTheme from "@kangc/v-md-editor/lib/theme/github.js";
// VMdEditor.use(githubTheme);

// Vue.use(VMdEditor);

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/user/mypage",
      name: "mypage",
      component: MyPage,
    },
    {
      path: "/users/otherpage/:nickname",
      name: "otherpage",
      component: OtherPage,
    },
    // 포스트
    {
      path: "/",
      name: constants.URL_TYPE.POST.MAIN,
      component: List,
    },
    {
      path: "/create",
      name: constants.URL_TYPE.POST.CREATEPOST,
      component: CreatePost,
    },
    {
      path: "/detail/:no",
      name: constants.URL_TYPE.POST.DETAILPOST,
      component: DetailPost,
    },
    {
      path: "/foodsafe/detail/:no",
      name: ApiDetailPost,
      component: ApiDetailPost,
    },
    {
      path: "/search",
      name: constants.URL_TYPE.POST.SEARCHPOST,
      component: SearchPost,
    },
    {
      path: "/notice",
      name: constants.URL_TYPE.POST.NOTICEPOST,
      component: NoticePost,
    },
  ],
});
