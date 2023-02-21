package com.acelerati.management_service.application.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.BigInteger;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class InventoryDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private Long stock;
    @NotNull
    private BigDecimal unit_price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal sale_price;
    @NotNull
    private Long id_product;
    @NotNull
    private Long id_supplier;

}
