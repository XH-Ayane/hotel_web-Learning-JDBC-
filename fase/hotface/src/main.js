import { createApp } from 'vue'
import App from './App.vue'

// 导入Element Plus及样式
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

// 导入路由
import router from './router/index'

// 导入axios实例
import axios from './axios/axiosInstance'

// 导入Element Plus图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)

// 注册Element Plus
app.use(ElementPlus)

// 注册路由
app.use(router)

// 全局注册图标
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

// 全局配置axios
app.config.globalProperties.$axios = axios

app.mount('#app')
