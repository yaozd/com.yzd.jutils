
- 大规模发版解决方案

```
1.gitlab--代码管理
2.jenkins--打包--上传--http.server静态文件服务器
3.rundeck--下载启动--A组（n+1台）
         --下载启动--B组（n+1台）
```

- jenkins- 文件上传
    - [jenkins中pipeline发送http请求](https://blog.csdn.net/u010885548/article/details/106058792/)
      ```
      curl http://ip:port/testController/upload_file -F
      如果都使用curl命令，其实postman可以直接导出shell脚本，这里只上传文件的接口使用了curl命令
      postman测试接口成功后，可以直接点击“Code”
      ```
## 示例
- [基于Gitlab+Jenkins的测试环境自动构建和生产多环境手动发布方案](https://blog.csdn.net/zwjzqqb/article/details/84241044)