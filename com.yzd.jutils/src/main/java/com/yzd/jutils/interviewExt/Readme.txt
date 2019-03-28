java 面试题-20180529
问题1：HashMap和HashTable的区别？
答：


问题2：equals 和 == 的区别？输出结果？
Long userId=127L;
Long authorId=127L;
System.out.println(userId==authorId);
userId=128L;
authorId=128L;
System.out.println(userId==authorId);
Long x=new Long(127);
Long y=new Long(127);
System.out.println(x==y);
System.out.println(x.equals(y));
答：
true
false
false
true




问题3：下面语句的输出结果？
package com.bird.classLoad;
public class Test1 {
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Singleton s = Singleton.getSingleton();
		System.out.println("counter1 = "+ s.counter1);
		System.out.println("counter2 = "+s.counter2);
	}
}
class Singleton{
	private static Singleton singleton = new Singleton();
	public static int counter1;
	public static int counter2 = 0;
	public Singleton(){
		counter1++;
		counter2++;
	}
	public static Singleton getSingleton(){
		return singleton;
	}
}
答：
counter1 = 1
counter2 = 0


问题4：ArrayBlockingQueue, CountDownLatch类的作用与使用场景
答：

问题5：mysql的事务四个特性以及事务的四个隔离级别
https://blog.csdn.net/csdnxingyuntian/article/details/57081233
spring的4种事务特性，5种隔离级别，7种传播行为
https://blog.csdn.net/weixin_38070406/article/details/78157603?utm_source=blogkpcl3


问题6：集合的默认初始容量、加载因子、扩容增量
http://www.cnblogs.com/lanzhi/p/6467269.html
Map是一个双列集合
==
HashMap：默认初始容量为16
（为何是16：16是2^4，可以提高查询效率，另外，32=16<<1       -->至于详细的原因可另行分析，或分析源代码）
加载因子为0.75：即当 元素个数 超过 容量长度的0.75倍 时，进行扩容
扩容增量：原容量的 1 倍
如 HashSet的容量为16，一次扩容后是容量为32
==
Java和guava关于hashmap在初始化的时候最好给个初始容量，避免扩容引起性能问题的探究。
https://blog.csdn.net/qq_27093465/article/details/52401308
guava里面有工具类Maps，可以很方便的创建一个集合，并且，带上合适的大小初始化值。具体如下：

Map<String, Object> map = Maps.newHashMapWithExpectedSize(7);

问题7：[BigDecimal 执行精确小数计算](https://www.cnblogs.com/winner-0715/p/6761168.html)
BigDecimal使用非String构造方法初始化，会出现精度缺失问题。
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