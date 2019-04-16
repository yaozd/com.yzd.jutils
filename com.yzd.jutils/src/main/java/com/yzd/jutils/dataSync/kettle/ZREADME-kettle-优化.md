## Kettle优化参考
- [Kettle提高表输出写入速度（每秒万条记录）](https://blog.csdn.net/qq_37124304/article/details/82664665)
    - NO.1
    ```
    mysql表输出的时候出现减速的原因可能是因为网络链接的属性设置
    
    在此处添加参数：
    
    useServerPrepStmts=false  
    rewriteBatchedStatements=true  
    useCompression=true
    ``` 
    - NO.4
    ```
    批量提交
    每次表输出的提交量
    ```
 ### Kettle批量导入（百度云）
 - Kettle示例-通过Javascript与Sql语句结合实现分页循环批量导入-2019-04-16-1632.zip
