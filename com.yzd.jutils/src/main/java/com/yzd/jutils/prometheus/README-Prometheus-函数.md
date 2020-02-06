## Prometheus 函数
- [Prometheus 函数](https://www.cnblogs.com/wayne-liu/p/9273492.html)

## 示例
- [prometheus 监控的部分常用promsql的写法](https://blog.csdn.net/sunyuhua_keyboard/article/details/81302165)
- prometheus 统计主机个数
    ```
    统计主机在线个数：可以通过heartmap 或 graph 展示
    //通用方法
    count(jvm_threads_current{app="$app",instance=~"$instance"}-jvm_threads_current{app="$app",instance=~"$instance"}+1)
    (jvm_threads_current{app="$app",instance=~"$instance"}-jvm_threads_current{app="$app",instance=~"$instance"}+1)
    //特定方法：jvm_info的值始终等于1
    count(jvm_info{app="$app",instance=~"$instance"})
    (jvm_info{app="$app",instance=~"$instance"})
    ```

- prometheus 集群监控-关注点
    1. 统计主机在线个数
    2. 主机连接数
    3. 主机流量
    4. 请求QPS
    5. 请求状态-成功
    6. 请求状态-失败
    7. 请求状态分布
    8. 线程相关状态
    9. 主机内存使用情况
    10. JVM-GC 情况
    11. 今日与昨日对比
        1. 请求QPS
        2. 失败请求数等