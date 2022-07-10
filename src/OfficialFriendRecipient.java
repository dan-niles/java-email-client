public class OfficialFriendRecipient extends OfficialRecipient{
    private String birthday;

    public OfficialFriendRecipient(String name, String email, String designation, String birthday) {
        super(name, email, designation);
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }
}
