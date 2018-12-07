
### [java8 list统计（求和、最大、最小、平均）-Stream](https://blog.csdn.net/hhs57/article/details/82666703)

```
java8 list统计（求和、最大、最小、平均）
----------------------------------------------------------------------------------
interge
list.stream().map(User::getHeight).sum()//和
list.stream().map(User::getHeight).max()//最大
list.stream().map(User::getHeight).min()//最小
list.stream().map(User::getHeight).average()//平均值
double
list.stream().mapToDouble(User::getHeight).sum()//和
list.stream().mapToDouble(User::getHeight).max()//最大
list.stream().mapToDouble(User::getHeight).min()//最小
list.stream().mapToDouble(User::getHeight).average()//平均值
----------------------------------------------------------------------------------
Double示例：

public class HelloWorld {
    private static final DecimalFormat df = new DecimalFormat("0.00");//保留两位小数点
    public static void main(String[] args) {
        Random random = new Random();
        List<User> list = new ArrayList<>();
        for(int i=1;i<=5;i++) {
            double weight = random.nextDouble() * 100 + 100;//随机身高：100-200
            User u = new User(i, "用户-" + i, weight);
            list.add(u);
        }
        System.out.println("用户：" + list);
        double sum = list.stream().mapToDouble(User::getHeight).sum();
        System.out.println("身高 总和：" + df.format(sum));
        double max = list.stream().mapToDouble(User::getHeight).max().getAsDouble();
        System.out.println("身高 最大：" + df.format(max));
        double min = list.stream().mapToDouble(User::getHeight).min().getAsDouble();
        System.out.println("身高 最小：" + df.format(min));
        double average = list.stream().mapToDouble(User::getHeight).average().getAsDouble();
        System.out.println("身高 平均：" + df.format(average));

    }

----------------------------------------------------------------------------------
BigDecimal示例：

public class HelloWorld {
    private static final DecimalFormat df = new DecimalFormat("0.00");//保留两位小数点
    public static void main(String[] args) {
        Random random = new Random();
        List<User> list = new ArrayList<>();
        for(int i=1;i<=5;i++) {
            double weight = random.nextDouble() * 100 + 100;//随机身高：100-200
            list.add(new User(i, new BigDecimal(weight).setScale(BigDecimal.ROUND_HALF_UP, 2)));
        }
        System.out.println("list：" + list);
        BigDecimal add = list.stream().map(User::getHeight).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("身高 总和：" + df.format(add));
        Optional<User> max = list.stream().max((u1, u2) -> u1.getHeight().compareTo(u2.getHeight()));
        System.out.println("身高 最大：" + df.format(max.get().getHeight()));
        Optional<User> min = list.stream().min((u1, u2) -> u1.getHeight().compareTo(u2.getHeight()));
        System.out.println("身高 最小：" + df.format(min.get().getHeight()));

    }
```