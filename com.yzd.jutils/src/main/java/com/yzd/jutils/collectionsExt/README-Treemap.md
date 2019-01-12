
### 降序返回在此 set 的元素上进行迭代的迭代器-反向集合
> TreeMap 根据key 倒序排序
```
NavigableMap<Integer,String> standardStepMap= activityEnum.getStepMap().descendingMap();

NavigableMap<Integer,String> standardStepMap=new TreeMap<Integer, String>(activityEnum.getStepMap()).descendingMap();
-----------------------------
private final SortedMap<Integer, String> stepMap=new TreeMap<Integer, String>();
```

