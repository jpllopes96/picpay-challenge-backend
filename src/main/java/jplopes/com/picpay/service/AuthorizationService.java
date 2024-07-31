package jplopes.com.picpay.service;

import jplopes.com.picpay.Exception.PicPayException;
import jplopes.com.picpay.client.AuthorizationClient;
import jplopes.com.picpay.entity.Transfer;
import jplopes.com.picpay.entity.dto.TransferDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transferDto){

        var resp = authorizationClient.isAuthorized();

        if(resp.getStatusCode().isError()){
            throw new PicPayException();
        }

        return resp.getBody().authorized();
    }
}
