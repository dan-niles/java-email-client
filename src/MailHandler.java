import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

public class MailHandler {
    private static final Boolean debugMode = true; // Set as false to send actual emails

    private static String userName;
    private static String userEmail;
    private static String userPassword;

    private static ArrayList<Mail> mailList = new ArrayList<>(); // Stores mail objects

    // Sends email via Gmail SMTP server
    public static void sendEmail(Mail mailObj) {
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
            message.setFrom(new InternetAddress(userEmail, userName));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailObj.getToEmail())
            );
            message.setSubject(mailObj.getSubject());
            message.setContent(mailObj.getContent(), "text/html");

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            mailObj.setDate(date);

            if (!debugMode)
                Transport.send(message);

            mailList.add(mailObj); // Add mail to mailList
            SerializationHandler.serializeObj(mailList, "mails/" + mailObj.getDate() + ".ser"); // Serialize mailList

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    // Getter and setter methods
    public static String getUserName() {
        return userName;
    }

    public static void setUsername(String userName) {
        MailHandler.userName = userName;
    }

    public static void setUserEmail(String userEmail) {
        MailHandler.userEmail = userEmail;
    }

    public static void setUserPassword(String userPassword) {
        MailHandler.userPassword = userPassword;
    }
}
