# quasar 协程
> PS:目前quasar使用是字节码增强的技术，需要配置agent才可以。

```
Quasar fiber 依赖 java instrumentation 修改你的代码，可以在运行时通过 java Agent 实现，也可以在编译时使用 ant task实现。

通过 java agent 很简单，在程序启动的时候将下面的指令加入到命令行，注意把 path-to-quasar-jar.jar 替换成你实际的 quasar java 的地址：
```

## demo
- [https://github.com/tigerMoon/spring-quasar-demo](https://github.com/tigerMoon/spring-quasar-demo)
- [Java 中的协程库 - Quasar](https://www.cnblogs.com/jmcui/archive/2020/03/25/12511623.html)
- [Java Maven 协程 Quasar框架Demo](https://blog.csdn.net/HumorChen99/article/details/113558849)

## 参考
- [Java之协程（quasar）](https://www.cnblogs.com/ll409546297/p/10945119.html)
- [次时代Java编程（一）：Java里的协程](https://blog.csdn.net/qiansg123/article/details/80123051)
- [java 协程 quasar 从原理到代码应用](https://blog.csdn.net/guzhangyu12345/article/details/84666423)
    ```
  但是同一个Fiber下，非嵌套的阻塞代码块(suspendable的代码)，quassar却不能帮我们做好调度，因为它们都只是某一条执行路径，
  一旦程序进入等待，后面suspendable的方法不会提交给scheduler；
  此时就需要我们在里面再将这些可以"并行"的任务（suspendable的代码）放入不同的Fiber并start，
  让多个不依赖的任务“并行”运行，可重入地生产以及消费数据。
  ```
- []()