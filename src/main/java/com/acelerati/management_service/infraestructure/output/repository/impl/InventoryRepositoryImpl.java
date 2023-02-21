package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
    public void persistData(InventoryEntity entity) {
        entityManager.persist(entity);
    }

    @Override
    public InventoryEntity getElementById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<InventoryEntity> query = criteriaBuilder.createQuery(InventoryEntity.class);
        Root<InventoryEntity> root = query.from(InventoryEntity.class);
        //root.get() va el nombre del campo de la entidad
        query.select(root).where(criteriaBuilder.equal(root.get("idProduct"), id));
        TypedQuery<InventoryEntity> typedQuery = entityManager.createQuery(query);
        List<InventoryEntity> resultList = typedQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }
    @Override
    public void updateInventory(InventoryEntity entity) {
        entityManager.merge(entity);
    }


}
