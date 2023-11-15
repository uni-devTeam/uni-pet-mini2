<script setup>
import {ref, reactive, watchEffect} from 'vue';
import MyNav from "@/components/mypage/MyNav.vue";
import Profile from "@/components/mypage/Profile.vue";
import Mypet from "@/components/mypage/Mypet.vue";
import {useRouter} from "vue-router";
import Mywriting from "@/components/mypage/Mywriting.vue";
import Myshare from "@/components/mypage/Myshare.vue";
import Mysharelikes from "@/components/mypage/Mysharelikes.vue";
import Main from "@/components/mypage/Main.vue";

const router = useRouter();
const path = ref(router.currentRoute.value.path);

watchEffect(() => {
  path.value = router.currentRoute.value.path;
  console.log(path.value);
});

</script>

<template>
  <Main v-if="path === '/'"></Main>
  <MyNav v-if="path !== '/'" />
  <Profile v-if="path === '/myprofile'" />
  <Mypet v-if="path === '/mypet'" />
  <Mywriting v-if="path === '/mywriting'" />
  <Myshare v-if="path === '/myshare'" />
  <Mysharelikes v-if="path === '/mysharelikes'" />
</template>

<style scoped>

</style>


