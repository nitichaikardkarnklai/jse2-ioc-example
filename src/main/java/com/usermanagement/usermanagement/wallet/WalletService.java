package com.usermanagement.usermanagement.wallet;

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

    public Wallet createWallet(WalletRequestDto requestDto) {
//
//            walletList.stream()
//                    .map(Wallet::getEmail)
//                    .filter( email -> email.equals(requestDto.email()))
//                    .findFirst()
//                    .ifPresent(wallet -> {
//                        throw new DuplicationException("Wallet with email: " + requestDto.email() + " is already exist");
//                    });
//
//            Optional<Integer> maxId = walletList.stream()
//                    .map(Wallet::getId)
//                    .max(Integer::compareTo);
//            int nextId = maxId.orElse(0) + 1;
//            Wallet wallet = new Wallet(nextId,requestDto.name(), requestDto.email());
//            walletList.add(wallet);
//
//            mailService.sentEmail("admin@wallet.com", "New wallet created");
//
//            return wallet;
        return null;
    }

    public void deleteWalletById(@PathVariable Integer id) {
//        walletList.removeIf(user -> user.getId().equals(id));
    }

    public void editWalletById(Integer id, WalletRequestDto requestDto) {
//        for (Wallet wallet: walletList) {
//            if (wallet.getId().equals(id)) {
//                wallet.setName(requestDto.name());
//                break;
//            }
//        }
    }

    public Wallet getWalletById(Integer id) {
//        return walletList.stream()
//                .filter(wallet -> wallet.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new NotFoundException("Wallet not found by id : "+id));
        return null;
    }

    private void callNormalService() {
        throw new RuntimeException();
    }
}
