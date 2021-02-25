# Netty-ChannelOption

## 常用ChannelOption
- 客户端Bootstrap之ChannelOption配置
    ```
    public static void main(String[] args) throws InterruptedException {
            EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap
                    //SO_KEEPALIVE=true,是利用TCP的SO_KEEPALIVE属性,当SO_KEEPALIVE=true的时候,服务端可以探测客户端的连接是否还存活着,
                    //如果客户端因为断电或者网络问题或者客户端挂掉了等,那么服务端的连接可以关闭掉,释放资源。
                    .option(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    //如果TCP_NODELAY没有设置为true,那么底层的TCP为了能减少交互次数,会将网络数据积累到一定的数量后,
                    //服务器端才发送出去,会造成一定的延迟。在互联网应用中,通常希望服务是低延迟的,建议将TCP_NODELAY设置为true。
                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    //CONNECT_TIMEOUT_MILLIS,表示客户端调用服务端接口的超时时间
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
  ```
## 默认配置
- io.netty.channel.DefaultChannelConfig
    ```
  //Netty参数，连接超时毫秒数，默认值30000毫秒即30秒。
  private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
  ```

## 参考
- [netty系列之ChannelOption](https://blog.csdn.net/zhongzunfa/article/details/94590670)
- [netty实战之ChannelOption配置](https://blog.csdn.net/linsongbin1/article/details/77685242)
- []()