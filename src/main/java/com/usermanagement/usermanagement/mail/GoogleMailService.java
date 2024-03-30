package com.usermanagement.usermanagement.mail;

//import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

//@Service
public class GoogleMailService implements MailService {
    @Value("${mail-provider.google.url}")
    private String url;
    @Value("${mail-provider.google.url-port}")
    private String port;

    public GoogleMailService() {
        // dev-smtp.google.com
        // test-smtp.google.com
        // smtp.google.com
//        this.setUrl("smtp.google.com");
//        this.setPort("465");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void sentEmail(String to, String body) {
        // TODO: Sent Email Notification
        System.out.println("Sent Email!!! by google: " + url + " : " + port);
    }
}
