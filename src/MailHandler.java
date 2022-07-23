import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MailHandler {
    private static final String userName = "Dan Niles";
    private static final String userEmail = "mailtocftg@gmail.com";
    private static final String userPassword;

    static {
        try {
            userPassword = new String(Files.readAllBytes(Paths.get("Keys.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Boolean debugMode = false; // Set to true to send actual emails

    // Sends email via Gmail SMTP server
    public static void sendEmail(String email, String subject, String content) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userEmail, userPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(content);

            if (!debugMode)
                Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    // Returns user name
    public static String getUserName() {
        return userName;
    }
}
