package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.SaleInventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleInventoryRepository extends JpaRepository<SaleInventoryEntity, Long>,
        SaleInventoryRepositoryCustom<SaleInventoryEntity> {

}
