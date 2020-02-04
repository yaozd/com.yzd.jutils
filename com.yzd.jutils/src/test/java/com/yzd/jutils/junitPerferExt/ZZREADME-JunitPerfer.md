## 参考：
- [用junitperf做并发测试带来的问题](http://www.blogjava.net/emu/articles/4358.html)
- [https://github.com/houbb/junitperf](https://github.com/houbb/junitperf)
- []()
- []()

## 解决 @JunitPerfConfig(duration = 1000,warmUp = 10)配置参数不能动态调整问题
```
约定大于配置
更改源代码，增加系统变量的方法来更改配置参数
@JunitPerfConfig(duration = 1000,warmUp = 10)
system_junit_duration
system_junit_warmup
```