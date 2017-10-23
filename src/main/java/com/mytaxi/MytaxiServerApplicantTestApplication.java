package com.mytaxi;

import com.google.common.collect.Lists;
import com.mytaxi.security.JWTLoginOperations;
import com.mytaxi.security.SecurityConstants;
import com.mytaxi.util.LoggingInterceptor;
import org.hibernate.validator.internal.util.CollectionHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ResolvedTypes;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiModelReader;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@EnableSwagger2
@SpringBootApplication
public class MytaxiServerApplicantTestApplication extends WebMvcConfigurerAdapter
{

    public static void main(String[] args)
    {
        SpringApplication.run(MytaxiServerApplicantTestApplication.class, args);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }


    @Bean
    public Docket docket()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(getClass().getPackage().getName()))
                .build()
                .apiInfo(generateApiInfo())
                .securitySchemes(Lists.newArrayList(apiKey()));
    }


    private ApiInfo generateApiInfo()
    {
        return new ApiInfo("mytaxi Server Applicant Test Service", "This service is to check the technology knowledge of a server applicant for mytaxi.", "Version 1.0 - mw",
            "urn:tos", "career@mytaxi.com", "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityScheme apiKey()
    {
        return new ApiKey("mykey", SecurityConstants.HEADER_STRING, "header");
    }

    @Bean
    SecurityConfiguration security()
    {
        return new SecurityConfiguration(null, null, "mytaxi",
                "mytaxi", null, ApiKeyVehicle.HEADER,
                "Authorization", ",");
    }


    @Primary
    @Bean
    public ApiListingScanner addExtraOperation(ApiDescriptionReader apiDescriptionReader,
                                               ApiModelReader apiModelReader,
                                               DocumentationPluginsManager pluginsManager)
    {
        return new JWTLoginOperations(apiDescriptionReader, apiModelReader, pluginsManager);
    }
}
