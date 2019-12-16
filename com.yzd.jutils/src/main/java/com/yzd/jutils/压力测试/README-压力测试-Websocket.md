- webSocket压测
    - [压测介绍-go实现的压测工具【单台机器100w连接压测实战】](https://blog.csdn.net/link_km/article/details/100130784) go-stress-testing go语言实现的压测工具
    ```
    # 压测webSocket连接
    go run main.go -c 10 -n 10 -u ws://127.0.0.1:8089/acc
    ```