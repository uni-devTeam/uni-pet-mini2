import { createRouter, createWebHistory } from "vue-router";

import MainPage from "@/pages/MainPage.vue";
import Mypage from "@/pages/MyPage.vue";
import LoginPage from "@/pages/LoginPage.vue";
import SignupPage from "@/pages/SignupPage.vue";
import Profile from "@/components/mypage/Profile.vue";
import Mypet from "@/components/mypage/Mypet.vue";

const index = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", component: MainPage },
    { path: "/login", component: LoginPage },
    { path: "/signup", component: SignupPage },

      // 마이페이지 라우터
    { path: "/mypage", component: Mypage },
    { path: "/myprofile", component: Profile },
    { path: "/mypet", component: Mypet },
    { path: "/mywriting", component: Mypage },
    { path: "/myshare", component: Mypage },
    { path: "/mysharelikes", component: Mypage },
    { path: "/myprofile/:kind", name: 'Edit', component: Profile },
    { path: "/mypet/:kind", component: Mypet }
  ],
});

export default index;
