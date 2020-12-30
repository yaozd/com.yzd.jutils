# 位运算集锦
- [位运算集锦](https://www.iteye.com/blog/kenby-1011480)
```
文中2'k代表2的k次方

 

1 除以2的k次幂可以用位运算：

n/2'k == n>>k

 

2 对2的k次幂取余数可以用位运算：

n%2'k == n & ((1<<k)-1)

比如 100%32

100的二进制为          1100100

((1<<5)-1)等于31为  0011111

两个数相与即得 100，故

100%32 = 4

 

3 对于整数n，从低位开始，把它的第k位（0<=k<=31）置为1的操作为：

n = n | (1<<k)

 

4 对于整数n，从低位开始，把它的第k位（0<=k<=31）置为0的操作为：

n = n & ~(1<<k)

 

 

5 对于整数n，从低位开始，测试它的第k位（0<=k<=31）是否为1，若为1，返回一个大于0的数，否则返回0

return  n & (1<<k)

 

6 对于整数n，判断它是奇数还是偶数

若n & 1大于0，则n是奇数，否则n是偶数

 

7 对于整数n，若n是奇数，则把n减1变成偶数，若n是偶数，则把n加1变成奇数

n = n ^ 1

 

8 对于奇数n，有如下性质

(n-1) ^ n ==1

 

9 最大的int

01 1111111111 1111111111 1111111111

MAX_INT = ~(1<<31)

 

10 最小的int

10 0000000000 0000000000 0000000000

MIN_INT = (1<<31)

 

11 把最低位的1变为0

比如： 111000   ---> 110000

n = n - (n&-n)

 

12 判断两个整数数是否同号

#define MASK 0x80000000

flag = (x & MASK) ^ (y & MASK)

如果flag为0，说明不同号，否则同号

 

13 交换两个值，不用临时变量


想将ａ和ｂ的值互换，可以用以下赋值语句实现：
    ａ＝a∧b;
    ｂ＝b∧a;
    ａ＝a∧b;

 

 

14 bitset的C语言实现
```
- [位运算实现权限管理及实现原理](https://blog.csdn.net/csdnbeyoung/article/details/88389233)
- [利用位运算实现权限控制](https://blog.csdn.net/weixin_38938840/article/details/104730469)
```

/**
 * 用位运算实现用户权限分配
 * 利用位运算最多可以实现32种权限，对于目前市面上的系统足够了
 * 位运算的执行效率是非常快的
 * @author James Lee
 *
 */
public class Permisson {
	
	private static final int ALLOW_SELECT = 1 << 0;// 查询1   
	private static final int ALLOW_INSERT = 1 << 1;// 新增2
	private static final int ALLOW_UPDATE = 1 << 2;// 修改4
	private static final int ALLOW_DELETE = 1 << 3;// 删除8
	private static final int ALLOW_PAGE = 1 << 4;// 分页16
	private static final int ALLOW_LOGIN = 1 << 5;// 登录32
	
	// 存储目前 权限的状态比如 拥有查询和修改 flag = 1+4
	private int flag;
 
	public Permisson(int flag) {
		super();
		this.flag = flag;
	}
	
	// 增加用户权限  批量or单个
	public void add(int per) {
		flag = flag | per;
	}
	// 删除用户权限  批量or单个
	public void del(int per) {
		flag = flag &~ per; 
	}
	// 查询用户是否具有某个权限
	public boolean isAllow(int per) {
		return ((flag & per) == per);
		
	}
	// 查询用户是否不具有某个权限
	public boolean isNotAllow(int per) {
		return ((flag & per) == 0);
		
	}
	public static void main(String[] args) {
		int flag = ALLOW_DELETE + ALLOW_INSERT + ALLOW_LOGIN /* + ALLOW_PAGE + ALLOW_SELECT + ALLOW_UPDATE */;
		System.out.println("当前权限状态：" + flag);
		Permisson permisson = new Permisson(flag);
		System.out.println("当前用户具有查询权限吗？" + permisson.isAllow(ALLOW_SELECT));
		System.out.println("当前用户具有新增权限吗？" + permisson.isAllow(ALLOW_INSERT));
		System.out.println("当前用户具有修改权限吗？" + permisson.isAllow(ALLOW_UPDATE));
		System.out.println("当前用户具有删除权限吗？" + permisson.isAllow(ALLOW_DELETE));
		System.out.println("当前用户具有分页权限吗？" + permisson.isAllow(ALLOW_PAGE));
		System.out.println("当前用户具有登录权限吗？" + permisson.isAllow(ALLOW_LOGIN));
		System.out.println("*******************************************************************************");
		System.out.println("当前用户具有查询权限吗？" + permisson.isAllow(ALLOW_SELECT));
		System.out.println("取消用户的删除权限！");permisson.del(ALLOW_DELETE);
		System.out.println("当前用户具有删除权限吗？" + permisson.isAllow(ALLOW_DELETE));
		System.out.println("给用户的新增查询、修改的权限！");permisson.add(ALLOW_SELECT+ALLOW_UPDATE);
		System.out.println("当前用户是否不具备分页查询权限？" + permisson.isNotAllow(ALLOW_PAGE));
		System.out.println("*******************************************************************************");
		System.out.println("当前用户具有查询权限吗？" + permisson.isAllow(ALLOW_SELECT));
		System.out.println("当前用户具有新增权限吗？" + permisson.isAllow(ALLOW_INSERT));
		System.out.println("当前用户具有修改权限吗？" + permisson.isAllow(ALLOW_UPDATE));
		System.out.println("当前用户具有删除权限吗？" + permisson.isAllow(ALLOW_DELETE));
		System.out.println("当前用户具有分页权限吗？" + permisson.isAllow(ALLOW_PAGE));
		System.out.println("当前用户具有登录权限吗？" + permisson.isAllow(ALLOW_LOGIN));
	}
}
```