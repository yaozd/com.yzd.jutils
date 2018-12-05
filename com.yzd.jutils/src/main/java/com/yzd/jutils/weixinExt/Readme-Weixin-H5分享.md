
### 微信H5分享逻辑

一：获得API的接口数据
```
1.获得Access Token
1.1-配置IP白名单
参考：
微信公众平台开发(26) ACCESS TOKEN
http://www.cnblogs.com/txw1958/p/weixin-access-token.html
-------------------------------------
2.获取jsapi_ticket(JS接口的临时票据)
参考：
h5 微信分享朋友和朋友圈
https://www.cnblogs.com/liangzia/p/7569443.html
-------------------------------------
3. 签名算法实现
参考：
h5 微信分享朋友和朋友圈
https://www.cnblogs.com/liangzia/p/7569443.html
-------------------------------------

```
二：JSSDK使用步骤

```
1、绑定域名
先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
-------------------------------------
2、引入js文件
-------------------------------------
3、通过config接口注入权限验证配置
-------------------------------------
分享接口：
wx.onMenuShareTimeline
1、获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
==
wx.onMenuShareAppMessage
2、获取“分享给朋友”按钮点击状态及自定义分享内容接口
-------------------------------------
参考：
微信JS-SDK实现自定义分享功能，分享给朋友，分享到朋友圈
https://blog.csdn.net/xdd19910505/article/details/50957713
```


参考：
- [微信公众平台开发(26) ACCESS TOKEN](http://www.cnblogs.com/txw1958/p/weixin-access-token.html)
- [h5 微信分享朋友和朋友圈](https://www.cnblogs.com/liangzia/p/7569443.html)
- [微信JS-SDK实现自定义分享功能，分享给朋友，分享到朋友圈](https://blog.csdn.net/xdd19910505/article/details/50957713)
- [《微信公众平台开发》最新版](https://www.cnblogs.com/txw1958/p/weixin-qrlogin.html)
