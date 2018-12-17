
lombok的使用详解（最详尽的解释，覆盖讲解所有可用注解）
https://blog.csdn.net/f641385712/article/details/82081900

使用lombok提高编码效率
https://blog.csdn.net/v2sking/article/details/73431364
Lombok注解详解

全局配置文件

我们可以从项目根目录下新建一个lombok.config（当然目录不是固定的，lombok会搜索所有lombok.config文件）
在这个文件加入一行
config.stopBubbling = true
表示该文件目录为根目录，lombok将从该目录下开始搜索。
每个子目录都可以配置lombok.config 作用范围只在该目录下，并且覆盖父目录的配置。


Lombok通常为所有生成的节点生成注释,添加@javax.annotation.Generated 。

可以用:

lombok.addJavaxGeneratedAnnotation = false 设置取消



下面看下lombok提供了哪些有趣的注解。



1.@val @var
使用Lombok ，java也能够像javascript一样使用弱类型定义变量了

val注解变量申明是final类型 var注解变量是非final类型

[java] view plain copy
 import java.util.ArrayList;
import java.util.HashMap;
import lombok.val;

public class ValExample {
  public String example() {
    val example = new ArrayList<String>();
    example.add("Hello, World!");
    val foo = example.get(0);
    return foo.toLowerCase();
  }

