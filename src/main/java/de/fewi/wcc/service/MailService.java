package de.fewi.wcc.service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${mailserver.host}")
    String mailServerHost;

    @Value("${mailserver.port}")
    int mailServerPort;

    @Value("${mailserver.username}")
    String mailServerUsername;

    @Value("${mailserver.password}")
    String mailServerPassword;

    @Value("${email.from}")
    String emailFrom;

    @Value("${email.subject}")
    String emailSubject;

    @Value("${email.message}")
    String emailMessage;

    @Value("${email.to}")
    String emailTo;

    public boolean sendMail(){
        Email email = new SimpleEmail();
        email.setHostName(mailServerHost);
        email.setSmtpPort(mailServerPort);
        email.setAuthenticator(new DefaultAuthenticator(mailServerUsername, mailServerPassword));
        email.setSSLOnConnect(false);
        try {
            email.setFrom(emailFrom);
            email.setSubject(emailSubject);
            email.setMsg(emailMessage);
            for (String to : getAdresses())
                email.addTo(to.trim());

            email.send();
            logger.info("Sent mail ...");
            return true;
        } catch (EmailException e) {
            logger.warn("Email error",e);
        }
            return false;
    }

    private String[] getAdresses(){
        String[] adresses = emailTo.split(",");
        return adresses;
    }
}
