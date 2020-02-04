package com.yzd.jutils.springBoot.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    /**
     * 提前加载拦截器，解决注入问题
     * @return
     */
   /* @Bean
    LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }*/

    /***
     * 解决输出中文都是乱码问号的问题
     * @return
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    /**
     * 配置拦截器
     *
     * @param registry
     * @author yzd
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/other01/*")
                .excludePathPatterns("/account/doLogin")
                .excludePathPatterns("/account/doLogout");*/
    }

}

