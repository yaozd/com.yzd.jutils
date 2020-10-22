//package com.yzd.jutils.rpcExt.t1;
//
//import com.alibaba.fastjson.JSONObject;
//import com.mian.bean.RpcRequest;
//import com.mian.bean.RpcResponse;
//import com.yzd.jutils.rpcExt.t1.bean.RpcRequest;
//import com.yzd.jutils.rpcExt.t1.bean.RpcResponse;
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.codec.Delimiters;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//
//// 长连接 (设置ReadTimeoutHandler，超时断开链接)
//public class NettyClient {
//
//	private static class SingletoHolder{
//		static final NettyClient instance = new NettyClient();
//	}
//
//	public static NettyClient getInstance(){
//		return NettyClient.SingletoHolder.instance;
//	}
//
//	private EventLoopGroup group;
//	private Bootstrap b;
//	private ChannelFuture cf ;
//
//	private NettyClient(){
//		group = new NioEventLoopGroup();
//		b = new Bootstrap();
//		b.group(group)
//				.channel(NioSocketChannel.class)
//				.handler(new LoggingHandler(LogLevel.INFO))
//				.handler(new ChannelInitializer<SocketChannel>() {
//					@Override
//					protected void initChannel(SocketChannel sc) throws Exception {
//						sc.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
//						sc.pipeline().addLast(new StringDecoder());
//						sc.pipeline().addLast(new NettyClientHandler());
//						sc.pipeline().addLast(new StringEncoder());
//						//超时handler（当服务器端与客户端在指定时间以上没有任何进行通信，则会关闭响应的通道，主要为减小服务端资源占用）
////						sc.pipeline().addLast(new ReadTimeoutHandler(5));
//					}
//				});
//	}
//
//	public void connect(){
//		try {
//			this.cf = b.connect("127.0.0.1", 8080).sync();
//			System.out.println("远程服务器已经连接, 可以进行数据交换..");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public ChannelFuture getChannelFuture(){
//
//		if(this.cf == null){
//			this.connect();
//		}
//		if(!this.cf.channel().isActive()){
//			this.connect();
//		}
//
//		return this.cf;
//	}
//
//	//发送数据
//	public RpcResponse send(RpcRequest request){
//		getChannelFuture().channel().writeAndFlush(JSONObject.toJSONString(request));
//		getChannelFuture().channel().writeAndFlush("\r\n");
//		AsyncFuture df = new AsyncFuture(request);
//		return df.get();
//
//	}
//
//
//
//}
