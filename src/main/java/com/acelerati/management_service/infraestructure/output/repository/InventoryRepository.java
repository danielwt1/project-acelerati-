package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface InventoryRepository extends JpaRepository<InventoryEntity,Long>,InventoryRepositoryCustom<InventoryEntity>{

}
