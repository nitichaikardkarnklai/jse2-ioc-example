package com.usermanagement.usermanagement.wallet;

import com.usermanagement.usermanagement.profile.Profile;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.coyote.BadRequestException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("")
    public List<Wallet> getWalletList() {
        return walletService.getWalletList();
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable Integer id) {
        return walletService.getWalletById(id);
    }

    @PostMapping("")
    public Wallet createWallet(@Valid @RequestBody WalletRequestDto requestDto) throws Exception {
        return this.walletService.createWallet(requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        this.walletService.deleteWalletById(id);
    }

    @PutMapping("/{id}")
    public Wallet editWallet(@PathVariable Integer id, @RequestBody WalletRequestDto requestDto) {
        return this.walletService.editWalletById(id, requestDto);
    }

}

record WalletRequestDto(
        @NotNull(message = "name cannot be null")
        @Size(min = 3, max = 20, message = "Wallet name should be between 3 and 20 characters")
        String name,
        @NotNull(message = "Email cannot be null")
        @Email(message = "Email should be valid")
        String email){}

