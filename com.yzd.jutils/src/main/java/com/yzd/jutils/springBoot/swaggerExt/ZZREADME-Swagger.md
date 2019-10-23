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
