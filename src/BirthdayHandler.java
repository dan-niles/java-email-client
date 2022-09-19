import java.util.*;

public class BirthdayHandler {
    private static ArrayList<IBdayGreetable> birthDayList = new ArrayList<>(); // Stores recipients with birthdays

    // Send birthday greetings to recipients who have birthdays today
    public static void sendBirthdayGreetings() {
        // Create calendar to get dates
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        // Get current day and month
        int todayDate = cal.get(Calendar.DAY_OF_MONTH);
        int todayMonth = cal.get(Calendar.MONTH) + 1;

        ArrayList<Recipient> recList = getRecipientsByBirthday(todayDate, todayMonth, 0); // Retrieve recipients who have birthdays today

        if (!recList.isEmpty()) {
            for (Recipient recipientObj : recList) {
                IBdayGreetable recObj = (IBdayGreetable) recipientObj; // Cast Recipient type to IBdayGreetable

                // Check if birthday greeting has already been sent
                Mail result = MailHandler.getMailList().stream()
                        .filter(item -> item.getToEmail().equals(recipientObj.getEmail()) && item.getSubject().equals("Birthday Greeting"))
                        .findAny()
                        .orElse(null);

                if (result != null) { // If greeting is already sent, skip sending again
                    continue;
                }

                // Extract recipient email and custom message
                String email = recipientObj.getEmail();
                String subject = "Birthday Greeting";
                String message = recObj.getBdayMessage() + " Best Regards, " + MailHandler.getUserName();

                Mail mailObj = new Mail(email, subject, message);
                MailHandler.sendEmail(mailObj); // Send email to recipient
            }
        }
    }

    // Returns list of recipients with given birthday
    // Pass year as 0 to search only by date and month
    public static ArrayList<Recipient> getRecipientsByBirthday(int date, int month, int year) {
        ArrayList<Recipient> returnList = new ArrayList<>();

        for (IBdayGreetable recipientObj : birthDayList) {
            String[] bdayDetails = recipientObj.getBirthday().split("/");
            int recYear = Integer.parseInt(bdayDetails[0]);
            int recMonth = Integer.parseInt(bdayDetails[1]);
            int recDate = Integer.parseInt(bdayDetails[2]);

            // If year is not required for search
            if (year == 0)
                recYear = 0;

            if (recDate == date && recMonth == month && recYear == year)
                returnList.add((Recipient) recipientObj);

        }

        return returnList;
    }

    // Appends given recipient to birthday list
    public static void appendToBirthdayList(IBdayGreetable recipientObj) {
        birthDayList.add(recipientObj);
    }
}
