/**
 * axios 组件. https://www.kancloud.cn/yunye/axios/234845
 *
 * @author liangzhong.tan
 * @date 2019-9-12 16:28:25
 */
layui.define('layer', exports => {
    let layer = layui.layer;

    // 创建axios实例
    const fetch = axios.create({
        timeout: 1800000 // 请求超时时间
    });

    // 添加请求拦截器
    fetch.interceptors.request.use(config => {
        // 在发送请求之前做些什么

        // CSRF
        let _csrf_header = $("meta[name='_csrf_header']").attr("content");
        config.headers[_csrf_header] = $("meta[name='_csrf']").attr("content");
        return config;
    }, error => {
        // 对响应错误做点什么
        console.log("Request Error: ", error);
        return Promise.reject(error);
    });

    // 添加响应拦截器
    fetch.interceptors.response.use(response => {
        console.log("Response: ", response);
        if (!response.data || response.data.code === 'E0000') layer.msg(`Error: ${response.data.message}`);
        // 对响应数据做点什么
        return response.data;
    }, error => {
        // 对响应错误做点什么
        console.log("Response Error: ", error);
        return Promise.reject(error);
    });

    // 输出 fetch
    exports('fetch', fetch);
});




