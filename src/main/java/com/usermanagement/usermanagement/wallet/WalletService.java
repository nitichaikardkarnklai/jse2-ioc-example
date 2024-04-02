package com.usermanagement.usermanagement.wallet;

import com.usermanagement.usermanagement.exception.BadRequestException;
import com.usermanagement.usermanagement.exception.DuplicationException;
import com.usermanagement.usermanagement.exception.InternalServiceException;
import com.usermanagement.usermanagement.exception.NotFoundException;
import com.usermanagement.usermanagement.mail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private final MailService mailService;

    private final WalletRepository walletRepository;

    public WalletService(@Qualifier("googleMail") MailService mailService, WalletRepository walletRepository) {
        this.mailService = mailService;
        this.walletRepository = walletRepository;
    }

    public List<Wallet> getWalletList() {
        List<Wallet> walletList = walletRepository.findAll();
        return walletList;
    }

    public Wallet createWallet(WalletRequestDto requestDto) throws Exception {
        Wallet wallet = new Wallet();
        wallet.setWalletName(requestDto.name());
        wallet.setActive(true);
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
