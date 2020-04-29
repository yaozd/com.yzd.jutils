### [java四种字符串拼接方式性能分析](https://blog.csdn.net/u012722531/article/details/79055989)

```
结论
从以上分析我们可以得出以下几点结论
1.无论如何直接用“+”号连接字符串都是最慢的
2.在拼接少数字符串（不超过4个）的时候，concat效率是最高的
3.多个字符串拼接的时候，StringBuilder/StringBuffer的效率是碾压的
4.在不需要考虑线程安全问题的时候，使用StringBuilder的效率比StringBuffer更高
```