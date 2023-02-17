package com.acelerati.management_service.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDTO {
    //Puedo usar las propiedas del javax.Persistence para dejarlo como solo
    // lectuira o escritura
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Integer id;
    private String name;

    public PersonDTO() {
    }

    public PersonDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
