package com.acelerati.management_service.domain.model;

public class ProductModel {
    private Long id;
    private String name;
    private String description;
    private String model;
    private Long idBrand;
    private Long idCategory;

    public ProductModel(Long id, String name, String description, String model, Long idBrand, Long idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.model = model;
        this.idBrand = idBrand;
        this.idCategory = idCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(Long idBrand) {
        this.idBrand = idBrand;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }
}
