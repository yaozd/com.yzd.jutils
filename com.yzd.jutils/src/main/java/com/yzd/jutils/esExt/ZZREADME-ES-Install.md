## ES Install
- [日志收集系统搭建 File Beat + Logstash](https://jingyan.baidu.com/article/acf728fdf42532b8e410a34d.html)
- 官方参考：
    ```
    input 配置参考：https://www.elastic.co/guide/en/logstash/current/input-plugins.html
    output 配置参考：https://www.elastic.co/guide/en/logstash/current/output-plugins.html
    filter 配置参考：https://www.elastic.co/guide/en/logstash/6.4/filter-plugins.html
    Logstash Reference：https://www.elastic.co/guide/en/logstash/6.4/index.html
    ```
- [logstash-best-practice-cn](https://doc.yonyoucloud.com/doc/logstash-best-practice-cn/get_start/hello_world.html)

## Logstash解析嵌套Json
- [Logstash解析嵌套Json](https://www.jianshu.com/p/de06284e1484) -首要参考
- [logstash解析嵌套json格式数据](https://www.cnblogs.com/jcici/p/11750690.html) -首要参考
- [Logstash处理json格式日志文件的三种方法](https://blog.csdn.net/zmx729618/article/details/80885179)
- []()

- Filebeat Install
    - [Filebeat安装及使用](https://blog.csdn.net/dwyane__wade/article/details/80169051)
    - filebeat.yml
    ```
    filebeat:
      prospectors:
      -
          paths:
            - /usr/local/elk/log/*.log
          fields:
             service: project_name
    output:
      #elasticsearch:
      #   hosts: ["localhost:9200"]
       logstash:
        hosts: ["172.30.1.45:5044"]
    ```
- Logstash Install
    - 启动logstash：
      > logstash -f logstash_filebeat.conf
    - logstash_filebeat.conf
    ```
    input {
      beats {
        port => 5044
      }
    }
    output {
            stdout { codec => rubydebug }
    }
    ```

##　统一日志字段
```
idc
host.name
ip
startTime
responseCost
responseStatus
log.level
traceId

```