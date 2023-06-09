package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;

public interface SaleRepositoryCustom<T> {
    SaleEntity createSale(T sale);
}
