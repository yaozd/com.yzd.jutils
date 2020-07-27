##　[Git恢复之前版本的两种方法reset、revert（图文详解）](https://blog.csdn.net/yxlshk/article/details/79944535)
# 特别提醒：使用reset时，一定要特别谨慎，撤销以后，很难再回恢
```
特别提醒：使用reset时，一定要特别谨慎，撤销以后，很难再回恢
特别提醒：使用reset时，一定要特别谨慎，撤销以后，很难再回恢
特别提醒：使用reset时，一定要特别谨慎，撤销以后，很难再回恢

git reset --hard 目标版本号
使用“git push -f”提交更改
ps:
git reset --hard b0ed8e7185
git push -f
```

### git reset --soft 和 --hard 的区别
- [Git Reset 三种模式](https://www.jianshu.com/p/c2ec5f06cf1a)
- [git reset与git revert](https://www.jianshu.com/p/14f9ef2f967d)
```
git reset中有三个命令（--hard、--soft与--mixed）；
主要用于工作区、暂存区、本地仓库三个区域的文件提交撤回。
//
-soft 和 --hard 的区别：
--hard 会清空工作目录和暂存区的改动,
--soft则会保留工作目录的内容，并把因为保留工作目录内容所带来的新的文件差异放进暂存区。
```
### git reset --soft 示例
- soft:回退并保留修改
```
git reset --soft 6134a1fea4

```

## reset的回恢参考：
- [github总结(4)--关于git reset --hard这个命令的惨痛教训](https://www.cnblogs.com/hope-markup/p/6683522.html)