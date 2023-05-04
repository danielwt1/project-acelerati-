
package com.acelerati.management_service.infraestructure.config.feign;


import feign.Logger;
import org.springframework.context.annotation.Bean;


public class FeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

