package com.acelerati.management_service.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;

@AllArgsConstructor
@Getter
@ToString
@Schema(description = "Wrapper object to specify how the Inventory records will be filtered when searching",
        name = "InventorySearchCriteria")
public class InventorySearchCriteriaDTO {

    @Schema(description = "The minimum sale price. It can be omitted only if toSalePrice was omitted already",
            example = "100000")
    @Min(value = 0, message = "The From range of the filter must be at least 0")
    private final Long fromSalePrice;

    @Schema(description = "The maximum sale price. It can be omitted only if fromSalePrice was omitted already",
            example = "250000")
    @Min(value = 0, message = "The To range of the filter must be at least 0")
    private final Long toSalePrice;

    @Schema(description = "The Product Category ID. It can be omitted", example = "2")
    @Min(value = 0, message = "The category ID to search is invalid")
    private final Long categoryId;

    @Schema(description = "The Product Brand ID. It can be omitted", example = "5")
    @Min(value = 0, message = "The brand ID to search is invalid")
    private final Long brandId;

}
