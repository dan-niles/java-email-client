abstract public class Recipient {
    private String name;
    private String email;
    private static Integer count = 0; // Keeps count of recipient objects in the application

    public Recipient (String name, String email) {
        this.name = name;
        this.email = email;
        count++;
    }

    // Getter method for name
    public String getName() {
        return name;
    }

    // Getter method for email
    public String getEmail() {
        return email;
    }

    // Getter method for count
    public static Integer getCount() {
        return count;
    }
    
}
