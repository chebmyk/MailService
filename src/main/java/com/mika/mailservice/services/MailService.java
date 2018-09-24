package com.mika.mailservice.services;

import com.mika.mailclient.model.Mail;
import org.springframework.http.HttpStatus;

public interface MailService {
    HttpStatus sendMimeMessage(Mail email);

    HttpStatus sendSimpleMail(Mail email);
}
