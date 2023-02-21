package com.acelerati.management_service.infraestructure.output.repository;
import java.util.List;

public interface InventoryRepositoryCustom <T>{
    void persistData(T entity);
     T getElementById(Long id);
     void updateInventory(T entity);


}
