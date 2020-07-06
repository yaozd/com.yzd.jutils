## 一致性算法-共识（Raft）算法
-[共识（Raft）算法](https://blog.csdn.net/m0_37609579/article/details/100189973)
-[编写你的第一个 Java 版 Raft 分布式 KV 存储](https://www.cnblogs.com/stateis0/p/10259339.html)
    - [https://github.com/stateIs0/lu-raft-kv](https://github.com/stateIs0/lu-raft-kv)
-[Raft算法的Java实现](https://blog.csdn.net/qq_33797928/article/details/91041381)-推荐参考byArvin(帮助理解Raft算法)
-[蚂蚁金服开源 SOFAJRaft：生产级 Java Raft 算法库](https://blog.csdn.net/weixin_34356310/article/details/91474270)
-[https://github.com/wenweihu86/raft-java](https://github.com/wenweihu86/raft-java)
-[]()
## 脑裂问题
-[分布式系统选举算法及脑裂](https://blog.csdn.net/m0_37609579/article/details/100189166)
-[分布式脑裂问题分析](https://blog.csdn.net/xinquanv1/article/details/103126372)-推荐byArvin
-[脑裂问题以及如何避免](https://blog.csdn.net/u014156013/article/details/81226424)-案例
```
1 "脑裂"定义
在一个高可用系统中，当联系着的节点断开联系时，本来为一个整体的系统，分裂成两个独立节点，两个节点开始争抢共享资源造成系统混乱、数据损坏的现象，成为“脑裂”。

2 "脑裂"成因
主要原因: 心跳检测做准备切换时的“不确定性”

当网络原因，导致心跳检测超时，主备切换的情况下，此时slave已经开始提供服务。但是后续之前被判定“死”的master由于网络恢复重新“复活”，此时系统存在两个“主”，发生脑裂问题；

3 "脑裂"解决思路
解决脑裂问题，有三种常用思路，分别如下：

设置仲裁机制
lease机制
设置隔离机制

```
## 拜占庭将军问题
-[拜占庭将军问题对应的分布式一致性算法](https://blog.csdn.net/caoyuanyenang/article/details/100543921)
-[]()
```
有N位将军一起合作进行军事行动，其中有M位叛将，这N位将军里有一位话事人（有可能由叛将担任话事人），话事人以通信的方式给其他将军发布军事指令，军事指令假设只有进攻和撤退两种，要有一种算法保证在该话事人给其他将军发送不一致的行动指令的情况下（部分将军收到进攻指令，部分将军收到撤退指令），所有的忠将都采取一致的动作，要么一起进攻，要么一起撤退。
  翻译成分布式环境下的一致性场景：一个集群有N个节点，其中有M个不稳定节点，在N个节点中有一个leader节点（有可能由不稳定节点担任leader），该leader节点会给其他节点发送0和1两种消息，需要有一种算法保证在leader给其他节点发送的消息不一致的情况下，能保证所有的稳定节点最后认可的消息是一致的，要么都是1，要么都是0。
```