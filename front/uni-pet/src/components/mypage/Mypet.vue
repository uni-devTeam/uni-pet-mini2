<script setup>
import {onMounted, ref, watchEffect} from "vue";
import { api } from "@/api/common";
import TopBackground from "@/components/common/TopBackground.vue";
import MyNav from "@/components/mypage/MyNav.vue";
import mypageBackground from "@/assets/images/topBackground/Mypage_bg.jpg";
import Pet from "@/components/mypage/Pet.vue";
import PetChange from "@/components/mypage/PetChange.vue";
import router from "@/router";

const mypageBackgroundURL = mypageBackground;
const mypageTitleText = "Mypage";

const age = ref('');
const mypet = ref(null);
const nopet = ref('');
const isActive = ref(true);
const changeForm = ref('');
let userId = '';

async function fetchPet() {
  try {
    const response = await api('http://localhost:8889/mypet', 'GET');
    age.value = response.age;
    mypet.value = response.mypet;
    nopet.value = response.nopet;
    userId = response.userId;

    console.log(response);
  } catch (error) {
    console.error(error);
  }
}

onMounted(async () => {
  await fetchPet();
});

const handleChangeForm = (change) => {
  isActive.value = false;
  changeForm.value = change;
  if(change === 'changeInfo') {
    router.push("/mypet/change")
  } else if (change === 'add') {
    router.push("/mypet/add")
  }

}

watchEffect(() => {
  const newPath = router.currentRoute.value.path;
  if (newPath === '/mypet') {
    isActive.value = true;
  }
});
</script>

<template>
  <TopBackground
      :imageURL="mypageBackgroundURL"
      :titleText="mypageTitleText"
  ></TopBackground>
  <div class="middle">
    <div class="nav_wrapper">
      <MyNav />
    </div>
    <Pet v-if="isActive" :mypet="mypet" :age="age" :nopet="nopet" @changeForm="handleChangeForm" />
    <PetChange v-if="!isActive && changeForm === 'changeInfo'" :mypet="mypet" />
    <PetChange v-if="!isActive && changeForm === 'add'" :userId="userId" />
  </div>
</template>

<style scoped>
@import "./css/myPet.css";
@import "./css/myPageLayout.css";
@import "bootstrap/dist/css/bootstrap.min.css";
.middle{
  display: flex;
}
.nav_wrapper{
  width: 200px;
  min-height: 100%;
}
</style>