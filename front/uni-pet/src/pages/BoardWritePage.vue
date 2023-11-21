<template>
  <div>
    <TopBackground
        :imageURL="boardBackgroundURL"
        :titleText="boardTitleText"
    ></TopBackground>
    <section class="main_container">
      <div class="main_wrap flex-column w-75">
        <form @submit.prevent="submitForm">
          <input type="hidden" v-model="board.id">
          <p>😍{{ userId }}</p><br>
          <input class="title" type="text" placeholder="제목을 입력해 주세요." v-model="board.title" style="width: 30%" required>
          <div class="form-floating">
            <div ref="editor"></div>
            <br>
            <button type="submit" class="btn btn-dark btn-lg">글작성</button>
          </div>
        </form>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue';
import axios from 'axios';
import TopBackground from "@/components/common/TopBackground.vue";
import boardBackground from "../assets/images/topBackground/Board_bg.jpg";
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

const boardBackgroundURL = boardBackground;
const boardTitleText = "Board";

let board = ref({
  boardNo: '',
  userId: '',
  boardId: '',
  title: '',
  content: '',
  postingDate: '',
  views: '',
  likeCounting: '',
  imgPath: ''
});
let userId = ref('');  // 사용자 ID를 저장할 변수.
let editorInstance = null;
let editor = ref(null);

onMounted(async () => {
  editorInstance = await ClassicEditor.create(editor.value);
  editorInstance.model.document.on('change:data', () => {
    board.value.content = editorInstance.getData();
  });


});

onBeforeUnmount(() => {
  if (editorInstance) {
    editorInstance.destroy();
    editorInstance = null;
  }
});

const submitForm = async () => {
  try {
    const response = await axios.post('http://localhost:8889/board/save', board.value);
    console.log(response);
    // 게시글 작성 후, 필요한 작업 수행
    // 예: 작성한 게시글 페이지로 이동, 성공 메시지 표시 등
  } catch (error) {
    console.error(error);
    // 에러 처리
    // 예: 에러 메시지 표시 등
  }
};
</script>

<style scoped>
@import '@/assets/css/posting.css';
</style>
