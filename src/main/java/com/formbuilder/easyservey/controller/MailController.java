package com.formbuilder.easyservey.controller;

import com.formbuilder.easyservey.service.MailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailServiceImpl mailService;
    @PostMapping("/sendmailtoUsers")
    public String sendLinkToUsers(@RequestParam List<String> mailIds,@RequestParam String link,@RequestParam String senderMailId ) throws MessagingException {
        return mailService.sendMailToUser(mailIds,link,senderMailId);
    }
}
