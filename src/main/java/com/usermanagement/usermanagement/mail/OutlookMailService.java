package com.usermanagement.usermanagement.mail;

//import org.springframework.stereotype.Service;

//@Service
public class OutlookMailService implements MailService {
    private String url;
    private String port;

    public OutlookMailService() {
        this.setUrl("mail.outlook.com");
        this.setPort("37");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void sentEmail(String to, String body) {
        // TODO: Sent Email Notification
        System.out.println("Sent Email!!! by outlook naja");
    }
}
