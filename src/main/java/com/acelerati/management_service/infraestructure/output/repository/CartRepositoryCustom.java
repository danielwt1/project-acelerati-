package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.CartEntity;

public interface CartRepositoryCustom {

    void persistData(CartEntity cartEntity);
}
