import { createRouter, createWebHashHistory } from 'vue-router'
import Login from '../pages/Login.vue'
import Home from '../pages/Home.vue'
import Main from '../pages/Main.vue'


// 定义路由规则
const routes = [
  { path: '/', redirect: '/Login' },
  { path: '/Login', component: Login, name: 'Login' },
  { 
    path: '/Home', 
    component: Home, 
    name: 'Home',
    children: [
      { path: '', redirect: { name: 'Main' } },
      { path: 'Main', component: Main, name: 'Main' },
      { path: 'rooms', component: () => import('../pages/Rooms.vue'), name: 'Rooms' },
      { path: 'members', component: () => import('../pages/Members.vue'), name: 'Members' },
      { path: 'orders', component: () => import('../pages/Orders.vue'), name: 'Orders' },
      { path: 'HotelStaff', component: () => import('../pages/HotelStaff.vue'), name: 'HotelStaff' },
    ]
  }
]

// 创建路由实例
const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router