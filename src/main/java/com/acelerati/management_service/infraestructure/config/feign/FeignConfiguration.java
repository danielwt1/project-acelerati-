
package com.acelerati.management_service.infraestructure.config.feign;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Content-Type", "application/json");


        };
    }
}

