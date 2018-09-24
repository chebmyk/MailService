package com.mika.mailclient.impl;

import com.mika.mailclient.MailClient;
import com.mika.mailclient.model.Mail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MailClientImpl implements MailClient {

    private final String SERVICE_URL;

    public MailClientImpl(String serviceUrl) {
        this.SERVICE_URL = serviceUrl.trim();
    }

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public HttpStatus sendMimeMessage(Mail email) {
        checkSettings();
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(SERVICE_URL + "/send/mime", email, HttpStatus.class);
        return response.getBody();

    }

    @Override
    public HttpStatus sendSimpleMail(Mail email) {
        checkSettings();
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(SERVICE_URL + "/send/simple", email, HttpStatus.class);
        return response.getBody();
    }

    private void checkSettings() {
        System.out.println("ServiceURL=" + SERVICE_URL);
        if (SERVICE_URL.length() == 0) {
            throw new RuntimeException("Mail service URL is not set.");
        }
    }
}
