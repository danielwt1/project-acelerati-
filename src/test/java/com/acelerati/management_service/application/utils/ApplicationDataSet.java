package com.acelerati.management_service.application.utils;

import com.acelerati.management_service.domain.model.InventoryModel;

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

}
