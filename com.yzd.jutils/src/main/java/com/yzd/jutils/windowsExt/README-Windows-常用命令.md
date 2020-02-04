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