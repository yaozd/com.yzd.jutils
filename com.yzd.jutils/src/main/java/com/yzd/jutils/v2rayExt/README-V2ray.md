### V2ray-Free
- [v2ray免费账号]
    - [https://github.com/Alvin9999/new-pac/wiki/v2ray%E5%85%8D%E8%B4%B9%E8%B4%A6%E5%8F%B7](https://github.com/Alvin9999/new-pac/wiki/v2ray%E5%85%8D%E8%B4%B9%E8%B4%A6%E5%8F%B7)--推荐参考byArvin (速度比较快)
- [https://view.freev2ray.org/](https://view.freev2ray.org/)
- Alvin9999/new-pac
    - [https://github.com/Alvin9999/new-pac/wiki](https://github.com/Alvin9999/new-pac/wiki) -推荐参考byArvin
    - [谷歌镜像](https://github.com/Alvin9999/new-pac/wiki/%E8%B0%B7%E6%AD%8C%E9%95%9C%E5%83%8F) -点击访问
        - [https://ac.scmor.com/](https://ac.scmor.com/) -谷歌镜像
        - []()
    - 实用网络小知识
        - [实用网络小知识](https://github.com/Alvin9999/new-pac/wiki/%E5%AE%9E%E7%94%A8%E7%BD%91%E7%BB%9C%E5%B0%8F%E7%9F%A5%E8%AF%86)
        ```
        Cloudflare 推出 1.1.1.1 公共 DNS 服务，
        号称任何人都可以使用它可以加快互联网访问速度并保持连接私密性。
        Cloudflare 与 APNIC 合作通过以下两个 IP 提供 DNS 服务：1.1.1.1 和 1.0.0.1
        因此，建议将自己的电脑ipv4 DNS设置为这两个来翻墙。
        ```
        - 可以用一个被封的国外网站来查询ip，比如这个http://allinfa.com/check-online-ip-address.html
        - []()
    - DNSCrypt
        - [DNSCrypt-这本书能让你连接互联网 ](https://hoodiearon.github.io/fq-book/#/dns&hosts/dnscrypt)
        - [使用DNSCrypt选择最佳的DoH Resolver，解决DNS污染问题](https://www.uedbox.com/post/56205/)
        - [Simple DNSCrypt](https://simplednscrypt.org/)-推荐安装
    - []()
### - 免费的VPN
- [Unlimited VPN proxy – Unblock Sites](https://chrome.google.com/webstore/detail/unlimited-vpn-proxy-%E2%80%93-unb/nkmghlbiclohebhhoapdhcflibiibeak) -推荐byArvin
- [Free VPN - 房子裡最快的VPN服務](https://chrome.google.com/webstore/detail/free-vpn-the-fastest-vpn/nkomfibbgccdjcahcpleidblgknecfhh)
- [https://www.myip.com/](https://www.myip.com/) -测试验证地址：myip
-  DNSCrypt-解决DNS污染问题
   - [DNSCrypt-这本书能让你连接互联网 ](https://hoodiearon.github.io/fq-book/#/dns&hosts/dnscrypt)
   - [使用DNSCrypt选择最佳的DoH Resolver，解决DNS污染问题](https://www.uedbox.com/post/56205/)
   - [Simple DNSCrypt](https://simplednscrypt.org/)-推荐安装
   
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