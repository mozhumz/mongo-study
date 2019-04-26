package com.hyj.mongostudy.config;

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
 * Created by tangyijun on 2017/2/24.
 * good good study,day day up!
 */
@Configuration
@EnableSwagger2
//@Profile({"dev"})
public class Swagger2Config {
  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.hyj.mongoSet.web.controller"))
            .paths(PathSelectors.any())

            .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("demo")
            .description("demo的接口文档")
            .version("1.0")
            .build();
  }

}
