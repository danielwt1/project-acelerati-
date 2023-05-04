package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import org.mapstruct.Context;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartInventoryEntityMapper extends BidirectionalCartMapperCustom {

    @Mapping(target = "cart", source = "cartModel")
    CartInventoryEntity toEntity(CartInventoryModel cartInventoryModel, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @InheritInverseConfiguration
    CartInventoryModel toModel(CartInventoryEntity cartInventoryEntity, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
