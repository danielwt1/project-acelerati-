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

    @NotBlank(message = "If the category name is specified, it can not be blank")
    private final String categoryName;

    @NotBlank(message = "If the brand name is specified, it can not be blank")
    private final String brandName;
}
