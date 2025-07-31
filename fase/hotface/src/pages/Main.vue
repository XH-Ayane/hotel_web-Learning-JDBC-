<template>
  <el-card class="main-card">
    <div class="welcome-section">
      <h1>欢迎使用酒店管理系统</h1>
      <p>当前系统状态：<el-tag type="success" v-if="isConnected">已连接后端</el-tag><el-tag type="danger" v-else>未连接后端</el-tag></p>
<p v-if="connectionError" class="error-message"><el-icon><WarningFilled /></el-icon> {{ connectionError }}</p>
    </div>
  </el-card>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { WarningFilled } from '@element-plus/icons-vue'
import axios from '../axios/axiosInstance'
import { ElMessage } from 'element-plus'

// 状态变量
const isConnected = ref(false)
const roomCount = ref('加载中...')
const todayCheckin = ref('加载中...')
const todayCheckout = ref('加载中...')
const availableRooms = ref('加载中...')
const connectionError = ref('')

// 页面加载时获取数据
onMounted(async () => {
  try {
    // 测试后端连接
    const response = await axios.get('/dashboard/stats')
    if (response.status === 200) {
      isConnected.value = true
      roomCount.value = response.data.roomCount
      todayCheckin.value = response.data.todayCheckin
      todayCheckout.value = response.data.todayCheckout
      availableRooms.value = response.data.availableRooms
      connectionError.value = ''
    } else {
      isConnected.value = false
      connectionError.value = `后端返回错误: ${response.code} - ${response.message}`
      ElMessage.warning('获取数据失败：' + response.message)
    }
  } catch (error) {
    isConnected.value = false
    if (error.response) {
      connectionError.value = `请求错误: ${error.response.status} - ${error.response.statusText}`
    } else if (error.request) {
      connectionError.value = '无法连接到后端服务器，请检查后端是否运行'
    } else {
      connectionError.value = `请求失败: ${error.message}`
    }
    ElMessage.error(connectionError.value)
  }
})
</script>

<style scoped>
.main-card {
  height: 100%;
  padding: 20px;
}

.welcome-section {
  margin-bottom: 30px;
  padding-bottom: 20px;
  border-bottom: 1px solid #eee;
}

.stats-section {
  margin-top: 20px;
}

.stat-card {
  text-align: center;
  padding: 15px 0;
}

.stat-title {
  font-size: 14px;
  color: #606266;
  margin-bottom: 10px;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #1890ff;
}
</style>