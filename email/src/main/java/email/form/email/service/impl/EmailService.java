package email.form.email.service.impl;

import email.form.email.model.Email;
import email.form.email.service.interfaces.IEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService implements IEmailService {

    @Autowired
    JavaMailSender sender;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(EmailService.class);

    public boolean sendEmail(Email email) {
        logger.info("EmailBody: {}", email.toString());
        return sendEmailTool(email.getTextMessage(),email.getAddress(), email.getSubject());
    }

    public boolean sendEmailTool(String textMessage, String address, String subject) {
        boolean send = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(address);
            helper.setText(textMessage, true);
            helper.setSubject(subject);
            sender.send(message);
            send = true;
            logger.info("e-mail sent");
        } catch (MessagingException e) {
            logger.error("There was an error sending the e-mail: {}", e);
        }
        return send;
    }
}

