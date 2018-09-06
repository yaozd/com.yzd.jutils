### 目标
- 读取Prometheus采集的数据，然后把数据放在influxdb中，再通过grafana进行数据展示

### Prometheus数据结果提取
```
1.
 过滤注释信息
 #[^\n\r]+
 # TYPE java_lang_MemoryPool_PeakUsage_committed untyped
 2.
 提取指标信息
 [\d\.]+$
 Tomcat_ProtocolHandler_maxExtensionSize{port="2001",} 8192.0
 3.
 java进行正则数据提取
 
```