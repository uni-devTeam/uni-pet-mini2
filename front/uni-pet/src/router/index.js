import { createRouter, createWebHistory } from 'vue-router'

import MainPage from '@/pages/MainPage.vue'
import Mypage from '@/pages/MyPage.vue'

const index = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/', component: MainPage },
        { path: '/mypage', component: Mypage },
        { path: '/myprofile', component: Mypage },
        { path: '/mypet', component: Mypage },
        { path: '/mywriting', component: Mypage },
        { path: '/myshare', component: Mypage },
        { path: '/mysharelikes', component: Mypage },
    ]
})

export default index;