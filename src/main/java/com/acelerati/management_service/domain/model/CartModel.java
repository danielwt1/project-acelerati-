package com.acelerati.management_service.domain.model;

import java.time.LocalDateTime;

public class CartModel {

    private Long id;

    private Long idUser;

    private LocalDateTime lastUpdate;

    public CartModel(Long id, Long idUser, LocalDateTime lastUpdate) {
        this.id = id;
        this.idUser = idUser;
        this.lastUpdate = lastUpdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
