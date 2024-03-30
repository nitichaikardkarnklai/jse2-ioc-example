package com.usermanagement.usermanagement.wallet;

import com.usermanagement.usermanagement.exception.DuplicationException;
import com.usermanagement.usermanagement.exception.InternalServiceException;
import com.usermanagement.usermanagement.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    private List<Wallet> walletList = new ArrayList<>(
            List.of(new Wallet(1,"Saving house", "saving@gmail.com"),
                    new Wallet(2,"Saving car", "savingcar@gmail.com"),
                    new Wallet(3,"Penny wallet", "savingpenny@gmail.com"))
    );


    public List<Wallet> getWalletList() {
        try {
            // For test Exception
            // callNormalService();
        } catch (Exception e) {
            throw new InternalServiceException("Internal service exception with Normal service");
        }
        return walletList;
    }

    public Wallet createWallet(WalletRequestDto requestDto) {

            walletList.stream()
                    .map(Wallet::getEmail)
                    .filter( email -> email.equals(requestDto.email()))
                    .findFirst()
                    .ifPresent(wallet -> {
                        throw new DuplicationException("Wallet with email: " + requestDto.email() + " is already exist");
                    });

            Optional<Integer> maxId = walletList.stream()
                    .map(Wallet::getId)
                    .max(Integer::compareTo);
            int nextId = maxId.orElse(0) + 1;
            Wallet wallet = new Wallet(nextId,requestDto.name(), requestDto.email());
            walletList.add(wallet);
            return wallet;
    }

    public void deleteWalletById(@PathVariable Integer id) {
        walletList.removeIf(user -> user.getId().equals(id));
    }

    public void editWalletById(Integer id, WalletRequestDto requestDto) {
        for (Wallet wallet: walletList) {
            if (wallet.getId().equals(id)) {
                wallet.setName(requestDto.name());
                break;
            }
        }
    }

    public Wallet getWalletById(Integer id) {
        return walletList.stream()
                .filter(wallet -> wallet.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Wallet not found by id : "+id));
    }

    private void callNormalService() {
        throw new RuntimeException();
    }
}
