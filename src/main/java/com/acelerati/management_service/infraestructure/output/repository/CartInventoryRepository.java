package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import org.springframework.data.jpa.mapping.JpaPersistentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartInventoryRepository extends JpaRepository<CartInventoryRepository, Long>, CartInventoryRepositoryCustom {
    void persistData(CartInventoryEntity cartInventoryEntity);
}
