import java.util.ArrayList;

public class EmailApp {
    String clientListFilePath = "clientList.txt";
    FileHandler clientListFile = new FileHandler(clientListFilePath);

    ArrayList<Recipient> recipientList = new ArrayList<>();
    ArrayList<IBdayGreetable> birthDayList = new ArrayList<>();

    public EmailApp() {
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
    public Recipient stringToRecipient(String record) throws IllegalArgumentException{
        String[] recordParts = record.split(":");
        String clientType = recordParts[0];
        if (recordParts.length < 2)
            throw new IllegalArgumentException("Error : Invalid recipient parameters.");
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
            default -> {
                throw new IllegalArgumentException("Error : Invalid recipient type. Please use \"Personal\", \"Official\" or \"Office_friend\".");
            }
        }

        return recipientObj;
    }

    // Returns the number of recipient objects in the application
    public Integer getRecipientCount() {
        return Recipient.getCount();
    }

}
