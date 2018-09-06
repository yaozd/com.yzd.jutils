
### [Class NumberUtils-官网](http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/math/NumberUtils.html)
### [NumberUtils的简单举例使用](https://blog.csdn.net/weixin_30363263/article/details/81287531)
```
int数据类型和long数据类型

int占32位，long占64位，long表示的数据更大；
public static int toInt(String str)
   NumberUtils.toInt(null) = 0
   NumberUtils.toInt("")   = 0
   NumberUtils.toInt("1")  = 1
说明：将一个字符串转换成int类型，如果转换失败返回0；
public static int toInt(String str, int defaultValue)
   NumberUtils.toInt(null, 1) = 1
   NumberUtils.toInt("", 1)   = 1
   NumberUtils.toInt("1", 0)  = 1
说明：将一个字符串转换成int类型，如果转换失败就返回一个默认值0；
参数：str-将要转换的字符串，可能会是null;
     defaultValue-默认值，如果转换失败就显示默认值
public static long toLong(String str)
   NumberUtils.toLong(null) = 0L
   NumberUtils.toLong("")   = 0L
   NumberUtils.toLong("1")  = 1L
说明：将一个字符串数据转换成一个long类型数据，如果转换失败返回0；
参数：转换的字符串，可能为null;
返回值：字符串代表的long数据类型，或者转换失败的默认值0；
public static long toLong(String str, long defaultValue)
   NumberUtils.toLong(null, 1L) = 1L
   NumberUtils.toLong("", 1L)   = 1L
   NumberUtils.toLong("1", 0L)  = 1L
说明：将一个字符串数据转换成一个long类型数据，如果转换失败返回默认值；
参数：str-转换的字符串，可能为null;defaultValue-默认值
返回值：字符串代表的long数据类型，转换失败的时候返回默认值；
double（双精度浮点数）和float（单精度浮点数）
在内存中占的字节数不同，单精度浮点数占4个字节，双精度浮点数占8个字节；
有效位数不同，单精度浮点数有效位数是8位，双精度浮点数有效位数是16位；
位数不同，单精度浮点的表示范围：-3.40E+38 ~ +3.40E+38，双精度浮点的表示范围：-1.79E+308 ~ +1.79E+308
处理速度不同，一般来说处理器处理单精度浮点数的速度比处理双精度的浮点数速度要快；
public static double toDouble(String str)
   NumberUtils.toDouble(null)   = 0.0d
   NumberUtils.toDouble("")     = 0.0d
   NumberUtils.toDouble("1.5")  = 1.5d这里写代码片
说明：将一个字符串转换成double类型，如果转换失败返回0.0d;如果字符串是null返回0.0d;
参数：str-转换的字符串，可能为null;
返回值：字符串代表的double值，如果转型失败返回0.0d;
public static double toDouble(String str, double defaultValue)
   NumberUtils.toDouble(null, 1.1d)   = 1.1d
   NumberUtils.toDouble("", 1.1d)     = 1.1d
   NumberUtils.toDouble("1.5", 0.0d)  = 1.5d
说明：将一个字符串转换成double类型，如果转换失败返回0.0d;如果字符串值是null将会把默认值返回；
参数：str-转换的字符串，可能为null;defaultValue-默认值；
返回值：字符串代表的double数据类型，或者转换失败时候的默认值；
public static float toFloat(String str)
   NumberUtils.toFloat(null)   = 0.0f
   NumberUtils.toFloat("")     = 0.0f
   NumberUtils.toFloat("1.5")  = 1.5f这里写代码片
说明：将一个字符串转换成float数据类型，如果转型失败返回0.0f;如果传入参数字符串为null，返回默认值0；
参数：str-转换的字符串，可能为null;
返回值：字符串代表的float数据类型，如果转型失败返回0；
public static float toFloat(String str, float defaultValue)
   NumberUtils.toFloat(null, 1.1f)   = 1.0f
   NumberUtils.toFloat("", 1.1f)     = 1.1f
   NumberUtils.toFloat("1.5", 0.0f)  = 1.5f
说明：将一个字符串转换成float数据类型，如果转型失败返回默认值;如果传入参数字符串为null，返回默认值；
参数：str-转换的字符串，可能为null;defaultValue-默认值
返回值：字符串代表的float数据类型，或者返回默认值；
public static boolean isDigits(String str)
说明：检查字符串是否是只包含数字字符，Null和空将会返回false;
参数：str-检查的字符串；
返回参数：true 
public static boolean isParsable(String str)
NumberUtils.isParsable("122")
说明：检验提供的字符串是否可以转换为number,可解析的number包括下面的方法 Integer.parseInt(String), Long.parseLong(String), Float.parseFloat(String) or Double.parseDouble(String)，这个方法可以替代ParseException异常当调用上面的方法时；
十六进制和科学符号被认为是不可解析的；
null和空字符串返回false；
参数：str-检验的字符串；
返回数据：true-如果参数是可转换的字符串；
public static boolean isCreatable(String str)

 说明：检查字符串是否是一个有效的number,有效数字包括进制标有0x或0X预选项，八进制数、科学记数法和标有类型限定符的数字，以前导零开头的非十六进制字符串被视为八进制值，因此字符串09将返回false,因为9不是有效的八进制，然而从0开始的数字，被视为十进制，null、空或者空串将返回false;
 参数：str-检查的字符串；
 返回值：true-如果字符串是一个正确格式化的数字；
public static int compare(int x, int y)

 说明：比较两个int数值，这是相同的功能（在java7提供）；
 参数：x-第一个int比较值，y-第二个int比较值；
 返回值：如果x==y返回0，如果x小于y返回负数，如果x大于y返回正数；
public static int compare(long x, long y)

说明：比较两个long数值，这是相同的功能（在java7提供）；
参数：x-第一个long比较值，y-第二个long比较值；
返回值：如果x==y返回0，如果x小于y返回负数，如果x大于y返回正数；
public static BigDecimal createBigDecimal(String str)

说明：将一个字符串转换成BigDecimal类型，如果字符串是null将会返回null;
参数：str-转换的字符串，可能为null;
返回值：被转换的BigDecimal（如果输入的字符串是null将会返回null）
public static Double createDouble(String str)

 说明：将一个字符串转换成Double类型，如果输入字符串是null将会返回null；
 参数：str-转换字符串，可能为null;
 返回值：被转换的Double值（如果输入字符串是null将会返回null）；
 抛出异常：如果值不能解析将会抛出NumberFormatException ;
public static Long createLong(String str)

 说明：将一个字符串转换为Long数据类型，自3.1以来，它处理六（0xhhhh）和八进制（0ddd）符号，注：前导零表示八进制；空间不剪裁。如果字符串值是null将会返回null；
参数：str-转换的字符串，可能为null；
返回值： 被转换的Long数据（如果输入的是null将返回null）；
抛出异常：如果值不能被转换将会抛出NumberFormatException异常；
public static BigInteger createBigInteger(String str)

 说明：将一个字符串转换为BigDecimal类型，自3.2以来，它处理六（0x或#）和八进制（0）符号。如果字符串为null将会返回null；
 参数：str-转换的字符串（可能为null）;
 返回参数：被转换的BigDecimal（如果输入字符串为null将返回null）；
 抛出异常：如果值不能被转换将会抛出NumberFormatException异常；
public static Number createNumber(String str)

 说明：将一个字符串值转换为java.lang.Number类型；
 参数：str-转换字符串（可能为null）；
 返回值：从字符串创建的Number值（如果输入的字符串为null则返回null）；
 抛出异常：如果值不能被转换将会抛出NumberFormatException异常；
public static int max(int a, int b, int c)

说明：获取三个int值中最大的一个；
参数：a-值1，b-值2，c-值3
返回值：最大的值；
public static int max(int… array)

 说明：返回数组中最大的值；
 参数：array-一定不能为空或者null；
 返回值：数组中最大的值；
 抛出异常：如果数组为空或者null将会抛出IllegalArgumentException异常；
```