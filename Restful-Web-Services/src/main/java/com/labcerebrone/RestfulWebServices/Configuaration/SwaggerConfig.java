package com.labcerebrone.RestfulWebServices.Configuaration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Configuration File
//Enable Swagger
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact("Abhishek",
            "https://github.com/Abhishek010397",
            "abhishekdg7880@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("API Title",
            "API Description","1.0",
            "urn:tos",
            DEFAULT_CONTACT,
            "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());
    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json",
                    "application/xml"));


    //Create a Bean call Docket
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                //to change the Swagger Documentation Description as per our needs
                .apiInfo(DEFAULT_API_INFO)
                // to show the produces and comsumes
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES);
    }
    //Configure Swagger for all the paths and apis
}
