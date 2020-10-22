package com.yzd.jutils.rpcExt.t1;




import com.yzd.jutils.rpcExt.t1.bean.RpcRequest;
import com.yzd.jutils.rpcExt.t1.bean.RpcResponse;

import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 请求挂起
 * 锁的方式
 */
public class AsyncFuture {
	
	public final static ConcurrentHashMap<String,AsyncFuture> FUTURE_MAP = new ConcurrentHashMap<>();
    final Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private RpcResponse response;

	private long timeout=2*60*1000L;
	private long startTime=System.currentTimeMillis();

	public long getTimeout() {
		return timeout;
	}

	public long getStartTime() {
		return startTime;
	}


	public AsyncFuture(RpcRequest request) {
		FUTURE_MAP.put(request.getRequestId(), this);
	}

	// 获取响应结果，有结果则返回
	public RpcResponse get() {
		lock.lock();
		try{
		   while(!done()){
			   condition.await();
		   }
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return this.response;
	}

	// 获取响应结果，有结果则返回 或者 超过指定时间就返回
	public RpcResponse get(long timeout, TimeUnit timeUnit) {
		lock.lock();
		try{
			while(!done()){
				condition.await(timeUnit.toSeconds(timeout), TimeUnit.SECONDS);
				if((System.currentTimeMillis()-startTime)>timeUnit.toSeconds(timeout)){
					break;
				}
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return this.response;
	}

	public static void receive(RpcResponse response){
		AsyncFuture df = FUTURE_MAP.get(response.getRequestId());
		if(df !=null){
			Lock lock =df.lock;
			lock.lock();
			try{
				df.setResponse(response);
				df.condition.signal();
				FUTURE_MAP.remove(df);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
		}
	}
	
	public void setResponse(RpcResponse response) {
		this.response = response;
	}

	private boolean done() {
		if(this.response!=null){
			return true;
		}
		return false;
	}

	static class FutureThread extends Thread{
		@Override
		public void run() {
			Set<String> ids = FUTURE_MAP.keySet();
			for(String id : ids){
				AsyncFuture df = FUTURE_MAP.get(id);
				if(df==null){
					FUTURE_MAP.remove(df);
				}else{
					// 超时的从map中删除
					if(System.currentTimeMillis()-df.getStartTime() > df.getTimeout()){
						RpcResponse resp = new RpcResponse();
						resp.setRequestId(id);
						resp.setException(new TimeoutException());
						receive(resp);
					}
				}
			}

		}

	}

	static{
		FutureThread futureThread = new FutureThread();
		futureThread.setDaemon(true);
		futureThread.start();
	}

	/**
     * CountDownLatch 的方式
     */
    public static class AsyncFuture1 implements Future {

        public final static ConcurrentHashMap<String,AsyncFuture1> FUTURE_MAP = new ConcurrentHashMap<String,AsyncFuture1>();

        // 因为请求和响应是一一对应的，因此初始化CountDownLatch值为1。
        private CountDownLatch latch = new CountDownLatch(1);

        // 需要响应线程设置的响应结果
        private RpcResponse response;

        // Futrue的请求时间，用于计算Future是否超时
        private long beginTime = System.currentTimeMillis();
        public AsyncFuture1(String requestId) {
            FUTURE_MAP.put(requestId, this);
        }

        public AsyncFuture1() {
        }

        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }
        @Override
        public boolean isCancelled() {
            return false;
        }
        @Override
        public boolean isDone() {
            if (response != null) {
                return true;
            }
            return false;
        }
        // 获取响应结果，直到有结果才返回。
        @Override
        public RpcResponse get() throws InterruptedException {
            latch.await();
            return this.response;
        }
        // 获取响应结果，直到有结果或者超过指定时间就返回。
        @Override
        public RpcResponse get(long timeout, TimeUnit unit) throws InterruptedException {
            if (latch.await(timeout, unit)) {
                return this.response;
            }
            return null;
        }

        public static void receive(RpcResponse response){
            AsyncFuture1 df = FUTURE_MAP.get(response.getRequestId());
            if(df !=null){
                df.setResponse(response);
                FUTURE_MAP.remove(response.getRequestId());
            }
        }

        // 用于设置响应结果，并且做countDown操作，通知请求线程
        public void setResponse(RpcResponse response) {
            this.response = response;
            latch.countDown();
        }
        public long getBeginTime() {
            return beginTime;
        }

        // 定期清理从全局的map中清理 略
    }
}
