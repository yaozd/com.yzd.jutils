package com.yzd.jutils.springBoot.beanExt;

/**
 * Spring Boot中静态方法如何调用Spring容器中的Bean
 * Created by zd.yao on 2017/9/28.
 */

import org.springframework.context.ApplicationContext;

/**
 * Created by zd.yao on 2017/9/27.
 */
public class SpringContextUtil {

    private static class SingletonHolder {
        private static final SpringContextUtil INSTANCE = new SpringContextUtil();
    }
    public static final SpringContextUtil getInstance() {
        return SingletonHolder.INSTANCE;
    }
    private SpringContextUtil (){
    }
    private ApplicationContext ctx;
    public void setCtx(ApplicationContext ctx) {
        if(this.ctx!=null){
            throw new IllegalStateException("当前上下文已存在！");
        }
        this.ctx = ctx;
    }
    public  <T> T getBean(Class<T> var1){
        return ctx.getBean(var1);
    }
}
