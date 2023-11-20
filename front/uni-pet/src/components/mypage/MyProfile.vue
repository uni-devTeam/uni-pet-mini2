<script setup>
import { api } from "@/api/common";
import {onMounted, reactive, defineEmits} from "vue";
import router from "@/router";

const state = reactive({
  petPic: '',
  name: '',
  email: ''
});

async function fetchUser() {
  const response = await api(`http://localhost:8889/mypage/myprofile`, 'GET');
  state.petPic = response.user.petPic;
  state.name = response.user.name;
  state.email = response.user.email;
}

onMounted(() => {
  fetchUser();
});

const emits = defineEmits(["editProfile", "currentEmail"]);
const goToEditProfile = (kind) => {
    router.push({ name: 'Edit', params: { kind: kind } });
    emits("editProfile", kind);
    emits("currentEmail", state.email);
};

// 로그아웃
const logout = async () => {
  await api('http://localhost:8889/logout', 'POST');
  console.log("로그아웃")
}

const deleteAccount = async () => {
  if(confirm("정말 탈퇴하시겠습니까?")){
    const response = await api('http://localhost:8889/mypage/delaccount', 'POST');
    alert(response)
    window.location.href = 'http://localhost:5173/';
  }

}
</script>

<template>
    <div class="con_info_wrapper">
      <div class="con_title">회원 정보</div>
      <div class="infobox">
        <p>이름 </p><span class="con_name">{{ state.name }}</span>
      </div>
      <div class="infobox">
        <p>이메일 </p><a class="con_link" @click="goToEditProfile('email')">{{ state.email }} &gt;</a>
      </div>
      <div class="infobox">
        <p>비밀번호 변경</p><a class="con_link" @click="goToEditProfile('pw')">&gt;</a>
      </div>
      <div class="con_bottom">
        <a class="con_link_bottom" @click="logout()">로그아웃</a> | <a class="con_link_bottom" @click="deleteAccount()">회원탈퇴</a>
      </div>
    </div>
</template>

<style scoped>
@import "./css/myProfile.css";
@import "./css/myPageLayout.css";
</style>