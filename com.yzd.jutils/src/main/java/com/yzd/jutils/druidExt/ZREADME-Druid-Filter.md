###  1.配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
- spring.datasource.filters=stat,wall,log4j
```
属性类型是字符串，通过别名的方式配置扩展插件，常用的插件有：
监控统计用的filter:stat
日志用的filter:log4j
防御sql注入的filter:wall
```