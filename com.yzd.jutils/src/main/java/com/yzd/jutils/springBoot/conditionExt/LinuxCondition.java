package com.yzd.jutils.springBoot.conditionExt;

//条件注解,并复写此类
import org.springframework.context.annotation.Condition;
//条件注解容器
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class LinuxCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // TODO Auto-generated method stub
        return context.getEnvironment().getProperty("os.name").contains("Linux");
    }

}
