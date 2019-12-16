- 参看GC情况
    ```
    jstat -gcutil ：
    
    eg:
    jstat -gcutil 28115 1000 10
    PS:jstat -gcutil 28115(PID) 1000(间隔时间) 10(执行次数)
    结果信息：
    
    S0 — Heap上的 Survivor space 0 区已使用空间的百分比 
    S1 — Heap上的 Survivor space 1 区已使用空间的百分比 
    E — Heap上的 Eden space 区已使用空间的百分比 
    O — Heap上的 Old space 区已使用空间的百分比 
    P — Perm space 区已使用空间的百分比 
    YGC — 从应用程序启动到采样时发生 Young GC 的次数 
    YGCT– 从应用程序启动到采样时 Young GC 所用的时间(单位秒) 
    FGC — 从应用程序启动到采样时发生 Full GC 的次数 
    FGCT– 从应用程序启动到采样时 Full GC 所用的时间(单位秒) 
    GCT — 从应用程序启动到采样时用于垃圾回收的总时间(单位秒)
    ————————————————
    原文链接：https://blog.csdn.net/jiankunking/article/details/79688903
    ```
- 参考堆栈信息