package com.example.emailtemplate.service;

import com.example.emailtemplate.entity.EmailDetails;
import com.example.emailtemplate.pojo.EmailPojo;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface EmailService {

    void sendEmail(EmailPojo emailPojo) throws MessagingException;
    String sendMailWithAttachment(MultipartFile file, String from, String to, String subject, String body);
}
