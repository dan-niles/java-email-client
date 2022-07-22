import java.util.ArrayList;

public class EmailApp {
    String clientListFilePath = "clientList.txt";
    FileHandler clientListFile;
    ArrayList<Recipient> recipientList = new ArrayList<Recipient>();;

    public EmailApp() {
        clientListFile = new FileHandler(clientListFilePath);
        initRecipients();
        System.out.println(recipientList);
    }

    private void initRecipients() {
        ArrayList<String> clientList = clientListFile.readLineByLine();
        for (String record : clientList) {
            Recipient recipientObj = stringToRecipient(record);
            if (recipientObj != null)
                recipientList.add(recipientObj);
        }
    }

    public void addRecipient(String record) {
        clientListFile.appendToFile(record);
    }

    public Recipient stringToRecipient(String record) {
        String[] recordParts = record.split(":");
        String clientType = recordParts[0];
        String[] clientDetails = recordParts[1].split(",");

        String clientName, clientNickname, clientMail, clientBirthday, clientDesignation;

        Recipient recipientObj = null;

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
        }

        return recipientObj;
    }
}
