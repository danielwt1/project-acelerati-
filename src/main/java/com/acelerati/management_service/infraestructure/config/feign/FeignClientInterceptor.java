package com.acelerati.management_service.infraestructure.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTHORIZATION_HEADER="Authorization";

    @Value("${external.products.microservice.api.base-url}")
    private  String feignClientUrl;


    @Override
    public void apply(RequestTemplate requestTemplate) {
        if (requestTemplate.feignTarget().url().startsWith(feignClientUrl)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = (String) authentication.getCredentials();
            requestTemplate.header(AUTHORIZATION_HEADER, token);
        }

    }
}
