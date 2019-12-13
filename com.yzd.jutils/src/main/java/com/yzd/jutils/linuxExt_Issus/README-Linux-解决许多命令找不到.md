##　解决许多命令找不到
- [Linux设置环境变量(解决许多命令找不到)](https://www.cnblogs.com/bugutian/p/5051109.html)
- []()

```
vi /etc/profile

export JAVA_HOME=/home/jdk
export PATH=$PATH:$JAVA_HOME/bin:/usr/local/bin
export PATH USER LOGNAME MAIL HOSTNAME HISTSIZE HISTCONTROL

apply: source /etc/profile
```
