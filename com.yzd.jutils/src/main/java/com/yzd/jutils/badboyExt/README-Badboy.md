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
