package com.usermanagement.usermanagement.profile;

import com.usermanagement.usermanagement.wallet.Wallet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Email
    private String email;

    private  String name;

    @OneToMany(mappedBy = "profile")
    private List<Wallet> walletList;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
