## idea profiling tools
### 参考
- [IDEA集成Async Profiler](https://zhuanlan.zhihu.com/p/81886875)
- Safepoint bias问题
    - [什么是SafePoint](https://www.zhihu.com/tardis/landing/360/ans/156390795?query=Safepoint+bias&mid=ab1334f6cdeeb934a51c63e59e8473ed&guid=75329C0CA1A1064445FCA1CFEE464F30.1577681923929)
    - [java GC进入safepoint的时间为什么会这么长？](https://www.zhihu.com/tardis/landing/360/ans/156390795?query=Safepoint+bias&mid=ab1334f6cdeeb934a51c63e59e8473ed&guid=75329C0CA1A1064445FCA1CFEE464F30.1577681923929)
- [如何读懂火焰图？](http://www.ruanyifeng.com/blog/2017/09/flame-graph.html)
    - 火焰图的含义
    ```
    y 轴表示调用栈，每一层都是一个函数。调用栈越深，火焰就越高，顶部就是正在执行的函数，下方都是它的父函数。
    x 轴表示抽样数，如果一个函数在 x 轴占据的宽度越宽，就表示它被抽到的次数多，即执行的时间长。
    注意，x 轴不代表时间，而是所有的调用栈合并后，按字母顺序排列的。
    火焰图就是看顶层的哪个函数占据的宽度最大。只要有"平顶"（plateaus），就表示该函数可能存在性能问题。
    颜色没有特殊含义，因为火焰图表示的是 CPU 的繁忙程度，所以一般选择暖色调
    ```
- idea官方文档
    - [IntelliJ IDEA is integrated with the following profiling tools:](https://www.jetbrains.com/help/idea/cpu-profiler.html)
    ```
    Async Profiler: a CPU and memory profiling tool for Linux and macOS.
    Java Flight Recorder: a CPU tool provided by Oracle available on Linux, macOS, and Windows.
    ```
    - [Java Flight Recorder](https://www.jetbrains.com/help/idea/java-flight-recorder.html)
    - [Read the profiling report﻿](https://www.jetbrains.com/help/idea/read-the-profiling-report.html)
    - []()
    - PS:当前ideal工具必须是商用版、旗舰版。To the VM options field, add the following line: -XX:+UnlockCommercialFeatures
        ```
        启动参考：
        To the VM options field, add the following line: -XX:+UnlockCommercialFeatures
        
        Java Flight Recorder
        https://www.jetbrains.com/help/idea/java-flight-recorder.html
        
        ```
- []()
- []()