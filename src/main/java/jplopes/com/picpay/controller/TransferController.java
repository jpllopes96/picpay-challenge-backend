package jplopes.com.picpay.controller;

import jakarta.validation.Valid;
import jplopes.com.picpay.entity.Transfer;
import jplopes.com.picpay.entity.dto.TransferDto;
import jplopes.com.picpay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDto dto){

        var resp = transferService.transfer(dto);

        return ResponseEntity.ok(resp);

    }
}
