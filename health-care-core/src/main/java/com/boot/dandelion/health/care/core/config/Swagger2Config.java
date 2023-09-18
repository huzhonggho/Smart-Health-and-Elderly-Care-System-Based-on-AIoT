package com.boot.dandelion.health.care.core.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2 // 开启 swagger2 的自动配置
public class Swagger2Config {
    @Bean
    Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
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
            .apis(RequestHandlerSelectors.basePackage("com.boot.dandelion.health.care.core.controller"))
            .build();
    }
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
//    @Bean
//    public Docket restApi() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("其他接口")
//                .apiInfo(apiInfo("Other APIs", "2.0"))
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.boot.dandelion.health.care.core.controller"))
//                .paths(PathSelectors.regex("/other.*"))
//                .build();
//    }
//
//    /**
//     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
//     * 访问地址：http://ip:port/swagger-ui.html
//     *
//     * @return
//     */
//    private ApiInfo apiInfo(String title, String version) {
//        return new ApiInfoBuilder()
//                .title(title)
//                .description("更多请关注: https://blog.csdn.net/xqnode")
//                .termsOfServiceUrl("https://blog.csdn.net/xqnode")
//                .contact(new Contact("xqnode", "https://blog.csdn.net/xqnode", "xiaqingweb@163.com"))
//                .version(version)
//                .build();
//    }

}