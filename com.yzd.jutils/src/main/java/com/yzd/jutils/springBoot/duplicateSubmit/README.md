#### SpringBoot防止重复请求，重复表单提交的注解实现
- [SpringBoot防止重复请求，重复表单提交超级简单的注解实现之二（改进版）](https://blog.csdn.net/u013042707/article/details/80524617)
- [SpringBoot防止重复请求，重复表单提交超级简单的注解实现之三（升级版2）](https://blog.csdn.net/u013042707/article/details/80539422)

### 在异常发生的时候就将重复提交标记就移除呢？当然可以！通过@AfterThrowing即可实现
```
1. 注解接口 
 

/**
 * @description 防止表单重复提交注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DuplicateSubmitToken {

    //保存重复提交标记 默认为需要保存
    boolean save() default true;
    //超时时间：默认5秒
    long timeout() default 5000;

}
==
1.某些情况下request获取不到，或造成异常情况，为了处理这种情况我将获取Request的方法进行了升级

2.能不能在异常发生的时候就将重复提交标记就移除呢？当然可以！通过@AfterThrowing即可实现
下面是改造后的拦截器代码：

/**
 * @description 防止表单重复提交拦截器
 */
@Aspect
@Component
@Slf4j
public class DuplicateSubmitAspect {
    public static final String  DUPLICATE_TOKEN_KEY="duplicate_token_key";

    @Pointcut("execution(public * cn.test.core.controller..*(..))")

    public void webLog() {
    }

    @Before("webLog() && @annotation(token)")
    public void before(final JoinPoint joinPoint, DuplicateSubmitToken token){
        if (token!=null){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            boolean isSaveSession=token.save();
            if (isSaveSession){
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null==t){
                    String uuid= UUID.randomUUID().toString();
                    request.getSession().setAttribute(key.toString(),uuid);
                    log.info("token-key="+key);
                    log.info("token-value="+uuid.toString());
                }else {
                    throw new DuplicateSubmitException(TextConstants.REQUEST_REPEAT);
                }
            }

        }
    }

    /**
     * 获取重复提交key
     * @param joinPoint
     * @return
     */
    public String getDuplicateTokenKey(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        StringBuilder key=new StringBuilder(DUPLICATE_TOKEN_KEY);
        key.append(",").append(methodName);
        return key.toString();
    }

    @AfterReturning("webLog() && @annotation(token)")
    public void doAfterReturning(JoinPoint joinPoint,DuplicateSubmitToken token) {
        // 处理完请求，返回内容
        log.info("出方法：");
        if (token!=null){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            boolean isSaveSession=token.save();
            if (isSaveSession){
                String key = getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null!=t){
                    request.getSession(false).removeAttribute(key);
                }
            }
        }
    }

    /**
     * 异常
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "webLog()&& @annotation(token)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e, DuplicateSubmitToken token) {
        if (null!=token
                && e instanceof DuplicateSubmitException==false){
            //处理处理重复提交本身之外的异常
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            boolean isSaveSession=token.save();
            //获得方法名称
            if (isSaveSession){
                String key=getDuplicateTokenKey(joinPoint);
                Object t = request.getSession().getAttribute(key);
                if (null!=t){
                    //方法执行完毕移除请求重复标记
                    request.getSession(false).removeAttribute(key);
                    log.info("异常情况--移除标记！");
                }
            }
        }
    }
}
3.使用：在你要使用的控制器要防止重复提交的方法添加注解@DuplicateSubmitToken即可
4.总结：有时候我们在思考问题的时候可以换一种思路，多考虑下是否还有其他方式。
```