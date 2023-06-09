package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import com.acelerati.management_service.infraestructure.output.repository.SaleRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class SaleRepositoryImpl implements SaleRepositoryCustom<SaleEntity> {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public SaleEntity createSale(SaleEntity sale) {
        entityManager.persist(sale);
        entityManager.flush();
        return sale;
    }
}
