<template>
  <header class="header">
    <div class="header_wrapper">
      <div class="header_container">
        <a href="/">
          <img
            class="header_logo"
            src="../../assets/images/header/main_logo.png"
            alt="main_logo"
          />
        </a>
        <div class="header_menu_container font_size_m drag_none">
          <p class="menu_text">공고 리스트</p>
          <p class="menu_text">커뮤니티</p>
          <p class="menu_text">편의기능</p>
        </div>
        <div class="header_sideMenu_container font_size_s drag_none">
          <a class="menu_text right_margin_20" href="/login">Login</a>
          <div class="weather_container">
            <a class="menu_text weather_icon"></a>
            <a class="menu_text weather_temp"></a>
          </div>
        </div>
        <!-- <th:block th:if="${session.userId != null}">
          <div class="header_sideMenu_container font_size_s drag_none">
            <a class="menu_text right_margin_20" href="/mypage">My Page</a>
            <a class="menu_text right_margin_20" href="/logout">Log Out</a>
            <div class="weather_container">
              <a class="menu_text weather_icon"></a>
              <a class="menu_text weather_temp"></a>
            </div>
          </div>
        </th:block> -->
      </div>
      <div class="drop_down_container">
        <div class="drop_down_wrapper">
          <div class="drop_down_menu_container">
            <a href="/animallist" class="drop_down_menu_text display_none"
              >유기동물 리스트</a
            >
          </div>
          <div class="drop_down_menu_container">
            <a class="drop_down_menu_text display_none" href="/board/list"
              >공지 게시판</a
            >
            <a class="drop_down_menu_text display_none" href="/board/list"
              >나눔 게시판</a
            >
            <a class="drop_down_menu_text display_none" href="/board/list"
              >자유 게시판</a
            >
          </div>
          <div class="drop_down_menu_container">
            <a href="/hospinfo" class="drop_down_menu_text display_none"
              >동물병원 위치</a
            >
            <a href="/trailInfo" class="drop_down_menu_text display_none"
              >산책로</a
            >
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import useHeader from "./js/header";
import { onMounted } from "vue";
onMounted(() => {
  useHeader();
});
</script>

<style scoped>
@import "./header.css";
</style>
