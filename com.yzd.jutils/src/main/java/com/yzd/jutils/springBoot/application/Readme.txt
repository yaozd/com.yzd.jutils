##
在Spring Boot中多个模块使用对应的配置文件
https://blog.csdn.net/cw_hello1/article/details/79639448
第一种方法：（使用属性配置文件，即properties结尾的配置文件）

我们使用属性文件（.properties）后缀结尾的配置文件。

1.    我们在各个模块的resource下创建我们的配置文件。例如我们在redis模块下的resource放置一个redis.properties文件。



        这个文件的内容是：

                spring.redis.open=true
                spring.redis.port=6379
                spring.redis.database=0
                spring.redis.host=localhost
                spring.redis.password=
                spring.redis.timeout=6000
                spring.redis.jedis.pool.max-active=1000
                spring.redis.jedis.pool.max-wait=-1
                spring.redis.jedis.pool.max-idle=10
                spring.redis.jedis.pool.min-idle=5

2.    我们读取相应的配置文件使用@PropertySource注解。

    使用@PropertySource(value = "classpath:redis.properties")注解，并配合@Value注解@Value("${spring.redis.open}"),完成参数的注入，一定要有@Configuration注解，不然不起作用。

注意：classpath:redis,properties中【冒号】之后没有空格，不然找不到文件

注意：我们将资源文件放入到resource目录下的话，就会在运行项目的放入到相应的classes目录下，



注意：这种方法，我们不需要各个模块之间的配合，可以很容易的将各个模块去掉。而不影响其他各个模块的运行



第二种方法：使用yml文件的配置文件（以yml后缀结尾）

现在我们经常使用yml文件作为我们的配置文件。如何让各个模块使用自己的yml文件呢？

注意：@PropertySource注解只支持properties文件。而不支持yml文件。

我也是尝试了好久，所以我们不能直接使用：@PropertySource(value="redis.yml")这样我们会读取不到数据，并报错，说不能解析变量占位符${spring.redis.open}。

1.    使用yml的配置文件，名称一定以application-开头，例如：application-dev.yml，application-pro.yml，application-redis.yml文件等你等。

        文件的内容：

            spring:
                redis:
                    database: 0
                    host: localhost
                    jedis:
                        pool:
                            max-active: 1000
                            max-idle: 10
                            max-wait: -1
                            min-idle: 5
                    open: true
                    password: ''
                    port: 6379

                    timeout: 1000



2.    在我们的主模块的application.yml中加入下面的一句话：

            spring:
                  profiles:

                        active: dev,redis（这里只写application-之后的名称。多个之间用逗号分隔）



就可以了。

注意：这个方法我们必须在主模块的application.yml中加入一句话。才可以。不然找不到这个文件。

这里有一个压缩包：https://download.csdn.net/download/cw_hello1/10301673