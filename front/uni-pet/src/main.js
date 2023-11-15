import './assets/main.css'

import { createApp } from 'vue'
import "bootstrap/dist/css/bootstrap.min.css"
import App from './App.vue'
import myrouter from './router/mypage/myrouter'
import { createPinia } from 'pinia'

createApp(App).use(myrouter).use(createPinia()).mount('#app')
