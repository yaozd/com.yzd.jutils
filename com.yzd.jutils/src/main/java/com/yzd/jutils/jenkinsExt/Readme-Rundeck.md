百度云--》j-r-rundeck中有备份 
# 官方文档

- [https://rundeck.org/docs/manual/getting-started.html](https://rundeck.org/docs/manual/getting-started.html)
- [https://rundeck.org/docs/api/](https://rundeck.org/docs/api/)
- [Download Rundeck-jar包的形式运行](https://rundeck.org/download/jar/)

## [火币 Jenkins+Rundeck 自动化构建部署方案](https://gitbook.cn/gitchat/activity/5bfe78d8bb58a42f47381696)

#### Rundeck存储

```
1.默认使用H2嵌入式数据库
2.Rundeck也支持mysql数据库
```
#### Rundeck并行执行测试
- [Rundeck并行执行测试](https://www.jianshu.com/p/4b774ea6c0d1)

```
需要配置2个地方：
1.workflow：Workflow
2.node节点：Nodes  Dispatch to Nodes   Execute locally  
```

```
总结：
在Orchestrator不做选择时，默认按照节点名排序执行，可选择排序方式
在Orchestrator中Random Subset选择 <b>1</b> 时，job最终只在一个节点执行，与采用哪种形式的Strategy，和Thread Count无关
即使在Node First模式下，调整Orchestrator节点数和Thread Count，可以实现节点的并行执行
Parallel执行，是job中Step并行执行，即step执行不会按照step顺序执行
Sequential方式，每个Step按照节点顺序执行, 当所有节点的上一个job step执行完，才会在所有节点执行下一个job step

```

#### Rundeck-安装部署参考：
- [Rundeck任务自动化发布系统部署与配置](https://blog.csdn.net/kwu_ganymede/article/details/51614276)
- []()

#### Rundeck-示例参考：
- [Rundeck部署和基本使用-示例参考-ARVIN推荐](https://www.cnblogs.com/bugsbunny/p/7614958.html)
- [【自动部署】服务器自动化操作 RunDeck](https://www.iyunv.com/thread-385193-1-1.html)

#### RUNDECK添加远程节点配置认证并执行作业
- [RUNDECK添加远程节点配置认证并执行作业](https://blog.csdn.net/lsysafe/article/details/83246762)

```
添加认证：
ssh-copy-id -i ~/.ssh/id_rsa.pub  root@192.168.1.241
```

#### Rundeck配置mysql
- [rundeck 自动发布平台](https://yq.aliyun.com/wenji/293772)
- [rundeck部署](https://wenku.baidu.com/view/84c78b15fab069dc5122019d.html)

#### jenkins-rundeck插件
- [RunDeck Plugin](https://wiki.jenkins.io/display/JENKINS/RunDeck+Plugin)
- [Comparing Rundeck and Jenkins (And Why They Work Great Together)](https://www.rundeck.com/blog/comparing-rundeck-and-jenkins-works-great-together)

####　rundeck 添加任务并向任务传递参数的简单配置
－[rundeck 添加任务并向任务传递参数的简单配置](https://blog.csdn.net/lsysafe/article/details/83152138)

```
Rundeck 部署使用
个人理解：
rundeck的优势：
        可以记录下来编辑的操作形成流程
        运行记录可以记录下来
ansible的优势：
        操作比较简单，无需复杂配置，命令行就可搞定，相对灵活

```