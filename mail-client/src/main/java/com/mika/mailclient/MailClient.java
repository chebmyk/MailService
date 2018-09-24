package com.mika.mailclient;

import com.mika.mailclient.model.Mail;
import org.springframework.http.HttpStatus;

public interface MailClient {
    HttpStatus sendMimeMessage(Mail email);

    HttpStatus sendSimpleMail(Mail email);
}
