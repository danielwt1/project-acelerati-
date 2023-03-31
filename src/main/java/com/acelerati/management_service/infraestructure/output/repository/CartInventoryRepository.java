package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartInventoryRepository extends JpaRepository<CartInventoryEntity,Long>,CartInventoryRepositoryCustom<CartInventoryEntity> {
}
