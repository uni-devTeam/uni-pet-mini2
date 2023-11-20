<script setup>
import {onMounted, ref} from "vue";
import {api} from "@/api/common";
// import "bootstrap/dist/css/bootstrap.min.css";

const currentPage = ref(0);
const writingList = ref([]);
const pageSize = ref(0);
const pageNumber = ref(0);
const totalPages = ref(0);
const first = ref(null);
const last = ref(null);

async function fetchWritings(boarId, currentPage) {
  const boardId = parseInt(boarId);
  const response = await api(
      'http://localhost:8889/mypage/mywritings?boardId=' + boardId
      + '&page=' + currentPage, 'GET');
  writingList.value = response.list.content;
  pageSize.value = response.list.pageable.pageSize;
  pageNumber.value = response.list.pageable.pageNumber;
  totalPages.value = response.list.totalPages;
  first.value = response.list.first;
  last.value = response.list.last;
  console.log(response)
}

onMounted(async () => {
  await fetchWritings(2, currentPage.value)
});

const goToPage = async (selectedPage) => {
  if(currentPage.value !== selectedPage-1) {
    await fetchWritings(2, selectedPage-1)
    currentPage.value = selectedPage-1;
  }
};

const updatePage = async (offset) => {
  const targetPage = currentPage.value + offset;
  if (targetPage >= 0 && targetPage < totalPages.value) {
    await fetchWritings(2, targetPage);
    currentPage.value = targetPage;
  }
};
</script>

<template>
    <div class="con_info">
      <div class="con_info_wrapper">
        <div class="con_title">나의 게시글</div>
          <table v-if="writingList" class="table table-hover">
            <thead class="table-light">
              <tr>
                <th scope="col">번호</th>
                <th scope="col">제목</th>
                <th scope="col">날짜</th>
              </tr>
            </thead>
            <tbody class="table-group-divider">
              <tr v-for="item in writingList" :key="item.boardNo">
                <input type="hidden" value="item.boardNo">
                <td class="tb_num">{{ item.boardNo }}</td>
                <td class="tb_title">
                  <a class="title_link" href="">{{ item.title }}</a>
                  </td>
                <td>{{ item.postingDate }}</td>
              </tr>
            </tbody>
          </table>
        <!-- 페이징 -->
        <div id="pagination" v-if="writingList">
          <nav aria-label="Page navigation example">
            <ul class="pagination">
              <li class="page-item" v-if="!first"><a class="page-link" @click="updatePage(-1)">이전</a></li>
              <li class="page-item" v-for="n in totalPages" :key="n"><a class="page-link" @click="goToPage(n)">{{ n }}</a></li>
              <li class="page-item" v-if="!last"><a class="page-link" @click="updatePage(1)">다음</a></li>
            </ul>
          </nav>
        </div>
      </div>
    </div>
</template>

<style scoped>
@import "./css/myWriting.css";
@import "./css/myPageLayout.css";
</style>