> 判断当前对象是否为List对象
```
public static Object deserialize(String cacheDataInRedis,Class returnType,  Class modelType) {
        // 序列化结果应该是List对象
        if (returnType.isAssignableFrom(List.class)) {
            return FastJsonUtil.deserializeList(cacheDataInRedis, modelType);
        } else {
            return FastJsonUtil.deserialize(cacheDataInRedis, modelType);
        }
    }
```

## String数据转List
```
//准备一个String数组
String[] strs = {"aa","bb","cc"};
//String数组转List
List<String> strsToList1= Arrays.asList(strs);
for(String s:strsToList1){
 System.out.println(s);
}
```