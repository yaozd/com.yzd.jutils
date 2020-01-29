- 首要参考byArvin
    - [Spring StateMachine详解](https://blog.csdn.net/u012129558/article/details/90519577) 有例子可参考-推荐参考byArvin
    - [Spring Statemachine 概念及应用](https://www.jianshu.com/p/9ee887e045dd) 理论
    - [StateMachine 状态机机制深入解析](https://www.jb51.net/article/168174.htm) 例子比较简单

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
- []()
- 特别推荐：
- [easy-statemachine](https://github.com/ycj007/easy-statemachine) -轻量级状态机工作流框架-推荐参考byArvin
    > PS:源码备份：软件开发-JAVA>J-Z-状态机>easy-statemachine-master.zip
- []()