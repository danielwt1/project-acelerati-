package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.InventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.InventoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface InventoryEntityMapper {
    InventoryEntity toEntity(InventoryModel inventoryModel);
    List<InventoryEntity> toListEntity(List<InventoryModel> inventoryModelList);
    InventoryModel toModel(InventoryEntity inventoryEntity);
    List<InventoryModel>toListModel( List<InventoryEntity> inventoryEntityList);
}
