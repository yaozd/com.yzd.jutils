##　时间问题
- [关于YYYY-MM-dd的使用而出现Bug的帖子](https://www.cnblogs.com/jzl123/p/12132518.html)
```
YYYY是week-based-year，表示：当天所在的周属于的年份，一周从周日开始，周六结束，只要本周跨年，那么这周就算入下一年。
所以2019年12月31日那天在这种表述方式下就已经 2020 年了。而当使用yyyy的时候，就还是 2019 年。
正确的时间表示方法：
yyyy-MM-dd
链接：https://www.jianshu.com/p/b6b84980b1b9
```