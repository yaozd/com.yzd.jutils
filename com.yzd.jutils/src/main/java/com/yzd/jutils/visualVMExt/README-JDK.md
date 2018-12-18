
### -执行jar 包时，设置执行内存

```
java -jar -Xms258m -Xmx258m  back-module.jar --spring.profiles.active=prod --server.port=8004
```

```
java -jar -Xms258m -Xmx258m -XX:PermSize=512M -XX:MaxPermSize=512m back-module.jar --spring.profiles.active=prod --server.port=8004
注：JDK8不在支持PermSize与MaxPermSize参数
```