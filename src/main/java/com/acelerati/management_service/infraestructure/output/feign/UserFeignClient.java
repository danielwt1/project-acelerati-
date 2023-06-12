package com.acelerati.management_service.infraestructure.output.feign;

import com.acelerati.management_service.application.dto.request.UserCredentialsRequestDTO;
import com.acelerati.management_service.application.dto.response.AuthenticationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-microservice-api", url = "${external.users.microservice.api.base-url}",
    configuration = FeignClientsConfiguration.class)
public interface UserFeignClient {

    @PostMapping("/auth/login")
    ResponseEntity<AuthenticationResponseDTO> doAuthentication(
            @RequestBody UserCredentialsRequestDTO userCredentialsRequestDTO);
}
