## [为什么需要状态机](https://segmentfault.com/a/1190000009906317)
```
有限状态机是一种对象行为建模工具，
适用对象有一个明确并且复杂的生命流（一般而言三个以上状态），
并且在状态变迁存在不同的触发条件以及处理行为。
从我个人的使用经验上，
使用状态机来管理对象生命流的好处更多体现在代码的可维护性、可测试性上，明确的状态条件、原子的响应动作、事件驱动迁移目标状态，
对于流程复杂易变的业务场景能大大减轻维护和测试的难度。
```

- 思路：
    ```
    为了将状态变更的操作都统一管理起来，我们会考虑在项目中引入状态机，
    这样其他的业务模块就和状态转移模块隔离开来了，
    其他业务模块也不会纠结于当前的状态是什么，
    应该做什么操作。
    在应用状态机实现业务需求时，
    关键是业务状态的分析，
    只要状态机设计得没问题，
    具体的实现可以选择用Spring StateMachine，也可以自己去实现一个状态机。
    //
    使用Spring StateMachine的好处在于自己无需关心状态机的实现细节，只需要关心业务有什么状态、它们之间的转移规则是什么、每个状态转移后真正要进行的业务操作。
    ```
- []()
- [Spring Boot 2.x实战之StateMachine](https://www.cnblogs.com/javaadu/p/11832581.html) -含github代码
- [实用技术篇 - StateMachine 状态机机制](https://www.cnblogs.com/powerwu/articles/10559105.html)
    - [https://github.com/lianggzone/springboot-action/tree/master/springboot-action-statemachine](https://github.com/lianggzone/springboot-action/tree/master/springboot-action-statemachine)
- [Spring Boot使用Spring StateMachine框架实现状态机](https://blog.csdn.net/chengqiuming/article/details/83713446)
- [https://github.com/kmyhy/statemachine](https://github.com/kmyhy/statemachine) -含github代码-完整例子
- [https://github.com/cjqCN/spring-statemachine-learning](https://github.com/cjqCN/spring-statemachine-learning) -github代码
- [https://github.com/antkorwin/statemachine](https://github.com/antkorwin/statemachine) -multiple state machine in one project

- 特别推荐：
- [easy-statemachine](https://github.com/ycj007/easy-statemachine) -轻量级状态机工作流框架-推荐参考byArvin
    > PS:源码备份：软件开发-JAVA>J-Z-状态机>easy-statemachine-master.zip
- []()

## 持久化
- []()
```
模块
spring-statemachine-core
spring-statemachine-recipes-common
spring-statemachine-kryo
spring-statemachine-data-common
spring-statemachine-data-jpa
spring-statemachine-data-redis
spring-statemachine-data-mongodb
spring-statemachine-zookeeper
spring-statemachine-test
spring-statemachine-cluster
spring-statemachine-uml
spring-statemachine-autoconfigure
spring-statemachine-bom
spring-statemachine-starter
```
