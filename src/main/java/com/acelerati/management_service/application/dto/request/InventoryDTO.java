package com.acelerati.management_service.application.dto.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    @Min(1)
    private Long stock;
    @NotNull
    @Min(100)
    private BigDecimal unitPrice;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal salePrice;
    @NotNull
    @Min(0)
    private Long idProduct;
    @NotNull
    @Min(1)
    private Long idSupplier;

}
