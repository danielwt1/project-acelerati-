package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;

public interface CartInventoryRepositoryCustom{

    void persistData(CartInventoryEntity cartInventoryEntity);
}
