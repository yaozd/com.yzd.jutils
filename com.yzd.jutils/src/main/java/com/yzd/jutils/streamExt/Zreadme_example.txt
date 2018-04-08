
Java8 Streams API 学习笔记
https://blog.csdn.net/ClementAD/article/details/53085322
主要的使用/处理对象（数据源）：

数组、列表等集合（Collection）对象，比如：
代码1：

// 1. Individual values
Stream stream = Stream.of("a", "b", "c");

// 2. Arrays
String [] strArray = new String[] {"a", "b", "c"};
stream = Stream.of(strArray);
stream = Arrays.stream(strArray);

// 3. Collections
List<String> list = Arrays.asList(strArray);
stream = list.stream();

// 4. Int Stream
IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
IntStream.range(1, 3).forEach(System.out::println);
IntStream.rangeClosed(1, 3).forEach(System.out::println);

不过，Steam的数据源本身可以是无限的，比如Stream Generator、IO channel（例如java.io.BufferedReader.lines()）等等……

Stream的主要功能：

聚合操作（aggregate operation）
大批量数据操作 (bulk data operation)
例如：
- 求平均值
- 求最大值
- 筛选（去除无效记录、按条件过滤、等）
- 排序

这些操作不会修改原来的Stream，而是生成新的；并且支持多线程并行处理。
一个简单的例子：
代码2：

List<Integer> transactionsIds =
    transactions.parallelStream(). //支持并行操作的流
    filter(t -> t.getType() == Transaction.GROCERY). //筛选
    sorted(comparing(Transaction::getValue).reversed()). //排序
    map(Transaction::getId). //映射（投影）操作，只选取id
    collect(toList()); //生成一个新的集合

简单地说，对Stream的使用就是实现一个 filter-map-reduce 过程，产生一个最终结果，或者导致一个副作用（side effect）。

Stream和Iterator的异同：

相同之处：

都是用来遍历和处理数组和列表等集合
都只能是单向的遍历
不同之处：

Stream可以并行地利用多线程操作；而Iterator只能单线程操作
Stream支持的数据源比Iterator多
Stream转换为其他数据结构的方法：

代码3：

// 1. Array
String[] strArray1 = stream.toArray(String[]::new);

// 2. Collection
List<String> list1 = stream.collect(Collectors.toList());
List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
Set set1 = stream.collect(Collectors.toSet());
Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));

// 3. String
String str = stream.collect(Collectors.joining()).toString();

// 4. toMap
Map<Integer, Integer> map = stream.collect(
    Collectors.toMap(
        e -> e.get("key"),
        e -> e.get("value")
    )
);

可见，主要是toArray()和collect()两个方法。

Stream的操作类型：

Intermediate： 一个流可以后面跟随零个或多个 intermediate 操作。其目的主要是打开流，做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的（lazy），就是说，仅仅调用到这类方法，并没有真正开始流的遍历。
Terminal： 一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。所以这必定是流的最后一个操作。Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，或者一个 side effect。
short-circuiting：
对于一个intermediate操作，如果它接受的是一个无限大（infinite/unbounded）的 Stream，但返回一个有限的新 Stream。
对于一个terminal操作，如果它接受的是一个无限大的Stream，但能在有限的时间计算出结果。
那么，对一个Stream进行多次Intermediate操作，是不是需要执行多次for循环？
不是的。因为Intermediate操作是lazy的，多个这样的操作只会在Terminal操作的时候才融合起来，在一次循环中就可以完成了。

常见的操作可以归类：

Intermediate：
map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
Terminal：
forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
Short-circuiting：
anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit
各种操作的实例：

map/flatMap： 映射、投影
代码4

//字符串列表转换成大写：
List<String> output = wordList.stream().
    map(String::toUpperCase).
    collect(Collectors.toList());

//整数转换为平方数：
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
List<Integer> squareNums = nums.stream().
    map(n -> n * n).
    collect(Collectors.toList());

//把多个List合并成一个List：
Stream<List<Integer>> inputStream = Stream.of(
    Arrays.asList(1),
    Arrays.asList(2, 3),
    Arrays.asList(4, 5, 6)
 );
Stream<Integer> outputStream = inputStream.
    flatMap((childList) -> childList.stream());

filter： 筛选、过滤
代码5

//只留下偶数：
Integer[] sixNums = {1, 2, 3, 4, 5, 6};
Integer[] evens =
    Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);

//从文件中获取所有单词
List<String> output = reader.lines(). //数据来源，可以是文件的reader
    flatMap(line -> Stream.of(line.split(REGEXP))). //对每行拆分出单词
    filter(word -> word.length() > 0). //过滤掉无效的单词
    collect(Collectors.toList());

