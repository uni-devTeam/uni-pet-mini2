<script setup>
import {api} from "@/api/common";
import {onMounted, ref} from "vue";

const petPic = ref('');
const name = ref('');

  async function fetchUser() {
    const response = await api(`http://localhost:8889/mypage/myprofile`, 'GET');
    petPic.value = response.user.petPic;
    name.value = response.user.name;
  }

  onMounted(() => {
    fetchUser();
  });
</script>

<template>
  <div class="nav_wrapper">
    <div class="side_nav_container">
      <div class="container_user_name">
        <div class="circle_petpic" :style="{ 'background-image': petPic ? 'url(' + 'http://localhost:8889' + petPic + ')' : 'url(/src/assets/images/mypage/default-image.png)' }"></div>
        <span id="user_name">{{ name }}</span><span>님</span>
      </div>
      <nav class="nav flex-column">
          <router-link to="/myprofile" class="nav-link nav_act">회원 정보</router-link>
          <router-link to="/mypet" class="nav-link nav_act">나의 펫</router-link>
          <router-link to="/mywriting" class="nav-link nav_act">나의 게시글</router-link>
          <router-link to="/myshare" class="nav-link nav_act">나눔 물품</router-link>
      </nav>
    </div>
  </div>
</template>

<style scoped>
@import "./css/myNav.css";
</style>