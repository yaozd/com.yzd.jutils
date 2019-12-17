- 通过chrome的调试器获取curl
    ```
    chrome 浏览器生成 curl文件，打开开发者模式(快捷键F12)=>Network=>Copy=>Copy as cURL 
    生成 curl 在终端执行命令
    ```
    - [压测介绍-go实现的压测工具【单台机器100w连接压测实战】](https://blog.csdn.net/link_km/article/details/100130784) go-stress-testing go语言实现的压测工具
- 查看请求信息
    ```
    curl -v http://127.0.0.1/hello
    ```
- FULL DEMO
    ```
    curl 'http://127.0.0.1/hello'  
    	-H 'Connection: keep-alive'  
    	-H 'Accept-Encoding: gzip, deflate, br'  
    	-H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8'  
    	-H 'groupID: 189702'  -H 'Origin: https://m.hualala.com'  
    	-H 'User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 13_1_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Mobile/15E148 MicroMessenger/7.0.8(0x17000820) NetType/WIFI Language/zh_CN'  
    	-H 'Cookie: ma_h5=ma_user:badb806cd5ea4ae0be3b8127f24217d8'  
    	-H 'Host: demo.yzd.com'  
    	-H 'Referer: http://demo.yzd.com'  
    	-H 'Accept-Language: zh-cn'  
    	-H 'Accept: application/json, text/plain, */*'  
    	-H 'Content-Length: 0'
    ```
- GET
    ```
    curl http://127.0.0.1/hello  -H "host:demo.yzd.com"
    ```
- POST
    ```
    curl http://127.0.0.1/hello -X POST -d "accessToken=xxx" -H "groupID:00001"  -H "host:demo.yzd.com"

    ```
- WEBSOCKET
    ``` 
    curl --include \
     --no-buffer \
     --header "Connection: Upgrade" \
     --header "Upgrade: websocket" \
     --header "Host: broker.rt.hualala.com" \
     --header "Origin: http://example.com:80" \
     --header "Sec-WebSocket-Key: SGVsbG8sIHdvcmxkIQ==" \
     --header "Sec-WebSocket-Version: 13" \
     http://127.0.0.1/broker/hello
     
    ```
- [curl下载文件的命令](https://blog.csdn.net/shile/article/details/78401122)
    ```
    curl文件下载
    curl将下载文件输出到stdout，将进度信息输出到stderr，不显示进度信息使用–silent 选项。
    
    1 . curl URL --silent
    这条命令是将下载文件输出到终端，所有下载的数据都被写入到stdout。
    
    2 . curl URL --silent -O
    使用选项 -O 将下载的数据写入到文件，必须使用文件的绝对地址。
    
    3 . curl URL -o filename --progress
    ######################################### 100.0%
    选项 -o 将下载数据写入到指定名称的文件中，并使用 –progress 显示进度条。
    ```