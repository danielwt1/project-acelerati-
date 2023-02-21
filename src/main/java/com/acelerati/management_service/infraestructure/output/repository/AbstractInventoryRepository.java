package com.acelerati.management_service.infraestructure.output.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public abstract class AbstractInventoryRepository <T,ID extends Serializable>implements Repository<T,ID> {
    // Que es mas limpio?=
    public abstract void saveAllData(List<T> entitys);
}
