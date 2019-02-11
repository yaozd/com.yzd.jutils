## [关于Guava中I/O中Files类各个方法的解读](https://blog.csdn.net/Husc2009/article/details/7797577)
```
4.1 newReader(File file, Charsetcharset)
public static BufferedReader newReader(File file, Charset charset)

       该方法使用提供的编码方式把输入文件做成一个BufferedReader对象，并返回。

       这个方法和java里边BufferedReader(inputStream,charset)一样的功能，只不过这里直接 使用file而不是inputstream。

4.2 newWriter(File file, Charsetcharset)
public static BufferedWriter newWriter(File file, Charset charset)

该方法是和上边的类似功能。

4.3 newInputStreamSupplier(Filefile)
public static InputSupplier<FileInputStream>newInputStreamSupplier(File file)

该方法是将文件做成一个FileInputStream，然后用InputSupplier封装，并返回。

4.4 newOutputStreamSupplier(Filefile)
public static OutputSupplier<FileOutputStream>newOutputStreamSupplier(File file)

该方法是将要写入的文件做成一个FileOutputStream，然后用OutputSupplier封装，并返回。

4.5 newOutputStreamSupplier(Filefile, boolean append)
public static OutputSupplier<FileOutputStream>newOutputStreamSupplier(File file, boolean append)

该方法和上一个功能一模一样，只不过这个用一个参数来规定接下来要写入的数据是追加的方式还是直接覆盖的，上面的那个是直接覆盖的方式。

4.6 newReaderSupplier(File file,Charset charset)
public static InputSupplier<InputStreamReader>newReaderSupplier(File file, Charset charset)

该方法实现的功能和CharStreams的newReaderSupplier功能差不多，只不过这个是把一个文件封装成带有InputStreamReader的InputSupplier。

4.7 newWriterSupplier(File file,Charset charset)
public static OutputSupplier<OutputStreamWriter>newWriterSupplier(File file, Charset charset)

该方法实现的功能和CharStreams的newWriterSupplier功能差不多，只不过这个更简单，不用把文件转换成OutputSupplier封装的了。

4.8 newWriterSupplier(File file,Charset charset, boolean append)
public static OutputSupplier<OutputStreamWriter>newWriterSupplier(File file, Charset charset, boolean append)

该方法和上一个方法是重载的，实现功能一样，只不过这个对要写入的文件是可以标注是否追加的方式，而上一个方法是直接覆盖的。

4.9 toByteArray(File file)
public static byte[] toByteArray(File file)

该方法是将一个文件直接转化成一个字节数组，并返回。

使用方法：

byte[] buffer = Files.toByteArray(file_from);

4.10 toString(File file, Charsetcharset)
public static String toString(File file, Charset charset)

该方法是把文件里的所有内容按照charset的编码方式读出来，然后赋值给一个String串，并返回。

使用方法：

String str = Files.toString(file_from);

4.11 copy(InputSupplier<?extends InputStream> from, File to)
public static void copy(InputSupplier<? extends InputStream>from, File to)

该方法是将使用InputSupplier封装的InputStream的内容全部拷贝到文件to里边。

使用方法：

Files.copy(Files.newInputStreamSupplier(file_from),file_to);

4.12 write(byte[] from, File to)
public static void write(byte[] from, File to)

该方法是将一个字节数组里的数据全部写入到文件to里边。

使用方法：

Files.write(buffer, file_to);

4.13 copy(File from,OutputSupplier<? extends OutputStream> to)
public static void copy(File from, OutputSupplier<? extendsOutputStream> to)

该方法是将文件from里的内容读取出来，然后拷贝到OutputStream对象里，然后使用OuputSupplier封装。

使用方法：

Files.copy(file_from,Files.newOutputSupplier(file_to));

4.14 copy(File from, OutputStreamto)
public static void copy(File from, OutputStream to)

该方法是将文件内容写入到OutputStream对象里边，这里不会关闭输出流。

使用方法：

Files.copy(file_from, outputStream);

//最后不要忘记flush()和close()

4.15 copy(File from, File to)
public static void copy(File from, File to)

该方法最直接，是将一个文件的内容完全复制到另一个文件中。

由于太直观，这里不再写使用方法。

4.16 copy(InputSupplier<R>from, File to, Charset, charset)
public static <R extends Readable & Closeable> voidcopy(InputSupplier<R> from, File to, Charset, charset)

该方法是将由InputSupplier提供的Readable和Closeable对象里的数据全部拷贝出来，然后使用编码方式charset写入到文件to里边。

使用方法：

Files.copy(Files.newReaderSupplier(f,Charsets.UTF_8), file, Charsets.UTF_8);

4.17 copy(File from, Charsetcharset, OutputSupplier<W> to)
public static <W extends Appendable & Closeable> voidcopy(File from, Charset charset, OutputSupplier<W> to)

该方法是将文件from的内容使用charset的编码方式全部拷贝到由OutputSupplier提供的Appendable或者Closeable对象里。

使用方法：

Files.copy(f, Charsets.UTF_8,Files.newWriterSupplier(file, Charsets.UTF_8, true));

4.18 copy(File from, Charsetcharset, Appendable to)
public static void copy(File from, Charset charset, Appendable to)

该方法是将文件from的内容按照编码方式写入到Appendable对象里。

使用方法：

Files.copy(file_from, Charsets.UTF-8,stringBuffer);

4.19 write(CharSequence from, Fileto, Charset charest)
public static void write(CharSequence from, File to, Charsetcharest)

该方法是将一个字符串使用charset的编码方式写入到一个文件里。

使用方法太简单，不赘述。

4.20 append(CharSequence from, Fileto, Charset charset)
public static void append(CharSequence from, File to, Charset charset)

该方法是将一个字符串按照规定的编码方式追加到一个文件中，和上一个方法很像，但上一个方法是覆盖式的写。

使用方法太简单，不赘述

4.21 equal(File file1, File file2)
public static boolean equal(File file1, File file2)

该方法是比较两个文件中是否包含相等的字节数，如果相等返回true，否则返回false。

使用方法太简单，不赘述。

4.22 createTempDir()
public static File createTempDir()

该方法会自动在系统的临时文件目录下创建一个新的目录，并返回他的名字。当你想创建一个临时文件夹而不是临时文件时，使用这个方法替代File.createTempFile(String, String)。一个比较常见的陷阱是使用createTempFile，然后删除这个临时文件，在那个位置上创建一个文件夹，但是如果一个可执行文件写入到这个文件夹后，这将会是一个非常大的安全漏洞。

使用方法：

File file = Files.createTempDir();

//输出文件位置为：

C:\Users\HSC~1.HSC\AppData\Local\Temp\1343358702677-0

4.23touch(Filefile)
public static void touch(File file)

该方法是用来创建一个空的文件或者使用和unix相同命令的方式更新文件的最后更新时间戳。

使用方法：

File file = new File("c.txt");

System.out.println(file.lastModified());

Files.touch(file);

System.out.println(file.lastModified());

4.24 createParentDirs(File file)
public static void createParentDirs(File file)

该方法对于指定的文件创建任意的必须但是不存在的父目录文件夹。需要注意的，如果最终创建失败，不能保证的是没有任何文件夹创建，也许在他的父目录路径上已经有某些文件夹被创建了。

使用方法：

File file = new File("a/c.txt");

Files.createParentDirs(file);

//这里如果c.txt文件存在，但是a文件夹不存在，就会被创建。

4.25 move(File from, File to)
public static void move(File from, File to)

该方法是讲一个文件从一个地方移动另一个地方，在移动的过程中可以重新命名，其实和copy功能差不多。

使用方法太简单，不赘述。

4.26 readFirstLine(File file,Charset charset)
public static String readFirstLine(File file, Charset charset)

该方法读取文件的第一行，这一行不包含终结符。

使用方法：

String str = Files.readFirstLine(file_from,Charsets.UTF-8)

4.27 readLines(File file, Charsetcharset)
public static List<String> readLines(File file, Charsetcharset)

该方法和上一个的实现都差不多，只不过这个要读出所有的行。

使用方法简单，不赘述。

4.28 readLines(File file, Charsetcharset, LineProcessor<T> callback)
public static <T> T readLines(File file, Charset charset,LineProcessor<T> callback)

该方法也是读取一个文件的所有行，但是这里会加上一个判断，凡是不满足LineProcesssor要求的这一行都不读。

使用方法：

File f = new File("b.txt");

ArrayList<String> list = Files.readLines(f,Charsets.UTF_8, new LineProcessor<ArrayList<String>>() {

                    ArrayList<String> result = newArrayList<String>();

 

                    @Override

                    public ArrayList<String>getResult() {

                      return result;

                    }

 

                    @Override

                    public boolean processLine(String line)throws IOException {

                      result.add(line.trim());

                      if(line.contains("haha"))return false;

                      else return true;

                    }

                  });

4.29 readBytes(File file,ByteProcessor<T> processor)
public static <T> T readBytes(File file,ByteProcessor<T> processor)

该方法和上一个方法大致相同，只不过上一个处理的是每一行，这一个处理的是字节数组，而且规定了从什么地方开始，处理多长。

其使用方法和上面的一样，但是我在编写代码的时候，总是会出现一些小问题，不过思路是正确的，而且结果也能出来，只是不知道为何每次都会多读好多个字节，我不知道是不是因为他们默认参数设置的问题。

4.30 getChecksum(File file,Checksum checksum)
public static long getChecksum(File file, Checksum checksum)

该方法功能是计算并返回输入文件的检查的值，然后Checksum对象将会重置。这个方法相当于对文件进行校验。

使用方法：

File file = new File(“b.txt”);

CRC32 crc = new CRC32();

long n = Files.getChecksum(file, crc);

4.31 hash(File, HashFunctionhashFunction)
public static HashCode hash(File, HashFunctionhashFunction)

该方法没有看懂，待研究。

4.32 map(File file)
public static MapperdByteBuffer map(File file)

该方法是把可读的文件Map到内存里，文件映射是从0-文件长度。需要注意的是文件的大小必须小于整数最大值。

使用方法暂时还没找到好的例子，因为我按照一般的用法写的，但是总是不成功。这里把例子放上。

使用方法（待研究）：

File file = new File("b.txt");

File file2 = new File("c.txt");

ByteBuffer buffer = Files.map(file);

ByteBuffer buffer2 = Files.map(file2);

buffer2.put(buffer);

buffer.clear();buffer2.clear();

4.33 map(File file, FileChannel.MapModemode)
public static MappedByteBuffer map(File file, FileChannel.MapModemode)

该方法把文件使用规定的方式完全映射到内存中。

这个问题和上一个问题是一样的，总是出问题，但是程序是没问题的，接下来需要待研究，而和上一个区别是要添加MapMode。

4.34 map(File file,FileChannel.MapMode mode, long size)
public static MappedByteBuffer map(File file, FileChannel.MapModemode, long size)

该方法和上面两个是一样的，只不过这个添加了一个size，意思是每次要Map到内存的大小，这个和上面的比较是这个可以正常使用。

使用方法：

ByteBuffer buffer = Files.map(file,FileChannel.MapMode.READ_NOLY, size);

ByteBuffer buffer2 = Files.map(file2, FileChannel.MapMode.READ_WRITE,size);

buffer2.put(buffer);

buffer.clear();buffer2.clear();

//注意：这个size不能超过原始文件的大小，否则会出现拒绝访问，另外，这段代码实现的功能是把一个文件内容复制到另一个文件中，这里使用内存直接映射了。

4.35 simplifyPath(String pathname)
public static String simplifyPath(String pathname)

该方法是将提供的目录路径简化，具体的就是空的字符串变成“.”，然后“.”依然保留，将“./”折叠起来，“../”不一定折叠起来，可能会，删除多余的“/”，而且会删除尾部的“/”。

4.36 getFileExtension(String filename)
public static String getFileExtension(String filename)

该方法是返回一个文件名的扩展名，如果一个文件没有扩展名，他将会返回一个空字符串。这个扩展名不包含“.”。
--------------------- 
作者：天上一堆星 
来源：CSDN 
原文：https://blog.csdn.net/Husc2009/article/details/7797577 
版权声明：本文为博主原创文章，转载请附上博文链接！
```