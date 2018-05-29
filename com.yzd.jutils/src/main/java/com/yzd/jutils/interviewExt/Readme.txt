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



问题4：ArrayBlockingQueue, CountDownLatch类的作用与使用场景
答：


