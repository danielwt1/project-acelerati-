package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.SaleInventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.SaleInventoryEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SaleInventoryEntityMapper extends BidirectionalSaleMapperCustom {
    @Mappings({
            @Mapping(target = "sale", source = "saleModel")
    })
    SaleInventoryEntity toEntity(SaleInventoryModel saleInventoryModel,
                                 @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    SaleInventoryModel toModel(SaleInventoryEntity saleInventoryEntity,
                               @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


    @Mapping(target = "sale", source = "saleModel")
    List<SaleInventoryEntity> toListEntity(List<SaleInventoryModel> saleInventoryModels);
}
