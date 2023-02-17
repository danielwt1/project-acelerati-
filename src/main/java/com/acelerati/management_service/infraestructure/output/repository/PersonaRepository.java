package com.acelerati.management_service.infraestructure.output.repository;

import com.example.hexagonalarch.infraestructure.output.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<PersonaEntity,Integer> {


}
