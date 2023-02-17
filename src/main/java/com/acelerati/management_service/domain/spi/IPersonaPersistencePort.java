package com.acelerati.management_service.domain.spi;

import com.example.hexagonalarch.domain.model.PersonaModel;

public interface IPersonaPersistencePort {
    //Metodos que va a implementar el componente de bd  () desde la infraestructura
    //logra inversion de dependencias
    //Puerto para  base de datos
    PersonaModel save(PersonaModel person);
}
