package com.yzd.jutils.springBoot.swaggerExt;

//spring-boot 禁用swagger的方法
//https://www.zhangshengrong.com/p/Mr1Wygg0NG/
//https://m.jb51.net/article/135075.htm

//关键就是这里的 @ConditionalOnProperty
//这里的属性key是 swagger.enable ,havingValue 是期望值，只有在值等于期望值的时候，才会生效。也就是说，swagger.enable只能为true的时候才会生效，其他值或不设值，都不会生效的。

/**
 *
 本篇文章主要介绍了spring-boot 禁用swagger的方法，小编觉得挺不错的，现在分享给大家，也给大家做个参考。一起跟随小编过来看看吧


 在使用spring-boot开发的时候，我们很多时候会使用swagger作为api文档输出。可以在UI界面上看到api的路径，参数等等。

 当然，作为开发环境是很方便的，但是上生产环境的时候，我们需要把swagger禁掉。怎么通过配置文件的方法来禁用swagger呢？

 代码如下：

 import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import springfox.documentation.builders.ApiInfoBuilder;
 import springfox.documentation.builders.ParameterBuilder;
 import springfox.documentation.builders.RequestHandlerSelectors;
 import springfox.documentation.schema.ModelRef;
 import springfox.documentation.service.Parameter;
 import springfox.documentation.spi.DocumentationType;
 import springfox.documentation.spring.web.plugins.Docket;
 import springfox.documentation.swagger2.annotations.EnableSwagger2;

 import java.util.ArrayList;
 import java.util.List;

 /**
 * Created by Bane.Shi.
 * User: Bane.Shi
 * Date: 2017/12/28
 * Time: 下午2:15
@Configuration
@ConditionalOnProperty(prefix = "swagger",value = {"enable"},havingValue = "true")
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket swagger(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("default")
                .apiInfo(new ApiInfoBuilder().title("SSP School API").version("1.0.0").build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fclassroom.ssp.school"))
                .build()
                .globalOperationParameters(globalOperationParameters());
    }


    private List<Parameter> globalOperationParameters(){
        List<Parameter> parameters = new ArrayList<>();
        // parameters.add(new ParameterBuilder().name("ACCESS-TOKEN").description("ACCESS-TOKEN").required(false).parameterType("header").modelRef(new ModelRef("string")).build());
        return parameters;
    }
}



如果要开启swagger，在配置文件中加上

        swagger.enable=true

        关键就是这里的 @ConditionalOnProperty

这里的属性key是 swagger.enable ,havingValue 是期望值，只有在值等于期望值的时候，才会生效。也就是说，swagger.enable只能为true的时候才会生效，其他值或不设值，都不会生效的。

        以上就是本文的全部内容，希望对大家的学习有所帮助，也希望大家多多支持脚本之家。

        */
