// Index No: 200421U
// (remove the  public access modifier from classes when you submit your code)

import java.util.Scanner;

public class EmailClient {

    public static void main(String[] args) {

        EmailApp mailApp = new EmailApp();

        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Enter option type: \n"
                        + "1 - Adding a new recipient\n"
                        + "2 - Sending an email\n"
                        + "3 - Printing out all the recipients who have birthdays\n"
                        + "4 - Printing out details of all the emails sent\n"
                        + "5 - Printing out the number of recipient objects in the application"
        );

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                // Adding a new recipient
                // input format - Official: nimal,nimal@gmail.com,ceo
                Scanner s = new Scanner(System.in);
                System.out.println("Input new recipient:");
                String recipientDetails = s.nextLine();
                mailApp.addRecipient(recipientDetails);

                // Use a single input to get all the details of a recipient
                // code to add a new recipient
                // store details in clientList.txt file
                // Hint: use methods for reading and writing files
                break;
            case 2:
                // Sending an email
                // input format - email, subject, content
                // code to send an email
                break;
            case 3:
                // Printing out all the recipients who have birthdays
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print recipients who have birthdays on the given date
                break;
            case 4:
                // Printing out details of all the emails sent
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print the details of all the emails sent on the input date
                break;
            case 5:
                // Printing out the number of recipient objects in the application
                // code to print the number of recipient objects in the application
                break;

        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes

    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)
