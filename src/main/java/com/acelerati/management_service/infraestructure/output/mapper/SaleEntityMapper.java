package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.SaleModel;
import com.acelerati.management_service.infraestructure.output.entity.SaleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleEntityMapper {
    SaleEntity toEntity(SaleModel saleModel);

    SaleModel toModel(SaleEntity saleEntity);
}
