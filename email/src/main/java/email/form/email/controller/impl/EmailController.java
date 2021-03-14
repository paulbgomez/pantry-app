package email.form.email.controller.impl;

import email.form.email.controller.interfaces.IEmailController;
import email.form.email.model.Email;
import email.form.email.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController implements IEmailController {

    @Autowired
    IEmailService emailService;

    @PostMapping("/email/send")
    @ResponseStatus(HttpStatus.OK)
    public boolean sendEmail(@RequestBody Email email) {
        return emailService.sendEmail(email);
    }
}
