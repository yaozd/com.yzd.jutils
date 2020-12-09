> 问题三：[解决retrofit OKhttp创建大量对外连接时内存溢出](https://blog.csdn.net/tianyaleixiaowu/article/details/78811488)
```
通过各方查证，有的说，请求header里的Connection，写的是keep alive，导致了长连接，所以我把构建retrofit的header的地方改成了Connection为close，然而没什么卵用。依旧是上面的问题，很快线程数超过就崩溃了。 
后来开始调查OkHttpClient的ConnectionPool，这个就是OkHttp网络请求的线程池，在OkHttpClient源码中可以看到

public OkHttpClient.Builder connectionPool(ConnectionPool connectionPool) {
            if (connectionPool == null) {
                throw new NullPointerException("connectionPool == null");
            } else {
                this.connectionPool = connectionPool;
                return this;
            }
        }

在OkHttpClient的源码中，默认的构造方法里可以看到默认最大线程空闲数是5，keepAlive时间为5分钟。也就是发起一次网络连接后，5分钟内不会断开连接。
那么问题就出在这里了，我在短时间内发起了大量网络连接，每个是一个线程，而且每个都默认保存5分钟，很快线程数就超标了。 
考虑到我的每次请求都是一次性的，所以我修改了ConnectionPool的keepAliveDuration时间，让每次连接1秒后就关闭。
之后再次运行程序，发现OK了，线程数最大也没超过200，程序也没再抛出过outofmemery异常。
----------------------------------------------------------------
public class OkHttpClientUtil {

    public static OkHttpClient.Builder generateBuilder(){
        return new OkHttpClient().newBuilder()
                .connectionPool(new ConnectionPool(5,1,TimeUnit.SECONDS));
    }
}
---------------------------------------------------------------- 
--------------------- 
```

- [基于Okhttp3做的封装+性能优化](https://blog.csdn.net/u012552275/article/details/104717223/)