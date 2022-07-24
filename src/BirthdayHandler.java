import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class BirthdayHandler {
    private static ArrayList<IBdayGreetable> birthDayList = new ArrayList<>(); // Stores recipients with birthdays

    // Initialize birthday list
    public static void appendToBirthdayList(IBdayGreetable recipientObj) {
        birthDayList.add(recipientObj);
    }

    // Send birthday greetings to recipients who have birthdays today
    public static void sendBirthdayGreetings() {
        // Create calendar to get dates
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        // Get current day and month
        String todayDate = new DecimalFormat("00").format(cal.get(Calendar.DAY_OF_MONTH));
        String todayMonth = new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

        ArrayList<Recipient> recList = getRecipientsByBirthday(todayDate, todayMonth, null); // Retrieve recipients who have birthdays today

        if (!recList.isEmpty()) {
            for (Recipient recipientObj : recList) {
                IBdayGreetable recObj = (IBdayGreetable) recipientObj; // Cast Recipient type to IBdayGreetable

                // Extract recipient email and custom message
                String email = recipientObj.getEmail();
                String subject = "Birthday Greeting";
                String message = recObj.getBdayMessage() + "<br/><br/>" + MailHandler.getUserName();

                Mail mailObj = new Mail(email, subject, message);
                MailHandler.sendEmail(mailObj); // Send email to recipient
            }
        }
    }

    // Returns list of recipients with given birthday
    // Pass year as null to search only by date and month
    public static ArrayList<Recipient> getRecipientsByBirthday(String date, String month, String year) {
        ArrayList<Recipient> returnList = new ArrayList<>();

        for (IBdayGreetable recipientObj : birthDayList) {
            String[] bdayDetails = recipientObj.getBirthday().split("/");
            String recYear = bdayDetails[0];
            String recMonth = bdayDetails[1];
            String recDate = bdayDetails[2];

            // If year is not required for search
            if (year == null)
                recYear = null;

            if (recDate.equals(date) && recMonth.equals(month) && Objects.equals(recYear, year))
                returnList.add((Recipient) recipientObj);

        }

        return returnList;
    }
}
