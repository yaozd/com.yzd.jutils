### V2ray-Openshife
> [https://www.openshift.com](https://www.openshift.com)

> Openshift配置方法:
```
1.image
b1nitp7iw/v2ray
----------------------
2.env-config(环境变量)
UUID    c8131989-451d-4689-9092-63d1e5add6a1
----------------------
3.基础设置
只要一个邮箱就能免费搭建免费V2ray服务器
http://www.cnblogs.com/ioufev/articles/9830000.html
V2Ray with OpenShift Container Platform
https://shui.azurewebsites.net/2017/12/10/v2ray-with-openshift-container-platform/
```

> [b1nitp7iw/v2ray镜像](https://shui.azurewebsites.net/2017/12/10/v2ray-with-openshift-container-platform/)
```
b1nitp7iw/v2ray镜像默认使用WebSocket模式。只需设置变量UUID即可。
UUID可以通过网站https://www.uuidgenerator.net/生成，用于服务器连接验证。
点击Deploy。完成部署之后，访问应用HTTPS地址，显示Bad Request，说明部署成功了。
```

> V2rayN-window-配置模板:
```
vmess://ew0KICAidiI6ICIyIiwNCiAgInBzIjogIm9wZW5zaGlmdC10bXAiLA0KICAiYWRkIjogInYycmF5LXYycmF5LWRldi43ZTE0LnN0YXJ0ZXItdXMtd2VzdC0yLm9wZW5zaGlmdGFwcHMuY29tIiwNCiAgInBvcnQiOiAiNDQzIiwNCiAgImlkIjogImM4MTMxOTg5LTQ1MWQtNDY4OS05MDkyLTYzZDFlNWFkZDZhMSIsDQogICJhaWQiOiAiNjQiLA0KICAibmV0IjogIndzIiwNCiAgInR5cGUiOiAibm9uZSIsDQogICJob3N0IjogIiIsDQogICJwYXRoIjogIiIsDQogICJ0bHMiOiAidGxzIg0KfQ==

```