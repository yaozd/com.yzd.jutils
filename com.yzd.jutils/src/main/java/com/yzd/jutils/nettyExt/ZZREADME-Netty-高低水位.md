## watermarks-Netty设置高低水位
- [Netty设置高低水位](https://www.cnblogs.com/eaglediao/p/6959793.html)
- [2014-twitter-meetup-netty](http://normanmaurer.me/presentations/2014-twitter-meetup-netty/slides.html#10.0) -推荐设置参考
```
Configure high and low write watermarks
//
Server
ServerBootstrap bootstrap = new ServerBootstrap();
bootstrap.childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);
bootstrap.childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024);
Client
Bootstrap bootstrap = new Bootstrap();
bootstrap.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);
bootstrap.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 8 * 1024)
//
思想二 byArvin：
如API-ROUTER,通过max pending size控制请求的上限
```
