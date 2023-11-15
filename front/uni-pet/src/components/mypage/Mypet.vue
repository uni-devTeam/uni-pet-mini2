<script setup>
import { usePetStore } from "@/stores/mypage/mypetstore";
import {onMounted} from "vue";

const petStore = usePetStore()

onMounted(async () => {
  await petStore.fetchPet();
  console.log(petStore);
})

</script>

<template>
  <div class="middle">
    <div class="con_info">
      <div class="con_info_wrapper">
        <div class="con_title">나의 펫</div>
        <div class="menu">
          <div v-if="petStore">
            <img class="circle_pet" :src="petStore.petPic ? petStore.petPic : '/src/assets/images/mypage/default-image.png'" alt="Pet Image"/>
            <div class="pet_details">
              <div class="infobox">
                <span>이름 </span><span class="con_text">{{ petStore.petName }}</span>
              </div>
              <div class="infobox">
                <span>생일 </span><span class="con_text">{{ petStore.petBirth }}</span>
              </div>
              <div class="infobox">
                <span>성별 </span><span class="con_text">{{ petStore.petGender === 'm' ? '남아' : '여아' }}</span>
              </div>
              <div class="infobox">
                <span>종류 </span><span class="con_text">{{ petStore.petKind }}</span>
              </div>
              <div class="infobox">
                <span>중성화 여부 </span><span class="con_text">{{ petStore.petNeuter === 'y' ? '유' : '무' }}</span>
              </div>
              <div class="infobox">
                <span>색상 </span><span class="con_text">{{ petStore.petColor }}</span>
              </div>
              <div class="infobox">
                <span>체중 </span><span class="con_text">{{ petStore.petWeight ? petStore.petWeight + 'kg' : '0 kg' }}</span>
              </div>
              <div class="mypet_text_container">
                <span class="memo_title_text">메모 </span>
                <span class="con_text">{{ petStore.petTrait }}</span>
              </div>
            </div>
            <button class="change_btn" @click="">수정</button>
          </div>
          <div v-else-if="!petStore">
            <div class="none_pet_container">
              <p>{{  }}</p>
              <button class="change_btn" @click="">등록하러 가기</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>