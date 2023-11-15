import { createRouter, createWebHistory } from 'vue-router'

import Mypage from '@/pages/Mypage.vue'

const myrouter = createRouter({
    history: createWebHistory(),
    routes : [
        { path: '/', component: Mypage },
        { path: '/mypage', component: Mypage },
        { path: '/myprofile', component: Mypage },
        { path: '/mypet', component: Mypage },
        { path: '/mywriting', component: Mypage },
        { path: '/myshare', component: Mypage },
        { path: '/mysharelikes', component: Mypage },
    ]
})

export default myrouter;