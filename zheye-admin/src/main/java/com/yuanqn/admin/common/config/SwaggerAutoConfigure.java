package com.yuanqn.admin.common.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 14:20
 * @des:
 */

@ConditionalOnProperty(prefix = "common", value = "enabledSwagger", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableSwagger2
public class SwaggerAutoConfigure {

    @Value("${springfox.documentation.swagger.title:web Api接口}")
    private String title;

    @Value("${springfox.documentation.swagger.author:作者}")
    private String author;

    @Value("${springfox.documentation.swagger.email:example123456@qq.com}")
    private String email;

    @Value("${springfox.documentation.swagger.description:web Api接口}")
    private String description;

    @Value("${springfox.documentation.swagger.host:}")
    private String host;

    @Bean
    @ConditionalOnMissingBean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        if (StringUtils.isEmpty(host)) {
            docket.host(host);
        }
        return docket
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select() // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.any())// 对所有api进行监控
                .paths(Predicates.not(PathSelectors.regex("/error.*")))// 错误路径不监控
                .paths(Predicates.not(PathSelectors.regex("/actuator.*")))// actuator路径不监控
                .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .contact(new Contact(author, "", email))
                .description(description)
                .termsOfServiceUrl("NO terms of service")
                .version("v1.0")
                .build();
    }

}
