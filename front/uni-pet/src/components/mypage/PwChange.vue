<script setup>

import {ref} from "vue";
import {api} from "@/api/common";
import router from "@/router";

const pw = ref('')
const checkPw = ref('')

async function submit() {
  const params = new URLSearchParams();
  params.append('changedPass', pw.value);
  params.append('changedPassCheck', checkPw.value);

  const url = 'http://localhost:8889/mypage/passchange?' + params.toString();
  console.log(url)
  try {
    const response = await api(url, 'POST');
    alert(response)
    await router.push('/myprofile');
  } catch (e) {
    console.log(e)
    alert("비밀번호가 일치하지 않습니다.")
  }
}
</script>

<template>
  <div class="con_info_wrapper">
    <div class="con_title">비밀번호 변경</div>
    <form method="post" @submit.prevent="submit" id="confirm_change">
      <div class="infobox">
        <p class="after_change">변경 비밀번호 </p>
        <input v-model="pw" type="password" class="customInput" name="changedPass">
      </div>
      <div class="infobox" id="doubleCheckPass">
        <p class="after_change">변경 비밀번호 확인 </p>
        <input v-model="checkPw" type="password" class="customInput" name="changedPassCheck">
      </div>
    </form>
    <button type="submit" form="confirm_change" id="e_btn">확인</button>
  </div>
</template>

<style scoped>
@import "./css/myEmail.css";
@import "./css/myPageLayout.css";
</style>