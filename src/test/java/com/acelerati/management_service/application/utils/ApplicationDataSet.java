package com.acelerati.management_service.application.utils;

import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.application.dto.response.ProductDTO;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;

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

    public static final List<InventoryEntity> INVENTORY_1_ENTITY_LIST = Arrays.asList(
            new InventoryEntity(1L, "Mother board", 100L, BigDecimal.valueOf(300_000), BigDecimal.valueOf(350_000), 1L, 1L),
            new InventoryEntity(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryEntity(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L),
            new InventoryEntity(4L, "USB Memory 64GB", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 4L, 1L)
    );

    public static final List<InventoryResponseDTO> INVENTORY_1_FILTERED_BY_CATEGORY_1_DTO = Arrays.asList(
            new InventoryResponseDTO(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryResponseDTO(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L)
    );

    public static final List<InventoryModel> INVENTORY_1_FILTERED_BY_CATEGORY_1_MODEL = Arrays.asList(
            new InventoryModel(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryModel(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L)
    );

    public static final List<ProductDTO> PRODUCT_MICROSERVICE_RESPONSE_1 = Arrays.asList(
            new ProductDTO(1L, "Mother board", "Description 1", "Model 1", 1L, 1L),
            new ProductDTO(2L, "Ram 16GB DDR4", "Description 2", "Model 2", 2L, 1L),
            new ProductDTO(3L, "SSD Disk .m2 250GB", "Description 3", "Model 3", 3L, 1L),
            new ProductDTO(4L, "USB Memory 64GB", "Description 4", "Model 4", 2L, 2L),
            new ProductDTO(5L, "SD Card 64GB", "Description 5", "Model 5", 2L, 2L),
            new ProductDTO(6L, "Logitech Headset", "Description 6", "Model 6", 3L, 2L),
            new ProductDTO(7L, "USB Hub 8 ports", "Description 7", "Model 7", 3L, 3L),
            new ProductDTO(8L, "Dell Full HD monitor 25'", "Description 8", "Model 8", 1L, 3L)
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

    public static final List<InventoryModel> INVENTORY_3 = Arrays.asList(
            new InventoryModel(1L, "Mother board", 100L, BigDecimal.valueOf(300_000), BigDecimal.valueOf(350_000), 1L, 1L),
            new InventoryModel(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryModel(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L),
            new InventoryModel(4L, "USB Memory 64GB", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 4L, 1L),
            new InventoryModel(5L, "SD Card 64GB", 100L, BigDecimal.valueOf(300_000), BigDecimal.valueOf(350_000), 5L, 1L),
            new InventoryModel(6L, "Logitech Headset", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 6L, 1L),
            new InventoryModel(7L, "USB Hub 8 portsB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 7L, 1L),
            new InventoryModel(8L, "Dell Full HD monitor 25", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 8L, 1L)
    );

    public static final List<InventoryResponseDTO> INVENTORY_3_RESPONSE_DTO = Arrays.asList(
            new InventoryResponseDTO(1L, "Mother board", 100L, BigDecimal.valueOf(300_000), BigDecimal.valueOf(350_000), 1L, 1L),
            new InventoryResponseDTO(2L, "Ram 16GB DDR4", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 2L, 1L),
            new InventoryResponseDTO(3L, "SSD Disk .m2 250GB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 3L, 1L),
            new InventoryResponseDTO(4L, "USB Memory 64GB", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 4L, 1L),
            new InventoryResponseDTO(5L, "SD Card 64GB", 100L, BigDecimal.valueOf(300_000), BigDecimal.valueOf(350_000), 5L, 1L),
            new InventoryResponseDTO(6L, "Logitech Headset", 100L, BigDecimal.valueOf(120_000), BigDecimal.valueOf(125_000), 6L, 1L),
            new InventoryResponseDTO(7L, "USB Hub 8 portsB", 100L, BigDecimal.valueOf(240_000), BigDecimal.valueOf(240_000), 7L, 1L),
            new InventoryResponseDTO(8L, "Dell Full HD monitor 25", 100L, BigDecimal.valueOf(180_000), BigDecimal.valueOf(180_000), 8L, 1L)
    );

}
