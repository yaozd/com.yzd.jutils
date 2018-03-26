package com.yzd.jutils.springBoot.conditionExt;


//条件注解接口类，复写metches
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WindowsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // TODO Auto-generated method stub
        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }

}
