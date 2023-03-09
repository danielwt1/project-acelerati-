package com.acelerati.management_service.application.handler.impl;

import com.acelerati.management_service.application.driven.ProductsFeingClientPort;
import com.acelerati.management_service.application.dto.request.InventoryDTO;
import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.request.PaginationDTO;
import com.acelerati.management_service.application.dto.response.FilterInventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.PaginationResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.application.dto.response.ProductsForSaleDTO;
import com.acelerati.management_service.application.handler.InventorySpringService;
import com.acelerati.management_service.application.mapper.InventoryRequestMapper;
import com.acelerati.management_service.application.mapper.InventorySearchMapper;
import com.acelerati.management_service.application.mapper.PaginationRequestMapper;
import com.acelerati.management_service.application.mapper.PaginationResponseMapper;
import com.acelerati.management_service.domain.api.InventoryServicePort;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import com.acelerati.management_service.domain.model.PaginationModel;
import com.acelerati.management_service.infraestructure.output.feignClient.ProductsMockFeign;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class InventorySpringServiceImpl implements InventorySpringService {
    private final InventoryServicePort inventoryServicePort;
    private final InventoryRequestMapper inventoryRequestMapper;
    private final InventorySearchMapper inventorySearchMapper;
    private final PaginationRequestMapper paginationRequestMapper;
    private final PaginationResponseMapper paginationResponseMapper;
    private final ProductsFeingClientPort productsFeingClientPort;

    public InventorySpringServiceImpl(InventoryServicePort inventoryServicePort, InventoryRequestMapper inventoryRequestMapper, InventorySearchMapper inventorySearchMapper, PaginationRequestMapper paginationRequestMapper, PaginationResponseMapper paginationResponseMapper, ProductsFeingClientPort productsFeingClientPort) {
        this.inventoryServicePort = inventoryServicePort;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.inventorySearchMapper = inventorySearchMapper;
        this.paginationRequestMapper = paginationRequestMapper;
        this.paginationResponseMapper = paginationResponseMapper;
        this.productsFeingClientPort = productsFeingClientPort;
    }

    @Override
    public void addInventory(List<InventoryDTO> inventoryDTO) {
        this.inventoryServicePort.addInventory(this.inventoryRequestMapper.toListModel(inventoryDTO));

    }

    @Override
    public FilterInventoryResponseDTO getInventoriesBy(InventorySearchCriteriaDTO searchCriteriaDTO, PaginationDTO paginationDTO) {
        InventorySearchCriteriaModel inventorySearchCriteriaModel = inventorySearchMapper.toModel(searchCriteriaDTO);
        PaginationModel paginationModel = paginationRequestMapper.toModel(paginationDTO);
        List<InventoryModel> inventoryModels = inventoryServicePort.getInventoriesBy(inventorySearchCriteriaModel, paginationModel);
        List<InventoryResponseDTO> inventoriesResponse = inventorySearchMapper.toDTOList(inventoryModels);
        PaginationResponseDTO paginationResponse = paginationResponseMapper.toResponseDTO(paginationModel);
        return new FilterInventoryResponseDTO(inventoriesResponse, paginationResponse);
    }

    @Override
    public List<ProductsForSaleDTO> getAllProductForSale(String name, String nombreMarca, String nombreCategoria,int page,int elementPerPage) {
        List<?> xd = this.productsFeingClientPort.getAllProducts();
        List<InventoryResponseDTO> inventoryList = this.inventorySearchMapper.toDTOList(this.inventoryServicePort.getAllInventoryWithStockAndSalePriceGreaterThan0());
        List<ProductDTO> products = ProductsMockFeign.getAll();
        List<ProductsForSaleDTO> dataFiltered = filterData(mergeData(inventoryList, products),name,nombreMarca,nombreCategoria);
        return dataPaginated(dataFiltered,page,elementPerPage);
    }
    public List<ProductsForSaleDTO> filterData(List<ProductsForSaleDTO> dataMerged,String name,
                                                            String nombreMarca, String nombreCategoria){
        return dataMerged.stream()
                .filter(product->product.getName().contains(name))
                //.filter(product->product.g)
                .collect(Collectors.toList());
    }
    public List<ProductsForSaleDTO>dataPaginated(List<ProductsForSaleDTO> dataFiltered,int page,int elementPerPage){
        return dataFiltered.stream()
                .skip((long) (page - 1) * elementPerPage)
                .limit(elementPerPage)
                .collect(Collectors.toList());
    }
    public List<ProductsForSaleDTO> mergeData(List<InventoryResponseDTO> inventoryList, List<ProductDTO> products) {
        Map<Long, InventoryResponseDTO> dataInventory = inventoryList.stream()
                //Function.identity() is equal to element -> element
                .collect(Collectors.toMap(InventoryResponseDTO::getId, Function.identity()));
        return products.stream()
                .filter(product -> dataInventory.containsKey(Long.valueOf(product.getId())))
                .map(product -> new ProductsForSaleDTO(Long.valueOf(product.getId()),
                        product.getName(),
                        dataInventory.get(Long.valueOf(product.getId())).getSalePrice(),
                        dataInventory.get(Long.valueOf(product.getId())).getStock(),
                        product.getDescription())).collect(Collectors.toList());
    }

}
