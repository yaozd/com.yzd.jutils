学习Spring Boot：（十五）使用Lombok来优雅的编码
https://www.cnblogs.com/qnight/p/8997493.html
前言
Lombok 是一种 Java™ 实用工具，可用来帮助开发人员消除 Java 的冗长，尤其是对于简单的 Java 对象（POJO）。它通过注解实现这一目的。

正文
添加依赖
在 pom.xml 文件中添加相关依赖：

<lombok.version>1.16.20</lombok.version>

<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
安装插件
由于 Lombok 采取的注解形式的，在编译后，自动生成相应的方法，为了不让 ide 疯了，需要下载插件了支持它。
以 idea 为例：查找插件 lombok plugin 安装即可。

用我的 User 实体类为例（set,get,toString 方法），

@Getter
@Setter
@ToString
public class SysUserEntity implements Serializable
在按快捷键 Ctrl + F12，可以查找到set,get,toString 方法。

注解
写点常用的，其余的 api 的打开 Jar 包一目了然

@Getter
@Setter
@ToString
@EqualsAndHashCode
构造函数
@AllArgsConstructor
会生成一个包含所有变量，同时如果变量使用了NotNull annotation ， 会进行是否为空的校验，
全部参数的构造函数的自动生成，该注解的作用域也是只有在实体类上，参数的顺序与属性定义的顺序一致。

@NoArgsConstructor
无参构造函数

@RequiredArgsConstructor
会生成一个包含常量（final），和标识了@NotNull的变量 的构造方法。

怎么使用
它们都有三个参数可以设置
1. String staticName() default "";

如果设置了它，将原来的构造方法的访问修饰符将会变成 私有的，而外添加一个静态构造方法，参数相同，名字是设置的字符串的名字，访问修饰符为公有的。

AnyAnnotation[] onConstructor() default {};
在构造方法上添加注解。使用方法@RequiredArgsConstructor(onConstructor=@__({@AnnotationsGoHere}))}

例如我们在 Spring 项目中需要注入多个值，写很多个 @Autowired 很麻烦，就可以使用这种方式：

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements IUserService {
   private final IUserRepository userRepository;
   private final IOrderRepository orderRepository;
   ………………
AccessLevel access() default lombok.AccessLevel.PUBLIC;
构造函数访问修饰符；

@NoArgsConstructor无参构造函数中还有个注解 boolean force() default false;
作者的注释是 If {@code true}, initializes all final fields to 0 / null / false. Otherwise, a compile time error occurs.

设置为 true 的时候，初始化所有的参数为默认值，否则编译错误。

@Data
我自己尝试了下，我们使用 @Data 注解就可以有下面几个注解的功能： @ToString、@Getter、@Setter、@EqualsAndHashCode、@NoArgsConstructor 。

注意的是，同时使用@Data 和 @AllArgsConstructor 后 ，默认的无参构造函数失效，如果需要它，要重新设置 @NoArgsConstructor

@Slf4j
//类上面注解了，直接调用 log 即可：
log.info(xxxx);
@Log
使用的是 java.util.logging.Logger ，直接使用 变量 log。

@Builder
bulder 模式构建对象。

@Cleanup
@Cleanup
InputStream in = new FileInputStream(args[0]);
@Cleanup
OutputStream out = new FileOutputStream(args[1]);
自动化关闭流，相当于 jdk1.7 种的 try with resource

val
类型推导。

 val example = new ArrayList<String>();
 example.add("Hello, World!");
对应的转换后代码就是：

 val example = new ArrayList<String>();
 example.add("Hello, World!");
@NonNull
public NonNullExample(@NonNull Person person) {
    this.name = person.getName();
 }
转换后就是：

public NonNullExample(@NonNull Person person) {
    if (person == null) {
      throw new NullPointerException("person");
    }
    this.name = person.getName();
 }
@SneakyThrows
翻译就是暗中抛出异常

当我们需要抛出异常，在当前方法上调用，不用显示的在方法名后面写 throw

@SneakyThrows(Exception.class)
@Synchronized
方法中所有的代码都加入到一个代码块中，默认静态方法使用的是全局锁，普通方法使用的是对象锁，当然也可以指定锁的对象。

private final Object lock = new Object();
@Synchronized("lock")
public void foo() {
    // Do something
}
个人认为这样的阅读起来比较麻烦，实际开发中往往将 synchronized 颗粒化到代码块中。

我的博客http://blog.wuwii.com

