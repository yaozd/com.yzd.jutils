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
> []()
> []()
