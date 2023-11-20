<script setup>
import {onMounted, ref} from "vue";
import {api} from "@/api/common";

const currentPage = ref(0);
const shareList = ref([]);
const pageSize = 6;
const pageNumber = ref(0);
const totalPages = ref(0);
const first = ref(null);
const last = ref(null);

async function fetchWritings(boarId, currentPage) {
  const boardId = parseInt(boarId);
  const response = await api(
      'http://localhost:8889/mypage/mywritings?boardId=' + boardId
      + '&page=' + currentPage + "&size=" + pageSize, 'GET');
  shareList.value = shareList.value = [...shareList.value, ...response.list.content];
  pageNumber.value = response.list.pageable.pageNumber;
  totalPages.value = response.list.totalPages;
  first.value = response.list.first;
  last.value = response.list.last;
  console.log(response)
}

onMounted(async () => {
  await fetchWritings(1, currentPage.value)
});

const loadMoreItems = async () => {
  currentPage.value++;
  await fetchWritings(1, currentPage.value)
}

</script>

<template>
    <div class="con_info">
      <div class="con_info_wrapper">
        <div class="con_title">나눔 물품</div>
        <div class="cardbox" v-if="shareList">
          <div v-for="item in shareList" :key="item.boardNo" class="sharecard">
            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img :src="item.imgPath ? '/src/assets/images/mypage/default-image.png' : '/src/assets/images/mypage/default-image.png'" class="img-fluid rounded-start" id="pic" alt="...">
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <a id="card_atag" href="#">
                      <h5 class="card-title">{{ item.title }}</h5>
                      <p class="card-text">{{ item.content }}</p>
                      <p class="card-text">{{ item.postingDate }}<small
                          class="text-muted"></small></p>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
          <div v-if="!shareList">나눔한 항목이 존재하지 않습니다.</div>
        <div>
          <button id="load-more" v-if="!last" @click="loadMoreItems()">더 보기</button>
        </div>
      </div>
    </div>
</template>

<style scoped>
@import "./css/myShare.css";
@import "./css/myPageLayout.css";
</style>