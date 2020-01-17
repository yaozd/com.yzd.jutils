## Socket模拟发送 HTTP数据包
- [怎么用java 模拟发送 HTTP数据包， 该如何避免](http://www.myexception.cn/j2se/800530.html)
- [java爬虫的2种爬取方式（HTTP||Socket）简单Demo(一)](https://blog.csdn.net/qq_24489717/article/details/52829572?locationNum=15&fps=1)
- [Java Socket发送与接收HTTP消息简单实现](https://blog.csdn.net/a9529lty/article/details/7174265) -首要参考byArin
- [jSocket](https://github.com/leegianOS/jSocket) -推荐参考byArin
    > [leegianOS-Module] jSocket is a channel based multisocket server/client http://www.enigmar.de
  
## socket示例
- SimpleHttpClient --首要参考byArvin
    -[System.out.println((int)(char)(byte) -1); 结果是？](https://blog.csdn.net/pmcasp/article/details/80746598)
    ```
    第一步：int类型的-1强转为byte类型 还是-1
    第二部：byte类型的-1转化为char，由ascii码表，-1是不在表范围之内，因为默认char占2个字节，即8个bit，所能存的最大值是65536个，所以char的数字范围是0-65535，把-1转为char，会倒着找，就是最大值65535，
    第三步：char类型的65535转换为int还是65535
    ```
    - [网络的FIN_WAIT_2状态解释和分析](https://www.cnblogs.com/langtianya/p/6648100.html)
    - [解决Linux服务器 FIN_WAIT2 连接过多的问题](https://blog.csdn.net/weixin_34356138/article/details/92308473)
- SocketHttpClient
