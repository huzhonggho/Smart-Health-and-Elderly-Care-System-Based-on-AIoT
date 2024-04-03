package com.boot.dandelion.health.care.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {


    @Autowired
    JwtInterceptor jwtInterceptor;

//    增加拦截的规则
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)//将自己写好的拦截类放进来
                .addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/user/login", "/user/add", "/user/testToken", "/mattress/**");//这两个放行

    }

}