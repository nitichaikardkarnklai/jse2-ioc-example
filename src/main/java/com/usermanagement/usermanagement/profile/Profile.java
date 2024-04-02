package com.usermanagement.usermanagement.profile;

import com.usermanagement.usermanagement.wallet.Wallet;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    private String email;

    private  String name;

    @OneToMany(mappedBy = "profile")
    private List<Wallet> walletList;
}
