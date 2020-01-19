## JAVA方法设置默认值
- [Java 方法的参数可以有默认值吗？](https://blog.csdn.net/weixin_33947521/article/details/91423853)
- 重载
```
void method(String p1, int p2, bool p3){
  // ....
};
void method(String p1, int p2){
  bool p3 = false;
  // ... 
};
```