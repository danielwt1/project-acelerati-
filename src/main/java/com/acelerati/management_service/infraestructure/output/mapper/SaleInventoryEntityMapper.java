package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.SaleInventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.SaleInventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleInventoryEntityMapper {

    @Mapping(target = "sale", ignore = true)
    SaleInventoryEntity toSaleInventoryEntity(SaleInventoryModel saleInventoryModel);

    List<SaleInventoryEntity> toListEntity(List<SaleInventoryModel> saleInventoryModels);

    @Mapping(target = "saleModel", ignore = true)
    SaleInventoryModel toSaleInventoryModel(SaleInventoryEntity saleInventoryEntity);

    List<SaleInventoryModel> toSaleInventoryModelList(List<SaleInventoryEntity> saleInventoryEntities);

}
