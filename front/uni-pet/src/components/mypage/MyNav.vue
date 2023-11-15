<script setup>
import {onMounted} from "vue";
import { useUserStore } from '@/stores/mypage/myuserstore';

const userStore = useUserStore();

onMounted(() => {
  userStore.fetchUser();
});
</script>

<template>

  <div class="middle">
    <div class="side_nav_container">
      <div class="container_user_name">
        <div class="circle_petpic" :style="{ 'background-image': userStore.petPic ? 'url(' + userStore.petPic + ')' : 'url(/src/assets/images/mypage/default-image.png)' }"></div>
        <span id="user_name">{{ userStore.name }}</span><span>님</span>
      </div>
      <nav class="nav flex-column">
          <router-link to="/myprofile" class="nav-link nav_act">회원 정보</router-link>
          <router-link to="/mypet" class="nav-link nav_act">나의 펫</router-link>
          <router-link to="/mywriting" class="nav-link nav_act">나의 게시글</router-link>
          <router-link to="/myshare" class="nav-link nav_act">나눔 물품</router-link>
          <router-link to="/mysharelikes" class="nav-link nav_act">나눔 좋아요</router-link>
      </nav>
    </div>
  </div>

</template>

<style scoped>
@import "./css/myNav.css";
</style>