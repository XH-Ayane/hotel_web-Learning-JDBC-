<template>
  <el-container class="home-container">
    <el-aside width="200px" class="home-aside">
      <el-menu default-active="/Home/Main" class="el-menu-vertical-demo" router>
        <el-menu-item index="/Home/Main">
          <el-icon><HomeIcon /></el-icon>
          <span>首页</span>
        </el-menu-item>
        <el-menu-item index="/Home/HotelStaff">
          <el-icon><GoodsIcon /></el-icon>
          <span>人员管理</span>
        </el-menu-item>
        <el-menu-item index="/Home/rooms">
          <el-icon><UserIcon /></el-icon>
          <span>客房管理</span>
        </el-menu-item>
        <el-menu-item index="/Home/members">
          <el-icon><UserIcon /></el-icon>
          <span>会员管理</span>
        </el-menu-item>
        <el-menu-item index="/Home/orders">
            <el-icon><ShoppingCartIcon /></el-icon>
            <span>订单管理</span>
          </el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="home-header">
        <div class="header-user">
          <span>欢迎回来，管理员</span>
          <el-button type="text" @click="handleLogout">退出登录</el-button>
        </div>
      </el-header>
      <el-main class="home-main">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { House as HomeIcon, Goods as GoodsIcon, User as UserIcon, ShoppingCart as ShoppingCartIcon, Setting as SettingIcon } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const handleLogout = () => {
  localStorage.removeItem('token')
  router.push('/Login')
}
</script>

<style scoped>
.home-container {
  height: 100vh;
}

.home-aside {
  background-color: #545c64;
  color: white;
}

.el-menu-vertical-demo {
  height: 100%;
  background-color: #545c64;
}

.el-menu-item {
  color: white;
}

.home-header {
  background-color: #f5f5f5;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  padding: 0 20px;
}

.header-user {
  display: flex;
  align-items: center;
  gap: 15px;
}

.home-main {
  padding: 20px;
}
</style>