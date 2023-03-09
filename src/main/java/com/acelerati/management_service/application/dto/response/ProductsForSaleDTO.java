package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductsForSaleDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long stock;
    private String description;
}
