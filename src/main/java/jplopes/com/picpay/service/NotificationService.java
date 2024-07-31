package jplopes.com.picpay.service;

import jplopes.com.picpay.client.NotificationClient;
import jplopes.com.picpay.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationClient notificationClient;

    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotificaiton(Transfer transfer){
        try{
            logger.info("Sending notification...");
            var resp = notificationClient.sendNotification(transfer);
            if (resp.getStatusCode().isError()){
                logger.error("Error while sending notification, status code is not Ok");
            }
        }catch (Exception e){
            logger.error("Error while sending notification", e);
        }
    }
}
