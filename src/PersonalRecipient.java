public class PersonalRecipient extends Recipient{
    private String birthday;
    private String nickName;

    public PersonalRecipient(String name, String nickName, String email, String birthday) {
        super(name, email);
        this.birthday = birthday;
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getNickName() {
        return nickName;
    }
}
