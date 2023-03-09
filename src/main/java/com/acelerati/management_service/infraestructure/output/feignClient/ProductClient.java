package com.acelerati.management_service.infraestructure.output.feignClient;



import com.acelerati.management_service.infraestructure.config.feign.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@FeignClient(value = "ClientePrueba",url = "https://jsonplaceholder.typicode.com",configuration = FeignConfiguration.class)
public interface ProductClient {
    @GetMapping(value= "/posts",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<?> gettAll();
}
