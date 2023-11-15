import { defineStore } from "pinia";
import { api } from "@/api/common";

export const usePetStore = defineStore('pet', {
    state: () => ({
        petId: 0,
        userId: '',
        petName: '',
        petBirth: null,
        petGender: '',
        petKind: '',
        petNeuter: '',
        petPic: '',
        petColor: '',
        petWeight: 0.0,
        petTrait: '',
        attachFile: null,
        age: ''
    }),
    actions: {
        async fetchPet() {
            const response = await api(`http://localhost:8889/mypet`, 'GET');
            this.petId = response.mypet.petId;
            this.userId = response.mypet.userId;
            this.petName = response.mypet.petName;
            this.petBirth = response.mypet.petBirth;
            this.petGender = response.mypet.petGender;
            this.petKind = response.mypet.petKind;
            this.petNeuter = response.mypet.petNeuter;
            this.petPic = response.mypet.petPic;
            this.petColor = response.mypet.petColor;
            this.petWeight = response.mypet.petWeight;
            this.petTrait = response.mypet.petTrait;
            this.attachFile = response.mypet.attachFile;
            this.age = response.age
            // console.log(response.mypet)
        }
    }
});
