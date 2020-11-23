# Grpc-特性研究

# 一、NettyChannelBuilder

## 1.1 NettyChannelBuilder是什么？

在gRPC的client端，我们经常会写出以下代码：

	ManagedChannel channel = NettyChannelBuilder.forAddress(host, port)
                .usePlainText()
                .build();
    GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
    stub.sayHello(HelloRequest.newBuilder().setName(name).build());

在上面的示例代码中，我们通过调用GreeterGrpc.GreeterBlockingStub.sayHello(HelloRequst)方法向server端发送请求。为了创建一个stub，我们需要一个ManagedChannel对象。

事实上，gRPC Java API可以被分为两部分：stub layer和call layer。其中stub layer通过包裹call layer简化了gRPC Java API的使用。

在上面的示例中，由protobuf插件根据proto文件自动生成的stub类GreeterGrpc.GreeterBlockingStub就像它的名字暗示的那样，属于stub layer，而其包裹的channel则属于call layer。显然，我们需要为channel指定其进行远程调用的地址，这正是NettyChannelBuilder在其forAddress(host, port)方法中所做的。除此之外，NettyChannelBuilder还可以做很多事情，包括指定压缩算法，配置重试机制等等。

也就是说，NettyChannelBuilder用于构造ManagedChannel，即Client端的call layer，为其指定远程调用的地址等信息。

## 1.2 NettyChannelBuilder的方法

NettyChannelBuilder的完整的方法列表（不包含deprecated的方法）如下（按功能进行组织）。

build：
* build()

server地址：
* forAddress(String host, int port)
* forTarget(String target)

