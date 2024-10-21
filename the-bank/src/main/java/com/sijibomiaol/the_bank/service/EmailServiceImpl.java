package com.sijibomiaol.the_bank.service;

import com.sijibomiaol.the_bank.dto.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;
    @Override
    @Async
    public void sendEMailAlert(EmailDetails emailDetails) {

        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(senderEmail);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setSubject(emailDetails.getSubject());
            mailMessage.setText(emailDetails.getBody());

            mailSender.send(mailMessage);
            System.out.println("mail sent successfully");
        }
        catch (MailException e) {
            throw new RuntimeException(e);

        }
    }
    @Override
    public void sendEmailWithAttachement(EmailDetails emailDetails){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(senderEmail);
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            mimeMessageHelper.setText(emailDetails.getBody(), true);

            FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);
            mailSender.send(mimeMessage);
            log.info(file.getFilename() + "has been sent to user with email " + emailDetails.getRecipient());
        } catch (Exception e) {
            throw new RuntimeException("Error occur");
        }
    }
}
