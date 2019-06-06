- [Vue.js 教程](https://www.runoob.com/vue2/vue-tutorial.html)-推荐参考
- 参考项目-byArvin
    - [https://github.com/yaozd/com.yzd.shiro.root](https://github.com/yaozd/com.yzd.shiro.root)
- vue axios 请求带token设置
    - [https://www.cnblogs.com/lfqcode/p/8690402.html](https://www.cnblogs.com/lfqcode/p/8690402.html)
```
API <br>axios.js
 
import axios from "axios";
let AUTH_TOKEN=(function(){
    return localStorage.getItem("token");
})();
 
var instance = axios.create({
});
instance.defaults.headers.common["Authorization"] = AUTH_TOKEN;
instance.interceptors.request.use(function(config){
    let url = config.url;
    if(url.indexOf("login")>-1){
        localStorage.setItem('token',"");
        config.headers.Authorization = "";
    }
    if(url.indexOf("user")>-1 && url.indexOf("login")<0){
        config.headers.Authorization =localStorage.getItem("token");
    }
    return config;
},function(err){
    return Promise.reject(err);
});
instance.interceptors.response.use(function(res){
    if(res.headers.token){
        localStorage.setItem('token',res.headers.token);
    }
    return res;
},function(err){
    return err;
});
export default instance;
---------------------------------------
import axios from '@/api/axios'
export function loginBywx(){
    return axios.get('/api/v1/wechat/login')    
}
export function login(params){
    return axios.post("/api/v1/user/info/login",params);
}
export function updateUser(params){
    return axios.put("/api/v1/user/info/update",params);
}
```
### axios-参考：
- [axios中文文档](https://www.jianshu.com/p/7a9fbcbb1114)
- [vue axios 请求带token设置](https://www.cnblogs.com/lfqcode/p/8690402.html)
- [使用axios.all处理并发请求](https://my.oschina.net/jamesview/blog/1860548)
- [axios拦截设置和错误处理](https://blog.csdn.net/sjn0503/article/details/74729300)
- [vue+axios 前端实现登录拦截（路由拦截、http拦截）-包含遮罩层设置](https://blog.csdn.net/wojiaomaxiaoqi/article/details/78558600)