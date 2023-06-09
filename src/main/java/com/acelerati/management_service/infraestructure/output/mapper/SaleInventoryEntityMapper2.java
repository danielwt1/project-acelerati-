package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.SaleInventoryModel;
import com.acelerati.management_service.domain.model.SaleModel;
import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import com.acelerati.management_service.infraestructure.output.entity.SaleInventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleInventoryEntityMapper2 {

    // Child
    @Mapping(target = "sale", qualifiedByName = "toSaleEntity", source = "saleModel")
    SaleInventoryEntity toSaleInventoryEntity(SaleInventoryModel saleInventoryModel);

    @Mapping(target = "sale", qualifiedByName = "toSaleEntity", source = "saleModel")
    List<SaleInventoryEntity> toListEntity(List<SaleInventoryModel> saleInventoryModels);

    // Father
    @Named("toSaleEntity")
    @Mapping(target = "purchasedItems", expression = "java(null)")
    SaleEntity toSaleEntity(SaleModel saleModel);

    // Model child
    @Named("toSaleInventoryModel")
    @Mapping(target = "saleModel", expression = "java(null)", source = "sale")
    SaleInventoryModel toSaleInventoryModel(SaleInventoryEntity saleInventoryEntity);

    @Named("toSaleInventoryModelList")
    @Mapping(target = "saleModel", qualifiedByName = "toSaleInventoryModel", source = "sale")
    List<SaleInventoryModel> toSaleInventoryModelList(List<SaleInventoryEntity> saleInventoryEntities);

    // Model father
    @Mapping(target = "purchasedItems", qualifiedByName = "toSaleInventoryModelList")
    SaleModel toSaleModel(SaleEntity saleEntity);
}
