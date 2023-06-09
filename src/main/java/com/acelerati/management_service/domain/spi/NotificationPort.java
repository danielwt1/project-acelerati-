package com.acelerati.management_service.domain.spi;

public interface NotificationPort {

    void sendNotification(String message);
}
