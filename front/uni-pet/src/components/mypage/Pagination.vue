<script setup lang="ts">
import { ref } from "vue";
import {VueAwesomePaginate} from "vue-awesome-paginate";

const props = defineProps(['totalItems', 'currentPage']);
const emit = defineEmits(["page-click"]);

const onClickHandler = (page: number) => {
  emit("page-click", page);
};

const currentPage = ref(props.currentPage);
</script>

<template>
  <vue-awesome-paginate
      :total-items='props.totalItems'
      :items-per-page="10"
      :max-pages-shown="5"
      v-model="currentPage"
      :on-click="onClickHandler"
  />
</template>

<style>
.pagination-container {
  position: absolute;
  top: 600px;
  right: 300px;
  display: flex;
  column-gap: 10px;

}
.paginate-buttons {
  margin-top: 30px;
  height: 40px;
  width: 40px;
  border-radius: 15px;
  cursor: pointer;
  background-color: rgb(242, 242, 242);
  border: 1px solid rgb(217, 217, 217);
  color: black;
}
.paginate-buttons:hover {
  background-color: #d8d8d8;
}
.active-page {
  background-color: #ccc;
  border: 1px solid lightgrey;
  color: white;
}
.active-page:hover {
  background-color: #ccc;
}
</style>