package com.acelerati.management_service.infraestructure.output.repository.impl;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;


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
    public Optional<InventoryEntity> getElementById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<InventoryEntity> query = criteriaBuilder.createQuery(InventoryEntity.class);
        Root<InventoryEntity> root = query.from(InventoryEntity.class);
        query.select(root)
                .where(criteriaBuilder
                        .equal(root.get("idProduct"), id));
        TypedQuery<InventoryEntity> typedQuery = entityManager.createQuery(query);

        return typedQuery.getResultList().stream().findFirst();

    }

    @Override
    public void  updateInventory(InventoryEntity entity) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<InventoryEntity> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(InventoryEntity.class);
        Root<InventoryEntity> root = criteriaUpdate.from(InventoryEntity.class);
        criteriaUpdate.set(root.get("name"), entity.getName());
        criteriaUpdate.set(root.get("stock"), entity.getStock());
        criteriaUpdate.set(root.get("unitPrice"), entity.getUnitPrice());
        criteriaUpdate.set(root.get("salePrice"), entity.getSalePrice());
        criteriaUpdate.set(root.get("idProduct"), entity.getIdProduct());
        criteriaUpdate.set(root.get("idSupplier"), entity.getIdSupplier());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), entity.getId()));
        entityManager.createQuery(criteriaUpdate).executeUpdate();
    }


}
