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

apply:  source ~/.bash_profile
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
##　[Linux中修改环境变量及生效方法](https://www.cnblogs.com/franson-2016/p/6063226.html)
```
方法一：

在/etc/profile文件中添加变量【对所有用户生效(永久的)】

用VI在文件/etc/profile文件中增加变量，该变量将会对Linux下所有用户有效，并且是“永久的”。

要让刚才的修改马上生效，需要执行以下代码

# source /etc/profile

方法二：

在用户目录下的.bash_profile文件中增加变量【对单一用户生效(永久的)】

用VI在用户目录下的.bash_profile文件中增加变量，改变量仅会对当前用户有效，并且是“永久的”。

要让刚才的修改马上生效，需要在用户目录下执行以下代码

# source ~/.bash_profile

方法三：

直接运行export命令定义变量【只对当前shell(BASH)有效(临时的)】

在shell的命令行下直接使用[export变量名=变量值]定义变量，该变量只在当前的shell(BASH)或其子shell(BASH)下是有效的，shell关闭了，变量也就失效了，再打开新shell时就没有这个变量，需要使用的话还需要重新定义。

例如：export PATH=/usr/local/webserver/php/bin:$PATH
```
