# flink 关键词
##　Source、Transform、Sink
- [Flink流处理API代码详解，含多种Source、Transform、Sink案例，Flink学习入门](https://blog.csdn.net/weixin_38586230/article/details/106637910)
### Source
> 获取数据

### Transform
> 转换算子
- 函数
    - 自定义实现UDF函数
    ```
    关于UDF，UDTF，UDAF
    UDF：User Defined Function，用户自定义函数，一进一出
    UDAF：User- Defined Aggregation Funcation 用户自定义聚合函数，多进一出
    UDTF： User-Defined Table-Generating Functions，用户定义表生成函数，用来解决输入一行输出多行
    ```
    - 富函数Rich Functions
    ```
    富函数Rich Functions，所有Flink函数类都有其Rich版本。
    它与常规函数的不同在于，可以获取运行环境的上下文，并拥有一些生命周期方法，
    open，close，getRuntimeContext，和 setRuntimeContext，所以可以实现更复杂的功能，比如累加器和计算器等
    ```
  
### Sink
> 当我们通过flink对数据处理结束后，要把结果数据放到相应的数据存放点，也就是sink了
```
数据放哪里呢:
ES
redis
Hbase
MYSQL
kafka
```
