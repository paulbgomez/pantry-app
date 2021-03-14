package email.form.email.controller.interfaces;

import email.form.email.model.Email;

public interface IEmailController {
    boolean sendEmail(Email email);
}
