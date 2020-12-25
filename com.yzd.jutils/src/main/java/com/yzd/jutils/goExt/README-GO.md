## go

## 教程
- [在Windows上安装Go语言开发包](http://c.biancheng.net/view/3992.html)
- [在IntelliJ idea上安装Go语言的开发环境 (在IDEA中配置 'go1.12.5' 最新版本的插件)](https://blog.csdn.net/cui_yonghua/article/details/90900336)
- 配置Go语言的环境变量
    ```
  GOROOT:指定SDK的安装路径c:/go
  Path:添加SDK的/bin目录
  GOPATH:工作目录，将来我们的go项目的工作路径
  ```
- 【工具使用】go build 命令打包成exe
    ```
  1.
  idea 会在用户的临时文件夹下生成一个可执行文件
  2.
  前提是有go环境
  go build -o test main.go
  -o 参数可以指定输出目录与打包后的文件名，最后生成为test.exe
  ```
- [go导入第三方包](https://www.cnblogs.com/just-save/p/12172947.html)

## 语法
- [Go语言基本语法](http://c.biancheng.net/view/3992.html)

## Go 项目目录结构-最佳实践
- [如何写出优雅的 Golang 代码](https://zhuanlan.zhihu.com/p/69445822)
- [Standard Go Project Layout](https://github.com/golang-standards/project-layout/blob/master/README_zh.md)

### 参考：
- Kratos是bilibili开源的一套Go微服务框架，包含大量微服务相关框架及工具
    - [https://github.com/go-kratos/kratos](https://github.com/go-kratos/kratos)

### 电子书
- [Go并发编程实战（第2版） PDF 下载](https://www.jb51.net/books/583925.html)
- []()

### 示例
- [https://github.com/smallnest/1m-go-tcp-server](https://github.com/smallnest/1m-go-tcp-server) 百万 Go TCP 连接的思考
    ```
  tune the linux:
  
  sysctl -w fs.file-max=2000500
  sysctl -w fs.nr_open=2000500
  sysctl -w net.nf_conntrack_max=2000500
  ulimit -n 2000500
  
  sysctl -w net.ipv4.tcp_tw_recycle=1
  sysctl -w net.ipv4.tcp_tw_reuse=1
  ```