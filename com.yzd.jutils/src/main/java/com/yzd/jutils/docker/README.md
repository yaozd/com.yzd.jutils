
### [容器(docker)中运行java需关注的几个小问题](http://ifeve.com/%E5%AE%B9%E5%99%A8docker%E4%B8%AD%E8%BF%90%E8%A1%8Cjava%E9%9C%80%E5%85%B3%E6%B3%A8%E7%9A%84%E5%87%A0%E4%B8%AA%E5%B0%8F%E9%97%AE%E9%A2%98/)
- java不知道自己运行在container里，以为它看到的资源都能用。结果：java工作在资源充足的
- jvm检测可用cpu个数来优化运行时，影响jvm后台做的一些决策。
