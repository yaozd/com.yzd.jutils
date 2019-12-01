

##　Oracle VM VirtualBox虚拟机安装Centos
- [在VirtualBox上安装CentOS7](https://jingyan.baidu.com/article/9c69d48f8ec01613c8024e58.html)-推荐参考
- [Oracle VM VirtualBox虚拟机安装Centos](https://blog.csdn.net/S_body/article/details/80550849)
- CentOS-7-x86_64-DVD-1511.iso（下载地址）
    - [http://mirrors.163.com/centos/7/isos/x86_64/](http://mirrors.163.com/centos/7/isos/x86_64/)
- []()
- []()

## VirtualBox网络设置
- VirtualBox中网络
    - 网络一
    ```
    仅主机（host only）网络
    ```
    - 网络二
    ```
    网络地址转换（NAT）
    ```
    
- CentOS7可视化界面
```
setting=>connection auto(自动连接)
```
- CentOS7无界面
    - ifcfg-enp0s3
    ```
    TYPE=Ethernet
    PROXY_METHOD=none
    BROWSER_ONLY=no
    BOOTPROTO=dhcp
    DEFROUTE=yes
    IPV4_FAILURE_FATAL=no
    IPV6INIT=yes
    IPV6_AUTOCONF=yes
    IPV6_DEFROUTE=yes
    IPV6_FAILURE_FATAL=no
    IPV6_ADDR_GEN_MODE=stable-privacy
    NAME=enp0s3
    UUID=1ef80c9a-d7a2-41ec-b84d-8ad020c286fd
    DEVICE=enp0s3
    ONBOOT=yes
    ```
    - ifcfg-enp0s8
    ```
    TYPE=Ethernet
    PROXY_METHOD=none
    BROWSER_ONLY=no
    BOOTPROTO=dhcp
    DEFROUTE=yes
    IPV4_FAILURE_FATAL=no
    IPV6INIT=yes
    IPV6_AUTOCONF=yes
    IPV6_DEFROUTE=yes
    IPV6_FAILURE_FATAL=no
    IPV6_ADDR_GEN_MODE=stable-privacy
    NAME=enp0s8
    UUID=345607e8-867f-4e04-a6e8-ae771acba160
    DEVICE=enp0s8
    ONBOOT=yes
    ```
 - >PS:[为CentOS 7配置静态IP地址及遇到的问题解决策略](https://blog.csdn.net/the_victory/article/details/44040065)
