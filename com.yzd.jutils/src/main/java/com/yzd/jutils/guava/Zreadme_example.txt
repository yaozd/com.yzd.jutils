

使用guava带来的方便
http://iamzhongyong.iteye.com/blog/1981199


guava是在原先google-collection 的基础上发展过来的，是一个比较优秀的外部开源包，最近项目中使用的比较多，列举一些点。刚刚接触就被guava吸引了。。。
    ​    ​这个是guava的一个官网ppt里面的介绍：

其实你可能发现，一些基本的校验完全可以自己写，但是。。
这些东西仅仅是看起来比较简单，实际上可能比我们想想的要复杂；
用一个公共的lib，别人可能更加容易理解你的代码；
当你用一个主流的开源包的时候，你可能在主流中；
当你发现一个可以提升的点的 时候，如果仅仅是修改自己的私有包，可能没有多少人能够受益；

1、非空检查，参数在赋值的时候就做一个检查

String inputName = "iamzhongyong";
String name = Preconditions.checkNotNull(inputName);
这个减少了代码行数，认为变量必须赋值才可以使用。
多条件校验

String inputName = "iamzhongyong";
Preconditions.checkArgument(inputName!=null && !"".equals(inputName),"input is null");
能够有效的减少代码行数
此外Preconditions中还有多个校验方法，可以优雅的进行判断了。

2、优雅的使用null
空指针异常是我们再写代码的时候经常遇到的，guava提供了Optional来让你不得不思考null的问题
Optional.of(T)：获得一个Optional对象，其内部包含了一个非null的T数据类型实例，若T=null，则立刻报错。
Optional.absent()：获得一个Optional对象，其内部包含了空值，内部代码看，是返回了Absent的一个实例。
Optional.fromNullable(T)：将一个T的实例转换为Optional对象，T的实例可以不为空，也可以为空[Optional.fromNullable(null)，和Optional.absent()等价。

Optional<String> name = Optional.of("iamzhongyong");
        if(name.isPresent()){
            System.out.println(name.get());
        }

3、Object中常用的方法
例如我们常见的equals方法和hashCode方法，Objects中都有与之对应的方法提供；
同时，toString是我们比较常用的，Objects.toStringHelper方法十分方便

public class StringHelp {
    public String name;
    public int age;
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("age", this.age).toString();
    }
}

4、字符转链接（Joiner 类）

public static void main(String[] args) {
        List<String> names = Lists.newArrayList();
        names.add("iamzhongyong");
        names.add("bixiao.zy");
        StringBuilder sb = new StringBuilder();
        String rs = Joiner.on("--").appendTo(sb, names).toString();
        System.out.println(rs);
    }

5、字符串分隔符
这个时候你可能说JDK自己有分割器，但是其实guava这个更加灵活，其实JDK的那个性能不咋滴

public static void main(String[] args) {
        String s = "dd  sfsfs  , dsfsf,ssfdfsdffsdfsf.sdfsfs,msfds";
        for(String name : Splitter.on(",").trimResults().split(s)){
            System.out.println(name);
        }
    }

6、不可变的集合（Immutable）
这里就拿一个map为例子，初始化一个集合，然后向里面放置几个不变的集合

Map<Integer,String> mapa = new HashMap<Integer,String>();
        mapa.put(122, "iamzhongyong");
        mapa.put(1222, "bixiao.zy");
现在用Immutable就可以很简单的完成了

ImmutableMap<Integer, String> map = ImmutableMap.of(122,"iamzhongyong",1222,"bixiao.zy");
        System.out.println(map.toString());

7、一个key对应多个Valve的情况
我们经常遇到这种，一个key有多个value的情况，一般的做法如下：Map<Key,List<Value>>的数据结构去搞，
但是现在又办法了，可以使用Multimap来解决这个问题，简洁明了

Multimap<Integer, String> keyValues = ArrayListMultimap.create();
        keyValues.put(1, "a");
        keyValues.put(1, "b");
        keyValues.put(2, "c");
        System.out.println(keyValues.toString());

8、本地缓存
guava的缓存设计的比较巧妙，这里就简单介绍一下，guava的缓存创建分为两种，一种是CacheLoader的方式，一种是callback的方式
参数的话就不多介绍了，直接在CacheBuilder中可以看到，类似这个

CacheBuilder.newBuilder()
            .maximumSize(10)
            .initialCapacity(3)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();
下面这个例子是callback的形式
public void getNameFromLocalCache() throws Exception{
        //new一个cache的对象出来
        Cache<String/*name*/,String/*nick*/> cache = CacheBuilder.newBuilder().maximumSize(10).build();
        //在get的时候，如果缓存里面没有，则通过实现一个callback的方法去获取
        String name = cache.get("bixiao", new Callable<String>() {
            public String call() throws Exception {
                return "bixiao.zy"+"-"+"iamzhongyong";
            }
        });
        System.out.println(name);
        System.out.println(cache.toString());
    }
下面这个例子是LoadingCache的
public void getNameLoadingCache(String name) throws Exception{
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            //设置大小，条目数
            .maximumSize(20)
            //设置失效时间，创建时间
            .expireAfterWrite(20, TimeUnit.SECONDS)
            //设置时效时间，最后一次被访问
            .expireAfterAccess(20, TimeUnit.HOURS)
            //移除缓存的监听器
            .removalListener(new RemovalListener<String, String>() {
                public void onRemoval(RemovalNotification<String, String> notification) {
                    System.out.println("有缓存数据被移除了");
                }})
            //缓存构建的回调
            .build(new CacheLoader<String, String>(){//加载缓存
                @Override
                public String load(String key) throws Exception {
                    return key + "-" + "iamzhongyong";
                }
        });

        System.out.println(cache.get(name));
        cache.invalidateAll();
    }


9、集合 并集、交集、补集的方法

    HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
    HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);

    SetView union = Sets.union(setA, setB);
    System.out.println(union);

    SetView difference = Sets.difference(setA, setB);
    System.out.println(difference);

    SetView intersection = Sets.intersection(setA, setB);
    System.out.println(intersection);
​
==============================================================end
private static List<Person> getOldestMan(List<Person> personList)
   {
       // If nullableReference is non-null, returns an Optional instance containing that reference; otherwise returns absent().
       //如果非空应用是非空的，返回非控制的实例，否则返回缺失
       Optional<List<Person>> personListField= Optional.fromNullable(personList);
        //注意，false时候，显示出调试信息
       Preconditions.checkState(personListField.isPresent(),"personList must not be null");

       // 1,找出所有的男人

       // 2,找出年纪最大的
         System.out.println("ok");
       return null;
   }

public static void main(String[] args)
   {
       getOldestMan(null);

   }