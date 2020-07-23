# 请求重试||方法重试算法 
## 退避算法backoff algorithm
- [flume之退避算法backoff algorithm](https://blog.csdn.net/iteye_5443/article/details/82327642)
- [Exponential Backoff with Java 8](https://carlosbecker.com/posts/exponential-backoff-java8/)
- []()

### 说明
```
目前在flume中主要运用了ExponentialBackoff，CappedExponentialBackoff，CumulativeCappedExponentialBackoff三种退避算法。

ExponentialBackOff是个简单的指数退避算法，仅仅让下次的等待时间是上次等待时间的2倍，当重试次数达到最大重试次数时，该任务将不能重试。

CappedExponentialBackoff对ExponentialBackOff算法作了简单的改造，该算法对每次的等待时间做了个限定，即每次的等待时间不超过某个值sleepCap。但该方法没有限定重试次数。

CumulativeCappedExponentialBackoff算法对CappedExponentialBackoff作了些改造，该算法加入了cumulativeCap变量，用来限制重试次数。在第一次backoff的时候设置failTime值为当前时间+cumulativeCap。是否可以重试由当前时间和failTime决定。当前时间小于failTime则表明还可以重试，否则，不能重试。

 

通过对以上的分析，可以得到一个Backoff算法必须提供四个接口（isFailed,backOff,waitUntilRetryOk,reset）。其中，isFailed用来判断是否可以重试，backoff用来设置等待时间，waitUntilRetryOk根据backoff设置的等待时间sleep，以便下次重试。reset的接口是在任务成功后，对backoff算法的一些变量重置。详细可以看ExponentialBackoff等源代码。

```