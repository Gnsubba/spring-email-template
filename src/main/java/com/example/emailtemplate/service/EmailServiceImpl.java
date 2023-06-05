package com.example.emailtemplate.service;

import com.example.emailtemplate.entity.EmailDetails;
import com.example.emailtemplate.pojo.EmailPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendEmail(EmailPojo emailPojo) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.setFrom(sender);
        message.setRecipients(MimeMessage.RecipientType.TO,emailPojo.getTo());
        message.setSubject(emailPojo.getSubject());
        String template = "This email is sent by: ${emailPojo.getFrom()}";
        message.setContent(template,"text/html; charset=utf-8");
        javaMailSender.send(message);
        System.out.println("The subject is:" + message.getSubject());
    }

    @Override
    public String sendMailWithAttachment(MultipartFile file,String from, String to, String subject, String body) {
        // Creating a mime message
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(body);
            mimeMessageHelper.setSubject(
                    subject);

            // Adding the attachment
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getName(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
