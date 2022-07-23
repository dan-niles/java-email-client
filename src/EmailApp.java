import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.ArrayList;

public class EmailApp {
    private static String username;
    private static String password;

    private String clientListFilePath = "clientList.txt";
    private FileHandler clientListFile = new FileHandler(clientListFilePath);

    private ArrayList<Recipient> recipientList = new ArrayList<>();
    private ArrayList<IBdayGreetable> birthDayList = new ArrayList<>();

    public EmailApp(String username, String password) {
        EmailApp.username = username;
        EmailApp.password = password;
        initRecipients();
        System.out.println(birthDayList);
    }

    // Loads recipients from client list and populates recipientList
    private void initRecipients() {
        ArrayList<String> clientList = clientListFile.readLineByLine();
        for (String record : clientList) {
            Recipient recipientObj = stringToRecipient(record);
            recipientList.add(recipientObj);
            if (recipientObj instanceof IBdayGreetable)
                birthDayList.add((IBdayGreetable) recipientObj);
        }
    }

    // Create new recipient
    public void createRecipient(String record) {
        Recipient recipientObj = stringToRecipient(record);
        recipientList.add(recipientObj); // Add to recipientList
        if (recipientObj instanceof IBdayGreetable)
            birthDayList.add((IBdayGreetable) recipientObj);
        clientListFile.appendToFile(record); // Add to clientList file
    }

    // Parses given string and returns a recipient object
    public Recipient stringToRecipient(String record) throws IllegalArgumentException {
        String[] recordParts = record.split(":");
        String clientType = recordParts[0];
        if (recordParts.length < 2)
            throw new IllegalArgumentException("Error : Invalid recipient parameters.");
        String[] clientDetails = recordParts[1].split(",");

        String clientName, clientNickname, clientMail, clientBirthday, clientDesignation;
        Recipient recipientObj;

        clientName = clientDetails[0].trim();
        switch (clientType) {
            case "Personal" -> {
                clientNickname = clientDetails[1].trim();
                clientMail = clientDetails[2].trim();
                clientBirthday = clientDetails[3].trim();
                recipientObj = new PersonalRecipient(clientName, clientNickname, clientMail, clientBirthday);
            }
            case "Official" -> {
                clientMail = clientDetails[1].trim();
                clientDesignation = clientDetails[2].trim();
                recipientObj = new OfficialRecipient(clientName, clientMail, clientDesignation);
            }
            case "Office_friend" -> {
                clientMail = clientDetails[1].trim();
                clientDesignation = clientDetails[2].trim();
                clientBirthday = clientDetails[3].trim();
                recipientObj = new OfficialFriendRecipient(clientName, clientMail, clientDesignation, clientBirthday);
            }
            default -> throw new IllegalArgumentException("Error : Invalid recipient type. Please use \"Personal\", \"Official\" or \"Office_friend\".");
        }

        return recipientObj;
    }

    // Returns the number of recipient objects in the application
    public Integer getRecipientCount() {
        return Recipient.getCount();
    }

    // Sends email via Gmail SMTP server
    public void sendEmail(String email, String subject, String content) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
