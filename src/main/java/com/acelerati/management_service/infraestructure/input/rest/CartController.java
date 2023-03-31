package com.acelerati.management_service.infraestructure.input.rest;

import com.acelerati.management_service.application.dto.request.AddProductToCartDTO;
import com.acelerati.management_service.application.handler.CartSpringService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart/")
@RequiredArgsConstructor
public class CartController {

    private final CartSpringService cartSpringService;

    @PostMapping("/")
    public ResponseEntity<Void> addProductToCart(@RequestBody AddProductToCartDTO addProductToCartDTO){
        cartSpringService.addProductToCart(addProductToCartDTO);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
