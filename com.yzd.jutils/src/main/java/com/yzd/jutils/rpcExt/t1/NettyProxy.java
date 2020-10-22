//package com.yzd.jutils.rpcExt.t1;
//
//import com.alibaba.fastjson.JSON;
//import com.yzd.jutils.rpcExt.t1.annotation.Reference;
//import com.yzd.jutils.rpcExt.t1.bean.RpcRequest;
//import com.yzd.jutils.rpcExt.t1.bean.RpcResponse;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.cglib.proxy.Enhancer;
//import org.springframework.cglib.proxy.MethodInterceptor;
//import org.springframework.cglib.proxy.MethodProxy;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.UUID;
//
//// ioc容器所创建的每一个bean在初期化方法前后都会调用
//@Component
//public class NettyProxy implements BeanPostProcessor {
//
//	@Override
//	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		return bean;
//	}
//	//动态代理
//	@Override
//	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//
//		Field[] fields = bean.getClass().getDeclaredFields();
//		for(Field f: fields){
//			if(f.isAnnotationPresent(Reference.class)){
//				Reference remoteInvoke = f.getAnnotation(Reference.class);
//				f.setAccessible(true);
//				Enhancer enhancer = new Enhancer();
//				enhancer.setInterfaces(new Class[]{f.getType()});
//				enhancer.setCallback(new MethodInterceptor() {
//					@Override
//					public Object intercept(Object instance, Method method, Object[] args, MethodProxy poxy) throws Throwable {
//
//						RpcRequest rpcRequest = new RpcRequest();
//						rpcRequest.setInterfaceName(method.getDeclaringClass().getName());
//						rpcRequest.setMethodName(method.getName());
//						rpcRequest.setParameters(args);
//						rpcRequest.setParameterTypes(method.getParameterTypes());
//						rpcRequest.setRequestId(UUID.randomUUID().toString());
//
//						// 长链(5秒没有连接设置超时)
////						RpcResponse resp = Client.getInstance().send(rpcRequest);
//
//						// 长链(无设置超时断开)
//						RpcResponse resp = NettyClient.getInstance().send(rpcRequest);
//						if (resp == null){
//							return null;
//						}
//
//						if (resp.getException() != null){
//							throw resp.getException();
//						}
//
//						Object result = resp.getResult();
//						try{
//							result = JSON.parseObject(String.valueOf(resp.getResult()), method.getReturnType());
//						}catch (Exception e){
//
//						}
//						return result;
//					}
//				});
//
//				try {
//					f.set(bean, enhancer.create());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return bean;
//	}
//
//
//}
