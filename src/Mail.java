public class Mail implements java.io.Serializable {
    private String to_email; // Email address of recipient
    private String subject; // Subject of the email
    private String content; // Body of the email
    private String timestamp; // Time the mail was sent

    public Mail(String to_email, String subject, String content) {
        this.to_email = to_email;
        this.subject = subject;
        this.content = content;
    }

    public String getToEmail() {
        return to_email;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
