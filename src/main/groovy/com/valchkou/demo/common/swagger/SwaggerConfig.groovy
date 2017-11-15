package com.valchkou.demo.common.swagger

import com.fasterxml.classmate.TypeResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.RequestMethod
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.ResponseMessage
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import static com.google.common.collect.Lists.newArrayList
import static springfox.documentation.builders.PathSelectors.regex

@Configuration
@EnableSwagger2
class SwaggerConfig {

    private static final String INCLUDE = "api"

    @Value('${spring.application.name}')
    protected String name

    @Value('${spring.application.description}')
    protected String description

    @Autowired
    private TypeResolver typeResolver


    @Bean
    Docket v1Api() {
        List<ResponseMessage> resp = buildGlobalResponses()
        ApiInfo info = buildApiInfo(INCLUDE)

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(true)
                .globalResponseMessage(RequestMethod.GET, resp)
                .globalResponseMessage(RequestMethod.POST, resp)
                .globalResponseMessage(RequestMethod.PUT, resp)
                .globalResponseMessage(RequestMethod.DELETE, resp)
                .groupName(INCLUDE)
                .apiInfo(info)
                .select()
                .paths(regex(".*/"+INCLUDE+".*"))
                .build()
    }


    private List<ResponseMessage> buildGlobalResponses() {
        return newArrayList(new ResponseMessageBuilder()
                .code(500)
                .message("Unexpected error during execution")
                .responseModel(new ModelRef("Error"))
                .build())
    }

    private ApiInfo buildApiInfo(String version) {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .version(version)
                .build()
    }
}