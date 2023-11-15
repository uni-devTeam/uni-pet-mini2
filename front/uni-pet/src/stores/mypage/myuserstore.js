import {defineStore} from "pinia";
import {api} from '@/api/common'

export const useUserStore = defineStore('user', {
    state: () => ({
        petPic: '',
        name: '',
        email: ''
    }),
    actions: {
        async fetchUser() {
            const response = await api(`http://localhost:8889/mypage/myprofile`, 'GET');
            this.petPic = response.user.petPic;
            this.name = response.user.name;
            this.email = response.user.email;
        }
    }
});
