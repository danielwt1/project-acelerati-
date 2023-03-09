package com.acelerati.management_service.application.dto.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;
@Getter
@AllArgsConstructor
public class InventoryResponseDTO {
    private final Long id;
    private final String name;
    private final Long stock;
    private final BigDecimal unitPrice;
    private final BigDecimal salePrice;
    private final Long idProduct;
    private final Long idSupplier;

}
