# 常见问题

## 关于报错Consider defining a bean of type 的解决
   - [关于报错Consider defining a bean of type 的解决](https://blog.csdn.net/qq_41937388/article/details/106967610)
        ```
        报错原因为配置中找不到一个指定自动注入类型的bean。
        调整：
        @ComponentScan(basePackages = {"com.google.*"})
        ```