  public void example2() {
    val map = new HashMap<Integer, String>();
    map.put(0, "zero");
    map.put(5, "five");
    for (val entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
}
翻译后

[java] view plain copy
<span style="font-weight:normal;">import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ValExample {
  public String example() {
    final ArrayList<String> example = new ArrayList<String>();
    example.add("Hello, World!");
    final String foo = example.get(0);
    return foo.toLowerCase();
  }

  public void example2() {
    final HashMap<Integer, String> map = new HashMap<Integer, String>();
    map.put(0, "zero");
    map.put(5, "five");
    for (final Map.Entry<Integer, String> entry : map.entrySet()) {
      System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
    }
  }
}</span>

2.@NonNull
在方法或构造函数的参数上使用@NonNull，lombok将生成一个空值检查语句。
[java] view plain copy
<span style="font-weight:normal;"> import lombok.NonNull;

public class NonNullExample extends Something {
  private String name;

  public NonNullExample(@NonNull Person person) {
    super("Hello");
    this.name = person.getName();
  }
}</span>
翻译后
[java] view plain copy
<span style="font-weight:normal;">import lombok.NonNull;

public class NonNullExample extends Something {
  private String name;

  public NonNullExample(@NonNull Person person) {
    super("Hello");
    if (person == null) {
      throw new NullPointerException("person");
    }
    this.name = person.getName();
  }
}</span>


3.@Cleanup
使用该注解能够自动释放io资源
[java] view plain copy
<span style="font-weight:normal;"> import lombok.Cleanup;
import java.io.*;

public class CleanupExample {
  public static void main(String[] args) throws IOException {
    @Cleanup InputStream in = new FileInputStream(args[0]);
    @Cleanup OutputStream out = new FileOutputStream(args[1]);
    byte[] b = new byte[10000];
    while (true) {
      int r = in.read(b);
      if (r == -1) break;
      out.write(b, 0, r);
    }
  }
}</span>
翻译后
[java] view plain copy
<span style="font-weight:normal;">import java.io.*;

public class CleanupExample {
  public static void main(String[] args) throws IOException {
    InputStream in = new FileInputStream(args[0]);
    try {
      OutputStream out = new FileOutputStream(args[1]);
      try {
        byte[] b = new byte[10000];
        while (true) {
          int r = in.read(b);
          if (r == -1) break;
          out.write(b, 0, r);
        }
      } finally {
        if (out != null) {
          out.close();
        }
      }
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }
}</span>
当然从1.7开始jdk已经提供了try with resources的方式自动回收资源

[java] view plain copy
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
4.@Getter/@Setter

[java] view plain copy
<span style="font-weight:normal;">import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class GetterSetterExample {
  /**
   * Age of the person. Water is wet.
   *
   * @param age New value for this person's age. Sky is blue.
   * @return The current value of this person's age. Circles are round.
   */
  @Getter @Setter private int age = 10;

  /**
   * Name of the person.
   * -- SETTER --
   * Changes the name of this person.
   *
   * @param name The new value.
   */
  @Setter(AccessLevel.PROTECTED) private String name;

  @Override public String toString() {
    return String.format("%s (age: %d)", name, age);
  }
}</span>
翻译后
[java] view plain copy
<span style="font-weight:normal;"> public class GetterSetterExample {
  /**
   * Age of the person. Water is wet.
   */
  private int age = 10;

  /**
   * Name of the person.
   */
  private String name;

  @Override public String toString() {
    return String.format("%s (age: %d)", name, age);
  }

  /**
   * Age of the person. Water is wet.
   *
   * @return The current value of this person's age. Circles are round.
   */
  public int getAge() {
    return age;
  }

  /**
   * Age of the person. Water is wet.
   *
   * @param age New value for this person's age. Sky is blue.
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Changes the name of this person.
   *
   * @param name The new value.
   */
  protected void setName(String name) {
    this.name = name;
  }
}</span>
扩展配置：
lombok.accessors.chain = [true | false] (default: false)如果设置为true，生成的setter将返回this(而不是void)，通过这个配置我们可以像jquery一样愉快的链式编程了。可以在类加增加一个@Accessors 注解 配置chain属性，优先于全局配置。
lombok.accessors.fluent = [true | false] (default: false)如果设置为true，生成的getter和setter将不会使用bean标准的get、is或set进行前缀;相反，方法将使用与字段相同的名称(减去前缀)。可以在类加增加一个@Accessors注解，配置fluent属性，优先于全局配置
lombok.accessors.prefix += a field prefix (default: empty list)给getter/setter方法增加前缀 例如配置 +=M 原有的 getFoo方法将变为getMFoo方法。 lombok.getter.noIsPrefix = [true | false] (default: false)如果设置为true，那么boolean型字段生成的getter将使用get前缀而不是默认的is前缀


5.@ToString
生成一个toString方法，log debug神器
默认的toString格式为：ClassName(fieldName= fieleValue ,fieldName1=fieleValue)
[java] view plain copy
<span style="font-weight:normal;">import lombok.ToString;

@ToString(exclude="id")
public class ToStringExample {
  private static final int STATIC_VAR = 10;
  private String name;
  private Shape shape = new Square(5, 10);
  private String[] tags;
  private int id;

  public String getName() {
    return this.getName();
  }

  @ToString(callSuper=true, includeFieldNames=true)
  public static class Square extends Shape {
    private final int width, height;

    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
  }
}</span>
翻译后
[java] view plain copy
import java.util.Arrays;

ublic class ToStringExample {
 private static final int STATIC_VAR = 10;
 private String name;
 private Shape shape = new Square(5, 10);
 private String[] tags;
 private int id;

 public String getName() {
   return this.getName();
 }

 public static class Square extends Shape {
   private final int width, height;

   public Square(int width, int height) {
     this.width = width;
     this.height = height;
   }

   @Override public String toString() {
     return "Square(super=" + super.toString() + ", width=" + this.width + ", height=" + this.height + ")";
   }
 }

 @Override public String toString() {
   return "ToStringExample(" + this.getName() + ", " + this.shape + ", " + Arrays.deepToString(this.tags) + ")";
 }
扩展配置：
lombok.toString.includeFieldNames = [true | false] (default: true)
通常，lombok以fieldName=fieldValue的形式为每个字段生成一个toString响应的片段。如果设置为false，lombok将省略字段的名称，可以在该注解上配置属性includeFieldNames来标示包含的字段，这样可以覆盖默认配置。

lombok.toString.doNotUseGetters = [true | false] (default: false)
如果设置为true，lombok将直接访问字段，而不是在生成tostring方法时使用getter(如果可用)。可以在该注解上配置属性doNotUseGetters来标示不使用getter的字段，这样可以覆盖默认配置。

6.@EqualsAndHashCode

给类增加equals和hashCode方法

[java] view plain copy
 import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude={"id", "shape"})
public class EqualsAndHashCodeExample {
  private transient int transientVar = 10;
  private String name;
  private double score;
  private Shape shape = new Square(5, 10);
  private String[] tags;
  private int id;

  public String getName() {
    return this.name;
  }

  @EqualsAndHashCode(callSuper=true)
  public static class Square extends Shape {
    private final int width, height;

    public Square(int width, int height) {
      this.width = width;
      this.height = height;
    }
  }
}
翻译后
[java] view plain copy
import java.util.Arrays;

ublic class EqualsAndHashCodeExample {
 private transient int transientVar = 10;
 private String name;
 private double score;
 private Shape shape = new Square(5, 10);
 private String[] tags;
 private int id;

 public String getName() {
   return this.name;
 }

 @Override public boolean equals(Object o) {
   if (o == this) return true;
   if (!(o instanceof EqualsAndHashCodeExample)) return false;
   EqualsAndHashCodeExample other = (EqualsAndHashCodeExample) o;
   if (!other.canEqual((Object)this)) return false;
   if (this.getName() == null ? other.getName() != null : !this.getName().equals(other.getName())) return false;
   if (Double.compare(this.score, other.score) != 0) return false;
   if (!Arrays.deepEquals(this.tags, other.tags)) return false;
   return true;
 }

 @Override public int hashCode() {
   final int PRIME = 59;
   int result = 1;
   final long temp1 = Double.doubleToLongBits(this.score);
   result = (result*PRIME) + (this.name == null ? 43 : this.name.hashCode());
   result = (result*PRIME) + (int)(temp1 ^ (temp1 >>> 32));
   result = (result*PRIME) + Arrays.deepHashCode(this.tags);
   return result;
 }

 protected boolean canEqual(Object other) {
   return other instanceof EqualsAndHashCodeExample;
 }

 public static class Square extends Shape {
   private final int width, height;

   public Square(int width, int height) {
     this.width = width;
     this.height = height;
   }

   @Override public boolean equals(Object o) {
     if (o == this) return true;
     if (!(o instanceof Square)) return false;
     Square other = (Square) o;
     if (!other.canEqual((Object)this)) return false;
     if (!super.equals(o)) return false;
     if (this.width != other.width) return false;
     if (this.height != other.height) return false;
     return true;
   }

   @Override public int hashCode() {
     final int PRIME = 59;
     int result = 1;
     result = (result*PRIME) + super.hashCode();
     result = (result*PRIME) + this.width;
     result = (result*PRIME) + this.height;
     return result;
   }

   protected boolean canEqual(Object other) {
     return other instanceof Square;
   }
 }
扩展配置：

lombok.config增加：

lombok.equalsAndHashCode.doNotUseGetters = [true | false] (default: false)如果设置为true，lombok将直接访问字段，而不是在生成equals和hashcode方法时使用getter(如果可用)。
可以在该注解上配置属性donotusegetter来标示不使用getter的字段，这样可以覆盖默认配置。
lombok.equalsAndHashCode.callSuper = [call | skip | warn] (default: warn)如果设置为call，lombok将生成对hashCode的超类实现的调用。如果设置为skip，则不会生成这样的调用。默认行为warn类似于skip，并带有附加警告。



7.@NoArgsConstructor, @RequiredArgsConstructor and @AllArgsConstructor
给类增加无参构造器 指定参数的构造器 包含所有参数的构造器

[java] view plain copy
 import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ConstructorExample<T> {
  private int x, y;
  @NonNull private T description;

  @NoArgsConstructor
  public static class NoArgsExample {
    @NonNull private String field;
  }
}
翻译后
[java] view plain copy
 public class ConstructorExample<T> {
  private int x, y;
  @NonNull private T description;

  private ConstructorExample(T description) {
    if (description == null) throw new NullPointerException("description");
    this.description = description;
  }

  public static <T> ConstructorExample<T> of(T description) {
    return new ConstructorExample<T>(description);
  }

  @java.beans.ConstructorProperties({"x", "y", "description"})
  protected ConstructorExample(int x, int y, T description) {
    if (description == null) throw new NullPointerException("description");
    this.x = x;
    this.y = y;
    this.description = description;
  }

  public static class NoArgsExample {
    @NonNull private String field;

    public NoArgsExample() {
    }
  }
}
扩展配置：
lombok.anyConstructor.suppressConstructorProperties = [true | false] (default: false)如果将其设置为true，那么lombok将跳过添加一个@java.bean.ConstructorProperties生成的构造器。这在android和GWT开发中很有用，因为这些注释通常不可用。

8.@Data

包含以下注解的集合

@ToString,@EqualsAndHashCode,所有字段的 @Getter 所有非final字段的@Setter ，@RequiredArgsConstructor

[java] view plain copy
 import lombok.AccessLevel;
import lombok.Setter;
import lombok.Data;
import lombok.ToString;

@Data public class DataExample {
  private final String name;
  @Setter(AccessLevel.PACKAGE) private int age;
  private double score;
  private String[] tags;

  @ToString(includeFieldNames=true)
  @Data(staticConstructor="of")
  public static class Exercise<T> {
    private final String name;
    private final T value;
  }
}

翻译后
[java] view plain copy
 import java.util.Arrays;

public class DataExample {
  private final String name;
  private int age;
  private double score;
  private String[] tags;

  public DataExample(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  void setAge(int age) {
    this.age = age;
  }

  public int getAge() {
    return this.age;
  }

  public void setScore(double score) {
    this.score = score;
  }

  public double getScore() {
    return this.score;
  }

  public String[] getTags() {
    return this.tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  @Override public String toString() {
    return "DataExample(" + this.getName() + ", " + this.getAge() + ", " + this.getScore() + ", " + Arrays.deepToString(this.getTags()) + ")";
  }

  protected boolean canEqual(Object other) {
    return other instanceof DataExample;
  }

  @Override public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof DataExample)) return false;
    DataExample other = (DataExample) o;
    if (!other.canEqual((Object)this)) return false;
    if (this.getName() == null ? other.getName() != null : !this.getName().equals(other.getName())) return false;
    if (this.getAge() != other.getAge()) return false;
    if (Double.compare(this.getScore(), other.getScore()) != 0) return false;
    if (!Arrays.deepEquals(this.getTags(), other.getTags())) return false;
    return true;
  }

  @Override public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final long temp1 = Double.doubleToLongBits(this.getScore());
    result = (result*PRIME) + (this.getName() == null ? 43 : this.getName().hashCode());
    result = (result*PRIME) + this.getAge();
    result = (result*PRIME) + (int)(temp1 ^ (temp1 >>> 32));
    result = (result*PRIME) + Arrays.deepHashCode(this.getTags());
    return result;
  }

  public static class Exercise<T> {
    private final String name;
    private final T value;

    private Exercise(String name, T value) {
      this.name = name;
      this.value = value;
    }

    public static <T> Exercise<T> of(String name, T value) {
      return new Exercise<T>(name, value);
    }

    public String getName() {
      return this.name;
    }

    public T getValue() {
      return this.value;
    }

    @Override public String toString() {
      return "Exercise(name=" + this.getName() + ", value=" + this.getValue() + ")";
    }

    protected boolean canEqual(Object other) {
      return other instanceof Exercise;
    }

    @Override public boolean equals(Object o) {
      if (o == this) return true;
      if (!(o instanceof Exercise)) return false;
      Exercise<?> other = (Exercise<?>) o;
      if (!other.canEqual((Object)this)) return false;
      if (this.getName() == null ? other.getValue() != null : !this.getName().equals(other.getName())) return false;
      if (this.getValue() == null ? other.getValue() != null : !this.getValue().equals(other.getValue())) return false;
      return true;
    }

    @Override public int hashCode() {
      final int PRIME = 59;
      int result = 1;
      result = (result*PRIME) + (this.getName() == null ? 43 : this.getName().hashCode());
      result = (result*PRIME) + (this.getValue() == null ? 43 : this.getValue().hashCode());
      return result;
    }
  }
}


9.@Value

@value是@data的不可变对象 （不可变对象的用处和创建：https://my.oschina.net/jasonultimate/blog/166810）

所有字段都是私有的，默认情况下是final的，并且不会生成setter。默认情况下，类本身也是final的，因为不可变性不能强制转化为子类。与@data一样，有用toString()、equals()和hashCode()方法也是生成的，每个字段都有一个getter方法，并且一个覆盖每个参数的构造器也会生成。



10.@Builder

建筑者模式

是现在比较推崇的一种构建值对象的方式。

[java] view plain copy
 import lombok.Builder;
import lombok.Singular;
import java.util.Set;

@Builder
public class BuilderExample {
  private String name;
  private int age;
  @Singular private Set<String> occupations;
}
翻译后
[java] view plain copy
 import java.util.Set;

public class BuilderExample {
  private String name;
  private int age;
  private Set<String> occupations;

  BuilderExample(String name, int age, Set<String> occupations) {
    this.name = name;
    this.age = age;
    this.occupations = occupations;
  }

  public static BuilderExampleBuilder builder() {
    return new BuilderExampleBuilder();
  }

  public static class BuilderExampleBuilder {
    private String name;
    private int age;
    private java.util.ArrayList<String> occupations;

    BuilderExampleBuilder() {
    }

    public BuilderExampleBuilder name(String name) {
      this.name = name;
      return this;
    }

    public BuilderExampleBuilder age(int age) {
      this.age = age;
      return this;
    }

    public BuilderExampleBuilder occupation(String occupation) {
      if (this.occupations == null) {
        this.occupations = new java.util.ArrayList<String>();
      }

      this.occupations.add(occupation);
      return this;
    }

    public BuilderExampleBuilder occupations(Collection<? extends String> occupations) {
      if (this.occupations == null) {
        this.occupations = new java.util.ArrayList<String>();
      }

      this.occupations.addAll(occupations);
      return this;
    }

    public BuilderExampleBuilder clearOccupations() {
      if (this.occupations != null) {
        this.occupations.clear();
      }

      return this;
    }

    public BuilderExample build() {
      // complicated switch statement to produce a compact properly sized immutable set omitted.
      // go to https://projectlombok.org/features/Singular-snippet.html to see it.
      Set<String> occupations = ...;
      return new BuilderExample(name, age, occupations);
    }

    @java.lang.Override
    public String toString() {
      return "BuilderExample.BuilderExampleBuilder(name = " + this.name + ", age = " + this.age + ", occupations = " + this.occupations + ")";
    }
  }
}

11.@SneakyThrows
把checked异常转化为unchecked异常，好处是不用再往上层方法抛出了，美其名曰暗埋异常

[java] view plain copy
 import lombok.SneakyThrows;

public class SneakyThrowsExample implements Runnable {
  @SneakyThrows(UnsupportedEncodingException.class)
  public String utf8ToString(byte[] bytes) {
    return new String(bytes, "UTF-8");
  }

  @SneakyThrows
  public void run() {
    throw new Throwable();
  }
}
翻译后：
[java] view plain copy
 import lombok.Lombok;

public class SneakyThrowsExample implements Runnable {
  public String utf8ToString(byte[] bytes) {
    try {
      return new String(bytes, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw Lombok.sneakyThrow(e);
    }
  }

  public void run() {
    try {
      throw new Throwable();
    } catch (Throwable t) {
      throw Lombok.sneakyThrow(t);
    }
  }
}


12.@Synchronized

类似于Synchronized 关键字 但是可以隐藏同步锁

[java] view plain copy
import lombok.Synchronized;

ublic class SynchronizedExample {
 private final Object readLock = new Object();

 @Synchronized
 public static void hello() {
   System.out.println("world");
 }

 @Synchronized
 public int answerToLife() {
   return 42;
 }

 @Synchronized("readLock")
 public void foo() {
   System.out.println("bar");
 }
翻译后
[java] view plain copy
public class SynchronizedExample {
  private static final Object $LOCK = new Object[0];
  private final Object $lock = new Object[0];
  private final Object readLock = new Object();

  public static void hello() {
    synchronized($LOCK) {
      System.out.println("world");
    }
  }

  public int answerToLife() {
    synchronized($lock) {
      return 42;
    }
  }

  public void foo() {
    synchronized(readLock) {
      System.out.println("bar");
    }
  }
}

xianzjdk推荐使用Lock了，这个仅供参考



13.@Getter(lazy=true)

如果getter方法计算值需要大量CPU，或者值占用大量内存，第一次调用这个getter，它将一次计算一个值，然后从那时开始缓存它

[java] view plain copy
import lombok.Getter;

public class GetterLazyExample {
  @Getter(lazy=true) private final double[] cached = expensive();

  private double[] expensive() {
    double[] result = new double[1000000];
    for (int i = 0; i < result.length; i++) {
      result[i] = Math.asin(i);
    }
    return result;
  }
}
翻译后
[java] view plain copy
public class GetterLazyExample {
 private final java.util.concurrent.AtomicReference<java.lang.Object> cached = new java.util.concurrent.AtomicReference<java.lang.Object>();

 public double[] getCached() {
   java.lang.Object value = this.cached.get();
   if (value == null) {
     synchronized(this.cached) {
       value = this.cached.get();
       if (value == null) {
         final double[] actualValue = expensive();
         value = actualValue == null ? this.cached : actualValue;
         this.cached.set(value);
       }
     }
   }
   return (double[])(value == this.cached ? null : value);
 }

 private double[] expensive() {
   double[] result = new double[1000000];
   for (int i = 0; i < result.length; i++) {
     result[i] = Math.asin(i);
   }
   return result;
 }



14.@Log

可以生成各种log对象，方便多了

[java] view plain copy
 import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Log
public class LogExample {

  public static void main(String... args) {
    log.error("Something's wrong here");
  }
}

@Slf4j
public class LogExampleOther {

  public static void main(String... args) {
    log.error("Something else is wrong here");
  }
}

@CommonsLog(topic="CounterLog")
public class LogExampleCategory {

  public static void main(String... args) {
    log.error("Calling the 'CounterLog' with a message");
  }
}
翻译为
[java] view plain copy
 public class LogExample {
  private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName());

  public static void main(String... args) {
    log.error("Something's wrong here");
  }
}

public class LogExampleOther {
  private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExampleOther.class);

  public static void main(String... args) {
    log.error("Something else is wrong here");
  }
}

public class LogExampleCategory {
  private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog("CounterLog");

  public static void main(String... args) {
    log.error("Calling the 'CounterLog' with a message");
  }
}

所有支持的log类型：

@CommonsLog Createsprivate static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(LogExample.class); @JBossLog Createsprivate static final org.jboss.logging.Logger log = org.jboss.logging.Logger.getLogger(LogExample.class); @Log Createsprivate static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(LogExample.class.getName()); @Log4j Createsprivate static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LogExample.class); @Log4j2 Createsprivate static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(LogExample.class); @Slf4j Creates private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LogExample.class); @XSlf4j Createsprivate static final org.slf4j.ext.XLogger log = org.slf4j.ext.XLoggerFactory.getXLogger(LogExample.class);
扩展配置：

lombok.log.fieldName = an identifier (default: log).生成log字段的名称 默认为log lombok.log.fieldIsStatic = [true | false] (default: true)生成log是否是static的 默认为static
