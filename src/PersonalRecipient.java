public class PersonalRecipient extends Recipient implements IBdayGreetable {
    private String nickName;
    private String birthday;

    public PersonalRecipient(String name, String nickName, String email, String birthday) {
        super(name, email);
        this.nickName = nickName;
        this.birthday = birthday;
    }

    public String getNickName() {
        return nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBdayMessage() {
        return "Hugs and love on your birthday.";
    }

    public Recipient createRecipient() {
        return null;
    }
}
