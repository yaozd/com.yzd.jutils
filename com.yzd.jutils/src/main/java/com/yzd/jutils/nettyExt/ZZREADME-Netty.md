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
 
 - 示例
    - [使用netty实现文件上传服务器](https://segmentfault.com/a/1190000020087277)
 
 
 ## LEAK: ByteBuf.release() was not called before it's garbage-collected
 - [1. in.readBytes 导致堆外内存泄漏](https://www.jianshu.com/p/944ab8e0ef34)
```
ResourceLeakDetector.setLevel(ResourceLeakDetector.Level.ADVANCED);
-Dio.netty.leakDetectionLevel=advanced
```
 
 ## netty IOException: 你的主机中的软件中止了一个已建立的连接
```
产生原因：
AdvancedLeakAwareByteBuf对象在channel关闭状态时，进入到channel read方法产生的
AdvancedLeakAwareByteBuf(PooledUnsafeDirectByteBuf(ridx: 0, widx: 8, cap: 512))
解决方案：
@Override
public void handleChannelRead(ChannelHandlerContext ctx, Object msg){
    if(!(msg instanceof HttpObject)){
        System.out.println(msg);
        ReferenceCountUtil.release(msg);
        return;
    }
}
```
## io.netty.util.IllegalReferenceCountException: refCnt: 0, decrement: 1
```
原因分析：channelInactive时出错
private void channelInputClosed(ChannelHandlerContext ctx, boolean callChannelInactive) {
    CodecOutputList out = CodecOutputList.newInstance();
    try {
        channelInputClosed(ctx, out);
    } catch (DecoderException e) {
        throw e;
    } catch (Exception e) {
        throw new DecoderException(e);
    } finally {
        try {
            if (cumulation != null) {
                cumulation.release();
                cumulation = null;
            }
            int size = out.size();
            fireChannelRead(ctx, out, size);
            if (size > 0) {
                // Something was read, call fireChannelReadComplete()
                ctx.fireChannelReadComplete();
            }
            if (callChannelInactive) {
                ctx.fireChannelInactive();
            }
        } finally {
            // Recycle in all cases
            out.recycle();
        }
    }
}
```
## io.netty.util.IllegalReferenceCountException: refCnt: 0
```
原因分析：writeData时数据已经被回收
frameWriter.writeData(channelHandlerContext, frameWrapper.getStreamId(), dataFrame.content(),
                        dataFrame.padding(), dataFrame.isEndStream(), promise);
```
 