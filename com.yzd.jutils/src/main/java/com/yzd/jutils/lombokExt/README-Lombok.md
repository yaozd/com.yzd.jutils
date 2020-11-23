## lombok
- [lombok的@Accessors注解3个属性说明](https://www.jianshu.com/p/784732369d46)
- [好用到爆的 Java 技巧](https://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651488579&idx=1&sn=a48d8fd07389c765dc56756f53dab963)
- []()

## 问题
- [Lombok使用@Data注解当循环依赖时调用hashcode导致StackOverflowError栈溢出的问题](https://blog.csdn.net/zyxzj/article/details/106563494)

## 注意点：
- @Data ,@Setter ,@Getter（@Data重写了hashCode与equals）
```
lombok 注解：
   @Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
   @Setter：注解在属性上:为属性提供 setting 方法,       注解再类上表示当前类中所有属性都生成setter方法
   @Getter：注解在属性上：为属性提供 getting 方法， 注解再类上表示当前类中所有属性都生成getter方法
   @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
   @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
   @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
```