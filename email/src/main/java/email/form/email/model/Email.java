package email.form.email.model;

public class Email {

    private String textMessage;
    private String address;
    private String subject;

    public Email() {
    }
    public Email(String textMessage, String address, String subject) {
        setTextMessage(textMessage);
        setAddress(address);
        setSubject(subject);
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
