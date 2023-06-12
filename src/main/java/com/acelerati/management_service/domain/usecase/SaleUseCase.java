package com.acelerati.management_service.domain.usecase;

import com.acelerati.management_service.domain.api.SaleServicePort;
import com.acelerati.management_service.domain.exception.CartEmptyException;
import com.acelerati.management_service.domain.exception.CartNotFoundException;
import com.acelerati.management_service.domain.model.*;
import com.acelerati.management_service.domain.spi.InventoryPersistencePort;
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
    private final InventoryPersistencePort inventoryPersistencePort;

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
        List<SaleInventoryModel> purchasedItems = cart.getProducts().stream()
                .map(cartInventoryModel -> new SaleInventoryModel(null, cartInventoryModel.getAmount(),
                        null, null, cartInventoryModel.getInventory()))
                .collect(Collectors.toList());
        log.debug("{} items are going to be attached to the current sale", purchasedItems.size());
        newSale.setPurchasedItems(purchasedItems);
        newSale = salePersistencePort.createSale(newSale);
        log.debug("Sale has been created with ID: {} with all its items", newSale.getIdSale());
        return newSale.getIdSale();
    }

    @Override
    public SaleModel findSaleById(String idSale) {
        return salePersistencePort.findSaleById(Long.parseLong(idSale));
    }

    @Override
    public void decreaseStock(SaleModel saleModel) {
        log.debug("Decreasing stock...");
        saleModel.getPurchasedItems().forEach(purchasedItem -> {
            InventoryModel inventoryToUpdate = purchasedItem.getInventory();
            inventoryToUpdate.setStock(inventoryToUpdate.getStock() - purchasedItem.getAmount());
            inventoryPersistencePort.updateInventory(inventoryToUpdate);
            purchasedItem.setExitPrice(purchasedItem.getInventory().getSalePrice());
        });
        salePersistencePort.updateSale(saleModel);
    }

    @Override
    public void rejectSale(Long idSale) {
        salePersistencePort.rejectSale(idSale);
    }

    @Override
    public void approveSale(Long idSale) {
        salePersistencePort.approveSale(idSale);
    }

}
