## 监控神器-普罗米修斯Prometheus
- [监控神器-普罗米修斯Prometheus的安装](https://blog.csdn.net/csolo/article/details/82460539)
- [监控神器普罗米修斯Prometheus安装配置](https://blog.csdn.net/ywd1992/article/details/85989259)

##　SpringBoot 2.0+prometheus+Grafana
- [https://github.com/yaozd/com.yzd.prometheus.demo](https://github.com/yaozd/com.yzd.prometheus.demo) -首要参考byArvin
- SpringBoot 2.0+prometheus+Grafana
    - [SpringBoot 2.0+prometheus+Grafana](https://blog.csdn.net/str0708/article/details/88405163)
    - [spring boot2.x暴露监控endpoint并配置prometheus及grafana对多个targets进行监控](https://blog.csdn.net/u013905744/article/details/97231735)
    - [springboot2.X整合prometheus监控](https://blog.csdn.net/qq_33430322/article/details/89488249)
    - []()
    - []()
    
## 自定义prometheus监控
- 自定义prometheus监控
    - [自定义Metrics：让Prometheus监控你的应用程序](https://blog.csdn.net/hxpjava1/article/details/80406222)
    - [SpringBoot自定义prometheus监控](https://blog.csdn.net/u010588262/article/details/83094560)
    - []()
    - []()
    
## Prometheus Monitoring for Java Developers 推荐
- [https://github.com/fstab/prometheus-for-java-developers](https://github.com/fstab/prometheus-for-java-developers)
- [https://github.com/yaozd/prometheus-for-java-developers](https://github.com/yaozd/prometheus-for-java-developers)
```
01-hello-world
02a-direct-instrumentation
02b-direct-instrumentation-with-spring-boot-endpoint
03a-spring-boot-actuator-enabled
03b-spring-boot-actuator-custom-metric
03c-spring-boot-actuator-prometheus-bridge
04a-jmx-enabled
04b-jmx-custom-metric
04c-jmx-remote-prometheus-bridge
04d-jmx-agent-prometheus-bridge
05a-dropwizard-enabled
05b-dropwizard-prometheus-bridge
2.
eg:
@Controller
@SpringBootApplication
@EnablePrometheusEndpoint
public class HelloWorldController {

    private final Counter promRequestsTotal = Counter.build()
            .name("requests_total")
            .help("Total number of requests.")
            .register();
    private final CounterService springRequestsTotal;

    public HelloWorldController(@Autowired CounterService sprintRequestsTotal) {
        this.springRequestsTotal = sprintRequestsTotal;
    }

    @RequestMapping(path = "/hello-world")
    public @ResponseBody String sayHello() {
        promRequestsTotal.inc();
        springRequestsTotal.increment("counter.calls.promdemo.hello_world");
        return "hello, world";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldController.class, args);
    }
}
```
