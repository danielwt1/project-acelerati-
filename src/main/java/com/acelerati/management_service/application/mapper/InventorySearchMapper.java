package com.acelerati.management_service.application.mapper;

import com.acelerati.management_service.application.dto.request.InventorySearchCriteriaDTO;
import com.acelerati.management_service.application.dto.response.InventoryResponseDTO;
import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.domain.model.InventorySearchCriteriaModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventorySearchMapper {
    InventorySearchCriteriaModel toModel(InventorySearchCriteriaDTO searchCriteriaDTO);
    List<InventoryResponseDTO> toDTOList(List<InventoryModel> inventoryModels);
}
