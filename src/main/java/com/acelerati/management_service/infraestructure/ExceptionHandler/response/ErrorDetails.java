package com.acelerati.management_service.infraestructure.ExceptionHandler.response;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime fecha;
    private String message;
    private String ruta;

    public ErrorDetails() {
    }

    public ErrorDetails(LocalDateTime fecha, String message, String ruta) {
        this.fecha = fecha;
        this.message = message;
        this.ruta = ruta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
}
