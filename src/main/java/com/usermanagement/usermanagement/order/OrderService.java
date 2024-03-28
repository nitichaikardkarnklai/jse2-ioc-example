package com.usermanagement.usermanagement.order;

import com.usermanagement.usermanagement.mail.GoogleMailService;

public class OrderService {
    private GoogleMailService googleMailService;

    public OrderService() {
        this.googleMailService = new GoogleMailService();
        this.googleMailService.setUrl("mail.google.com");
        this.googleMailService.setPort("42");
    }

    public void createOrder() {
        googleMailService.sentEmail("user@gmail.com", "Order created");
    }
}
