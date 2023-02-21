package com.acelerati.management_service.infraestructure.output.repository;

import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InventoryRepositoryCustom <T>{
    void persistData(List<T> entitys);


}
