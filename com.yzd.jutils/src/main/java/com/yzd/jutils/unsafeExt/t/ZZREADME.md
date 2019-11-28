
## 常量是不能被改变的。
> 静态变量可以被改变，但是常量是不能被改变的

```
//取的是常量
System.out.println( SafeKlass.getConstant() );
//取的是常量的反射，是两个概念，不要误解
SafeKlass.getConstantReflection()
```