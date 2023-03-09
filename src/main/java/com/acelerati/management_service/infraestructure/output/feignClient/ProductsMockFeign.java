package com.acelerati.management_service.infraestructure.output.feignClient;

import com.acelerati.management_service.application.dto.response.ProductDTO;

import java.util.ArrayList;
import java.util.List;

public class ProductsMockFeign {

    public static List<ProductDTO>getAll(){
        List<ProductDTO>products = new ArrayList<>();
        products.add(new ProductDTO(1,"Producto1","Descripcion1","Model 1",1,1));
        products.add(new ProductDTO(2,"Producto2","Descripcion2","Model 2",2,1));
        products.add(new ProductDTO(3,"Producto3","Descripcion3","Model 3",3,1));
        products.add(new ProductDTO(4,"Producto4","Descripcion4","Model 4",1,2));
        products.add(new ProductDTO(5,"Producto5","Descripcion5","Model 5",2,2));
        products.add(new ProductDTO(6,"Producto6","Descripcion6","Model 6",3,2));

        return products;
    }

}
