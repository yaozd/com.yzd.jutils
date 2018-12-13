### [HTML页面自动清理js、css文件的缓存（自动添加版本号）](https://blog.csdn.net/sinat_29740819/article/details/72875811)

```
//执行后会在当前代码下面，增加一条记录
<script type="text/javascript">
document.write("<link rel='stylesheet' type='text/css' href='/css/layout.css?v="+new Date().getTime()+"'>"); 
</script>

```
### [前端开发静态文件自动添加版本号解决方案](https://blog.csdn.net/sun_nan_vip/article/details/76459063)