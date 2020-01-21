##　ＮＥＴＴＹ
- netty-源码
  -  [https://github.com/netty/netty](https://github.com/netty/netty)

- netty-参考
  - [Netty 源码分析之 零 磨刀不误砍柴工 源码分析环境搭建](https://github.com/yongshun/learn_netty_source_code/blob/f129f37978e29746f07ea6a8baef2479ee3b0593/Netty%20源码分析之%20零%20磨刀不误砍柴工%20源码分析环境搭建/Netty%20源码分析之%20零%20磨刀不误砍柴工%20源码分析环境搭建.md)
  - [Netty 实战精髓篇](https://www.w3cschool.cn/essential_netty_in_action/)
  - [Netty 4.x 用户指南](https://www.w3cschool.cn/netty4userguide/)
- netty-HttpProxy
    - support http/https proxy.类似于finddler,由java编写，代码简单便于理解。支持http/https代理！
        - [https://github.com/puhaiyang/easyHttpProxy](https://github.com/puhaiyang/easyHttpProxy)
        - [https://github.com/yaozd/easyHttpProxy](https://github.com/yaozd/easyHttpProxy)
    - [java实现http/https抓包拦截](https://blog.csdn.net/puhaiyang/article/details/102649498)
    - []()
- netty-HttpClient
    - [netty实现 http 长连接](https://blog.csdn.net/u014284000/article/details/94995198)
        - 源代码在https://github.com/zhwaaaaaa/rpollingc
        ```
        许多http服务器在一个连接上处理完一定数量的请求后，会把这个连接close掉，
        比如nginx，默认一个连接只处理100个请求，处理完毕后会强制关闭这个连接，
        当然可以通过设置keepalive_requests 这个参数取修改它的数量。
        ```
    - [netty实战-netty client连接池设计](https://www.jianshu.com/p/7132d84c2461?from=singlemessage)
    - [netty实战-自定义解码器处理半包消息](https://blog.csdn.net/linsongbin1/article/details/77915686)
    - []()
- netty-故障案例参考：
    - [Netty使用案例 -发送队列积压导致内存泄漏](https://blog.csdn.net/u013642886/article/details/86632752)
    - []()
 
 