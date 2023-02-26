package com.acelerati.management_service.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;

@Validated
@AllArgsConstructor
@Getter
public class InventorySearchCriteriaDTO {

    @Min(0)
    private final Long fromUnitPrice;

    @Min(0)
    private final Long toUnitPrice;

    private final String category;
}
