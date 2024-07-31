package jplopes.com.picpay.service;

import jakarta.transaction.Transactional;
import jplopes.com.picpay.Exception.InsufficientBalanceException;
import jplopes.com.picpay.Exception.TransferNotAllowedForWalletTypeException;
import jplopes.com.picpay.Exception.TransferNotAuthorizedException;
import jplopes.com.picpay.Exception.WalletNotFoundException;
import jplopes.com.picpay.entity.Transfer;
import jplopes.com.picpay.entity.Wallet;
import jplopes.com.picpay.entity.dto.TransferDto;
import jplopes.com.picpay.repository.TransferRepository;
import jplopes.com.picpay.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TransferService {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private WalletRepository walletRepository;


    @Transactional
    public Transfer transfer(TransferDto transferDto){

        var sender = walletRepository.findById(transferDto.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payer()));

        var receiver = walletRepository.findById(transferDto.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDto.payee()));

        validateTransfer(transferDto, sender);

        sender.debit(transferDto.value());
        receiver.credit(transferDto.value());

        var transfer = new Transfer(sender, receiver, transferDto.value());

        walletRepository.save(sender);
        walletRepository.save(receiver);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotificaiton(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDto transferDto, Wallet sender){
        if (!sender.isTransferAllowedForWalletType()){
            throw new TransferNotAllowedForWalletTypeException();
        }

        if (!sender.isBalanceEqualOrGreatherThan(transferDto.value())){
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDto)){
            throw new TransferNotAuthorizedException();
        }
    }

}
