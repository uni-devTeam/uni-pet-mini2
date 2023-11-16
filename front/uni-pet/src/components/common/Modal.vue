<template>
  <div v-if="errorMessage">
    <div class="black_bg"></div>
    <div class="alert">
      <p>{{ errorMessage }}</p>
      <button class="alert_btn">확인</button>
    </div>
  </div>
</template>

<script setup>
import useModal from "../../assets/js/modal";
import { onMounted, defineProps } from "vue";

onMounted(() => {
  useModal();
});

const props = defineProps(["errorMessage"]);
</script>

<style scoped>
@import "../../assets/css/modal.css";
</style>
