// 创建axios实例
const service = axios.create({
    timeout: 1800000 // 请求超时时间
});

// 添加请求拦截器
service.interceptors.request.use(config => {
    console.log("Request Config : ", config);
    //TODO 在发送请求之前做些什么
    return config;
}, error => {
    //TODO 对响应错误做点什么
    console.log("Request Error: ", error);
    return Promise.reject(error);
});

// 添加响应拦截器
service.interceptors.response.use(response => {
    console.log("Response: ", response);
    //TODO 对响应数据做点什么
    return response;
}, error => {
    //TODO 对响应错误做点什么
    console.log("Response Error: ", error);
    return Promise.reject(error);
});




