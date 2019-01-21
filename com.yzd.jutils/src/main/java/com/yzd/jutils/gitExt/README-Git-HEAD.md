
## 通过GIT的HEAD可已实现代码的回滚
### -GIT在以前提交的记录上建立分支
PS:使用场景-html静态资源多版本同时存在
```
git log --oneline -10
PS: -10 代表显示前10行记录。
1. 查询HEAD的值
$ git log --oneline
afa6050 (HEAD -> master, origin/master, origin/HEAD) init
f030f81 Update .gitignore
1563cb4 Initial commit

2.根据HEAD的值签出
$ git checkout 1563cb4
Note: checking out '1563cb4'.

3.查看当前状态
$ git status
HEAD detached at 1563cb4

4.创建新的分支
$ git branch newBr

5.通过sourcetree提交到远程

```
> 参考
- [Git教程](http://www.softwhy.com/qiduan/git_course/)
- [detached HEAD详解-主要参考byArvin](http://www.softwhy.com/article-8500-1.html)

> 案例：html-静态资源忘记为老版本建立分支
```
1.
git log --oneline -10
2.
git checkout e1e727e
3.
git status
4.
git branch dev-v-190103-01
5.
通过sourcetree提交到远程
```