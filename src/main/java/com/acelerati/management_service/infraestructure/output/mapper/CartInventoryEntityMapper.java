package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartInventoryEntityMapper {

    CartInventoryEntity toEntity(CartInventoryModel cartInventoryModel);

}
