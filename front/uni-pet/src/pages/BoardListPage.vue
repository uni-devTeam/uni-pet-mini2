<template>
  <div>
    <TopBackground
        :imageURL="boardBackgroundURL"
        :titleText="boardTitleText"
    ></TopBackground>
    <section id="entire_screen">
      <div class="container_nav">
        <nav class="nav_flex_column">
          <router-link to="/board/list" class="nav-link"><span class="bnav">공지 게시판</span></router-link>
          <router-link to="/board/list" class="nav-link"><span class="bnav">나눔 게시판</span></router-link>
          <router-link to="/board/list" class="nav-link"><span class="bnav">자유 게시판</span></router-link>
        </nav>
      </div>
      <div class="main_container">
        <form id="search_btn" @submit.prevent="search">
          <input type="text" id="search_text" placeholder="검색어를 입력해주세요." v-model="searchText">
          <button type="submit" class="btn btn-light">
            <img :src="searchIcon" alt=""/> <!-- 이미지 src에 바인딩 -->
          </button>
        </form>
        <br>
        <br>
        <div class="table" v-if="boardList.length > 0">
          <table class="table">
            <thead>
            <tr class="table-dark">
              <th width="600px">제목</th>
              <th>작성자</th>
              <th>작성일</th>
              <th>조회</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="board in boardList" :key="board.id">
              <td>
                <router-link :to="`/board/content?boardNo=${board.boardNo}`">{{ board.title }}</router-link>
              </td>
              <td>{{ board.userId }}</td>
              <td>{{ board.postingDate }}</td>
              <td>{{ board.views }}</td>
            </tr>
            </tbody>
          </table>
          <button id="posting_btn1" class="btn btn-dark" @click="goToPosting">글쓰기</button>
        </div>
        <div v-if="boardList.length === 0">
          <h3>{{ msg }}</h3>
          <router-link to="/">
            <button class="btn btn-dark">메인으로 돌아가기</button>
          </router-link>
          <button id="posting_btn2" class="btn btn-dark" @click="goToPosting">글쓰기</button>
        </div>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import TopBackground from "@/components/common/TopBackground.vue";
import searchIcon from '@/assets/images/board/searchIcon.png'; //검색 이미지 아이콘
import router from "@/router";

import boardBackground from "../assets/images/topBackground/Board_bg.jpg";
const boardBackgroundURL = boardBackground;
const boardTitleText = "Board";


const boardList = ref([]);  // 게시판 리스트 데이터를 저장할 변수
const searchText = ref('');  // 검색어를 저장할 변수
const msg = ref('');  // 메시지를 저장할 변수

onMounted(async () => {
  // 컴포넌트가 마운트될 때 API 호출
  const response = await axios.get('http://localhost:8889/board/list');
  boardList.value = response.data;
});

const search = () => {
  // 검색 기능을 구현하세요.
};

const goToPosting = () => {
  // 글쓰기 페이지로 이동하는 기능을 구현하세요.
  router.push('/write');
};
</script>

<style>
@import '@/assets/css/board.css';
</style>