[压缩与解压缩](#13-压缩与解压缩)：
* compressorRegistry(CompressorRegistry registry)
* decompressorRegistry(DecompressorRegistry registry)
* enableFullStreamDecompression()

[指定执行应用代码的线程池](#14-指定执行应用代码的线程池)：
* executor(Executor executor)
* directExecutor()

[安全](#15-安全)：
* overrideAuthority(String authority)
* useTransportSecurity()
* sslContext(SslContext sslContext)
* negotiationType(NegotiationType type)
* usePlaintext()

[接收的最大数据量](#16-接收的最大数据量)：
* maxInboundMessageSize(int max)
* maxInboundMetadataSize(int bytes)

[netty](#17-netty)：
* eventLoopGroup(EventLoopGroup eventLoopGroup)	
* withOption(ChannelOption<T> option, T value)
* channelType(Class<? extends Channel> channelType)

[keep alive机制](#18-keep-alive机制)：
* keepAliveTime(long keepAliveTime, TimeUnit timeUnit)
* keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit)
* keepAliveWithoutCalls(boolean enable)

拦截器：
* intercept(ClientInterceptor... interceptors)
* intercept(List<ClientInterceptor> interceptors)

负载均衡：
* defaultLoadBalancingPolicy(String policy)

重试机制（在grpc 1.18.0版本中，重试机制还未完成）：
* disableRetry()
* enableRetry()
* maxHedgedAttempts(int maxHedgedAttempts)
* maxRetryAttempts(int maxRetryAttempts)

idle mode：
idleTimeout(long value, TimeUnit unit)

name resolver：
* nameResolverFactory(NameResolver.Factory resolverFactory)

http/2：
* flowControlWindow(int flowControlWindow)
* userAgent(String userAgent)

buffer：
* perRpcBufferLimit(long bytes)	 
* retryBufferSize(long bytes)

连接本地端口：
* localSocketPicker(NettyChannelBuilder.LocalSocketPicker localSocketPicker)

maxTraceEvents：
* maxTraceEvents(int maxTraceEvents)

binary log（目前好像还没有能够处理binary log的工具）：
* setBinaryLog(BinaryLog log)

## 1.3 压缩与解压缩

### 1.3.1 自定义压缩与解压缩逻辑

    /**
     * 演示如何自定义压缩与解压逻辑
     * 这个例子同时演示了如何通过ServerInterceptor.interceptCall来调用ServerCall.setCompression
     */
    @Test
    public void customCodec() throws Exception {
        int port = 50001;
        CompressorRegistry compressorRegistry = CompressorRegistry.newEmptyInstance();
        compressorRegistry.register(new CustomCodec());
        DecompressorRegistry decompressorRegistry = DecompressorRegistry.emptyInstance()
                .with(new CustomCodec(), true); // 第二个参数为true，则会在client request的头部声明可接收该压缩方法，即向头部添加grpc-accept-encoding=CustomCodec
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .compressorRegistry(compressorRegistry)
                .decompressorRegistry(decompressorRegistry)
                .intercept(new SetCompressionServerInterceptor()) // 注意这里需要Interceptor来调用ServerCall.setCompression
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("127.0.0.1", port)
                .usePlaintext()
                .compressorRegistry(compressorRegistry)
                .decompressorRegistry(decompressorRegistry)
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel).withCompression("CustomCodec");
        stub.sayHello(request("request 1"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
      
    public class CustomCodec implements Codec {
     
        private static Logger logger = LoggerFactory.getLogger(CustomCodec.class);
     
        @Override
        public String getMessageEncoding() {
            return "CustomCodec";
        }
     
        @Override
        public InputStream decompress(InputStream is) throws IOException {
            return new InputStream() {
                @Override
                public int read() throws IOException {
                    int readInt = is.read();
                    logger.info("read: {}", readInt);
                    return readInt;
                }
            };
        }
     
        @Override
        public OutputStream compress(OutputStream os) throws IOException {
            return new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    logger.info("write: {}", b);
                    os.write(b);
                }
            };
        }
    }
      
    public class SetCompressionServerInterceptor implements ServerInterceptor {
     
        private static Logger logger = LoggerFactory.getLogger(SetCompressionServerInterceptor.class);
     
        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            logger.info("intercept call, set custom codec");
            call.setCompression("CustomCodec");
            return next.startCall(call, headers);
        }
    }

### 1.3.2 ChannelBuilder.enableFullStreamDecompression()

调用这一方法后，channel将在header中写入accept-encoding=gzip。如果server支持该功能，server会使用gzip对回复信息进行压缩。

    /**
     * 演示ChannelBuilder.enableFullStreamDecompression()
     */
    @Test
    public void enableFullStreamDecompression() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .intercept(new DetermineCodecServerInterceptor())
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .enableFullStreamDecompression()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        stub.sayHello(request("request 1"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
      
    public class DetermineCodecServerInterceptor implements ServerInterceptor {
     
        private static Logger logger = LoggerFactory.getLogger(DetermineCodecServerInterceptor.class);
     
        @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
            logger.info("intercept call, headers: {}", headers);
            // logger打印的信息为：intercept call, headers: Metadata(content-type=application/grpc,user-agent=grpc-java-netty/1.18.0,grpc-accept-encoding=gzip,accept-encoding=gzip)
            String acceptEncoding = headers.get(Metadata.Key.of("accept-encoding", Metadata.ASCII_STRING_MARSHALLER));
            logger.info("accept encoding: {}", acceptEncoding);
            if (acceptEncoding != null && acceptEncoding.contains("gzip")) {
                call.setCompression("gzip");
            }
            return next.startCall(call, headers);
        }
    }

## 1.4 指定执行应用代码的线程池

executor(Executor executor)方法可以指定client端运行代码的线程池。

而directExecutor()方法相当于调用executor(MoreExecutors.directExecutor())，可以让client在传输线程直接执行应用代码。

举例：

* 若没有调用，应用代码执行线程为grpc-default-executor-0。
* 调用后，应用代码执行线程为grpc-default-worker-ELG-3-1。

调用这个方法时应确保应用代码不会阻塞，否则将阻塞传输线程导致其他在同一个event loop进行数据传输的rpc调用被阻塞。

### 1.4.1 directExecutor的使用及其影响

下面将使用asynchronous stub来展示ManagedChannel.directExecutor()方法，因为使用blocking stub的client端并没有能够在direct executor中执行的应用代码。

    /**
     * 测试channel的directExecutor方法。
     */
    @Test
    public void clientDirectExecutor() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
    //          .directExecutor() //注释此行查看改变
                .build();
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);
        stub.sayHello(request("request"), new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                logger.info("on next");
            }

            @Override
            public void onError(Throwable t) {
                logger.info("on error");
            }

            @Override
            public void onCompleted() {
                logger.info("on completed");
            }
        });
        Thread.sleep(1000);
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
    
当注释掉directExecutor()方法时，运行结果如下：

2019-02-25 16:40:27 [grpc-default-executor-0] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request  
2019-02-25 16:40:27 [grpc-default-executor-1] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-25 16:40:27 [grpc-default-executor-1] INFO  c.example.grpc.helloworld.DemoTests - on completed  

否则，运行结果如下：

2019-02-25 16:41:49 [grpc-default-executor-0] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request  
2019-02-25 16:41:49 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-25 16:41:49 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on completed

从运行结果可以看到，directExecutor()方法可以指定StreamObserver方法的执行线程。

### 1.4.2 应用代码有阻塞时，调用directExecutor()的运行结果

示例代码如下。

    /**
     * 测试当应用代码有阻塞时，channel的directExecutor方法的影响。
     */
    @Test
    public void clientDirectExecutor() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .directExecutor() //注释此行查看改变
                .build();
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);
        stub.sayHello(request("request"), new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                logger.info("on next");
            }

            @Override
            public void onError(Throwable t) {
                logger.info("on error");
            }

            @Override
            public void onCompleted() {
                logger.info("on completed");
            }
        });
        Thread.sleep(1000);
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }

    @Test
    public void clientDirectExecutorBlocked() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
    //          .directExecutor() //注释此行查看改变
                .build();
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);
        StreamObserver streamObserver = new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("on next");
            }

            @Override
            public void onError(Throwable t) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("on error");
            }

            @Override
            public void onCompleted() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info("on completed");
            }
        };
        stub.sayHello(request("request 1"), streamObserver);
        stub.sayHello(request("request 2"), streamObserver);
        Thread.sleep(10000);
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
    
