### Window TCP连接优化
```
windows客户端
打开注册表：regedit HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\ Services\TCPIP\Parameters
新建 DWORD值，name：TcpTimedWaitDelay，value：0（十进制） –> 设置为0
新建 DWORD值，name：MaxUserPort，value：65534（十进制） –> 设置最大连接数65534 重启系统

PS:测试可用-byArvin
```

### windows如何统计端口的连接数
- [windows如何统计端口的连接数](https://www.cnblogs.com/XYDsoft/p/10099110.html)
- [Windows netstat命令查看连接数](https://blog.csdn.net/dodod2012/article/details/84029843)
```
netstat -an|find "9091" /C
netstat -an|find "ES"|find "9091" 
netstat -an|find "9091" 
```