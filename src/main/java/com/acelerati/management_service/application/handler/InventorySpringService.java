package com.acelerati.management_service.application.handler;

import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductsForSaleDTO;
import com.acelerati.management_service.application.dto.response.ProductFeignClientResponseDTO;

import java.util.List;

public interface InventorySpringService {
    void addInventory(List<InventoryDTO> inventoryDTO);
    FilterInventoryResponseDTO getInventoriesBy(InventorySearchCriteriaDTO searchCriteria, PaginationDTO paginationDTO);
	List<ProductsForSaleDTO> getAllProductForSale(String name,String nombreMarca,String nombreCategoria,int page,int elementsPerPage);
	List<ProductFeignClientResponseDTO> fetchProductsFromMicroservice();
}