当注释掉directExecutor()方法时，运行结果如下：

2019-02-25 17:02:56 [grpc-default-executor-1] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1  
2019-02-25 17:02:56 [grpc-default-executor-1] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 2  
2019-02-25 17:02:57 [grpc-default-executor-1] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-25 17:02:57 [grpc-default-executor-0] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-25 17:02:58 [grpc-default-executor-1] INFO  c.example.grpc.helloworld.DemoTests - on completed  
2019-02-25 17:02:58 [grpc-default-executor-0] INFO  c.example.grpc.helloworld.DemoTests - on completed

否则，运行结果如下：

2019-02-25 17:03:32 [grpc-default-executor-0] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1  
2019-02-25 17:03:32 [grpc-default-executor-1] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 2  
2019-02-25 17:03:33 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-25 17:03:34 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-25 17:03:35 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on completed  
2019-02-25 17:03:36 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on completed

从运行结果可以看到，

* 在不使用directExecutor()时，两条请求是在grpc-default-executor线程池并行执行的。
* 在使用directExecutor()时，两条请求是在传输线程grpc-default-worker-ELG-3-2串行执行的。

当应用代码有阻塞时使用directExecutor()将使吞吐率降低。

## 1.5 安全

### 1.5.1 使用plaintext



    @Test
    public void plaintext() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        stub.sayHello(request("request"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
        logger.info("status count success: {}", StatusCollector.getSuccessCount());
    }
    
如上例所示，使用plaintext进行传输时，server端不需要进行配置，client端只需要调用usePlaintext方法即可。
    
### 1.5.2 使用tls

首先需要生成自签名证书，运行以下命令：

1. openssl genpkey -out private.pem -algorithm RSA -pkeyopt rsa_keygen_bits:2048
1. openssl req -new -key server.key -out server.csr
1. openssl x509 -req -days 3650 -in server.csr -signkey server.key -out server.crt

grpc使用tls的示例代码如下：

    /**
     * 演示如何使用tls
     */
    @Test
    public void withTls() throws Exception {
        int port = 50001;
        Server server = NettyServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .useTransportSecurity(new ClassPathResource("conf/server.crt").getInputStream(), new ClassPathResource("conf/server.key").getInputStream())
                .build();
        server.start();

        ApplicationProtocolConfig applicationProtocolConfig = new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL, ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL, "h2");
        SslContextBuilder builder = SslContextBuilder.forClient()
                .trustManager(new ClassPathResource("conf/server.crt").getInputStream())
                .applicationProtocolConfig(applicationProtocolConfig);
        ManagedChannel channel = NettyChannelBuilder.forAddress("localhost", port)
                .negotiationType(NegotiationType.TLS)
                .sslContext(builder.build())
                .useTransportSecurity()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        stub.sayHello(request("request 1"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
    
### 1.5.3 ChannelBuilder.overrideAuthority()

overrideAuthority方法意味着在client端将把server端的权限替换为指定主机的权限。若client信任该指定的主机，则client将信任所有server，否则其将不信任所有server。
这种特性意味着该方法仅应应用于测试。

示例代码如下。

    /**
     * 演示overrideAuthority的使用。
     */
    @Test
    public void overrideAuthority() throws Exception {
        int port = 50001;
        Server server = NettyServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .useTransportSecurity(new ClassPathResource("conf/server.crt").getInputStream(), new ClassPathResource("conf/server.key").getInputStream())
                .build();
        server.start();

        ApplicationProtocolConfig applicationProtocolConfig = new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.CHOOSE_MY_LAST_PROTOCOL, ApplicationProtocolConfig.SelectedListenerFailureBehavior.CHOOSE_MY_LAST_PROTOCOL, "h2");
        SslContextBuilder builder = SslContextBuilder.forClient()
                .trustManager(new ClassPathResource("conf/server.crt").getInputStream())
                .applicationProtocolConfig(applicationProtocolConfig);
        ManagedChannel channel = NettyChannelBuilder.forAddress("127.0.0.1", port)
                .sslContext(builder.build())
                .overrideAuthority("localhost")  // 注释该行查看改变
                .useTransportSecurity()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        stub.sayHello(request("request 1"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
    
若注释掉overrideAuthority方法，client端将不信任server并报错。否则，client端将信任该server。

### 1.5.4 ChannelBuilder.negotiationType(NegotiationType type)

NegotiationType类共有三个实例：

* TLS。channel将使用TLS ALPN协议。
调用ChannelBuilder.negotiationType(NegotiationType.TLS)等价于调用ChannelBuilder.useTransportSecurity()。
* PLAINTEXT。channel将使用明文传输。同时使用http/2协议进行传输。
调用ChannelBuilder.negotiationType(NegotiationType.PLAINTEXT)等价于调用ChannelBuilder.plaintext()。
* PLAINTEXT_UPGRADE。channel将使用明文传输，同时使用http/1.1协议进行传输，但将尝试升级为http/2协议。

## 1.6 接收的最大数据量

设置接收消息的最大大小的方法有两个，分别是：
* maxInboundMessageSize(int max)。设置接收的最大消息size，默认为4MiB。
* maxInboundMetadataSize(int bytes)。设置接收元数据的最大size，默认为8KiB。

当使用blocking stub时，若client接收消息大小超过限制，将抛出io.grpc.StatusRuntimeException: RESOURCE_EXHAUSTED异常。

## 1.7 Netty

### 1.7.1 event group loop

netty中的每一个channel对应一个EventLoop，每个EventLoop对应一个线程。

而一个event loop可对应多个channel，这意味着很多channel共享一个线程，netty通过这种方式减少线程数，以提高性能。

event loop group即一个event loops的集合，为server指定event loop group后，netty会将新产生的channel平均分配给group中的event loop，以提高性能。

client端一个channel只需要一个EventLoop，因而只需要为NettyChannelBuilder指定一个EventLoopGroup。默认为grpc-default-worker-ELG。

示例代码如下：

    /**
     * 演示NettyChannelBuilder.eventLoopGroup的影响
     */
    @Test
    public void eventLoopGroup() throws Exception {
        int port = 50001;
        Server server = NettyServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .build();
        server.start();

        ManagedChannel channel = NettyChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
    //          .eventLoopGroup(new NioEventLoopGroup())
                .directExecutor()
                .build();
        GreeterGrpc.GreeterStub stub = GreeterGrpc.newStub(channel);
        stub.sayHello(Util.request("request 1"), new StreamObserver<HelloReply>() {
            @Override
            public void onNext(HelloReply value) {
                logger.info("on next");
            }

            @Override
            public void onError(Throwable t) {
                logger.info("on error");
            }

            @Override
            public void onCompleted() {
                logger.info("on completed");
            }
        });
        Thread.sleep(1000);
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
    
若注释掉eventLoopGroup，运行结果为：

2019-02-26 11:56:03 [grpc-default-executor-0] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1  
2019-02-26 11:56:03 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-26 11:56:03 [grpc-default-worker-ELG-3-2] INFO  c.example.grpc.helloworld.DemoTests - on completed

否则，运行结果为：

2019-02-26 12:01:04 [grpc-default-executor-0] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1  
2019-02-26 12:01:04 [nioEventLoopGroup-4-2] INFO  c.example.grpc.helloworld.DemoTests - on next  
2019-02-26 12:01:04 [nioEventLoopGroup-4-2] INFO  c.example.grpc.helloworld.DemoTests - on completed

由上述示例可以看出，使用workerEventLoopGroup可改变传输线程所在的线程池。

### 1.7.2 ChannelOption

NettyChannelBuilder.withOption(ChannelOption<T> option, T value)用于配置channel config，支持的option取决于channel config的具体实现。

### 1.7.3 channel type

NettyChannelBuilder.channelType(Class<? extends Channel> channelType)可指定netty使用的channel类型。

channel是netty中的一个接口，主要用于封装与简化java socket。channel有着许多不同的实现，可以用于各种不同的场景。

下面是几个例子。

* EmbeddedChannel，主要用于单元测试
* NioSocketChannel，可用于client端socket通信
* NioServerSocketChannel，可用于server端socket通信
* DatagramChannels，可用于无连接通信

## 1.8 keep-alive机制

ChannelBuilder关于keep alive机制有如下配置项：

* keepAliveTime，keep alive ping的间隔时间。默认不发送keepAlivePing。
* keepAliveTimeout，发送keep alive ping的超时时间。默认时间为20s。
* keepAliveWithoutCalls，当没有未完成的RPC调用时，是否会执行keep alive操作。默认不执行。

然而事实上，gRPC的连接将会被一直保留，直到channel或server主动关闭连接。

https://stackoverflow.com/questions/48865706/how-long-grpc-streaming-can-last

## 1.9 拦截器

下面的示例代码演示了如何使用拦截器重写request。

    /**
     * 使用clientInterceptor重写message信息。
     * @throws Exception
     */
    @Test
    public void clientInterceptor() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .build();
        server.start();

        ClientInterceptor loggingInterceptor = new ClientInterceptor() {
            @Override
            public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
                    final MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {

                return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
                        next.newCall(method, callOptions)) {

                    @Override
                    public void sendMessage(ReqT message) {
                        logger.info("message: {}", message);
                        if (message.getClass().equals(HelloRequest.class)) {
                            HelloRequest helloRequest = (HelloRequest) message;
                            helloRequest = helloRequest.toBuilder().setName("message is override").build();
                            message = (ReqT) helloRequest;
                        }
                        super.sendMessage(message);
                    }
                };
            }
        };

        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .intercept(loggingInterceptor)
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        stub.sayHello(Util.request("request 1"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }


# 二、NettyServerBuilder

## 2.1 NettyServerBuilder是什么？

在gRPC的server端，我们常常会写出如下代码：

    int port = 50001;
    Server server = NettyServerBuilder.forPort(port)
            .addService(new EchoGreeter())
            .build();
    server.start();
    
如代码所示，NettyServerBuilder用于构建gRPC server，为server指定端口与服务。当然NettyServerBuilder同时也有很多其他功能。

## 2.2 NettyServerBuilder等方法

NettyChannelBuilder的完整的方法列表（不包含deprecated的方法）如下（按功能进行组织）。

[添加切面特性](#23-添加切面特性)：
* addStreamTracerFactory(ServerStreamTracer.Factory factory)
* addTransportFilter(ServerTransportFilter filter)
* intercept(ServerInterceptor interceptor)

[压缩与解压缩](#24-压缩与解压缩)：
* compressorRegistry(CompressorRegistry registry)
* decompressorRegistry(DecompressorRegistry registry)

[应用代码的执行线程池](#25-应用代码执行的线程池)：
directExecutor()
executor(Executor executor)

[安全](#26-安全)：
* protocolNegotiator(ProtocolNegotiator protocolNegotiator)
* sslContext(SslContext sslContext)
* useTransportSecurity(InputStream certChain, InputStream privateKey)

[netty](#27-netty)：
* bossEventLoopGroup(EventLoopGroup group)
* workerEventLoopGroup(EventLoopGroup group)
* channelType(Class<? extends ServerChannel> channelType)
* withChildOption(ChannelOption<T> option, T value)
* workerEventLoopGroup(EventLoopGroup group)

[接收的最大数据量](#28-接收的最大数据量)：
* maxInboundMessageSize(int bytes)
* maxInboundMetadataSize(int bytes)

通信协议相关：
* handshakeTimeout(long timeout, TimeUnit unit)
* flowControlWindow(int flowControlWindow)	

keepAlive机制：
* keepAliveTime(long keepAliveTime, TimeUnit timeUnit)
* keepAliveTimeout(long keepAliveTimeout, TimeUnit timeUnit)
* permitKeepAliveTime(long keepAliveTime, TimeUnit timeUnit)
* permitKeepAliveWithoutCalls(boolean permit)

限制连接机制：
* maxConcurrentCallsPerConnection(int maxCalls)
* maxConnectionAge(long maxConnectionAge, TimeUnit timeUnit)
* maxConnectionAgeGrace(long maxConnectionAgeGrace, TimeUnit timeUnit)
* maxConnectionIdle(long maxConnectionIdle, TimeUnit timeUnit)

## 2.3 添加切面特性

### 2.3.1 StreamTracer

通过ServerBuilder.addStreamTracerFactory(ServerStreamTracer.Factory)可向server stream添加stream tracer。

特点：
* StreamTracer可以监听stream事件，主要用于获取server端的流量数据。
* 可添加多个factory，ServerStreamTracer.Factory将按照添加顺序生成StreamTracer。
* 每次RPC调用都会在server端产生一个stream，此时每个factory都将产生一个新的stream tracer。
* 当stream发生事件时，如server call started与stream closed，都会调用每一个tracer的相应方法。
* stream事件有发送消息，接收消息，关闭stream等。

### 2.3.2 StreamTracer用途举例-统计rpc成功调用的次数

    /**
     * 使用ServerStreamTracer统计server call的status信息。
     */
    @Test
    public void collectServerCallStatus() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .addStreamTracerFactory(new StatusCollector.Factory())
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        stub.sayHello(request("request"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
        logger.info("status count success: {}", StatusCollector.getSuccessCount());
    }
      
    public class StatusCollector extends ServerStreamTracer {
     
        private static Logger logger = LoggerFactory.getLogger(StatusCollector.class);
     
        private static AtomicInteger successCount = new AtomicInteger(0);
     
        public static int getSuccessCount() {
            return successCount.get();
        }
     
        public void streamClosed(Status status) {
            logger.info("stream closed, status: {}", status);
            if (status.isOk()) {
                successCount.incrementAndGet();
            }
        }
     
        public static class Factory extends ServerStreamTracer.Factory {
     
            @Override
            public ServerStreamTracer newServerStreamTracer(String fullMethodName, Metadata headers) {
                return new StatusCollector();
            }
        }
    }
    
## 2.4 压缩与解压缩

请参考1.3

## 2.5 应用代码的执行线程池

executor(Executor executor)方法可以指定client端运行代码的线程池。

而directExecutor()方法相当于调用executor(MoreExecutors.directExecutor())，可以让client在传输线程直接执行应用代码。

举例：

* 若没有调用，应用代码执行线程为grpc-default-executor-0。
* 调用后，应用代码执行线程为grpc-default-worker-ELG-3-1。

调用这个方法时应确保应用代码不会阻塞，否则将阻塞传输线程导致其他在同一个event loop进行数据传输的rpc调用被阻塞。

### 2.5.1 directExecutor的使用及其影响

示例代码：

    /**
     * 演示若服务代码有阻塞，ServerBuilder.directExecutor的影响
     */
    @Test
    public void directExecutorBlocked() throws Exception {
        int port = 50001;
        Server server = ServerBuilder.forPort(port)
                .addService(new BlockGreeter())
                .directExecutor() // 注释该行查看改变
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.execute(() -> stub.sayHello(request("request 1")));
        pool.execute(() -> stub.sayHello(request("request 2")));
        pool.shutdown();
        pool.awaitTermination(BlockGreeter.BLOCK_TIME_MILLS * 3, TimeUnit.MILLISECONDS);
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }
      
    public class BlockGreeter extends GreeterGrpc.GreeterImplBase {
     
        private Logger logger = LoggerFactory.getLogger(EchoGreeter.class);
     
        public static int BLOCK_TIME_MILLS = 2000;
        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            logger.info("hello request. name: {}", req.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage((req.getName())).build();
            try {
                Thread.sleep(BLOCK_TIME_MILLS);
            } catch (Exception e) {
                logger.info("interrupted", e);
            }
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
    
运行结果为：

2019-02-22 11:38:36 [grpc-default-worker-ELG-3-3] INFO c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 2  
2019-02-22 11:38:38 [grpc-default-worker-ELG-3-3] INFO c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1

若注释掉.directExecutor，结果为：

2019-02-22 11:39:50 [grpc-default-executor-0] INFO c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 2  
2019-02-22 11:39:50 [grpc-default-executor-1] INFO c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1

分析结果可知：

* 调用direct executor会使线程池改变，从grpc-defalut-executor变为grpc-default-worker-ELG-n
* 调用directExecutor()后，应用代码在传输线程同步执行
* 若不调用directExecutor()，应用代码在grpc-default-executor的不同线程中异步执行
* 若应用代码有阻塞，调用directExecutor()会阻塞传输线程，导致吞吐量降低

## 2.6 安全

有关安全方面的内容请参考1.5

## 2.7 Netty

### 2.7.1 event loop group
netty中的每一个channel对应一个EventLoop，每个EventLoop对应一个线程。

而一个event loop可包含多个channel，这意味着很多channel共享一个线程，netty通过这种方式减少线程数，以提高性能。

event loop group即一个event loops的集合，为server指定event loop group后，netty会将新产生的channel平均分配给group中的event loop，以提高性能。

server端的channel分为两种，一种是用来接收所有数据的ServerChannel，有且只有一个。一种是每当一个连接建立后，都会产生的连接到Client的channel，可以有0个或多个。

因而server端需要两个EventLoopGroup:

* BossEventLoopGroup，用来分配给ServerChannel
* WorkerEventLoopGroup，用来分配给连接到Client的Channel

### 2.7.2 event loop group示例代码

示例代码：

    /**
     * 演示NettyServerBuilder.workerEventLoopGroup的影响
     */
    @Test
    public void workerEventLoopGroup() throws Exception {
        int port = 50001;
        Server server = NettyServerBuilder.forPort(port)
                .addService(new EchoGreeter())
                .workerEventLoopGroup(new NioEventLoopGroup()) // 注释该行查看改变
                .directExecutor()
                .build();
        server.start();
        ManagedChannel channel = ManagedChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
        GreeterGrpc.GreeterBlockingStub stub = GreeterGrpc.newBlockingStub(channel);
        stub.sayHello(Util.request("request 1"));
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        server.shutdown().awaitTermination();
    }

运行结果为：

2019-02-26 16:44:59 [nioEventLoopGroup-2-1] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1

若注释掉workerEventLoopGroup方法，运行结果为：

2019-02-26 16:45:55 [grpc-default-worker-ELG-3-3] INFO  c.e.g.helloworld.greeter.EchoGreeter - hello request. name: request 1

由上述示例可以看出，使用workerEventLoopGroup方法可改变传输线程所在的线程池。

类似的，使用bossEventLoopGroup方法也可改变传输线程所在的线程池。区别在于workerEventLoopGroup影响的是与具体的client对应channel，bossEventLoopGroup影响的是server channel。

### 2.7.3 channel option

NettyServerBuilder.channelType(Class<? extends Channel> channelType)用于指定netty server使用的channel的类型。参见1.7.3

## 1.8 接收的最大数据量

设置接收消息的最大大小的方法有两个，分别是：
* maxInboundMessageSize(int max)。设置接收的最大消息size，默认为4MiB。
* maxInboundMetadataSize(int bytes)。设置接收元数据的最大size，默认为8KiB。

在使用blocking stub时，若server接收消息大小超过限制，server将抛出io.grpc.StatusRuntimeException: RESOURCE_EXHAUSTED异常，但仍能正常工作。
此时client端将抛出io.grpc.StatusRuntimeException: INTERNAL: Half-closed without a request异常。