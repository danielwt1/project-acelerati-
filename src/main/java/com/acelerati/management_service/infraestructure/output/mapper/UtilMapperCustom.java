package com.acelerati.management_service.infraestructure.output.mapper;

import com.acelerati.management_service.domain.model.CartInventoryModel;
import com.acelerati.management_service.infraestructure.output.entity.CartEntity;
import com.acelerati.management_service.infraestructure.output.entity.CartInventoryEntity;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

public interface UtilMapperCustom {
    @Mapping(source = "cart", target = "cartModel")
    CartInventoryModel toModel(CartInventoryEntity cartInventoryEntity);
    default CartEntity truncateBidirectionalRelationCart(CartEntity cartEntity) {
        List<CartInventoryEntity> products = new ArrayList<>();
        return new CartEntity(cartEntity.getIdCart(), cartEntity.getIdUser(), cartEntity.getLastUpdate(), products);
    }
    default List<CartInventoryModel> toListModel(List<CartInventoryEntity> products) {
        if (products == null) {
            return null;
        }
        List<CartInventoryModel> models = new ArrayList<>(products.size());
        products.forEach(product -> {
                    CartEntity cartEntity = truncateBidirectionalRelationCart(product.getCart());
                    product.setCart(cartEntity);
                    models.add(toModel(product));
                }
        );
        return models;
    }
}
