package com.website.common.service;

import com.website.common.dto.request.SendEmailRequest;import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${MAIL_USERNAME}")
    private String sender;

    public void sendEmail(SendEmailRequest sendEmailRequest) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(sendEmailRequest.getRecipient());
            mimeMessageHelper.setSubject(sendEmailRequest.getSubject());
            mimeMessageHelper.setText(sendEmailRequest.getMessage(), true); // Set to true to indicate HTML

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage(), e);
        }
    }
}
