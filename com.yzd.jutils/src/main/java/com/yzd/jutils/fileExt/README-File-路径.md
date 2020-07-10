## Java中文件的相对路径与绝对路径
- [Java中文件的相对路径与绝对路径](https://blog.csdn.net/u011983531/article/details/48443195)-推荐参考byArvin
- [Apache-common-io](https://www.jianshu.com/p/dae8b71f9c98)-Apache-common-io帮助类
- [springboot获取根目录及资源路径及解决jar发布时的出现D:/export-0.0.1-SNAPSHOT.jar!/BOOT-INF/classes!/static](https://blog.csdn.net/Shiyxu/article/details/100762311)

### [getPath()、getAbsolutePath()、getCanonicalPath()的区别](https://blog.csdn.net/u011983531/article/details/48443195)
```
三、getPath()、getAbsolutePath()、getCanonicalPath()的区别
getPath()获取的是新建文件时的路径，例如：

File file1 = new File(".\\test1.txt");通过getPath()获取的是.\\test1.txt

File file = new File("D:\\Text.txt");通过getPath()获取的是D:\\Text.txt

getAbsolutePath()获取的是文件的绝对路径，返回当前目录的路径+构造file时候的路径,例如：

File file1 = new File(".\\test1.txt");通过getAbsolutePath()获取的是D:\workspace\test\.\test1.txt

getCanonicalPath()获取的也是文件的绝对路径，而且把..或者.这样的符号解析出来，例如：File file = new File("..\\src

\\test1.txt");通过getCanonicalPath()获取的是D:\workspace\src\test1.txt

```
### [获取资源的路径](https://blog.csdn.net/u011983531/article/details/48443195)
```
1.Class.getResource(String path)
path  不以’/'开头时，默认是从此类所在的包下取资源；path  以’/'开头时，则是从ClassPath根下获取；
什么意思呢？看下面这段代码的输出结果就明白了：
package testpackage;
public class TestMain{
    public static void main(String[] args) {
        System.out.println(TestMain.class.getResource(""));
        System.out.println(TestMain.class.getResource("/"));
    }
} 
输出结果：
file:/E:/workspace/Test/bin/testpackage/
file:/E:/workspace/Test/bin/
上面说到的【path以’/'开头时，则是从ClassPath根下获取】，在这里就是相当于bin目录(Eclipse环境下)

```
```
InputStream inputStream=this.getClass().getResourceAsStream("/static/temp/hello.ftl");
```
