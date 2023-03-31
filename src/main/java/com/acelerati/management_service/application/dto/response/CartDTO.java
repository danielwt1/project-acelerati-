package com.acelerati.management_service.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class CartDTO {
    private Long idCart;
    private Long idUser;
    private LocalDateTime lastUpdate;
    private List<CartInventoryDTO> products;

}
