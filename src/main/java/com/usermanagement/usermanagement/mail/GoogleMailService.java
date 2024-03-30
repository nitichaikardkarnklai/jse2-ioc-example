package com.usermanagement.usermanagement.mail;

//import org.springframework.stereotype.Service;

//@Service
public class GoogleMailService implements MailService {
    private String url;
    private String port;

    public GoogleMailService() {
        this.setUrl("smtp.google.com");
        this.setPort("465");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void sentEmail(String to, String body) {
        // TODO: Sent Email Notification
        System.out.println("Sent Email!!! by google");
    }
}
