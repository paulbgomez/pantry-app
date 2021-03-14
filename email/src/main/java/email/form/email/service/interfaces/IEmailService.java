package email.form.email.service.interfaces;

import email.form.email.model.Email;

public interface IEmailService {
    boolean sendEmail(Email email);
    boolean sendEmailTool(String textMessage, String address, String subject);
}
