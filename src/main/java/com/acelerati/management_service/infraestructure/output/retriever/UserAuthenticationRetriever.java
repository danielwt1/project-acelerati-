package com.acelerati.management_service.infraestructure.output.retriever;

import com.acelerati.management_service.application.dto.request.UserCredentialsRequestDTO;
import com.acelerati.management_service.application.dto.response.AuthenticationResponseDTO;
import com.acelerati.management_service.infraestructure.config.security.JwtTokenUtil;
import com.acelerati.management_service.infraestructure.output.feign.UserFeignClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class UserAuthenticationRetriever {
    private final UserFeignClient userFeignClient;
    private final JwtTokenUtil jwtTokenUtil;
    @Value("${application.self-authentication.userDni}")
    private String userDni;
    @Value("${application.self-authentication.password}")
    private String password;
    private String cachedToken = "";

    public UserAuthenticationRetriever(UserFeignClient userFeignClient, JwtTokenUtil jwtTokenUtil) {
        this.userFeignClient = userFeignClient;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @HystrixCommand(fallbackMethod = "produceEmptyToken")
    public String getToken() throws IOException {
        if (cachedToken.isEmpty() || jwtTokenUtil.isTokenExpired(cachedToken)) {
            log.debug("Doing authentication against the Users microservice...");
            AuthenticationResponseDTO authenticationResponseDTO =
                    Optional.ofNullable(userFeignClient.doAuthentication(new UserCredentialsRequestDTO(userDni, password)).getBody())
                            .orElseThrow(IOException::new);
            cachedToken = authenticationResponseDTO.getToken();
            return cachedToken;
        }
        log.debug("Valid cached JWT found, returning it...");
        return cachedToken;
    }

    public String produceEmptyToken() {
        log.debug("Could not get connection to the Users microservice. Try again later");
        return "";
    }
}
