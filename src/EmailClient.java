// Index No: 200421U
// (Remove the  public access modifier from classes when you submit your code)

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailClient {

    static {
        String userPassword = null;
        try {
            userPassword = new String(Files.readAllBytes(Paths.get("Keys.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Provide credentials here...
        MailHandler.setUsername("Dan Niles");
        MailHandler.setUserEmail("mailtocftg@gmail.com");
        MailHandler.setUserPassword(userPassword);

        RecipientHandler.initRecipientList();  // Initialize recipients
        BirthdayHandler.sendBirthdayGreetings(); // Send birthday greetings
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Enter option type:\n"
                        + "0 - Exit\n"
                        + "1 - Adding a new recipient\n"
                        + "2 - Sending an email\n"
                        + "3 - Printing out all the recipients who have birthdays\n"
                        + "4 - Printing out details of all the emails sent\n"
                        + "5 - Printing out the number of recipient objects in the application"
        );

        while (true) {
            System.out.println("Enter option (0-5): ");
            int option = scanner.nextInt();

            Scanner s = new Scanner(System.in);
            switch (option) {
                case 0:
                    // Exit application
                    return;
                case 1:
                    // Adding a new recipient
                    // Input format -
                    //     Personal: <name>,<nickname>,<email>,<birthday>
                    //     Official: <name>,<email>,<designation>
                    //     Office_friend: <name>,<email>,<designation>,<birthday>
                    System.out.println("Input new recipient: ");
                    String recipientDetails = s.nextLine();

                    // Catch exceptions for invalid input
                    try {
                        RecipientHandler.createRecipient(recipientDetails);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error : Please enter the correct set of parameters");
                    }

                    break;
                case 2:
                    // Sending an email
                    // input format - <email>,<subject>,<content>
                    System.out.println("Input mail parameters (<email>,<subject>,<content>): ");
                    String mailString = s.nextLine();
                    String[] mailDetails = mailString.split(","); // Split input string into email, subject, content

                    if (mailDetails.length < 3) { // Check if correct no. parameters are passed
                        System.out.println("Error: Invalid input. Make sure to enter all three parameters (Email, Subject, Content).");
                        continue;
                    }

                    // Create new mail object and pass it to mail handler
                    Mail mailObj = new Mail(mailDetails[0], mailDetails[1], mailDetails[2]);
                    MailHandler.sendEmail(mailObj);

                    break;
                case 3:
                    // Printing out all the recipients who have birthdays
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    System.out.println("Input date (YYYY/MM/DD): ");
                    String str = s.nextLine();
                    String[] bdayDetails = str.split("/");
                    String year = bdayDetails[0];
                    String month = bdayDetails[1];
                    String date = bdayDetails[2];

                    // Fetch recipients with given birthday
                    ArrayList<Recipient> bdayList = BirthdayHandler.getRecipientsByBirthday(date, month, year);

                    // Check if returned list is not empty
                    if (bdayList.size() != 0) {
                        // Print names of fetched recipients
                        for (Recipient recObj : bdayList) {
                            System.out.println(recObj.getName());
                        }
                    } else {
                        System.out.println("No recipients found with given birthday.");
                    }

                    break;
                case 4:
                    // Printing out details of all the emails sent
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    System.out.println("Input date (YYYY/MM/DD): ");
                    String dateStr = s.nextLine();
                    String[] dateArr = dateStr.split("/");
                    dateStr = String.join("-", dateArr);

                    try {
                        ArrayList<Mail> mailList = (ArrayList<Mail>) SerializationHandler.deserializeObj("mails/" + dateStr + ".ser");
                        for(Mail mail : mailList)
                        {
                            System.out.println("Sent to: " + mail.getToEmail() + " | Subject: " + mail.getSubject() + " | Content: " + mail.getContent());
                        }
                    } catch (IOException e) {
                        System.out.println("No mails found for given date.");
                        continue;
                    }

                    break;
                case 5:
                    // Printing out the number of recipient objects in the application
                    System.out.println("No. of recipients : " + Recipient.getCount());

                    break;

            }
        }

    }
}
