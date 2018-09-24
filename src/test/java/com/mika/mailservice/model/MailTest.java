package com.mika.mailservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mika.mailclient.model.Mail;
import org.junit.Test;

import java.io.IOException;

public class MailTest {

    @Test
    public void testJSON() throws JsonProcessingException {
        Mail mail = new Mail.Builder()
                .to("to")
                .subject("email subject")
                .text("email text").build();
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(mail));
    }

    @Test
    public void testFromJSON() throws IOException {
        String json = "{\"from\":null,\"to\":\"to\",\"cc\":\"\",\"bcc\":\"\",\"subject\":\"email subject\",\"text\":\"email text\"}";
        ObjectMapper mapper = new ObjectMapper();
        mapper.reader().forType(Mail.class).readValue(json);
    }

}