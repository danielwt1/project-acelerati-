package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.SaleServicePort;
import com.acelerati.management_service.domain.exception.CartEmptyException;
import com.acelerati.management_service.domain.exception.CartNotFoundException;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.model.SaleInventoryModel;
import com.acelerati.management_service.domain.model.SaleModel;
import com.acelerati.management_service.domain.spi.SaleInventoryPersistencePort;
import com.acelerati.management_service.domain.spi.SalePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
public class SaleUseCase implements SaleServicePort {

    private final SalePersistencePort salePersistencePort;
    private final SaleInventoryPersistencePort saleInventoryPersistencePort;

    @Override
    public Long createSale(Long idUser, CartModel cart) {
        if (cart == null) {
            throw new CartNotFoundException("Could not create the purchase request: User's cart not found.");
        }
        if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new CartEmptyException("Could not create a purchase request with an empty cart");
        }
        SaleModel newSale = new SaleModel(null, idUser, LocalDate.now(), SaleModel.STATUS_PENDING,
                null, null);
        newSale = salePersistencePort.createSale(newSale);
        log.debug("Sale has been created with ID: {}", newSale.getIdSale());
        createSaleItems(newSale, cart.getProducts());
        log.debug("Transaction for saving items of the sale has been finished");
        return newSale.getIdSale();
    }

    private void createSaleItems(SaleModel newSale, List<CartInventoryModel> products) {
        List<SaleInventoryModel> purchasedItems = products.stream()
                .map(cartInventoryModel -> new SaleInventoryModel(null, cartInventoryModel.getAmount(),
                        null, newSale, cartInventoryModel.getInventory()))
                .collect(Collectors.toList());
        log.debug("{} items are going to be attached to the current sale", purchasedItems.size());
        saleInventoryPersistencePort.saveAllSaleInventories(purchasedItems);
    }

    @Override
    public SaleModel findSaleById(String idSale) {
        return salePersistencePort.findSaleById(Long.parseLong(idSale));
    }
}
