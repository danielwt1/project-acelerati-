package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.domain.exception.CartNotFoundException;
import com.acelerati.management_service.infraestructure.output.entity.CartEntity;
import com.acelerati.management_service.infraestructure.output.repository.CartRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Slf4j
@Transactional
public class CartRepositoryImpl implements CartRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CartEntity> getCart(Long idUser) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CartEntity>criteriaQuery = criteriaBuilder.createQuery(CartEntity.class);
        Root<CartEntity> root = criteriaQuery.from(CartEntity.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("idUser"),idUser));
        TypedQuery<CartEntity> response = entityManager.createQuery(criteriaQuery);
        return response.getResultList().stream().findFirst();
    }

    @Override
    public void deleteCartByIdUser(Long idUser) {
        CartEntity cartEntity = getCart(idUser)
                .orElseThrow(() -> new CartNotFoundException("Could not delete the cart, it was not found"));
        log.debug("Found cart ID {} to remove for user {}", cartEntity.getIdCart(), idUser);
        entityManager.remove(cartEntity);
    }
}
