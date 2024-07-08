package com.example.smartpensionsystem.utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String toEmail, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(toEmail);
            helper.setFrom("2199098121@qq.com");
            helper.setSubject(subject);
            helper.setText(text, true); // true indicates HTML content

            javaMailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            e.printStackTrace(); // handle the exception appropriately
        }
    }
}
