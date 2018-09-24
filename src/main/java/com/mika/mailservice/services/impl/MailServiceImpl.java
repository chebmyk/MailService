package com.mika.mailservice.services.impl;

import com.mika.mailclient.model.Mail;
import com.mika.mailservice.services.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/mail")
public class MailServiceImpl implements MailService {

    private JavaMailSender emailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    @GET
    public Response service() {
        return Response.ok("Serive is running").build();
    }

    @POST
    @Path("/send/mime")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Async("threadPoolExecutor")
    public HttpStatus sendMimeMessage(Mail email) {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(email.getTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText());
        } catch (MessagingException e) {
            throw new RuntimeException(e.toString());
        }
        emailSender.send(message);
        log.info("Sending email: [" + email.toString() + "]");
        return HttpStatus.OK;
    }

    @POST
    @Path("/send/simple")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@Async("threadPoolExecutor")
    public HttpStatus sendSimpleMail(Mail email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        emailSender.send(message);
        log.info("Sending email: [" + email.toString() + "]");
        return HttpStatus.OK;
    }
}
