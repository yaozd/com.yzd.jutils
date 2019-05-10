## 1.编程语言变量命名
- [https://codeif.xinke.org.cn](https://codeif.xinke.org.cn)-推荐参考byArvin

### 2.问题分析总结
- [为什么阿里巴巴禁止开发人员使用isSuccess作为变量名](https://mp.weixin.qq.com/s/B3iZcnN0-Tk05Bbbmu9aFQ)
    ```
    有过一个『强制性』规定:POJO中布尔类型变量都不要加is,如：isDelete、isSuccess
    在特殊情况情况下会产生Java对象的序列化与反序列化问题
    eg:
    private Boolean isDelete;
    private Boolean isSuccess;
    调整为(状态)：
    private Boolean deleteStatus;
    private Boolean successStatus;
     ```
-   通用命令参考
    ```
    item4DataMonitor
    itemList4DataMonitor
    ```
