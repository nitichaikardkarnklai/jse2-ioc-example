package com.usermanagement.usermanagement.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Modifying
    @Transactional  // If implement modifying query, we must add "transactional".
    @Query("UPDATE Wallet w SET w.active = true")
    void setAllWalletActive();

    @Modifying
    @Transactional
    @Query("DELETE FROM Wallet w WHERE w.id < 3")
    void deleteWalletByIdBelow3();
}