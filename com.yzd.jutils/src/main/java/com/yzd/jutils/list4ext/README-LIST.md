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