package emailclient;

class RecipientOfficeFriend extends Recipient implements CloseRecipient {

    private final String designation;
    private final String birthday;

    public RecipientOfficeFriend(String name, String emailAddress, String designation, String birthday) {
        super(name, emailAddress);
        this.recipientType = "Office_friend";
        this.designation = designation;
        this.birthday = birthday;
    }


    public String getDesignation() {

        return designation;
    }

    public String getBirthday() {

        return birthday;
    }

    @Override
    public String getGreetingSubject() {
        return "Birthday Greetings!";
    }

    @Override
    public String getBirthdayGreeting() {
        return "Wish you a Happy Birthday!\n- Dimuthu";
    }
}