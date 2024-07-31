package jplopes.com.picpay.controller;

import jakarta.validation.Valid;
import jplopes.com.picpay.entity.Wallet;
import jplopes.com.picpay.entity.dto.CreateWalletDto;
import jplopes.com.picpay.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid CreateWalletDto dto){
       var wallet =  walletService.createWallet(dto);
       return ResponseEntity.ok(wallet);
    }
}