forEach： 接收一个Lambda表达式，然后在Stream的每一个元素上执行该表达式
代码6

//从花名册中筛选出男性然后打印出来
roster.stream()
    .filter(p -> p.getGender() == Person.Sex.MALE)
    .forEach(p -> System.out.println(p.getName()));

注意： forEach是terminal操作，如果要对Stream做多次输出操作，可以使用peek（偷窥）代替：
代码7

//peek对每个元素执行操作并返回一个新的 Stream
Stream.of("one", "two", "three", "four")
 .filter(e -> e.length() > 3)
 .peek(e -> System.out.println("Filtered value: " + e))
 .map(String::toUpperCase)
 .peek(e -> System.out.println("Mapped value: " + e))
 .collect(Collectors.toList());

reduce： 把Stream中的元素组合起来
　　它接收一个起始值（种子，第一个参数），然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第n个元素组合。字符串拼接、数值的sum、min、max、average都是特殊的reduce。例如Stream的sum就相当于
　　Integer sum = integers.reduce(0, (a, b) -> a+b); 或
　　Integer sum = integers.reduce(0, Integer::sum);
　　也有没有起始值的情况，这时会把Stream的前面两个元素组合起来，返回的是Optional对象。
代码8

// 字符串连接，concat = "ABCD"
String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);

// 求最小值，minValue = -3.0
double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);

// 求和，sumValue = 10, 有起始值
int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);

// 求和，sumValue = 10, 无起始值，这里reduce返回的是Optional，需要get()一下
sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();

// 过滤，字符串连接，concat = "ace"
concat = Stream.of("a", "B", "c", "D", "e", "F").
 filter(x -> x.compareTo("Z") > 0).
 reduce("", String::concat);

limit/skip： 返回Stream的前面n个元素 / 扔掉前n个元素
代码9

List<String> personList2 = persons.stream()
    .map(Person::getName)
    .limit(10)
    .skip(3)
    .collect(Collectors.toList());
System.out.println(personList2);

sorted： 排序
代码10

List<Person> personList2 = persons.stream()
    .limit(2)
    .sorted((p1, p2)->p1.getName().compareTo(p2.getName()))
    .collect(Collectors.toList());
System.out.println(personList2);

min/max/distinct 找出最小、最大、排除重复
代码11

//找出最长的一行：
BufferedReader br = new BufferedReader(new FileReader("c:\\SUService.log"));
int longest = br.lines().
 mapToInt(String::length).
 max().
 getAsInt();
br.close();
System.out.println(longest);

// 找出全文的单词，合并重复的，转小写，并排序
List<String> words = br.lines().
 flatMap(line -> Stream.of(line.split(" "))).
 filter(word -> word.length() > 0).
 map(String::toLowerCase).
 distinct().
 sorted().
 collect(Collectors.toList());
br.close();
System.out.println(words);

match：allMatch/anyMatch/noneMatch 根据是否match返回true/false
代码12

boolean isAllAdult = persons.stream().
    allMatch(p -> p.getAge() > 18);

boolean isThereAnyChild = persons.stream().
    anyMatch(p -> p.getAge() < 12);

其他高级操作：

generate/Supplier/iterate 生成一个流：
代码13

//生成10个0~100之间的随机数：
IntStream.generate(() -> (int) (System.nanoTime() % 100)).
    limit(10).
    forEach(System.out::println);

//生成一个有10个Person对象的流
Stream.generate(new PersonSupplier()).
    limit(10).
    forEach(p -> System.out.println(p.getName() + ", " + p.getAge()));

private class PersonSupplier implements Supplier<Person> {
    private int index = 0;
    private Random random = new Random();

    @Override
    public Person get() {
        return new Person(index++, "StormTestUser" + index, random.nextInt(100));
    }
}

//生成一个等差数列
Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));

groupingBy/partitioningBy 分组/归组：
代码14

//按照年龄归组：
Map<Integer, List<Person>> personGroups =
    Stream.generate(new PersonSupplier()).
    limit(100).
    collect(Collectors.groupingBy(Person::getAge));

//按照是否小于18岁进行分组：
Map<Boolean, List<Person>> children =
    Stream.generate(new PersonSupplier()).
    limit(100).
    collect(Collectors.partitioningBy(p -> p.getAge() < 18));

版权声明：本文为原创文章，转载请注明转自Clement-Xu的csdn博客。 https://blog.csdn.net/ClementAD/article/details/53085322