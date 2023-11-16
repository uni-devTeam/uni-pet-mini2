<template>
  <div class="black_bg" th:if="${message}"></div>
  <div class="alert" th:if="${message}">
    <p th:text="${message}"></p>
    <button class="alert_btn">확인</button>
  </div>
</template>

<script setup>
import useModal from "../../assets/js/modal";
import { onMounted } from "vue";
onMounted(() => {
  useModal();
});
</script>

<style scoped>
@import "../../assets/css/modal.css";
</style>
