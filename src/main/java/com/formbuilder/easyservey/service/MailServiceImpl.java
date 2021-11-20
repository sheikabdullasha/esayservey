package com.formbuilder.easyservey.service;


import com.formbuilder.easyservey.repo.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;
import java.util.List;
import java.util.Random;
import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {


    private final TemplateEngine templateEngine;



    private final IUserRepository iUserRepository;

    private final JavaMailSender mailSender;




    @Override
    public String sendMailToUser(List<String> mailIds,String link,String senderMailId) throws MessagingException {
        for (String mailId : mailIds
        ) {
            Context context = new Context();
            context.setVariable("link", link);
            String process = templateEngine.process("emails/UserNotification", context);
            javax.mail.internet.MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setSubject("Easy survey ");
            helper.setText(process, true);
            helper.setTo(mailId);
            helper.setFrom(senderMailId);
            mailSender.send(mimeMessage);
        }
        return "Sent";
    }


}
