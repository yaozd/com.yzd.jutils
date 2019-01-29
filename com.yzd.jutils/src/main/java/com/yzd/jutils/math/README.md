
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

### [java对BigDecimal类型数据的操作、比较](https://blog.csdn.net/sy18868876085/article/details/84716118)
```
int r=big_decimal.compareTo(BigDecimal.Zero); //和0，Zero比较
if(r==0) //等于
if(r==1) //大于
if(r==-1) //小于

```
