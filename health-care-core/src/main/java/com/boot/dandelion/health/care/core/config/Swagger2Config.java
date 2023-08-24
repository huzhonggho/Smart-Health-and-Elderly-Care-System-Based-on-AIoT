package com.boot.dandelion.health.care.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger2Config {
    @Bean
    Docket docket() {
        return new Docket(DocumentationType.OAS_30)
            //配置网站的基本信息
            .apiInfo(new ApiInfoBuilder()
            //网站标题
            .title("智慧康养接口文档")
            //标题后面的版本号
            .version("v1.0")
            .description("智慧康养接口文档")
            //联系人信息
            .contact(new Contact("anonymous", "#", "1595010699@qq.com"))
            .build())
            .select()
            //指定接口的位置
            .apis(RequestHandlerSelectors.basePackage("com.boot.dandelion.e.cigarette.core.controller"))
            .build();
    }
}