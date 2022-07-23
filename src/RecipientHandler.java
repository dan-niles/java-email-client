import java.util.ArrayList;

public class RecipientHandler {
    private static final FileHandler clientListFile = new FileHandler("clientList.txt");
    private static ArrayList<Recipient> recipientList = new ArrayList<>(); // Stores recipients

    // Loads recipients from client list and populates recipientList and birthDayList
    public static void initRecipientList() {
        // Read clientList.txt and retrieve recipient records
        ArrayList<String> clientList = clientListFile.readLineByLine();

        // Convert each string record into a recipient object
        for (String record : clientList) {
            Recipient recipientObj = stringToRecipient(record);
            recipientList.add(recipientObj); // Append to recipient list
            if (recipientObj instanceof IBdayGreetable)
                BirthdayHandler.appendToBirthdayList((IBdayGreetable) recipientObj);
        }
    }

    // Create new recipient
    public static void createRecipient(String record) {
        Recipient recipientObj = stringToRecipient(record);
        recipientList.add(recipientObj); // Add to recipientList
        if (recipientObj instanceof IBdayGreetable)
            BirthdayHandler.appendToBirthdayList((IBdayGreetable) recipientObj);  // Add to birthdayList

        // Add to clientList file
        clientListFile.appendToFile(record);
    }

    // Parses given string and returns a recipient object
    public static Recipient stringToRecipient(String record) throws IllegalArgumentException {
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
            default ->
                    throw new IllegalArgumentException("Error : Invalid recipient type. Please use \"Personal\", \"Official\" or \"Office_friend\".");
        }

        return recipientObj;
    }

    // Returns the number of recipient objects in the application
    public static Integer getRecipientCount() {
        return Recipient.getCount();
    }
}
