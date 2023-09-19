package emailclient;

class RecipientPersonal extends Recipient implements CloseRecipient {
    private final String nickname;
    private final String birthday;

    public RecipientPersonal(String name, String nickname, String emailAddress, String birthday) {
        super(name, emailAddress);
        this.recipientType = "Personal";
        this.nickname = nickname;
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public String getBirthday() {
        return birthday;
    }

    @Override
    public String getGreetingSubject() {
        return "Happy Birthday!";
    }

    @Override
    public String getBirthdayGreeting() {
        return "Hugs and love on your birthday!\n- Dimuthu";
    }
}
