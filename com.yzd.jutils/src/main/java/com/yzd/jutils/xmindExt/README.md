### xmind思维导图
```
通过思维导图的方式来学习前端，将思维形象化，串联知识点，系统学习前端知识，提高学习效率，加深记忆 🎉
```
#### 架构规范模板-规范一些基本流程
#### [jbone电商设计图参考（spring cloud）-byAvin推荐参考](https://github.com/417511458/jbone)
- [jbone](https://github.com/yaozd/jbone)
- jbone的架构图需要通过亿图工具打开（百度网盘->开发工具->亿图9.2）
```
github:
https://github.com/yaozd/jbone(备份)
https://github.com/417511458/jbone
==
jbone定位
对企业
jbone采用微服务架构，为中小企业提供系统管理、内容管理、电商平台等解决方案。
使中小企业花最少的成本建立自己的电商平台、企业管理平台、支付平台等。 
jbone功能包括服务管理、系统管理平台、内容管理平台、电商平台、支付平台、工作流平台等子系统。
==
jbone功能架构图
jbone物理结构图
jbone功能和进度表
==
项目模块划分
jbone-cas : 用户单点登录模块
jbone-cas-client：客户端jar包，用于集成到需要CAS授权的系统
jbone-cas-server：CAS服务端，单独部署，用于完成单点登录、票据管理等
jbone-cas-manager：CAS服务管理，用于管理授权服务等
jbone-sm : 服务管理模块
jbone-sm-admin : 服务管理系统，包括服务监控、服务管理等
jbone-sm-register : 服务注册中心，原则上所有服务都要注册进来
jbone-sm-monitor : 服务监控系统，主要包含服务调用链分析和trace跟踪
jbone-sys : 系统管理模块
jbone-sys-admin ： 系统管理后台
jbone-sys-api : 系统服务对外接口定义
jbone-sys-api-feign : 基于Spring Cloud Feign的调用实现
jbone-sys-core : 系统管理核心
jbone-sys-server : 系统管理服务
jbone-tag ：全平台标签系统
jbone-cms ：内容管理模块
jbone-bpm : 工作流模块
jbone-common : 共用模块
jbone-configuration : 公共配置模块
jbone-b2b2c : 多店铺电商平台模块
jbone-pay : 支付平台模块
jbone-im : 即时通信模块
jbone-ui : 以webjars形式管理前端静态资源，所有包含页面的工程需要依赖此模块。
```