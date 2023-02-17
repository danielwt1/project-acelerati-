package com.acelerati.management_service.domain.api;

import com.example.hexagonalarch.domain.model.PersonaModel;

public interface IPersonServicePort {
    // Metodos que queremos que el dominio exponga
    void saVe(PersonaModel persona);
}
