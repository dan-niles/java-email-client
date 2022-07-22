public class OfficialFriendRecipient extends OfficialRecipient implements IBdayGreetable {
    private String birthday;

    public OfficialFriendRecipient(String name, String email, String designation, String birthday) {
        super(name, email, designation);
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBdayMessage() {
        return "Wish you a Happy Birthday. <your name>";
    }
}
