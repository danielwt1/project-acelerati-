package com.acelerati.management_service.infraestructure.input.rest;


import com.example.hexagonalarch.application.handler.IPersonaHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/persona/")
public class PersonaCtrl {

    private final IPersonaHandler personaHndler;

    public PersonaCtrl(IPersonaHandler personaHndler) {
        this.personaHndler = personaHndler;
    }



}
