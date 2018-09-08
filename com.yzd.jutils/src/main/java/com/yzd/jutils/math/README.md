
### A.java代码怎么把科学计数法转换为具体数字
```
//2.7262976E7 显示：27262976
String numStr="2.7262976E7";
BigDecimal decimalVal= NumberUtils.createBigDecimal(numStr);
System.out.println("number.BigDecimal()="+decimalVal.toString());
//
BigDecimal bd = new BigDecimal("3.40256010353E11");
String str = bd.toPlainString();

```
### B.Number操作推荐使用commonsloang 3.x -NumberUtils
### C.[NumberUtils的简单举例使用](https://blog.csdn.net/weixin_30363263/article/details/81287531)