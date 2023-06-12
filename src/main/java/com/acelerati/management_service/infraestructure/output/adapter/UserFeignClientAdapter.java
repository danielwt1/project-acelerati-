package com.acelerati.management_service.infraestructure.output.adapter;

import com.acelerati.management_service.application.driven.UserFeignClientPort;
import com.acelerati.management_service.infraestructure.output.retriever.UserAuthenticationRetriever;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UserFeignClientAdapter implements UserFeignClientPort {

    private final UserAuthenticationRetriever userAuthenticationRetriever;

    public UserFeignClientAdapter(UserAuthenticationRetriever userAuthenticationRetriever) {
        this.userAuthenticationRetriever = userAuthenticationRetriever;
    }

    @Override
    public String doManualAuthentication() {
        try {
            return userAuthenticationRetriever.getToken();
        } catch (IOException e) {
            log.error("Could not extract authentication token from the body request");
            return "";
        }
    }
}
