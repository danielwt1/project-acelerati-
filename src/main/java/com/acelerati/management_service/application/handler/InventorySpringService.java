package com.acelerati.management_service.application.handler;

import com.acelerati.management_service.application.dto.request.InventoryDTO;

import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.*;

import com.acelerati.management_service.application.dto.request.InventoryUpdateRequestDTO;
import com.acelerati.management_service.infraestructure.exception.UnavailableMicroserviceException;


import java.util.List;

public interface InventorySpringService {
    void addInventory(List<InventoryDTO> inventoryDTO);
    FilterInventoryResponseDTO getInventoriesBy(InventorySearchCriteriaDTO searchCriteria, PaginationDTO paginationDTO) throws UnavailableMicroserviceException;
	List<ProductsForSaleDTO> getAllProductForSale(String name,String brandName,String categoryName,int page,int elementsPerPage) throws UnavailableMicroserviceException;
	List<ProductDTO> fetchProductsFromMicroservice(Integer page, Integer itemsNumber) throws UnavailableMicroserviceException;
    void updateProductSalePrice(InventoryUpdateRequestDTO inventoryDTO);

}
