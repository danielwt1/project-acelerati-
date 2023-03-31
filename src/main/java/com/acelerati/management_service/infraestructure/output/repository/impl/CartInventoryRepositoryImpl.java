package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.CartInventoryRepository;
import com.acelerati.management_service.infraestructure.output.repository.CartInventoryRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class CartInventoryRepositoryImpl implements CartInventoryRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void persistData(CartInventoryEntity cartInventoryEntity) {
        entityManager.persist(cartInventoryEntity);
    }
}
