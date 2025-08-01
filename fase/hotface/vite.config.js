import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  // 服务器配置
  server: {  
  // 服务器主机名，默认是 localhost
        host: "127.0.0.1",
        cors: true,
		// 端口号，默认是 5173
        port: 5173,
		 // 是否开启自动打开浏览器
        open: false, 
		 // 服务器代理配置
        proxy: {
          // 当url地址包含 /api/字符串时，将当前url请求交给代理服务器
          "^/api": {
            target: "http://127.0.0.1:8080", // 真实接口地址
            changeOrigin: true, // 允许跨域
            secure: false,
            rewrite: (path) => path.replace(/^\/api/, '')
          },
          // 代理/uploads路径的请求到后端服务器
          "^/uploads": {
            target: "http://127.0.0.1:8080",
            changeOrigin: true,
            secure: false
          }
        },
    }
  
})
