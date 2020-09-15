##　wrk压力测试
- [性能测试工具 wrk 使用教程](https://www.cnblogs.com/quanxiaoha/p/10661650.html)
- 示例 （开启KEEP ALIVE可以提高性能：HTTP建立连接是特别消耗资源）

```
wrk -c 300 -d 60s -t 16 http://172.20.60.45:8081/
HTTP DEMO--byDisheng
30多万/秒
wrk -c 300 -d 60s -t 16 http://172.20.60.45:8081/
===================================================

```

## wrk 动态参数请求脚本
- dynamic_parameter_request.lua
```
path = "/hello?name="
name = 1

request = function()
  name = name + 1
  return wrk.format("GET", path..name)
end

apply:  wrk -t 500 -c 500 -d 120 -s dynamic_parameter_request.lua --latency http://127.0.0.1:8888/
```

## wrk 脚本 
- wrk 不可以通过header设置host,只能通过脚本中wrk.host变量设置
- http_get.lua
    ```
    wrk.method = "GET"
    wrk.headers["Content-Type"] = "application/x-www-form-urlencoded"
    wrk.host = "demo.yzd.com"
    
    apply: wrk -t 1 -c 1 -d 10 -s http_get.lua --latency http://127.0.0.1/
    ```
    > PS:设置host: 正确 wrk.host = "demo.yzd.com" ；错误：wrk.headers["host"] = "demo.yzd.com"
- http_post.lua
    ```
    wrk.method = "POST"
    wrk.body   = "foo=bar&baz=quux"
    wrk.headers["Content-Type"] = "application/x-www-form-urlencoded"
    wrk.host = "demo.yzd.com"
    
    apply: wrk -t 1 -c 1 -d 10 -s http_get.lua --latency http://127.0.0.1/
    ```
## 参考：
- [Lua 运算符](https://www.runoob.com/lua/lua-miscellaneous-operator.html)
```
..	连接两个字符串	eg: a..b ，其中 a 为 "Hello " ， b 为 "World", 输出结果为 "Hello World"。
#	一元运算符，返回字符串或表的长度。	eg: #"Hello" 返回 5
```
- [Http压测工具wrk使用指南](https://www.cnblogs.com/jiftle/p/7158291.html)
```
wrk方法说明：
function wrk.format(method, path, headers, body)
wrk.format returns a HTTP request string containing the passed parameters
merged with values from the wrk table.
根据参数和全局变量wrk，生成一个HTTP rquest string。
```