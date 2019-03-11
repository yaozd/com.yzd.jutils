# 1个接口对应多个实现类
> 示例--服务仓库
```
@Service
public class TempServiceRepository {

    @Qualifier("T1TempServiceImp")
    @Autowired
    ITempServiceInf t1TempServiceImp;
    @Qualifier("T2TempServiceImp")
    @Autowired
    ITempServiceInf t2TempServiceImp;

    public ITempServiceInf getITempServiceInf(Integer code){
        switch (code){
            case 1:return t1TempServiceImp;
            case 2:return t2TempServiceImp;
        }
        throw new IllegalArgumentException("没有找到对应ITempServiceInf实现;CODE="+code);
    }

}
```
> [@Component, @Repository, @Service的区别](https://blog.csdn.net/fansili/article/details/78740877)
```
@Component就是跟<bean>一样，可以托管到Spring容器进行管理。

@Service, @Controller , @Repository = {@Component + 一些特定的功能}。这个就意味着这些注解在部分功能上是一样的。
---------------------
@Component, @Service, @Controller, @Repository是spring注解，注解后可以被spring框架所扫描并注入到spring容器来进行管理 
@Component是通用注解，其他三个注解是这个注解的拓展，并且具有了特定的功能 
@Repository注解在持久层中，具有将数据库操作抛出的原生异常翻译转化为spring的持久层异常的功能。 
@Controller层是spring-mvc的注解，具有将请求进行转发，重定向的功能。 
@Service层是业务逻辑层注解，这个注解只是标注该类处于业务逻辑层。 
用这些注解对应用进行分层之后，就能将请求处理，义务逻辑处理，数据库操作处理分离出来，为代码解耦，也方便了以后项目的维护和开发。
```
> [用@Primary 或者@Qualifier消除@Autowired引起的歧义现象](https://www.cnblogs.com/1540340840qls/p/6970692.html)
```
@Primary和@Qualifier这两个注解的意思：
@Primary的意思是在众多相同的bean中，优先使用用@Primary注解的bean.
而@Qualifier这个注解则指定某个bean有没有资格进行注入。
```
> [Spring Boot系列四 Spring @Value 属性注入使用总结一](https://blog.csdn.net/hry2015/article/details/72353994/)
```
 @Value("${influxdb.url}")
 public void setUrl(String url) {
     InfluxDBUtils.url = url;
 }

 @Value("${influxdb.username}")
 public void setUsername(String username) {
     InfluxDBUtils.username = username;
 }
 ---------------------
@Value("normal")
private String normal; // 注入普通字符串

@Value("#{systemProperties['os.name']}")
private String systemPropertiesName; // 注入操作系统属性

@Value("#{ T(java.lang.Math).random() * 100.0 }")
private double randomNumber; //注入表达式结果

@Value("#{beanInject.another}")
private String fromAnotherBean; // 注入其他Bean属性：注入beanInject对象的属性another，类具体定义见下面

@Value("classpath:com/hry/spring/configinject/config.txt")
private Resource resourceFile; // 注入文件资源

@Value("http://www.baidu.com")
private Resource testUrl; // 注入URL资源
 --------------------- 
```
> [springBoot注解大全](https://www.cnblogs.com/tanwei81/p/6814022.html)
```
Spring自带依赖注入注解

1 @Required：依赖检查；

2 @Autowired：自动装配2 自动装配，用于替代基于XML配置的自动装配 基于@Autowired的自动装配，默认是根据类型注入，可以用于构造器、字段、方法注入

3 @Value：注入SpEL表达式 用于注入SpEL表达式，可以放置在字段方法或参数上
@Value(value = "SpEL表达式")  
@Value(value = "#{message}")  
4 @Qualifier：限定描述符，用于细粒度选择候选者
@Qualifier限定描述符除了能根据名字进行注入，但能进行更细粒度的控制如何选择候选者
@Qualifier(value = "限定标识符") 
-------------------------------------
一、注解(annotations)列表 
@SpringBootApplication：包含了@ComponentScan、@Configuration和@EnableAutoConfiguration注解。其中@ComponentScan让spring Boot扫描到Configuration类并把它加入到程序上下文。

@Configuration 等同于spring的XML配置文件；使用Java代码可以检查类型安全。

@EnableAutoConfiguration 自动配置。

@ComponentScan 组件扫描，可自动发现和装配一些Bean。

@Component可配合CommandLineRunner使用，在程序启动后执行一些基础任务。

@RestController注解是@Controller和@ResponseBody的合集,表示这是个控制器bean,并且是将函数的返回值直 接填入HTTP响应体中,是REST风格的控制器。

@Autowired自动导入。

@PathVariable获取参数。

@JsonBackReference解决嵌套外链问题。

@RepositoryRestResourcepublic配合spring-boot-starter-data-rest使用。
```
> []()
