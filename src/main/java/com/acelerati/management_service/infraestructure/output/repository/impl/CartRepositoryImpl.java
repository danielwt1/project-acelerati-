package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.entity.CartEntity;
import com.acelerati.management_service.infraestructure.output.repository.CartRepository;
import com.acelerati.management_service.infraestructure.output.repository.CartRepositoryCustom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class CartRepositoryImpl implements CartRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persistData(CartEntity cartEntity) {
        entityManager.persist(cartEntity);
    }
}
