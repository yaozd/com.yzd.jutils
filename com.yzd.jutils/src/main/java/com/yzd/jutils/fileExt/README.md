
## 1.关闭数据流-IOUtils.closeQuietly(inputStream);
```
 InputStream inputStream = FileUtil.class.getClass().getResourceAsStream(path);
        try {
            return IOUtils.toString(inputStream, encoding);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
return null;

```
## 2.1亿行数据读取-[对大数据文本文件读取（按行读取）的优化](https://my.oschina.net/u/2900652/blog/1636022)
```
测试代码：ReadBigFile
测试BufferedRandomAccessFileReadLine读取文件--byArvin推荐
//
如果在RandomAccessFile基础上，整合内部缓冲区，效率会有提高，测试过程中1000w行数据用时1秒，
1亿行数据用时103（比1438秒快了13倍左右）

```