package com.acelerati.management_service.infraestructure.input.rest;


import com.acelerati.management_service.application.handler.InventorySpringService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persona/")
public class PersonaCtrl {

    private final InventorySpringService inventorySpringService;

    public PersonaCtrl(InventorySpringService inventorySpringService) {
        this.inventorySpringService = inventorySpringService;
    }



}
