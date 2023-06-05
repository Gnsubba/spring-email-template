package com.example.emailtemplate.controller;

import com.example.emailtemplate.entity.EmailDetails;
import com.example.emailtemplate.pojo.EmailPojo;
import com.example.emailtemplate.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/email/")
public class EmailController {

    @Autowired
 private EmailService emailService;

    @PostMapping("send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailPojo emailPojo) throws MessagingException {
        emailService.sendEmail(emailPojo);
        return ResponseEntity.status(HttpStatus.OK).body("sent");
    }

    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment(@RequestParam("file") MultipartFile file,
            @RequestParam("from") String from,@RequestParam("to") String to, @RequestParam("subject") String subject, @RequestParam("body") String body)
    {
        String status
                = emailService.sendMailWithAttachment(file,from,to,subject,body);

        return status;
    }


}
