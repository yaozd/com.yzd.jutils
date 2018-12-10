
#### -Nginx-灰度发布

```
1.通过map的方式做分流-（推荐byArvin）
```
#### -Map-$COOKIE-正则（~ 符号）

```
1.abcdexpid的值以“1”结尾
map $COOKIE_abcdexpid $group {
~*1$ apache001;
~*2$ apache002;
default root;
}
---------------------------------
map $COOKIE_ickey $group {
~*[0-5]$ zone01;     #cookie 0-5结尾走zone01
~*[6-9]$ zone02;     # cookie 6-9 结尾走zone02
default root;          #默认
}
---------------------------------
2.user_city的值等于“sh”,~ 符号代表正则的意思
map $cookie_user_city $upst {
    default web;
    ~sh	sh;
    ~nj	nj;
}
---------------------------------
3.id的值等于“700003508”，不是通过正则的方式，性能最优！！
map $COOKIE_id $group {
    700003508 admin;
    ~*3$ admin;
    default user;
}
```

#### 参考-cookie分流
- [Nginx高效分发之cookie篇-推荐byArvin](http://blog.51cto.com/leon0long/1917256)
- [nginx根据cookie里的信息分流](http://blog.51cto.com/feihan21/990804)
- [nginx 基于cookie分流](http://blog.51cto.com/dyc2005/2299567)
- [nginx根据cookie分流](https://blog.csdn.net/weixin_39891030/article/details/82761744)
- []()
