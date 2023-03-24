package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.request.AddProductToCartDTO;
import com.acelerati.management_service.domain.model.CartInventoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CartRequestMapper {

    CartInventoryModel toCartInventoryModel(AddProductToCartDTO addProductToCartDTO);
    //It's missing an attribute (idProduct) because of the difference between idProduct and InventoryModel

}
