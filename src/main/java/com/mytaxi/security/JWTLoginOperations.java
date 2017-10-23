package com.mytaxi.security;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.Multimap;
import com.mytaxi.domainobject.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiListingBuilder;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiListing;
import springfox.documentation.service.Operation;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.spring.web.scanners.ApiDescriptionReader;
import springfox.documentation.spring.web.scanners.ApiListingScanner;
import springfox.documentation.spring.web.scanners.ApiListingScanningContext;
import springfox.documentation.spring.web.scanners.ApiModelReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class JWTLoginOperations extends ApiListingScanner
{
    @Autowired
    private TypeResolver typeResolver;

    public JWTLoginOperations(ApiDescriptionReader apiDescriptionReader, ApiModelReader apiModelReader, DocumentationPluginsManager pluginsManager)
    {
        super(apiDescriptionReader, apiModelReader, pluginsManager);
    }

    @Override
    public Multimap<String, ApiListing> scan(ApiListingScanningContext context)
    {
        final Multimap<String, ApiListing> def = super.scan(context);
        final List<ApiDescription> apis = new LinkedList<>();

        final List<Operation> operations = new ArrayList<>();
        operations.add(new OperationBuilder(new CachingOperationNameGenerator())
                .method(HttpMethod.POST)
                .uniqueId("login")
                .parameters(Arrays.asList(new ParameterBuilder()
                        .name("user")
                        .description("{\"username\": \"user01\", \"password\": \"user01pw\"}")
                        .required(true)
                        .defaultValue("{\"username\": \"username\", \"password\": \"password\"}")
                        .parameterType("body")
                        .type(typeResolver.resolve(UserDO.class))
                        .modelRef(new ModelRef("User"))
                        .build()))
                .summary("Log in")
                .notes("Here you can get a JWT token")
                .build());
        apis.add(new ApiDescription("/login", "Authentication documentation",
                operations, false));
        def.put("Authentication", new ApiListingBuilder(context.getDocumentationContext()
        .getApiDescriptionOrdering())
                .apis(apis)
                .description("JWT authentication")
                .build());
        return def;
    }
}
