## 代码整理版

## 2.1亿行数据读取-[对大数据文本文件读取（按行读取）的优化](https://my.oschina.net/u/2900652/blog/1636022)
```
代码已验证-byArvin
测试代码：ReadBigFile
测试BufferedRandomAccessFileReadLine读取文件--byArvin推荐
//
如果在RandomAccessFile基础上，整合内部缓冲区，效率会有提高，测试过程中1000w行数据用时1秒，
1亿行数据用时103（比1438秒快了13倍左右）
//
如果在RandomAccessFile基础上，整合内部缓冲区，效率会有提高，测试过程中1000w行数据用时1秒，1亿行数据用时103（比1438秒快了13倍左右）
```
### GenerateBigLog
```
 * 测试-大文件读取（PS：1亿条数）
 百云度->软件开发-java->J-B-BigLog->com.yzd.generate.big.log-2019-01-28-1109-大文件读取1亿条测试数据.zip
```