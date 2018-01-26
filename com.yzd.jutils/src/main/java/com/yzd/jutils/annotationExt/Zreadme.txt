

   //参考：
   //http://blog.csdn.net/meiyang1990/article/details/50562046
   //java aop redis缓存

    通过注解我们可以获得：
    1，当前方法请求参数
    2，当前方法返回值类型
    3，当前方法的注解信息



    //获得当前方法的注解信息
    private <T extends Annotation> T getAnnotation(ProceedingJoinPoint jp, Class<T> clazz) {
            MethodSignature sign = (MethodSignature) jp.getSignature();
            Method method = sign.getMethod();
            return method.getAnnotation(clazz);
    }
    //获得当前方法的请求参数
    private Object[] getRequestArgs(ProceedingJoinPoint jp){
        Object[] args = jp.getArgs();
        return args;
    }
    //获得当前方法
    private Method getMethod(ProceedingJoinPoint jp){
        MethodSignature sign = (MethodSignature) jp.getSignature();
        Method method = sign.getMethod();
        return method;
    }
    //获得当前方法的注解信息
    private <T extends Annotation> T getAnnotation(Method method, Class<T> clazz){
        return method.getAnnotation(clazz);
    }
    //获得当前方法的返回值类型
    private Type getReturnType(Method method){
        Type t = method.getAnnotatedReturnType().getType();
        return t;
    }

    //获得当前方法的返回值类型-Class
    private Class getReturnType(Method method) {
        Class returnType = method.getReturnType();
        return returnType;
    }
    //当前方法路径：public void com.yzd.jutils.annotationExt.redisCacheExt.UserService.empty(java.lang.Integer)
    private String getMethodPath(Method method){
        return method.toString();
    }