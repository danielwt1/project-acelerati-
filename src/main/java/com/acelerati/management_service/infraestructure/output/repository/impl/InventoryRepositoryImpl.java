package com.acelerati.management_service.infraestructure.output.repository.impl;
import com.acelerati.management_service.domain.util.InventorySearchCriteriaUtil;
import com.acelerati.management_service.domain.util.PaginationUtil;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.InventoryRepositoryCustom;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    public void updateInventory(InventoryEntity entity) {
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

    @Override
    public List<InventoryEntity> getInventoriesBy(InventorySearchCriteriaUtil searchCriteriaUtil, PaginationUtil paginationModel) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<InventoryEntity> criteriaQuery = criteriaBuilder.createQuery(InventoryEntity.class);
        Root<InventoryEntity> root = criteriaQuery.from(InventoryEntity.class);
        List<Predicate> predicates = buildFilteringPredicates(searchCriteriaUtil, criteriaBuilder, root);

        criteriaQuery
                .select(root)
                .where(predicates.toArray(new Predicate[0]));

        TypedQuery<InventoryEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

	@Override
    public List<InventoryEntity> getAllInventoryWithStockAndSalePriceGreaterThan0() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<InventoryEntity> criteriaQuery = criteriaBuilder.createQuery(InventoryEntity.class);
        Root<InventoryEntity> root = criteriaQuery.from(InventoryEntity.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.and(
                        criteriaBuilder.gt(root.get("salePrice"), 0),
                        criteriaBuilder.gt(root.get("stock"), 0)));

        TypedQuery<InventoryEntity> typedQuery = entityManager.createQuery(criteriaQuery);

        return typedQuery.getResultList();
    }

    private List<Predicate> buildFilteringPredicates(InventorySearchCriteriaUtil inventorySearchCriteriaModel,
                                                     CriteriaBuilder criteriaBuilder, Root<InventoryEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (inventorySearchCriteriaModel.getToSalePrice() != null && inventorySearchCriteriaModel.getFromSalePrice() != null)
            predicates.add(criteriaBuilder.between(root.get("salePrice"), inventorySearchCriteriaModel.getFromSalePrice(), inventorySearchCriteriaModel.getToSalePrice()));
        return predicates;
    }

}
