# minio

## 官方
- [https://docs.min.io/docs/java-client-api-reference.html](https://docs.min.io/docs/java-client-api-reference.html) 
- [https://docs.min.io/docs/minio-disk-cache-guide.html](https://docs.min.io/docs/minio-disk-cache-guide.html)

## Mini调研
- [MinIO学习笔记](https://blog.csdn.net/shijinghan1126/article/details/107041001)
```
MinIO压测
可采用 cosBench 工具做压测，cosBench是专门对对象存储做压测的工具。

在12个节点，每个节点12个盘，混合读写64KB。
结果是：
1、读请求：4千QPS，响应的平均延迟是35毫秒；
2、写请求：900 QPS时，平均响应时间是45毫秒。
```

## minio java
- [https://github.com/trotyzyq/miniOss](https://github.com/trotyzyq/miniOss)
- [https://github.com/minio/minio-java](https://github.com/minio/minio-java)
- [MinIO Java Client API](https://blog.csdn.net/DMW2016/article/details/102903447)
- []()

## 演示
- [Java使用MINIO作为对象存储测试](https://blog.csdn.net/yuxiangdeming/article/details/109289683) -Spring boot 推荐参考byArvin
- [java使用MinIO，简单快速上手](https://blog.csdn.net/qq_41569151/article/details/103836724)


## minio-jenkins
- [minio-jenkins](https://github.com/minio/minio-jenkins)

## Minio benchmarks
- [Minio benchmarks with COSBench](https://www.colabug.com/2018/0117/2217189/)
- [https://github.com/minio/benchmarks](https://github.com/minio/benchmarks)
- [https://blog.min.io/hdfsbenchmark/](https://blog.min.io/hdfsbenchmark/)