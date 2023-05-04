package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String model;
    private Long idBrand;
    private Long idCategory;
}
