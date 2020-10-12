## windows常用命令
- win10如何打开控制面板
    ```
    方法一：按下Win+R弹出运行面板，输入control点击确定
    ```
- 更新DNS
    ```
    按下Win+R弹出运行面板，输入cmd点击确定
    ipconfig /flushdns
    ```
- MD5
    ```
    WINDOWS:
    certutil -hashfile .\hyperspace-container-0.0.1-SNAPSHOT.jar  MD5
    LINUX:
    md5sum hyperspace-container-0.0.1-SNAPSHOT.jar
    ```
- 启用/禁用休眠功能
```
 powercfg /h /?     //输出帮助信息
 powercfg -h off
 powercfg -h on
```

- [Win10在当前目录快速打开cmd的方法](https://www.cnblogs.com/yizhilin/p/12975052.html)
```
Windows Registry Editor Version 5.00

[HKEY_CLASSES_ROOT\Directory\shell\OpenCmdHere]

@="在此处打开命令提示符"

"Icon"="cmd.exe"

[HKEY_CLASSES_ROOT\Directory\shell\OpenCmdHere\command]

@="PowerShell -windowstyle hidden -Command \"Start-Process cmd.exe -ArgumentList '/s,/k, pushd,%V' -Verb RunAs\""

[HKEY_CLASSES_ROOT\Directory\Background\shell\OpenCmdHere]

@="在此处打开命令窗口"

"Icon"="cmd.exe"

[HKEY_CLASSES_ROOT\Directory\Background\shell\OpenCmdHere\command]

@="PowerShell -windowstyle hidden -Command \"Start-Process cmd.exe -ArgumentList '/s,/k, pushd,%V' -Verb RunAs\""

[HKEY_CLASSES_ROOT\Drive\shell\OpenCmdHere]

@="在此处打开命令窗口"

"Icon"="cmd.exe"

[HKEY_CLASSES_ROOT\Drive\shell\OpenCmdHere\command]

@="PowerShell -windowstyle hidden -Command \"Start-Process cmd.exe -ArgumentList '/s,/k, pushd,%V' -Verb RunAs\""

[HKEY_CLASSES_ROOT\LibraryFolder\background\shell\OpenCmdHere]

@="在此处打开命令窗口"

"Icon"="cmd.exe"

[HKEY_CLASSES_ROOT\LibraryFolder\background\shell\OpenCmdHere\command]

@="PowerShell -windowstyle hidden -Command \"Start-Process cmd.exe -ArgumentList '/s,/k, pushd,%V' -Verb RunAs\""

apply:
1.保存后,把后缀名改成reg格式
2.这样就变成了注册表的形式,最后,双击,点击确认
```