package com.usermanagement.usermanagement.wallet;

import com.usermanagement.usermanagement.exception.BadRequestException;
import com.usermanagement.usermanagement.exception.DuplicationException;
import com.usermanagement.usermanagement.exception.InternalServiceException;
import com.usermanagement.usermanagement.exception.NotFoundException;
import com.usermanagement.usermanagement.mail.MailService;
import com.usermanagement.usermanagement.profile.Profile;
import com.usermanagement.usermanagement.profile.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private final MailService mailService;

    private final WalletRepository walletRepository;
    private final ProfileRepository profileRepository;

    public WalletService(@Qualifier("googleMail") MailService mailService,
                         WalletRepository walletRepository,
                         ProfileRepository profileRepository) {
        this.mailService = mailService;
        this.walletRepository = walletRepository;
        this.profileRepository = profileRepository;
    }

    public List<Wallet> getWalletList() {
        List<Wallet> walletList = walletRepository.findAll();
        return walletList;
    }

    @Transactional
    public Wallet createWallet(WalletRequestDto requestDto) throws Exception {
        Optional<Profile> optionalProfile = profileRepository.findByEmail(requestDto.email());
        Profile profile;
        if(optionalProfile.isPresent()) {
            profile = optionalProfile.get();
        } else {
            profile = new Profile();
            profile.setName("Someone");
            profile.setEmail(requestDto.email());
            profileRepository.save(profile);
        }

        Wallet wallet = new Wallet();
        wallet.setWalletName(requestDto.name());
        wallet.setActive(true);
        wallet.setProfile(profile);
        walletRepository.save(wallet);
        return wallet;
    }

    public Wallet editWalletById(Integer id, WalletRequestDto requestDto) {
        Optional<Wallet> optionalWallet = walletRepository.findById(Long.valueOf(id));
        if (optionalWallet.isEmpty()) {
            throw new BadRequestException("Invalid wallet id");
        }

        Wallet wallet = optionalWallet.get();
        wallet.setWalletName((requestDto.name()));
        walletRepository.save(wallet);
        return wallet;
    }

    public Wallet getWalletById(Integer id) {
        Optional<Wallet> optionalWallet = walletRepository.findById(Long.valueOf(id));
        if (optionalWallet.isEmpty()) {
            throw new BadRequestException("Invalid wallet id");
        }

        Wallet wallet = optionalWallet.get();
        return wallet;
    }

    public void deleteWalletById(@PathVariable Integer id) {
        walletRepository.deleteById(Long.valueOf(id));
    }

    // NOT IMPLEMENT
//    public void activeAllWallet() {
//        walletRepository.setAllWalletActive();
//        // walletRepository.deleteWalletByIdBelow3();
//    }
}
