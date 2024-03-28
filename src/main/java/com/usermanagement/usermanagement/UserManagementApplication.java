package com.usermanagement.usermanagement;

import com.usermanagement.usermanagement.mail.GoogleMailService;
import com.usermanagement.usermanagement.mail.MailService;
import com.usermanagement.usermanagement.mail.OutlookMailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserManagementApplication.class, args);
    }

//    @Bean
//    public MailService mailService() {
//        return new GoogleMailService();
//    }

//    @Bean
//    public MailService mailService() {
//        return new OutlookMailService();
//    }

    @Bean
    public MailService outlookMail() {
        return new OutlookMailService();
    }

    @Bean
    public MailService googleMail() {
        return new GoogleMailService();
    }

}
