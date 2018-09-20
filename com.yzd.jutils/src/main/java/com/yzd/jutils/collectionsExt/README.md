### Arrays.asList（）vs Collections.singletonList（）
- [Arrays.asList（）vs Collections.singletonList（）](https://www.cnblogs.com/xingzc/p/9144375.html)
- [使用Collections.singletonList（）方法-不可变列表](http://www.leftso.com/blog/438.html)

```
HttpHeaders headers = new HttpHeaders();
headers.setAccept( Collections.singletonList( MediaType.APPLICATION_JSON ) )
//使用Collections.singletonList（）方法[不可变列表]
List<String> list = Collections.singletonList( "data" );  
//使用Arrays.asList（）方法
List<String> list = Arrays.asList( "data");
```