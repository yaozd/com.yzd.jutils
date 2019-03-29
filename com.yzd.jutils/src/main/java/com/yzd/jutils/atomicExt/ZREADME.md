
> 解决：AtomicInteger实现边界值控制
-  DataRepository.PRODUCT.incrementAndGet()
```
 public synchronized int incrementAndGet() {
        int value= pkgId.incrementAndGet();
        if(value>maxPkgId){
            pkgId.set(0);
            value=pkgId.incrementAndGet();
            //pkgIdResetCount.incrementAndGet();
        }
        return value;
    }
```