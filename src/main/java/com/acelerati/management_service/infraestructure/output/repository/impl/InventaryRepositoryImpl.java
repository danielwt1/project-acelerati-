package com.acelerati.management_service.infraestructure.output.repository.impl;

import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import com.acelerati.management_service.infraestructure.output.repository.AbstractInventoryRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;


@Repository
public class InventaryRepositoryImpl extends AbstractInventoryRepository<InventoryEntity, BigInteger> {


    @Override
    public void saveAllData(List<InventoryEntity> entitys) {

    }
}
