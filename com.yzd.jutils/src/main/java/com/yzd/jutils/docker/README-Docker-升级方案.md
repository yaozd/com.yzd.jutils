# docker 升级方案

## 基本标准化要求
1. 主机名配置 
    - 因为K8S的规定，主机名只支持包含 - 和 .(中横线和点)两种特殊符号，并且主机名不能出现重复。
2. host规范
    - Linux系统安装完成后，在hosts(/etc/hosts)中应该有localhost指向127.0.0.1，如果没有则手动添加上。
    - 配置每台主机的hosts(/etc/hosts)，添加host_ip $hostname到/etc/hosts文件中。
3. CentOS关闭selinux
    - sudo sed -i 's/SELINUX=enforcing/SELINUX=disabled/g' /etc/selinux/config
4. 时间同步
5. 关闭firewall安装iptables
## 内核优化
```
net.bridge.bridge-nf-call-ip6tables=1
net.bridge.bridge-nf-call-iptables=1
net.ipv4.ip_forward=1
net.ipv4.conf.all.forwarding=1
net.ipv4.neigh.default.gc_thresh1=4096
net.ipv4.neigh.default.gc_thresh2=6144
net.ipv4.neigh.default.gc_thresh3=8192
net.ipv4.neigh.default.gc_interval=60
net.ipv4.neigh.default.gc_stale_time=120
# 参考 https://github.com/prometheus/node_exporter#disabled-by-default
kernel.perf_event_paranoid=-1
#sysctls for k8s node config
net.ipv4.tcp_slow_start_after_idle=0
net.core.rmem_max=16777216
fs.inotify.max_user_watches=524288
kernel.softlockup_all_cpu_backtrace=1
kernel.softlockup_panic=0
kernel.watchdog_thresh=30
fs.file-max=2097152
fs.inotify.max_user_instances=8192
fs.inotify.max_queued_events=16384
vm.max_map_count=262144
fs.may_detach_mounts=1
net.core.netdev_max_backlog=16384
net.ipv4.tcp_wmem=4096 12582912 16777216
net.core.wmem_max=16777216
net.core.somaxconn=32768
net.ipv4.ip_forward=1
net.ipv4.tcp_max_syn_backlog=8096
net.ipv4.tcp_rmem=4096 12582912 16777216
net.ipv6.conf.all.disable_ipv6=1
net.ipv6.conf.default.disable_ipv6=1
net.ipv6.conf.lo.disable_ipv6=1
kernel.yama.ptrace_scope=0
vm.swappiness=0
# 可以控制core文件的文件名中是否添加pid作为扩展。
kernel.core_uses_pid=1
# Do not accept source routing
net.ipv4.conf.default.accept_source_route=0
net.ipv4.conf.all.accept_source_route=0
# Promote secondary addresses when the primary address is removed
net.ipv4.conf.default.promote_secondaries=1
net.ipv4.conf.all.promote_secondaries=1
# Enable hard and soft link protection
fs.protected_hardlinks=1
fs.protected_symlinks=1
# 源路由验证
# see details in https://help.aliyun.com/knowledge_detail/39428.html
net.ipv4.conf.all.rp_filter=0
net.ipv4.conf.default.rp_filter=0
net.ipv4.conf.default.arp_announce = 2
net.ipv4.conf.lo.arp_announce=2
net.ipv4.conf.all.arp_announce=2
# see details in https://help.aliyun.com/knowledge_detail/41334.html
net.ipv4.tcp_max_tw_buckets=5000
net.ipv4.tcp_syncookies=1
net.ipv4.tcp_fin_timeout=30
net.ipv4.tcp_synack_retries=2
kernel.sysrq=1
```
## nofile 优化
```
cat >> /etc/security/limits.conf <<EOF
* soft nofile 65535
* hard nofile 65536
EOF
```
## K8S相关优化升级
### 备份ETCD数据并有效还原
- 备份ETCD数据
```
ETCDCTL_API=3 etcdctl --endpoints="https://IP:2379,https://IP:2379,https://IP:2379" --cert=/XXX/etcd.pem --key=/XXX/etcd-key.pem --cacert=/XXX/ca.pem  snapshot save XXX.db
```
- 使用备份数据还原（每个节点执行一次）
```
ETCDCTL_API=3 etcdctl --name=主机名 --endpoints="https://XXX:2379" --cert=/XXX/etcd.pem --key=/XXX/etcd-key.pem --cacert=/XXX/ca.pem --initial-cluster-token=etcd-cluster-0-XXX --initial-advertise-peer-urls=https://IP:2380 --initial-cluster=主机名=https://XXX:2380,主机名=https://XXX:2380,主机名=https://XXX:2380 --data-dir=/XXX/etcd snapshot restore XXX.db
```
### k8s pod 驱散
***驱散步骤从slave开始（避重就轻，查询每个kubelet的任务重要性，可以从最轻的开始升级）***
```
kubectl taint node [node] key=value[effect]
其中[effect] 可取值： [ NoSchedule | PreferNoSchedule | NoExecute ]
NoSchedule ：一定不能被调度。
PreferNoSchedule：尽量不要调度。
NoExecute：不仅不会调度，还会驱逐Node上已有的Pod。
选最后一个 NoExecute
```
***注意： 升级完K8S记得取消污点，使用以下命令 ***
```
kubectl taint node 主机名或者ip key-
```
***如果驱散不了的以下类型*** 
- demoset 一般业务是不存在这种类型的，基础服务的话不用在意，一般服务于当前主机
- statefulset 也会被驱逐，驱逐前统计该类型的，是否存在业务使用此类型，如果有确认，并手动迁移，保证业务的稳定以及数据安全
### 升级 docker 服务
#### 安装升级 docker 
- 选择 aliyun 的 yum 源，有稳定版本的 docker ce 版本
```
yum install ca-certificates ;
update-ca-trust;
cp /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo-bak
cat << 'EOF' > /etc/yum.repos.d/CentOS-Base.repo
# CentOS-Base.repo
#
# The mirror system uses the connecting IP address of the client and the
# update status of each mirror to pick mirrors that are updated to and
# geographically close to the client.  You should use this for CentOS updates
# unless you are manually picking other mirrors.
#
# If the mirrorlist= does not work for you, as a fall back you can try the 
# remarked out baseurl= line instead.
#
#
[base]
name=CentOS-$releasever - Base - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/os/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/os/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/os/$basearch/
gpgcheck=1
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
#released updates
[updates]
name=CentOS-$releasever - Updates - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/updates/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/updates/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/updates/$basearch/
gpgcheck=1
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
#additional packages that may be useful
[extras]
name=CentOS-$releasever - Extras - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/extras/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/extras/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/extras/$basearch/
gpgcheck=1
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
#additional packages that extend functionality of existing packages
[centosplus]
name=CentOS-$releasever - Plus - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/centosplus/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/centosplus/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/centosplus/$basearch/
gpgcheck=1
enabled=0
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
#contrib - packages by Centos Users
[contrib]
name=CentOS-$releasever - Contrib - mirrors.aliyun.com
failovermethod=priority
baseurl=http://mirrors.aliyun.com/centos/$releasever/contrib/$basearch/
        http://mirrors.aliyuncs.com/centos/$releasever/contrib/$basearch/
        http://mirrors.cloud.aliyuncs.com/centos/$releasever/contrib/$basearch/
gpgcheck=1
enabled=0
gpgkey=http://mirrors.aliyun.com/centos/RPM-GPG-KEY-CentOS-7
EOF
```
- 安装 docker 脚本，尽量不要手工安装，避免出错
- 安装版本通过调研选择 docker-ce 18.06.3 版本很稳定
***注意：Docker-Engine Docker官方已经不推荐使用，请务必安装Docker-CE***
```
# 定义用户名
NEW_USER=hke
# 添加用户(可选)
sudo adduser $NEW_USER
# 为新用户设置密码
sudo passwd $NEW_USER
# 为新用户添加sudo权限
sudo echo "$NEW_USER ALL=(ALL) ALL" >> /etc/sudoers
# 卸载旧版本Docker软件
sudo yum remove docker \
              docker-client \
              docker-client-latest \
              docker-common \
              docker-latest \
              docker-latest-logrotate \
              docker-logrotate \
              docker-selinux \
              docker-engine-selinux \
              docker-engine \
              container*
# 定义安装版本
export docker_version=18.06.3
# 对系统进行全面的更新升级，推荐升级一下（可选）
sudo yum update -y;
# 安装必要的一些系统工具
sudo yum install -y yum-utils device-mapper-persistent-data \
    lvm2 bash-completion;
# Step 2: 添加软件源信息
sudo yum-config-manager --add-repo \
    http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo;
# Step 3: 更新并安装 Docker-CE
sudo yum makecache all;
version=$(yum list docker-ce.x86_64 --showduplicates | sort -r|grep ${docker_version}|awk '{print $2}');
sudo yum -y install --setopt=obsoletes=0 docker-ce-${version} docker-ce-selinux-${version};
# 如果已经安装高版本Docker,可进行降级安装(可选)
yum downgrade --setopt=obsoletes=0 -y docker-ce-${version} docker-ce-selinux-${version};
# 把当前用户加入docker组
sudo usermod -aG docker $NEW_USER;
# 设置开机启动
sudo systemctl enable docker;
```
#### 锁定Docker版本
***可能因为某些原因无意间执行了yum update或者apt-get -y upgrade;导致Docker版本升级。为了避免此类问题发生，建议在安装好Docker后对Docker软件进行锁定，防止Docker意外更新。***
1. 安装yum-plugin-versionlock插件
```
yum install yum-plugin-versionlock
```
1. 锁定软件包
```
yum versionlock add docker-ce docker-ce-cli
```
1. 查看已锁定的软件包
```
yum versionlock list
```
1. 解锁指定的软件包
```
yum versionlock delete <软件包名称>
```
1. 解锁所有的软件包
```
yum versionlock clear
```
#### Docker 配置
对于通过systemd来管理服务的系统(比如CentOS7.X、Ubuntu16.X), Docker有两处可以配置参数: 一个是docker.service服务配置文件,一个是Docker daemon配置文件daemon.json。
- daemon.json
```
{
    "oom-score-adjust": -1000,
    "log-driver": "json-file",
    "log-opts": {
    "max-size": "100m",
    "max-file": "3"
    },
    "max-concurrent-downloads": 20,
    "max-concurrent-uploads": 10,
    "bip": "169172.254200.30200.1/2824", // 最好自定义一个完全不会冲突的网断
    "insecure-registries": ["xxxxxxxx"], // 公司的镜像仓库
    "registry-mirrors": ["https://100.125.0.198:20202"],
    "storage-driver": "overlay2",
    "storage-opts": [
    "overlay2.override_kernel_check=true"
    ]
}
```
- /usr/lib/systemd/system/docker.service
    - 防止docker服务OOM： OOMScoreAdjust=-1000
    - 开启iptables转发链：ExecStartPost=/usr/sbin/iptables -P FORWARD ACCEPT
    ![](media/15911513179158/15911557037988.jpg)
## 升级完回归策略
1. 升级完的机器增加到原来集群，并且设置污点标签
2. 部署一个业务 demo 通过 node selector 部署到对应升级的 node 上
3. 测试 demo 测试无问题之后可以先切入不重要的业务进来再次进行接口调用测试
4. 确认无问题后，删除污点进行测试
5. 一台一台逐步升级