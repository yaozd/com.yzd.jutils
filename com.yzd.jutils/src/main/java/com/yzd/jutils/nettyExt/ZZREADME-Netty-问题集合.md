## 问题
- [netty中channelDisconnected和channelClosed这两个事件的区别](http://www.voidcn.com/article/p-erdctjax-bpb.html)
- [Netty中ctx.writeAndFlush与ctx.channel().writeAndFlush的区别](http://www.voidcn.com/article/p-syiylgpt-bqt.html)
- [Netty 中 IOException: Connection reset by peer 与 java.nio.channels.ClosedChannelException: null](https://www.cnblogs.com/zemliu/p/3864131.html)
    ```
    #ClosedChannelException, 
    大概的意思是说在 channel close 以后, 如果还调用了 write 方法, 则会将 write 的 future 设置为 failure, 并将 cause 设置为 ClosedChannelException, 同样 SSLHandler 中也类似
    # Connection reset by peer
    connection reset by peer 异常, 是因为 connect 成功以后, client 段先会触发 connect 成功的 listener, 这个时候 server 段虽然断开了 channel,
    也触发 channel 断开的事件 (它会触发一个客户端 read 事件, 但是这个 read 会返回 -1, -1 代表 channel 关闭, client 的 channelInactive 跟 channel  active 状态的改变都是在这时发生的)
    ```
- []()
- []()
- []()
- []()
- []()