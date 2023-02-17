package com.acelerati.management_service.infraestructure.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tabla")
public class PersonaEntity {
    @Id
    private Integer id;

    public PersonaEntity() {
    }

    public PersonaEntity(Integer idPersona) {
        this.id = idPersona;
    }

    public Integer getIdPersona() {
        return id;
    }

    public void setIdPersona(Integer idPersona) {
        this.id = idPersona;
    }
}
