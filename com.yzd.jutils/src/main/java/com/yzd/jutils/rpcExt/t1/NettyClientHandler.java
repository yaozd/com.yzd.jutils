//package com.yzd.jutils.rpcExt.t1;
//
//import com.alibaba.fastjson.JSONObject;
//import com.yzd.jutils.rpcExt.t1.bean.RpcResponse;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.util.ReferenceCountUtil;
//
//public class NettyClientHandler extends ChannelInboundHandlerAdapter {
//
//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		try{
//			if("ping".equals(msg.toString())){
//				ctx.channel().writeAndFlush("ping\r\n");
//				return ;
//			}
//
//			RpcResponse response = JSONObject.parseObject(msg.toString(), RpcResponse.class);
//			System.out.println(response.getRequestId() + "接收到请求....");
//			AsyncFuture.receive(response );
//		}finally {
//			ReferenceCountUtil.release(msg);
//		}
//
//	}
//
//	@Override
//	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
//	}
//
//
//
//
//
//
//}
