## [*几乎所有的压力测试工具大全*](https://www.iamle.com/archives/2173.html)
```
apache ab
老牌简单方便的ab压测


 
yum install apr-util

1
2
yum install apr-util
 
webench
web http压力测试

wget http://blog.zyan.cc/soft/linux/webbench/webbench-1.5.tar.gz
tar zxvf webbench-1.5.tar.gz
cd webbench-1.5
make && make install

1
2
3
4
5
wget http://blog.zyan.cc/soft/linux/webbench/webbench-1.5.tar.gz
tar zxvf webbench-1.5.tar.gz
cd webbench-1.5
make && make install
 
wrk
wrk官网
Modern HTTP benchmarking tool
web http压力测试

提示：
wrk测试结果可能不是那么准确，原因见
openresty作者(agentzh) 春哥在微博中说道 https://weibo.com/1834459124/G9ew2d5Ky?type=repost

我们的一家付费客户之前反映用 wrk 压测时发现有延时较高的长尾请求，
我们仔细使用 systemtap 和抓包工具分析之后，
发现 wrk 报告的延时结果错得离谱（比实际延时可能高出一个数量级的水平），
同时发现 wrk 输出的报告里不同的数字之间甚至都相互矛盾，
最后发现 wrk 内部有一个 stats_correct 函数会故意向实际统计的结果里加入噪音数据，
按其作者的说法，是为了摸拟真实互联网环境下的延时，
我就大汗了……我们自己去掉 wrk 那个 C 函数调用结果就对了。

1
2
3
4
5
6
7
8
我们的一家付费客户之前反映用 wrk 压测时发现有延时较高的长尾请求，
我们仔细使用 systemtap 和抓包工具分析之后，
发现 wrk 报告的延时结果错得离谱（比实际延时可能高出一个数量级的水平），
同时发现 wrk 输出的报告里不同的数字之间甚至都相互矛盾，
最后发现 wrk 内部有一个 stats_correct 函数会故意向实际统计的结果里加入噪音数据，
按其作者的说法，是为了摸拟真实互联网环境下的延时，
我就大汗了……我们自己去掉 wrk 那个 C 函数调用结果就对了。
 
go-wrk
go-wrk官网
go-wrk – a HTTP benchmarking tool based in spirit on the excellent wrk tool (https://github.com/wg/wrk)

Gatling
Gatling官网
Async Scala-Akka-Netty based Load Test Tool http://gatling.io

sniper
sniper官网
A powerful & high-performance http load tester

hey
hey官网
HTTP load generator, ApacheBench (ab) replacement, formerly known as rakyll/boom

Siege
Siege官网
Siege is an http load tester and benchmarking utility

http_load
http_load官网
http_load runs multiple http fetches in parallel, to test the throughput of a web server.

vegeta
vegete官网
HTTP load testing tool and library. It’s over 9000!

t50
t50官网
mixed packet injector tool

GoReplay
GoReplay官网
GoReplay is an open-source tool for capturing and replaying live HTTP traffic into a test environment in order to continuously test your system with real data. It can be used to increase confidence in code deployments, configuration changes and infrastructure changes.
https://github.com/buger/goreplay

tcpcopy
tcpcoy官网
An online request replication tool, also a tcp stream replay tool, fit for real testing, performance testing, stability testing, stress testing, load testing, smoke testing, etc

gryphon
Gryphon官网
Gryphon是由网易自主研发的能够模拟千万级别并发用户的一个软件，目的是能够用较少的资源来模拟出大量并发用户，并且能够更加真实地进行压力测试， 以解决网络消息推送服务方面的压力测试的问题和传统压力测试的问题。Gryphon分为两个程序，一个运行gryphon，用来模拟用户，一个是 intercept，用来截获响应包信息给gryphon。Gryphon模拟用户的本质是用一个连接来模拟一个用户，所以有多少个连接，就有多少个用户，而用户的素材则取自于pcap抓包文件。值得注意的是，Gryphon架构类似于tcpcopy，也可以采用传统使用方式和高级使用方式。

locust.io
Locust官网
An open source load testing tool.
Define user behaviour with Python code, and swarm your system with millions of simultaneous users.
python编写，用python脚本定义压测规则，分布式，有WEB UI界面
推荐使用

Jmeter
Apache JMeter官网
Apache JMeter是Apache组织开发的基于Java的压力测试工具。用于对软件做压力测试，它最初被设计用于Web应用测试，但后来扩展到其他测试领域。
比较轻量,较多测试同学喜欢用

Tsung
Tsung官网
Tsung is an open-source multi-protocol distributed load testing tool
It can be used to stress HTTP, WebDAV, SOAP, PostgreSQL, MySQL, LDAP, MQTT and Jabber/XMPP servers. Tsung is a free software released under the GPLv2 license.
Tsung是一个开源的支持多协议的分布式压力测试工具
目前支持HTTP分布式压力测试、WebDAV分布式压力测试、SOAP分布式压力测试、PostgreSQL分布式压力测试、MySQL分布式压力测试、LDAP分布式压力测试、MQTT分布式压力测试、Jabber/XMPP servers分布式压力测试

LoadRunner
LoadRunner官网
老牌压力测试工具，安装包非常之大,功能也非常全,目前被微软收购了

nGrinder
nGrinder官方
nGrinder is a platform for stress tests that enables you to execute script creation, test execution, monitoring, and result report generator simultaneously. The open-source nGrinder offers easy ways to conduct stress tests by eliminating inconveniences and providing integrated environments.
nGrinder是基于Grinder开源项目，由NHN公司的开发团队进行了重新设计和完善。nGrinder是一款非常易用，有简洁友好的用户界面和controller-agent分布式结构的强大的压力测试工具。
nGrinder测试基于python测试脚本(groovy也可)，用户按照一定规范编写测试脚本，controller会将脚本一集需要的资源分发到agent，用jython执行。并且在执行的过程中收集运行情况、相应时间、测试目标服务器的运行情况等。并且保存这些数据生成测试报告，以供查看。
这款框架的一大特点就是非常的简单易用，安装也很容易，可以说是开箱即用。

nGrinder使用
nGrinder-运维人员轻量级性能测试平台 https://www.jianshu.com/p/f336180806cc

BuoyantIO/slow_cooker
BuoyantIO/slow_cooker官网
A load tester focused on lifecycle issues and long-running tests
负载测试人员专注于生命周期问题和长期运行的测试
大多数负载测试人员通过向后端发送尽可能多的流量来工作。
我们想要一种不同的方法，我们希望能够在很长一段时间内测试具有可预测的负载和并发级别的服务。
我们希望定期报告qps和延迟，而不是最后收到报告。

twitter/iago
twitter/iago官网
A load generator, built for engineers
面向开发者的负载生成器
Iago是一个负载生成工具，可以针对给定目标重放生产或合成流量。
除此之外，它与其他负载生成工具的不同之处在于它试图保持事务速率不变。
例如，如果您想以每分钟100K的请求测试您的服务，Iago会尝试达到该速率。
由于Iago重放流量，您必须指定流量来源。
您使用事务日志作为流量来源，其中每个事务都会向您的服务生成服务处理请求。
以固定速率重放事务使您可以在预期负载下研究服务的行为。
Iago还允许您识别在生产环境中可能无法轻易观察到的瓶颈或其他问题，在这种环境中，您的最大预期负载很少发生。

fortio
fortio官网
fortio
Fortio load testing library, command line tool, advanced echo server and web UI in go (golang).
Allows to specify a set query-per-second load and record latency histograms and other useful stats.
Fortio是一个用golang写的负载测试库，包括了命令行工具，高级echo服务器和Web UI。
允许指定设置的每秒查询负载和记录延迟直方图以及其他有用的统计信息。

autocannon
autocannon官网
A HTTP/1.1 benchmarking tool written in node, greatly inspired by wrk and wrk2, with support for HTTP pipelining and HTTPS. On my box, autocannon can produce more load than wrk and wrk2.

loader.io(在线服务)
loader.io官网
Simple Cloud-based
LOAD TESTING
Loader.io is a FREE load testing service that allows you to stress test
your web-apps & apis with thousands of concurrent connections.

gaps腾讯压测大师(在线服务)
gaps官网
一分钟完成用例配置，让压测更简单
支持HTTP、HTTPS协议的API接口、网站、公众号内页等主流压测对象

阿里云PTS(在线服务)
阿里云PTS官网
性能测试PTS（Performance Testing Service）是面向所有技术相关背景人员的云化性能测试工具，孵化自阿里内部平台。有别于传统工具的繁复，PTS以互联网化的交互，面向分布式和云化的设计，更适合当前的主流技术架构。无论是自研还是适配开源的功能，PTS都可以轻松模拟大量用户访问业务的场景，任务随时发起，免去搭建和维护成本。更是紧密结合监控类产品提供一站式监控、定位等附加价值，高效检验和管理业务性能。
```