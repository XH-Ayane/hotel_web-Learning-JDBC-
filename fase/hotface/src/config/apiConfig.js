// API配置文件
const API_CONFIG = {
  // 后端基础URL（通过vite proxy转发）
  baseUrl: '/api',
  
  // 后端服务状态检查接口
  healthCheck: '/health',
  
  // 首页数据统计接口
  dashboardStats: '/dashboard/stats',
  
  // 客房管理接口
  rooms: {
    list: '/rooms',
    detail: '/rooms/{id}',
    add: '/rooms',
    update: '/rooms/{id}',
    delete: '/rooms/{id}'
  },
  
  // 订单管理接口
  orders: {
    list: '/orders',
    detail: '/orders/{id}',
    add: '/orders',
    update: '/orders/{id}',
    delete: '/orders/{id}'
  },
  
  // 用户管理接口
  users: {
    list: '/users',
    detail: '/users/{id}',
    add: '/users',
    update: '/users/{id}',
    delete: '/users/{id}'
  }
}

export default API_CONFIG