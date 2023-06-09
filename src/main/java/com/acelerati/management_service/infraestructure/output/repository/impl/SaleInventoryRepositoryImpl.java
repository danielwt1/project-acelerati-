package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.entity.SaleInventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.SaleInventoryRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class SaleInventoryRepositoryImpl implements SaleInventoryRepositoryCustom<SaleInventoryEntity> {
    private final EntityManager entityManager;

    /*
     * This function makes use of spring.jpa.properties.hibernate.jdbc.batch_size declared in application.properties
     * so that, there will be 1 database call per 10 entities to be saved. Of course, this number could change to be
     * a higher one. But, if we are going to store a huge amount of entities, say 10.000, then we have to refactor
     * this code to include explicit calls to entityManager.flush() and entityManager.clear() to free-up memory.
     * Otherwise, an OutOfMemoryError could be thrown because it won't get empty until .flush() and .clear() are being
     * called at the end of the transaction (end of the method).
     */
    @Override
    public void saveAllSaleInventories(List<SaleInventoryEntity> saleInventoryEntities) {
        for (SaleInventoryEntity saleInventoryEntity : saleInventoryEntities) {
            entityManager.persist(saleInventoryEntity);
        }
    }
}
