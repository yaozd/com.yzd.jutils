# grpc-benchmarks
## 脚本
```SHELL
- 服务端运行命令：
nohup java -Xmx2g -Xms2g -Xmn1g -XX:+UseCompressedOops -XX:MaxGCPauseMillis=50 -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+CMSClassUnloadingEnabled -cp /tmp/grpc-benchmarks-1.0-SNAPSHOT.jar io.grpc.benchmarks.qps.AsyncServer --address=172.20.134.79:30009 --save_histogram=/tmp/grpc-benchmarks-result.log &
 
- 客户端运行脚本：
nohup java -Xmx2g -Xms2g -Xmn1g -XX:+UseCompressedOops -XX:MaxGCPauseMillis=50 -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+CMSClassUnloadingEnabled -cp /tmp/grpc-benchmarks-1.0-SNAPSHOT.jar io.grpc.benchmarks.qps.AsyncClient --address=172.20.134.79:30009 --client_payload=0 --server_payload=0 --channels=10 --outstanding_rpcs=1 --fast_check=false --save_histogram=/tmp/grpc-benchmarks-result.log &

```

## 调优参数
```
1）Linux 内核调优，详情请见《内核调优》

2）JVM 调优：

-Xmx2g -Xms2g -Xmn1g -XX:+UseCompressedOops -XX:MaxGCPauseMillis=50 -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+CMSClassUnloadingEnabled
```
## 四、压测结论
```
1）空包场景下，QPS 最高可以达到19W+，在1000并发压力下，请求未发生错误，可以满足当前系统需求。

2）payload size 从增加过程，速率下降明显。
```
## GRPC 参数调优对 server 性能影响的分析
- 默认的 server 创建源码
```
static Server newServerDefault(ServerConfiguration config) throws IOException {
    Server server = NettyServerBuilder
            .forAddress(config.address)
            .addService(new HelloServiceImpl())
            .build();
    return server;
}
```
- grpc-benchmarks 调优后的 server 创建源码
```
static Server newServer1(ServerConfiguration config) throws IOException {
    final EventLoopGroup boss;
    final EventLoopGroup worker;
    final Class<? extends ServerChannel> channelType;
    ThreadFactory tf = new DefaultThreadFactory("server-elg-", true /*daemon */);
 
    boss = new NioEventLoopGroup(1, tf);
    worker = new NioEventLoopGroup(0, tf);
    channelType = NioServerSocketChannel.class;
 
    NettyServerBuilder builder = NettyServerBuilder
            .forAddress(config.address)
            .bossEventLoopGroup(boss)
            .workerEventLoopGroup(worker)
            .channelType(channelType)
            .addService(new HelloServiceImpl())
            .flowControlWindow(config.flowControlWindow)
            .directExecutor();
 
    return builder.build();
}
```
