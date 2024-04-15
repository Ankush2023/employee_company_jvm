package com.epms.company.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailConfig {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.from}")
    private String from;
    public void sendEmail(String to, String subject,String body) {
        try {
            MimeMessage mime = javaMailSender.createMimeMessage();
            MimeMessageHelper mail = new MimeMessageHelper(mime);
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            mail.setFrom(from);
            javaMailSender.send(mime);
            System.out.println("send");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
