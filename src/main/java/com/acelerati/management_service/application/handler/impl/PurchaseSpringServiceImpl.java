package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.driven.ProductFeignClientPort;
import com.acelerati.management_service.application.driven.QueueClientPort;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.application.handler.PurchaseSpringService;
import com.acelerati.management_service.domain.api.CartServicePort;
import com.acelerati.management_service.domain.api.SaleServicePort;
import com.acelerati.management_service.domain.exception.NotEnoughStockException;
import com.acelerati.management_service.domain.exception.ProductNotFoundException;
import com.acelerati.management_service.domain.exception.SaleNotFoundException;
import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.domain.model.SaleInventoryModel;
import com.acelerati.management_service.domain.model.SaleModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PurchaseSpringServiceImpl implements PurchaseSpringService {
    private final CartServicePort cartServicePort;
    private final SaleServicePort saleServicePort;
    private final QueueClientPort queueClientPort;
    private final ProductFeignClientPort productFeignClientPort;

    @Override
    public String createPurchaseRequest(Long idUser) {
        CartModel cartModel = cartServicePort.getCartByUserId(idUser);
        log.debug("Extracted cart: {}", cartModel);
        Long saleID = saleServicePort.createSale(idUser, cartModel);
        cartServicePort.deleteCartByIdUser(idUser);
        queueClientPort.submitPurchaseRequest(saleID);
        return "The purchase request was sent successfully. A confirmation e-mail will be delivered soon.";
    }

    @Override
    public void performSales(List<String> saleIds) {
        for (String saleId: saleIds) {
            try {
                SaleModel foundSale = saleServicePort.findSaleById(saleId);
                log.debug("Sale recovered with {} items.", foundSale.getPurchasedItems().size());

                // TODO Implement microservice validation
                // log.debug("Validating all the selected products to be registered in the Products database...");
                // validateAllProductsRegistered(foundSale.getPurchasedItems());

                log.debug("Validating stock availability for each product...");
                validateStockAvailability(foundSale.getPurchasedItems());

                decreaseStock(foundSale);
                log.debug("All stocks were decreased successfully!");

                // TODO Add e-mail procedure call here (To client and admin)

                approveSale(saleId);
                log.debug("Sale flagged as APPROVED");
            } catch (SaleNotFoundException | ProductNotFoundException e) {
                log.debug("Sale will not be performed.", e);

                // It does not make sense to notify the client for these caught exceptions since they are more likely
                // weird scenarios like the user selecting a product not being registered in the Products database,
                // or we had lost the Sale ID because of a database drop while processing all the pending sales.

                rejectSale(saleId);
                log.debug("Sale flagged as REJECTED");
            } catch (NotEnoughStockException e) {
                log.debug("Not enough stock exception caught, sending sale failure to the client", e);

                // TODO Add e-mail procedure call here (To client)

                rejectSale(saleId);
                log.debug("Sale flagged as REJECTED");
            } catch (Exception e) {
                log.debug("Generic exception occurred", e);

                rejectSale(saleId);
                log.debug("Sale flagged as REJECTED");
            }
        }
    }

    private void rejectSale(String idSale) {
        saleServicePort.rejectSale(Long.parseLong(idSale));
    }

    private void approveSale(String idSale) {
        saleServicePort.approveSale(Long.parseLong(idSale));
    }

    private void decreaseStock(SaleModel saleModel) {
        saleServicePort.decreaseStock(saleModel);
    }

    // TODO Implement microservice validation
    private void validateAllProductsRegistered(List<SaleInventoryModel> productsToSale) {
       List<ProductDTO> productsFromMicroservice = fetchProductsFromMicroservice(1, 1_000);
       productsToSale.forEach(productToSale -> {
           boolean productExist = productsFromMicroservice.stream()
                   .anyMatch(productFromMicroservice ->
                           productFromMicroservice.getId().equals(productToSale.getInventory().getIdProduct()));
           if (!productExist) {
               throw new ProductNotFoundException(String.format("Sale verification failed: Product ID %d not found in the Products database",
                       productToSale.getInventory().getIdProduct()));
           }
       });
    }

    private void validateStockAvailability(List<SaleInventoryModel> productsToSale) {
        productsToSale.forEach(productToSale -> {
            boolean stockAvailable = (productToSale.getInventory().getStock() - productToSale.getAmount()) >= 0;
            if (!stockAvailable) {
                throw new NotEnoughStockException(String.format("Sale verification failed: Not enough stock for Product %d, need at least %d but we have %d",
                        productToSale.getInventory().getIdInventory(), productToSale.getAmount(), productToSale.getInventory().getStock()));
            }
        });
    }

    @Override
    public List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber) {
        return productFeignClientPort.fetchProductsFromMicroservice(page, itemsNumber);
    }
}
