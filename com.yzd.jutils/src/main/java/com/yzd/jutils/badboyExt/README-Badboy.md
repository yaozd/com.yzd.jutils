
### 参考阅读
- [BadBoy检查点的添加](https://blog.csdn.net/zhangtaoee/article/details/54233918)
```
1.通过“Add Assertion for Selection”来实现中断，如：登录页面有验证码的时候
选择需要做检查点的内容，点击“Tool”,选择-“Add Assertion for Selection”
```

### DOS执行脚本
>全量发版-还原到测试分支[check out test].cmd
```
ECHO off
ECHO START 01-打包-jenkins-[测试环境-XXX-更新与打包]-V201810151336.bx
START 01-打包-jenkins-[测试环境-XXX-更新与打包]-V201810151336.bx
PAUSE
ECHO START 03-API接口-jenkins-[测试环境-XXX-程序发布]-V201810151518.bx
START 03-API接口-jenkins-[测试环境-XXX-程序发布]-V201810151518.bx
PAUSE
ECHO START 04-移动与微信端-jenkins-[测试环境-XXX-程序发布]-V201810151518.bx
START 04-移动与微信端-jenkins-[测试环境-XXX-程序发布]-V201810151518.bx
PAUSE
ECHO START 02-PC端-jenkins-[测试环境-XXX-程序发布]-V201810151518.bx
START 02-PC端-jenkins-[测试环境-XXX-程序发布]-V201810151518.bx
PAUSE
ECHO 全量发版完成！！！！！
PAUSE
```
