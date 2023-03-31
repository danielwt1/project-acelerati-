package com.acelerati.management_service.infraestructure.output.repository.impl;
import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.CartInventoryRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class CartInventoryRepositoryImpl implements CartInventoryRepositoryCustom<CartInventoryEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CartInventoryEntity> getCartInventoryByIdProduct(Long idProduct) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CartInventoryEntity> query = criteriaBuilder.createQuery(CartInventoryEntity.class);
        Root<CartInventoryEntity> root = query.from(CartInventoryEntity.class);
        query.select(root)
                .where(criteriaBuilder
                        .equal(root.get("inventory"), idProduct));
        TypedQuery<CartInventoryEntity> typedQuery = entityManager.createQuery(query);
        return typedQuery.getResultList().stream().findFirst();
    }
    @Override
    public void deleteProductFromCart(CartInventoryEntity productFromCart) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<CartInventoryEntity> criteriaDelete = criteriaBuilder.createCriteriaDelete(CartInventoryEntity.class);
        Root<CartInventoryEntity> root = criteriaDelete.from(CartInventoryEntity.class);

        // Agrega los predicados para eliminar el producto específico de la base de datos
        Predicate productPredicate = criteriaBuilder.equal(root.get("inventory"), productFromCart.getInventory());
        Predicate cartPredicate = criteriaBuilder.equal(root.get("cart"), productFromCart.getCart());
        Predicate combinedPredicate = criteriaBuilder.and(productPredicate, cartPredicate);
        criteriaDelete.where(combinedPredicate);
        entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
