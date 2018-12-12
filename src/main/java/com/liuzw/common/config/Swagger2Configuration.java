package com.liuzw.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @email :a1774214410@163.com
 * @author: liuzw
 * @date: 2018/11/6 14:17
 */
@EnableSwagger2
@Configuration
//配置swagger开关  下面两种注解效果一样
@ConditionalOnExpression("${swagger.flag:true}")
//@ConditionalOnProperty(prefix = "swagger",name = "flag", havingValue = "true",matchIfMissing = true)
public class Swagger2Configuration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为接口包路径
                .apis(RequestHandlerSelectors.basePackage("com.liuzw.common.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息函数
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("LessonOne")
                //版本号
                .version("1.0")
                //描述
                .description("我的swagger测试文档")
                .build();
    }
}
