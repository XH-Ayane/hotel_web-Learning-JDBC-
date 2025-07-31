import axios from 'axios'

//使用axios下面的create([config])方法创建axios实例，其中config参数为axios最基本的配置信息。
const axiosObj = axios.create({
	//请求后端数据的根路径地址，每次使用axios异步请求 都要填写域名比较麻烦
	//可以设置一个baseUrl,这样少写点代码,
	//而且后面更换域名时,生产环境和开发环境统一时很有用
	baseURL: '/api',
	timeout: 2000 //请求超时设置，单位ms
})



//导出axios实例模块
export default axiosObj;