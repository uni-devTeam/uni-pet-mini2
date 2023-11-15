<script setup>
import { useUserStore } from '@/stores/mypage/myuserstore';

const userStore = useUserStore();

</script>

<template>
  <div class="middle">
    <div class="con_info">
      <div class="con_info_wrapper">
        <div class="con_title">회원 정보</div>
        <div class="infobox">
          <p>이름 </p><span class="con_name">{{ userStore.name }}</span>
        </div>
        <div class="infobox">
          <p>이메일 </p><a class="con_link">&gt; {{userStore.email}}</a>
        </div>
        <div class="infobox">
          <p>비밀번호 변경</p><a class="con_link">&gt;</a>
        </div>
        <div class="con_bottom"><a class="con_link_bottom">로그아웃</a> | <a
            class="con_link_bottom">회원탈퇴</a></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "./css/myProfile.css";
@import "./css/myPageLayout.css";
</style>