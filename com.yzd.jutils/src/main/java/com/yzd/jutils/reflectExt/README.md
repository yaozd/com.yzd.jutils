## 参考
- 高效的反射工具包
    - [Java高性能反射工具包ReflectASM](https://www.cnblogs.com/juetoushan/p/7724793.html)
- [java获取当前类名和方法名](https://www.cnblogs.com/wanshiming/p/8241700.html)
- [Java获取类中的所有方法](https://blog.csdn.net/u011983531/article/details/80248945)
- [java 非常好用的反射框架Reflections](https://blog.csdn.net/chenwiehuang/article/details/83114641)
```

// 初始化工具类
Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages(basePackages).addScanners(new SubTypesScanner()).addScanners(new FieldAnnotationsScanner()));
 
// 获取某个包下类型注解对应的类
Set<Class<?>> typeClass = reflections.getTypesAnnotatedWith(RpcInterface.class, true);
 
// 获取子类
Set<Class<? extends SomeType>> subTypes = reflections.getSubTypesOf(SomeType.class);
 
// 获取注解对应的方法
Set<Method> resources =reflections.getMethodsAnnotatedWith(SomeAnnotation.class);
 
// 获取注解对应的字段
Set<Field> ids = reflections.getFieldsAnnotatedWith(javax.persistence.Id.class);
 
// 获取特定参数对应的方法
Set<Method> someMethods = reflections.getMethodsMatchParams(long.class, int.class);
 
Set<Method> voidMethods = reflections.getMethodsReturn(void.class);
 
Set<Method> pathParamMethods =reflections.getMethodsWithAnyParamAnnotated(PathParam.class);
 
// 获取资源文件
Set<String> properties = reflections.getResources(Pattern.compile(".*\\.properties"));

```
```
 <dependency>
   <groupId>org.reflections</groupId>
   <artifactId>reflections</artifactId>
   <version>0.9.11</version>
   <scope>test</scope>
 </dependency>
 @Test
    public void findSharable() {
        Set<Class<?>> set = new Reflections("io.netty.handler").getTypesAnnotatedWith(ChannelHandler.Sharable.class);
        System.out.println("sharable class size =>  " + set.size());
        for (Class<?> clazz : set) {
            System.out.println("sharable class => " + clazz.getName());
        }
    }
```