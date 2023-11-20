<template>
  <Modal></Modal>
  <TopBackground
    :imageURL="loginBackgroundURL"
    :titleText="loginTitleText"
  ></TopBackground>
  <section class="sign_main_container">
    <h1>로그인</h1>
    <form method="post" action="/login">
      <div class="info_container">
        <label class="input_lable_text" for="id">아이디</label>
        <input class="inputTag" id="id" type="text" name="id" />
        <label class="input_lable_text" for="password">비밀번호</label>
        <input class="inputTag" id="password" type="password" name="password" />
        <div class="go_signup_text_container margin_bottom_50">
          <a href="/signup">회원가입</a>
        </div>
        <button class="sign_btn">로그인</button>
      </div>
    </form>
  </section>
</template>

<script setup>
import Modal from "../components/common/Modal.vue";
import TopBackground from "../components/common/TopBackground.vue";
import loginBackground from "../assets/images/topBackground/Login_bg.jpg";
import { onMounted } from "vue";

const loginBackgroundURL = loginBackground;
const loginTitleText = "Login";

onMounted(() => {
  window.scrollTo(0, 0);
});
</script>

<style scoped>
@import "../assets/css/sign.css";
</style>
