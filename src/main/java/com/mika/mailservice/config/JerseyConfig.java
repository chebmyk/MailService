package com.mika.mailservice.config;

import com.mika.mailservice.handlers.RsExceptionHandler;
import com.mika.mailservice.services.impl.MailServiceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(MailServiceImpl.class);
        register(RsExceptionHandler.class);
    }
}
