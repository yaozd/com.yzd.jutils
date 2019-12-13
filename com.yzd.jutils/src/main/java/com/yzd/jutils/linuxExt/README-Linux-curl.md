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