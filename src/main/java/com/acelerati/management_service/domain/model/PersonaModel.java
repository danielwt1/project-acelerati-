package com.acelerati.management_service.domain.model;

public class PersonaModel {
    private Integer id;
    private String nombrre;

    public PersonaModel() {
    }

    public PersonaModel(Integer id, String nombrre) {
        this.id = id;
        this.nombrre = nombrre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrre() {
        return nombrre;
    }

    public void setNombrre(String nombrre) {
        this.nombrre = nombrre;
    }
}
