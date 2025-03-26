package com.security.demo.controller;

import com.security.demo.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmailController {
    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/enviar")
    public String sendEmail(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String text){
        emailService.sendEmail(to, subject, text);
        return "Email enviado com sucesso para: " + to;
    }
}
