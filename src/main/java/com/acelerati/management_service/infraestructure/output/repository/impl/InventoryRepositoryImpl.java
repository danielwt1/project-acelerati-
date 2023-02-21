package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import java.util.List;


@Repository
@Transactional
public class InventoryRepositoryImpl implements InventoryRepositoryCustom<InventoryEntity> {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void persistData(List<InventoryEntity> entitys) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<InventoryEntity> query = cb.createQuery(InventoryEntity.class);
        Root<InventoryEntity> root = query.from(InventoryEntity.class);
        entitys.forEach( producto -> entityManager.persist(producto));

    }


}
