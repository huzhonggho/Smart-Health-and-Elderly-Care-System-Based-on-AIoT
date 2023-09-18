package com.boot.dandelion.health.care.core;


import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.SocketException;
import java.net.UnknownHostException;

@EnableTransactionManagement
@MapperScan("com.boot.dandelion.health.care.dao")
@PropertySources(value = {@PropertySource(value = {"classpath:important.properties", "classpath:application.properties"},
        encoding = "utf-8")})
@EnableScheduling
//开启swagger
//@SpringBootApplication(scanBasePackages = "com.boot.dandelion.health.care.core.controller")
//@ComponentScan(basePackages = "com.boot.dandelion.health.care.core.service")
@SpringBootApplication
//开启swagger
@EnableSwagger2
@EnableOpenApi
public class Application {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                // 如果要强制使用https，请松开以下注释
                // SecurityConstraint constraint = new SecurityConstraint();
                // constraint.setUserConstraint("CONFIDENTIAL");
                // SecurityCollection collection = new SecurityCollection();
                // collection.addPattern("/*");
                // constraint.addCollection(collection);
                // context.addConstraint(constraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(createStandardConnector()); // 添加http

        //
        return tomcat;
    }

    // 配置http
    private Connector createStandardConnector() {
        // 默认协议为org.apache.coyote.http11.Http11NioProtocol
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setSecure(false);
        connector.setScheme("http");
        connector.setPort(httpPort);
        connector.setRedirectPort(httpsPort); // 当http重定向到https时的https端口号
        return connector;
    }

    @Value("${server.http.port}")
    private Integer httpPort;

    @Value("${server.port}")
    private Integer httpsPort;
}
