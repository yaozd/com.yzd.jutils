##　解决许多命令找不到
- [Linux设置环境变量(解决许多命令找不到)](https://www.cnblogs.com/bugutian/p/5051109.html)
- []()

### 设置JDK 环境变量
```
bash_profile(用户专用环境变量)
vim ~/.bash_profile
cat ~/.bash_profile

# .bash_profile

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
	. ~/.bashrc
fi

# User specific environment and startup programs

JAVA_HOME=/home/hll/jdk

PATH=$JAVA_HOME/bin:$PATH:$HOME/bin

export PATH
```

```
vi /etc/profile

export JAVA_HOME=/home/jdk
export PATH=$PATH:$JAVA_HOME/bin:/usr/local/bin
export PATH USER LOGNAME MAIL HOSTNAME HISTSIZE HISTCONTROL

apply: source /etc/profile
```

- 环境配置-示例
```
1）查看是否存在.bash_profile, 如果不存在则新建.bash_profile文件
vi /etc/profile
2）添加环境变量 在文件后面追加如下文本：
# GOROOT
export GOROOT=/usr/lib/golang
# GOPATH
export GOPATH=/root/Work/programmer/go/gopath/
# GOPATH bin
export PATH=$PATH:$GOROOT/bin:$GOPATH/bin
3）需要立即生效，在终端执行如下命令：
source /etc/profile
```

