##　mybatis拦截器
- 动态修改sql
    - [Mybatis拦截器修改sql语句](https://blog.csdn.net/qq_22200097/article/details/82942908) -Github源码推荐参考
    ```
    当我们需要改变sql的时候，显然我们要在预编译SQL(prepare方法前加入修改的逻辑)。
    
    当我们需要修改参数的时候我们可以在调用parameterize方法前修改逻辑。或者使用ParameterHandler来改造设置参数。
    
    我们需要控制组装结果集的时候，也可以在query方法前后加入逻辑，或者使用ResultHandler来改造组装结果。
    
    分页插件可以拦截Executor的方法进行。
    
    Github源码地址：https://github.com/UVliuwei/mybatis-interceptor
    ```
    - []()
- 统一sql+增加数据权限
    - [Mybatis拦截实现查询sql统一处理](https://blog.csdn.net/e_anjing/article/details/79102693)
    - []()