## [为什么需要状态机](https://segmentfault.com/a/1190000009906317)
```
有限状态机是一种对象行为建模工具，
适用对象有一个明确并且复杂的生命流（一般而言三个以上状态），
并且在状态变迁存在不同的触发条件以及处理行为。
从我个人的使用经验上，
使用状态机来管理对象生命流的好处更多体现在代码的可维护性、可测试性上，明确的状态条件、原子的响应动作、事件驱动迁移目标状态，
对于流程复杂易变的业务场景能大大减轻维护和测试的难度。
```

## statemachine的选型分析
- [状态机选型简记](http://childe.net.cn/2018/04/28/%E7%8A%B6%E6%80%81%E6%9C%BA%E9%80%89%E5%9E%8B%E7%AE%80%E8%AE%B0/)
- [状态机引擎选型](https://segmentfault.com/a/1190000009906317) -首要参考byArvin
- stateless4j   (PS:更加轻量级)
- spring-statemachine   (PS:重量级)
    ```
    StateMachine 状态机实例，
    spring statemachine支持单例、工厂模式两种方式创建，
    每个statemachine有一个独有的machineId用于标识machine实例；
    需要注意的是statemachine实例内部存储了当前状态机等上下文相关的属性，因此这个实例不能够被多线程共享；
    ```
- squirrel-foundation   (PS:更加轻量级) -暂时推荐此项目byArvin
    - [squirrel-foundation状态机的使用细节](https://segmentfault.com/a/1190000009906469)
    - [squirrel-foundation java状态机](https://www.jianshu.com/p/b326bcc2c5bc)
    - [squirrel-foundation有限状态机快速入门](https://zhuanlan.zhihu.com/p/91482662)

## spring statemachine
- [spring statemachine 多个状态机实践](https://blog.csdn.net/chenpei1990/article/details/81636897) -解决单实例不能够被多线程共享-解决线程安全问题-使用工厂模式（需要进行代码测试验证byArvin）
- [Spring Statemachine Using Distributed States](https://blog.csdn.net/xichenguan/article/details/89978165) -分布式状态机


## 可视化状态拓扑
- [使用graphviz生成状态拓扑图](https://segmentfault.com/a/1190000009906469)
- []()
- []()