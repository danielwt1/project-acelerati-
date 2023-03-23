package com.acelerati.management_service.application.utils;

import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductFeignClientResponseDTO;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.ProductModel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ApplicationDataSet {

    public static final List<InventoryModel> INVENTORY_1 = Arrays.asList(
            new InventoryModel(1L, "Mother board", 100L, BigDecimal.valueOf(300_000), BigDecimal.valueOf(350_000), 1L, 1L),
            new InventoryModel(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryModel(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L),
            new InventoryModel(4L, "USB Memory 64GB", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 4L, 1L)
    );

    public static final List<InventoryResponseDTO> INVENTORY_1_RESPONSE_DTO = Arrays.asList(
            new InventoryResponseDTO(1L, "Mother board", 100L, BigDecimal.valueOf(300_000), BigDecimal.valueOf(350_000), 1L, 1L),
            new InventoryResponseDTO(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryResponseDTO(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L),
            new InventoryResponseDTO(4L, "USB Memory 64GB", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 4L, 1L)
    );

    public static final List<ProductModel> PRODUCT_MICROSERVICE_RESPONSE_1 = Arrays.asList(
            new ProductModel(1L, "Mother board", "Description 1", "Model 1", 1L, 1L),
            new ProductModel(2L, "Ram 16GB DDR4", "Description 2", "Model 2", 2L, 1L),
            new ProductModel(3L, "SSD Disk .m2 250GB", "Description 3", "Model 3", 3L, 1L),
            new ProductModel(4L, "USB Memory 64GB", "Description 4", "Model 4", 2L, 2L),
            new ProductModel(5L, "SD Card 64GB", "Description 5", "Model 5", 2L, 2L),
            new ProductModel(6L, "Logitech Headset", "Description 6", "Model 6", 3L, 2L),
            new ProductModel(7L, "USB Hub 8 ports", "Description 7", "Model 7", 3L, 3L),
            new ProductModel(8L, "Dell Full HD monitor 25'", "Description 8", "Model 8", 1L, 3L)
    );

    public static final List<ProductFeignClientResponseDTO> PRODUCT_MICROSERVICE_RESPONSE_DTO_1 = Arrays.asList(
            new ProductFeignClientResponseDTO(1L, "Mother board", "Description 1", "Model 1", 1L, 1L),
            new ProductFeignClientResponseDTO(2L, "Ram 16GB DDR4", "Description 2", "Model 2", 2L, 1L),
            new ProductFeignClientResponseDTO(3L, "SSD Disk .m2 250GB", "Description 3", "Model 3", 3L, 1L),
            new ProductFeignClientResponseDTO(4L, "USB Memory 64GB", "Description 4", "Model 4", 2L, 2L),
            new ProductFeignClientResponseDTO(5L, "SD Card 64GB", "Description 5", "Model 5", 2L, 2L),
            new ProductFeignClientResponseDTO(6L, "Logitech Headset", "Description 6", "Model 6", 3L, 2L),
            new ProductFeignClientResponseDTO(7L, "USB Hub 8 ports", "Description 7", "Model 7", 3L, 3L),
            new ProductFeignClientResponseDTO(8L, "Dell Full HD monitor 25'", "Description 8", "Model 8", 1L, 3L)
    );

    public static final List<InventoryModel> INVENTORY_2 = Arrays.asList(
            new InventoryModel(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryModel(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L),
            new InventoryModel(4L, "USB Memory 64GB", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 4L, 1L)
    );

    public static final List<InventoryResponseDTO> INVENTORY_2_RESPONSE_DTO = Arrays.asList(
            new InventoryResponseDTO(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryResponseDTO(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L),
            new InventoryResponseDTO(4L, "USB Memory 64GB", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 4L, 1L)
    );

}
