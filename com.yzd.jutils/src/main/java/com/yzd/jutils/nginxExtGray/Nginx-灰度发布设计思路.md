
### Nginx-灰度发布设计思路

### Nginx-工具列表与作用

```
1.Nginx:3个Nginx：
  1个主（通过map分流+正则匹配域名server_name=*.yzd.com），2个副（正常版本与灰度版本）
2.Upsync 模块：1个副(正常版本),作用：动态更新upstream
3.consul:1个副(正常版本)服务发现与协作
4.gitlab:代码管理
5.jenkins:打包管理
6.rundeck:分组发版（分组：gray组、A组、B组）
```