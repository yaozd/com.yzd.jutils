### [JDK中的函数式接口举例](https://www.cnblogs.com/runningTurtle/p/7092632.html)

```
JDK中的函数式接口举例
java.lang.Runnable,
java.awt.event.ActionListener, 
java.util.Comparator,
java.util.concurrent.Callable
java.util.function包下的接口，如Consumer、Predicate、Supplier等
```

### 函数式接口@FunctionalInterface
- 将一个方法做为参数进行传递-Callable或Function

```
1.无参数数据方法
public Integer myMethod(Callable<Integer> func) {
        try {
            return func.call();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
--------------------------------
2.有参数数据方法
public  static int getData(Function fun){
        return 1;
    }
```
示例：缓存数据时-传入数据获得取的方法

```
@RequestMapping("getTempTestDataList")
public JsonResult getTempTestDataList() throws Exception {
    //List<TbTempTest> entityList = tempTestServiceInf.getList(0, 10);
    Callable<List<TbTempTest>> fun=()->{

        return tempTestServiceInf.getList(0, 10);
    };
    List<TbTempTest> entityList = CachedHongbaoUtil.getData1(fun);
    //
    List<TempTestData> dataList = TempTestData.toViewDataList(entityList);
    return new JsonResultList<>(dataList);
}
--------------------------------
public static List<TbTempTest> getData2(Callable<List<TbTempTest>> fun) throws Exception {
        ShardedRedisUtil redisUtil = ShardedRedisUtil.getInstance();
        CachedSetting cachedSetting = Constants.CACHED_API_Hongbao_DATA;
        String fullKey=cachedSetting.getKey()+keyVale;
        CachedWrapper<List<TbTempTest>> listCached = redisUtil.getCachedWrapperByMutexKey(fullKey,
                cachedSetting.getKeyExpireSec(),
                cachedSetting.getNullValueExpireSec(),
                cachedSetting.getKeyMutexExpireSec(),
                new CachedWrapperExecutor<List<TbTempTest>>() {
                    @Override
                    public List<TbTempTest> execute() {
                        List<TbTempTest> resultList;
                        try {
                            resultList= fun.call();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                        if (resultList == null || resultList.size() == 0) {
                            return null;
                        }
                        return resultList;
                    }
                });
       return   listCached.getData();
}
```