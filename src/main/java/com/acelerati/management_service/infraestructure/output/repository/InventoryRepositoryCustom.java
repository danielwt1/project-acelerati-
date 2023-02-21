package com.acelerati.management_service.infraestructure.output.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InventoryRepositoryCustom <T>{
    String saveAllData(List<T> entitys);

}
