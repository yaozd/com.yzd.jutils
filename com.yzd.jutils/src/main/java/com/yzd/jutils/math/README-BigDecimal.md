
> [BigDecimal 执行精确小数计算](https://www.cnblogs.com/winner-0715/p/6761168.html)
- BigDecimal使用非String构造方法初始化，会出现精度缺失问题。--特别注意byArvin
- 解决方法一：API 建议优先使用 String 构造方法【必须使用String】
- PS:千万不能随随便便使用 BigDecimal(double) 构造器来创建 BigDecimal 对象，因为该构造器是根据它的参数的精确值来创建实例对象的，
  该构造方法的结果还是有一定的不可预知性
```
错误使用一：
public class Test {
    public static void main(String[] args) {
        System.out.println(0.4 + 0.8); // = 1.2 ?
        System.out.println(2 - 1.1);   // = 0.9 ?
        System.out.println(0.2 * 3);  // = 0.6 ?
        System.out.println(1.2 / 3); // = 0.4 ?
    }
}
----------------------
错误使用二：
public class Test {
    public static void main(String[] args) {
        System.out.println(new BigDecimal(0.4).add(new BigDecimal(0.8)));// = 1.2 ?
        System.out.println(new BigDecimal(2).subtract(new BigDecimal(1.1)));// = 0.9 ?
        System.out.println(new BigDecimal(0.2).multiply(new BigDecimal(3)));// = 0.6 ?
        System.out.println(new BigDecimal(1.2).divide(new BigDecimal(3)));// = 0.4 ?
    }
}
----------------------
正确使用二：
public class Test {
    public static void main(String[] args) {

        System.out.println(new BigDecimal("0.4").add(new BigDecimal("0.8")));  // = 1.2 √
        System.out.println(new BigDecimal("2").subtract(new BigDecimal("1.1"))); // = 0.9 √
        System.out.println(new BigDecimal("0.2").multiply(new BigDecimal("3")));// = 0.6 √
        System.out.println(new BigDecimal("1.2").divide(new BigDecimal("3"))); // = 0.4 √

    }
}
----------------------

```