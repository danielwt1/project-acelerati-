package com.acelerati.management_service.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class InventorySearchCriteriaDTO {

    @Min(value = 0, message = "The From range of the filter must be at least 0")
    private final Long fromUnitPrice;

    @Min(value = 0, message = "The To range of the filter must be at least 0")
    private final Long toUnitPrice;

    @Min(value = 0, message = "The category ID to search is invalid")
    private final Long categoryId;

    @Min(value = 0, message = "The brand ID to search is invalid")
    private final Long brandId;
}
