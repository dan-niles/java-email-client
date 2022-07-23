// Index No: 200421U
// (remove the  public access modifier from classes when you submit your code)

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailClient {

    public static void main(String[] args) throws IOException {

        String pw = new String(Files.readAllBytes(Paths.get("Keys.txt")));
        EmailApp mailApp = new EmailApp("mailtocftg@gmail.com", pw);

        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Enter option type: \n"
                        + "0 - Exit \n"
                        + "1 - Adding a new recipient\n"
                        + "2 - Sending an email\n"
                        + "3 - Printing out all the recipients who have birthdays\n"
                        + "4 - Printing out details of all the emails sent\n"
                        + "5 - Printing out the number of recipient objects in the application"
        );

        while (true) {
            System.out.println("Enter option: ");
            int option = scanner.nextInt();

            Scanner s = new Scanner(System.in);
            switch (option) {
                case 0:
                    // Exit application
                    return;
                case 1:
                    // Adding a new recipient
                    // input format -
                    //     Personal: daniel,dan,daniel@gmail.com,2000/10/10
                    //     Official: nimal,nimal@gmail.com,ceo
                    //     Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12
                    System.out.println("Input new recipient: ");
                    String recipientDetails = s.nextLine();

                    // Catch exceptions for invalid input
                    try {
                        mailApp.createRecipient(recipientDetails);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Error : Please enter the correct set of parameters");
                    }

                case 2:
                    // Sending an email
                    // input format - email, subject, content
                    System.out.println("Input mail parameters (<email>,<subject>,<content>): ");
                    String mailString = s.nextLine();
                    String[] mailDetails = mailString.split(","); // Split input string into email, subject, content

                    if (mailDetails.length < 3) { // Check if correct no. parameters are passed
                        System.out.println("Error: Invalid input. Make sure to enter all three parameters (Email, Subject, Content).");
                        continue;
                    }

                    // Pass parameters to send email
                    mailApp.sendEmail(mailDetails[0], mailDetails[1], mailDetails[2]);

                case 3:
                    // Printing out all the recipients who have birthdays
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    System.out.println("Input date (YYYY/MM/DD): ");
                    String str = s.nextLine();

                    // Fetch recipients with given birthday
                    ArrayList<Recipient> bdayList = mailApp.getRecipientsByBirthday(str);

                    // Check if returned list is not empty
                    if (bdayList.size() != 0) {
                        // Print names of fetched recipients
                        for (Recipient recObj : bdayList) {
                            System.out.println(recObj.getName());
                        }
                    } else {
                        System.out.println("No recipients found with given birthday.");
                    }

                case 4:
                    // Printing out details of all the emails sent
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    break;
                case 5:
                    // Printing out the number of recipient objects in the application
                    System.out.println("No. of recipients : " + mailApp.getRecipientCount());
                    break;

            }
        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes

    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)
