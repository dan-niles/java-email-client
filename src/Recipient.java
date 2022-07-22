abstract public class Recipient {
    private String name;
    private String email;
    private static Integer count = 0;

    public Recipient (String name, String email) {
        this.name = name;
        this.email = email;
        count++;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
