<script setup>
import {ref, watchEffect} from 'vue';
import MyNav from "@/components/mypage/MyNav.vue";
import Profile from "@/components/mypage/Profile.vue";
import Mypet from "@/components/mypage/Mypet.vue";
import {useRouter} from "vue-router";
import Mywriting from "@/components/mypage/Mywriting.vue";
import Myshare from "@/components/mypage/Myshare.vue";
import Mysharelikes from "@/components/mypage/Mysharelikes.vue";
import Main from "@/components/mypage/Main.vue";
import TopBackground from "../components/common/TopBackground.vue";
import mypageBackground from "../assets/images/topBackground/Mypage_bg.jpg"
// 부트스트랩
import "bootstrap/dist/css/bootstrap.min.css";

const mypageBackgroundURL = mypageBackground;
const mypageTitleText = "Mypage";

const router = useRouter();
const path = ref(router.currentRoute.value.path);

watchEffect(() => {
  path.value = router.currentRoute.value.path;
  console.log(path.value);
});

</script>

<template>
  <TopBackground
      :imageURL="mypageBackgroundURL"
      :titleText="mypageTitleText"
  ></TopBackground>
  <Main v-if="path === '/mypage'" />
  <div class="middle">
    <div class="nav_wrapper">
      <MyNav v-if="path !== '/mypage'" />
    </div>
      <Profile v-if="path === '/myprofile'" />
      <Mypet v-if="path === '/mypet'" />
      <Mywriting v-if="path === '/mywriting'" />
      <Myshare v-if="path === '/myshare'" />
      <Mysharelikes v-if="path === '/mysharelikes'" />
  </div>
</template>

<style scoped>
@import "../../src/components/mypage/css/myPageLayout.css";
.middle{
  display: flex;
}
.nav_wrapper{
  width: 200px;
  min-height: 100%;
}
</style>
