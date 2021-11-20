package com.formbuilder.easyservey.service;



import javax.mail.MessagingException;
import java.util.List;

public interface IMailService {
    String sendMailToUser(List<String> mailInfoPayload,String link,String senderMailId) throws MessagingException;


}
