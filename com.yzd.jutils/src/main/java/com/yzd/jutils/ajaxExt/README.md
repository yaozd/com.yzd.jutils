### ajax-demo参考
- [asyHandleCore](https://github.com/AAA-AA/asyHandleCore)
- [Axios 中文说明](https://www.kancloud.cn/yunye/axios/234845)

Axios 中文说明
```
1.使用 cdn:
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
2.全局的 axios 默认值
  axios.defaults.baseURL = 'https://api.example.com';
  axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
  axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
3.拦截器
  在请求或响应被 then 或 catch 处理前拦截它们。
4.等等
```

### 全局为每一个ajax请求都附带一个loading效果 .ajaxSend
- [全局为每一个ajax请求都附带一个loading效果 .ajaxSend](https://www.cnblogs.com/zhanghai/p/5559957.html)
- [jQuery - Ajax请求时自动显示Loading效果（设置全局加载框）](https://www.hangge.com/blog/cache/detail_2057.html#)