package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.response.ProductFeignClientResponseDTO;
import com.acelerati.management_service.domain.model.ProductModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {
    List<ProductModel> toProductModelList(List<ProductFeignClientResponseDTO> productFeignClientResponseDTOS);
    List<ProductFeignClientResponseDTO> toProductFeignClientResponseDTOList(List<ProductModel> productModelList);
}
