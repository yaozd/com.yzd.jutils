## Centos7 编译安装 wrk 压力测试
- [Http压测工具wrk使用指南](https://www.cnblogs.com/jiftle/p/7158291.html) 推荐参考byArvin
- [Centos7 编译安装 wrk 压力测试](https://blog.csdn.net/Diligent_ten/article/details/79081433)
- [https://github.com/wg/wrk](https://github.com/wg/wrk)
- [https://github.com/giltene/wrk2](https://github.com/giltene/wrk2)
    ```
    小工具推荐 wrk2，https://github.com/giltene/wrk2，是 wrk 的分支。
    推荐看看作者 Gil Tene 在 readme 里写的关于如何正确测量响应时间，推荐搜一下作者讲 coordinated omission（CO） 的 2 个youtube视频。
    wrk 也可用，性能也更好些。建议跟 wrk2 对照，毕竟CO是个大坑，全世界绝大部分压测工具都掉坑里。
    ```
- [性能测试工具的 Coordinated Omission 问题](https://www.jianshu.com/p/bfb2b0f50edd?from=timeline&isappinstalled=0)
- [性能测试之－wrk(转)](https://www.cnblogs.com/rainy-shurun/p/5867946.html) -推荐参考byArvin

- 安装wrk
```
git clone https://github.com/wg/wrk.git  
cd wrk 
make
ln -s /usr/local/src/wrk/wrk /usr/local/bin 
PS:Linux设置环境变量(解决许多命令找不到) https://www.cnblogs.com/bugutian/p/5051109.html
wrk -t 2 -c 50 -d 20 --latency http://localhost:5000

```

```
wrk -t 500 -c 500 -d 60 --latency http://demo.yzd.com/proxy

wrk -t 500 -c 500 -d 60 --latency http://127.0.0.1/proxy 

wrk -t 500 -c 500 -d 10 --latency http://127.0.0.1

wrk -t 1 -c 1 -d 10 -s http_get.lua --latency http://127.0.0.1/

nohup wrk -t 1 -c 1 -d 10 -s http_get.lua --latency http://127.0.0.1/ &

ps -ef |grep wrk

```
- 参数据说明
```
参数说明
短参数	长参数	描述
-c	--connections	保持开启的总连接数, 每个线程均摊服务
-d	--duration	持续时间, 比如: 2s, 2m, 2h
-t	--threads	被使用的总线程数
-s	--script	LuaJIT script, see SCRIPTING
-H	--header	HTTP 请求头, 比如: "User-Agent: wrk"
--latency	打印详细的延迟统计信息
--timeout	记录超时的响应报文
```
- wrk 脚本 
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