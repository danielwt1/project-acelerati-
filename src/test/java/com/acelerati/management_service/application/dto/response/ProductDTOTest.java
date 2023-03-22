package com.acelerati.management_service.application.dto.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDTOTest {
    private ProductDTO productDTO= null;
    @BeforeEach
    void setup(){
        productDTO = new ProductDTO(1L,"Producto1","Producto1Des","Modelo1",2L,1L);
    }
    @Test
    void getId() {
        assertEquals(1L,this.productDTO.getId());
    }

    @Test
    void getName() {
        assertEquals("Producto1",this.productDTO.getName());
    }

    @Test
    void getDescription() {
        assertEquals("Producto1Des",this.productDTO.getDescription());
    }

    @Test
    void getModel() {
        assertEquals("Modelo1",this.productDTO.getModel());
    }

    @Test
    void getIdBrand() {
        assertEquals(2L,this.productDTO.getIdBrand());
    }

    @Test
    void getIdCategory() {
        assertEquals(1L,this.productDTO.getIdCategory());
    }

}