## 自动化测试工具-uirecorder
### -uirecorder-install
```
C:\Users\zd.yao\AppData\Roaming\npm\node_modules
1.
node -v
2.安装cnpm
npm install -g cnpm --registry=https://registry.npm.taobao.org
3.安装uirecorder
cnpm install uirecorder mocha -g
4.
安装相关依赖
cnpm install jwebdriver expect.js mocha-generators faker --save-dev 

```
#### -本地自动化测试
```
4.1初始化配置

在D盘根目录建立一个文件夹uirecorder，

打开cmd窗口，切换到uirecorder目录

D:\uirecorder>

执行cmd命令：

1
cnpm install
执行cmd命令：

1
uirecorder init
```
#### -uirecorder初始化信息
```
Official Site: http://uirecorder.com
------------------------------------------------------------------

? Path扩展属性配置,除id,name,class之外 data-id,data-name,type,data-type,role,data-role,data-value
? 属性值黑名单正则
? class值黑名单正则
? 断言前隐藏
? WebDriver域名或IP 127.0.0.1
? WebDriver端口号 4444
? 需要同时测试的浏览器列表 chrome, ie 11

config.json 文件保存成功
package.json 文件创建成功
README.md 文件创建成功
screenshots 文件夹创建成功
commons 文件夹创建成功
uploadfiles 文件夹创建成功
.editorconfig 文件创建成功
.gitignore 文件创建成功
install.sh 文件创建成功
run.bat 文件创建成功
run.sh 文件创建成功
hosts 文件创建成功
.vscode/launch.json 文件创建成功

Start install project dependencies...
--------------------------------------------

/ [5/7] Installing wordwrap@~0.0.2
```
#### -测试demo
```
uirecorder start sample/test.spec.js
sample/test.spec.js:保存地址
```