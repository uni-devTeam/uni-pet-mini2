<script setup>
import { usePetStore } from "@/stores/mypage/mypetstore";
import {onMounted} from "vue";

const petStore = usePetStore()

onMounted(() => {
  petStore.fetchPet();
  console.log(petStore.getMypet())
  // console.log(mypet.age)
})

</script>

<template>
  <div class="middle">
    <div class="con_info">
      <div class="con_info_wrapper">
        <div class="con_title">나의 펫</div>
        <div class="menu">
          <div v-if="mypet">
            <img class="circle_pet" alt="Pet Image"/> <!-- '/img/mypage/default-image.png'" -->
            <div class="pet_details">
              <div class="infobox">
                <span>이름 </span><span class="con_text">{{  }}</span>
              </div>
              <div class="infobox">
                <span>생일 </span><span class="con_text">{{}}</span>
              </div>
              <div class="infobox">
                <span>성별 </span><span class="con_text">{{   }}</span> <!-- 'm' ? '남아' : '여아' -->
              </div>
              <div class="infobox">
                <span>종류 </span><span class="con_text">{{  }}</span>
              </div>
              <div class="infobox">
                <span>중성화 여부 </span><span class="con_text">{{  }}</span> <!-- 'y' ? '유' : '무' -->
              </div>
              <div class="infobox">
                <span>색상 </span><span class="con_text">{{  }}</span>
              </div>
              <div class="infobox">
                <span>체중 </span><span class="con_text">{{  }}</span> <!--  `${mypet.pet_weight} kg` : '0 kg' -->
              </div>
              <div class="mypet_text_container">
                <span class="memo_title_text">메모 </span>
                <span class="con_text">{{  }}</span>
              </div>
            </div>
            <button class="change_btn" @click="">수정</button>
          </div>
          <div v-else-if="nopet">
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