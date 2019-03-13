> 参考：
- [SpringCloud基础篇AOP之拦截优先级详解](https://blog.csdn.net/liuyueyi25/article/details/88428572)
```
@Component
@Aspect
public class OrderAspect {

    @Pointcut("execution(public * com.git.hui.boot.aop.order.*.*())")
    public void point() {
    }

    @Before(value = "point()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("do before!");
    }

    @After(value = "point()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("do after!");
    }

    @AfterReturning(value = "point()", returning = "ans")
    public void doAfterReturning(JoinPoint joinPoint, String ans) {
        System.out.println("do after return: " + ans);
    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("do in around before");
            return joinPoint.proceed();
        } finally {
            System.out.println("do in around over!");
        }
    }
}
------------------------------
执行顺序：
do in around before
do before!
in innerDemoBean start!
1552219604035|e9a31f44-6a31-4485-806a-834361842ce1
in innerDemoBean over!
do in around over!
do after!
do after return: 1552219604035|e9a31f44-6a31-4485-806a-834361842ce1
result: 1552219604035|e9a31f44-6a31-4485-806a-834361842ce1
--------------------- 
```