package jplopes.com.picpay.service;

import jplopes.com.picpay.Exception.WalletDataAlreadyExistsException;
import jplopes.com.picpay.entity.Wallet;
import jplopes.com.picpay.entity.dto.CreateWalletDto;
import jplopes.com.picpay.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;


    public Wallet createWallet(CreateWalletDto dto){
         var walletDB = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());
         if (walletDB.isPresent()){
            throw new WalletDataAlreadyExistsException("CpfCnpj or Email already exists");
         }
        return walletRepository.save(dto.toWallet());

    }
}
