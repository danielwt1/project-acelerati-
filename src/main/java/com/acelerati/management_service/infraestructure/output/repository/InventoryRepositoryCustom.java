package com.acelerati.management_service.infraestructure.output.repository;
import java.util.Optional;

public interface InventoryRepositoryCustom <T>{
    void persistData(T entity);
     Optional<T> getElementById(Long id);
     void updateInventory(T entity);


}
