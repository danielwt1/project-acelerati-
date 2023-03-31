package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.CartModel;
import com.acelerati.management_service.infraestructure.output.entity.CartEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartEntityMapper {

    CartEntity toEntity(CartModel cartModel);

}
