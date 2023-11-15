import { defineStore } from "pinia";
import { api } from "@/common";

export const usePetStore = defineStore('pet', {
    state: () => ({
        mypet: {},
        age: ''
    }),
    actions: {
        async fetchPet() {
            const response = await api(`http://localhost:8889/mypet`, 'GET');
            this.mypet = response.mypet;
            this.age = response.mypet.age
        }
    },
    getters: {
        getMypet() {
            return this.mypet;
        }
    }
});
