package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.SaleInventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import com.acelerati.management_service.infraestructure.output.entity.SaleInventoryEntity;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface BidirectionalSaleMapperCustom {
    @Mapping(source = "sale", target = "saleModel")
    SaleInventoryModel toModel(SaleInventoryEntity saleInventoryEntity);

    default SaleEntity truncateBidirectionalRelationSale(SaleEntity saleEntity) {
        List<SaleInventoryEntity> purchasedProducts = new ArrayList<>();
        return new SaleEntity(saleEntity.getIdSale(), saleEntity.getIdUser(), saleEntity.getSaleDate(),
                saleEntity.getStatus(), saleEntity.getIdShipping(), purchasedProducts);
    }

    default List<SaleInventoryModel> toListModel(List<SaleInventoryEntity> saleInventoryEntities) {
        if (saleInventoryEntities == null) {
            return Collections.emptyList();
        }
        List<SaleInventoryModel> saleInventoryModels = new ArrayList<>(saleInventoryEntities.size());
        saleInventoryEntities.forEach(saleInventoryEntity -> {
            SaleEntity saleEntity = truncateBidirectionalRelationSale(saleInventoryEntity.getSale());
            saleInventoryEntity.setSale(saleEntity);
            saleInventoryModels.add(toModel(saleInventoryEntity));
        });
        return saleInventoryModels;
    }
}
