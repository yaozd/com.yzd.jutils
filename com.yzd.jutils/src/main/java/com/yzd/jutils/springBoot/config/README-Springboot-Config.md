# spring boot config
- [在使用 Spring Boot 的过程中，你可能不太知道的点？](https://blog.csdn.net/qq_35246620/article/details/106910302)
```
Spring Boot 能从多种属性源获得属性，以下属性源按优先级从高到低排序，任何在高优先级属性源里设置的属性都会覆盖低优先级的相同属性，包括：
命令行参数；
java:comp/env里的 JNDI 属性；
JVM 系统属性；
操作系统环境变量；
随机生成的带random.*前缀的属性（在设置其他属性时，可以引用它们，比如${random.long}）；
应用程序以外的application.properties或者application.yml文件；
打包在应用程序内的application.properties或者application.yml文件；
通过@PropertySource标注的属性源；
默认属性。
application.properties和application.yml文件能够放在以下四个位置，优先级从高到低排序：
外置，相对于应用程序运行目录的/config子目录里；
外置，在应用程序运行的目录里；
内置，在config包内；
内置，在Classpath根目录。
如果你在同一优先级的位置同时有application.properties和application.yml文件时，那么application.yml里的属性会覆盖application.properties里的属性。
```