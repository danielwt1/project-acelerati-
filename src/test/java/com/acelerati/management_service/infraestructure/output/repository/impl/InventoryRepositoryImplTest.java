package com.acelerati.management_service.infraestructure.output.repository.impl;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryRepositoryImplTest {
    @Mock
    EntityManager entityManager;
    @Mock
    Path path;
    @Mock
    CriteriaQuery query;

    @Mock
    CriteriaBuilder cb;
    @Mock
    Root root;
    InventoryEntity inventoryEntity;
    @InjectMocks
    InventoryRepositoryImpl inventoryRepository;

    @BeforeEach
    void setUp() {
        inventoryEntity = new InventoryEntity(1L, "producto", 5000L, BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), 1L, 1L);
    }

    @Test
    void persistData() {
        inventoryRepository.persistData(inventoryEntity);
        verify(entityManager).persist(inventoryEntity);
    }
/*
    @Test
    void getElementById() {
        Long id = 1L;
        InventoryEntity expectedEntity = new InventoryEntity(id, "producto", 5000L, BigDecimal.valueOf(5000), BigDecimal.valueOf(6000), 1L, 1L);
        //CriteriaQuery<InventoryEntity> query = mock( CriteriaQuery.class);
        Predicate predicado = mock(Predicate.class);
        when(entityManager.getCriteriaBuilder()).thenReturn(this.cb);
        when(cb.createQuery(InventoryEntity.class)).thenReturn(query);
        when(query.from(InventoryEntity.class)).thenReturn(root);
        when(root.get(any(String.class))).thenReturn(path);
        when(cb.equal(path,inventoryEntity.getIdProduct())).thenReturn(predicado);
        Optional<InventoryEntity> result = inventoryRepository.getElementById(id);

        assertTrue(result.isPresent());
        assertEquals(expectedEntity, result.get());

    }
    */

    @Test
    void updateInventory() {
        inventoryRepository.updateInventory(inventoryEntity);
        verify(entityManager).merge(inventoryEntity);
    }
}