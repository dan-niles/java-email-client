import java.io.Serializable;

public class Mail implements Serializable {
    private String toEmail; // Email address of recipient
    private String subject; // Subject of the email
    private String content; // Body of the email
    private String date; // Date the mail was sent

    public Mail(String to_email, String subject, String content) {
        this.toEmail = to_email;
        this.subject = subject;
        this.content = content;
    }

    // Getter and setter methods
    public String getToEmail() {
        return toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }
}
