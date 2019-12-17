- go-stress-testing
    - [go-stress-testing 下载地址](https://github.com/link1st/go-stress-testing/releases)
    - [压测介绍-go实现的压测工具【单台机器100w连接压测实战】](https://blog.csdn.net/link_km/article/details/100130784) go-stress-testing go语言实现的压测工具
    
- [Centos Linux 使用Yum安装Go和配置环境](https://www.jianshu.com/p/b2222fc04f47) 推荐参考byArvin
    ```
    yum install -y epel-release
    yum install -y golang
    ```
- 安装go-stress-testing
    - [https://github.com/link1st/go-stress-testing](https://github.com/link1st/go-stress-testing)
    - [go-stress-testing 下载地址](https://github.com/link1st/go-stress-testing/releases)
    ```
    chmod 777 go-stress-testing-linux
    ./go-stress-testing-linux
    ```
- go-stress-testing 参数说明:
    ```
    -c 表示并发数
    
    -n 每个并发执行请求的次数(是单个用户请求的次数)，总请求的次数 = 并发数 * 每个并发执行请求的次数
    
    -u 需要压测的地址
    ```
- 使用示例
    ```
    # 查看用法
    ./go-stress-testing-mac
    
    # 使用请求百度页面
    ./go-stress-testing-mac -c 1 -n 100 -u https://www.baidu.com/
    
    # 使用debug模式请求百度页面
    ./go-stress-testing-mac -c 1 -n 1 -d true -u https://www.baidu.com/
    
    # 使用 curl文件(文件在curl目录下) 的方式请求
    ./go-stress-testing-mac -c 1 -n 1 -p curl/baidu.curl.txt
    
    # 压测webSocket连接
    ./go-stress-testing-mac -c 10 -n 10 -u ws://127.0.0.1:8089/acc
    
    # linux环境：
    ./go_stress_testing_linux -c 100 -n 10000 -u http://127.0.0.1:8088/
    ```