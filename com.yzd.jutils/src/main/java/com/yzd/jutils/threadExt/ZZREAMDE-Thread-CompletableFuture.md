## CompletableFuture
- [Java8新特性整理之CompletableFuture：组合式、异步编程](https://blog.csdn.net/u011726984/article/details/79320004)
- [Java CompletableFuture：allOf等待所有异步线程任务结束](https://www.bbsmax.com/A/obzb7rgBJE/)

```
 @Benchmark
    public void m() {
        int nThreads = 1;
        CompletableFuture[] tasks = new CompletableFuture[nThreads];
        for (int i = 0; i < nThreads; i++) {
            tasks[i] = CompletableFuture.runAsync(this::run);
        }
        CompletableFuture<Void> all = CompletableFuture.allOf(tasks);
        //等待所有异步程序处理完成
        all.join();
//        for (CompletableFuture task : tasks) {
//            task.join();
//        }
//        all.cancel(true);
        nioEventLoopGroup.shutdownGracefully();
        log.info("completed");
    }
```