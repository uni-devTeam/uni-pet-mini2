<template>
  <div>
    <select v-model="selectedFilter">
      <option value="all">모두 보기</option>
      <option value="개">강아지</option>
      <option value="고양이">고양이</option>
      <option value="others">기타</option>
    </select>

  <div id="card_container" class="card-deck">
    <div v-for="animal in filteredAnimals" :key="animal.imageId" class="card" style="width: 18rem;">
      <a :href="`/animals/${animal.imageId}`">
        <img :src="animal.popfile" class="card-img-top" alt="Animal Image">
      </a>
      <div class="card-body">
          <h5 class="card-title">{{ animal.noticeNo }}</h5>
          <p class="card-text">{{ animal.kindCd }}</p>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">{{ animal.processState }}</li>
      <li class="list-group-item">{{ animal.careNm }}</li>
      <li class="list-group-item">{{ animal.happenPlace }}</li>
      <li class="list-group-item">{{ animal.specialMark }}</li>
      </ul>
    </div>
  </div>
</div>
</template>
<script>
import axios from 'axios';
export default {
  data() {
    return {
      animals: [],
      selectedFilter: 'all',

    };
  },
  mounted() {
    axios.get('http://localhost:8889/animals/list')
        .then(response => {
          this.animals = response.data;
        })
        .catch(error => console.error('Error fetching the animal list:', error));
  },
  computed: {
    filteredAnimals() {
      if (this.selectedFilter === 'all') {
        return this.animals;
      } else if (this.selectedFilter === 'others') {
        return this.animals.filter(animal => !animal.kindCd.includes('개') && !animal.kindCd.includes('고양이'));
      } else {
        return this.animals.filter(animal => animal.kindCd.includes(this.selectedFilter));
      }
    }
  }
};
</script>
<style>
@import "./css/animalList.css";
</style>
