package com.acelerati.management_service.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
public class InventorySearchCriteriaDTO {

    @Min(value = 0, message = "The From range of the filter must be at least 0")
    private final Long fromUnitPrice;

    @Min(value = 0, message = "The To range of the filter must be at least 0")
    private final Long toUnitPrice;

    @Min(value = 1, message = "The category ID must be at least 1")
    private final Long category;

    @Min(value = 1, message = "The brand ID must be at least 1")
    private final Long brand;
}
