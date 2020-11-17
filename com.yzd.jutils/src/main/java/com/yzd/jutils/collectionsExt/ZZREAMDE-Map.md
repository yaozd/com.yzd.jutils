### MAP新方法
- [java.util.Map中的putIfAbsent、computeIfAbsent、computeIfPresent、compute的区别](https://blog.csdn.net/wsen1229/article/details/81511554)
- [JAVA8 Map新方法：compute，computeIfAbsent，putIfAbsent与put的区别](https://blog.csdn.net/wang_8101/article/details/82191146)
- []()
- []()

### 注意点：
- 如果StringMap.remove(1)与iterator.remove()同时执行，则StringMap.size()是负数
```
1. IntObjectHashMap类，StringMap.size()是负数
 @Test
    public void testIntObjectHashMap(){
        //IntObjectHashMap<String> StringMap=new IntObjectHashMap<>();
        IntObjectHashMap<String> StringMap=new IntObjectHashMap<>();
        StringMap.put(1,"a");
        StringMap.put(1,"a");
        StringMap.put(2,"b");
        StringMap.put(1,"a");

        Iterator<IntObjectMap.PrimitiveEntry<String>> iterator = StringMap.entries().iterator();
        while (iterator.hasNext()) {
            IntObjectMap.PrimitiveEntry<String> next = iterator.next();
            int key = next.key();
            String value = next.value();
            System.out.println(value);
            System.out.println(key);
            StringMap.remove(1);
            //tringMap.size()是负数,目前暂定为bug.
            //如果StringMap.remove(1)与iterator.remove()同时执行，则StringMap.size()是负数
            StringMap.remove(1);
            iterator.remove();
        }
        System.out.println(StringMap.size());
    }
2.HashMap 使用StringMap.remove(1),会报 java.util.ConcurrentModificationException
    @Test
    public void testHashMap(){
        HashMap<Integer,String> StringMap=new HashMap<>();
        StringMap.put(1,"a");
        StringMap.put(1,"a");
        StringMap.put(2,"b");
        StringMap.put(1,"a");
        Iterator<Map.Entry<Integer, String>> iterator = StringMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            //如果在jdk默认的map实现类，使用StringMap.remove(1),会报 java.util.ConcurrentModificationException
            StringMap.remove(1);
            StringMap.remove(1);
            iterator.remove();
        }
        System.out.println(StringMap.size());
    }
3. ConcurrentHashMap 则StringMap.size()是0,结果正常
    @Test
    public void testConcurrentHashMap(){
        Map<Integer,String> StringMap=new ConcurrentHashMap<>();
        StringMap.put(1,"a");
        StringMap.put(1,"a");
        StringMap.put(2,"b");
        StringMap.put(1,"a");
        Iterator<Map.Entry<Integer, String>> iterator = StringMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> next = iterator.next();
            //如果StringMap.remove(1)与iterator.remove()同时执行，则StringMap.size()是0,结果正常
            StringMap.remove(1);
            StringMap.remove(1);
            iterator.remove();
        }
        System.out.println(StringMap.size());
    }
```