package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.SaleModel;
import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        uses = SaleInventoryEntityMapper.class)
public interface SaleEntityMapper {

    SaleEntity toEntity(SaleModel saleModel);

    SaleModel toModel(SaleEntity saleEntity);
}
