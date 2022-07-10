import java.util.Scanner;
import java.util.ArrayList;

public class EmailClient {
    FileHandler clientListFile;
    ArrayList<Recipient> RecipientList;

    public EmailClient() {
        clientListFile = new FileHandler("clientList.txt");
        initRecipients();
    }

    private void initRecipients() {
        ArrayList<String> clientList = clientListFile.readLineByLine();
        System.out.println(clientList);
    }

    public void addRecipient(String record) {
        clientListFile.appendToFile(record);
    }
}
