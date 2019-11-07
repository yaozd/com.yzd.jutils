##　注解
- @ApiIgnore
    - 忽略当前接口
    ```
    @ApiIgnore
    @RestController
    @RequestMapping(value = "/env", produces = {"application/json;charset=UTF-8"})
    @Api(value = "Manager Env Controller", tags = "环境管理")
    public class EnvController {
    ```

## Spring Boot 2.x基础教程：Swagger接口分类与各元素排序问题详解
- [Spring Boot 2.x基础教程：Swagger接口分类与各元素排序问题详解](https://www.cnblogs.com/didispace/p/11639671.html)
```
swagger.ui-config.operations-sorter=alpha

```