package emailclient;

class Recipient {
    protected String recipientType;
    protected String name;
    protected String emailAddress;
    private static int recipientCount = 0;

    public Recipient(String name, String emailAddress) {
        recipientCount++;

        this.name = name;
        this.emailAddress = emailAddress;
    }

    public static int getRecipientCount() {
        return recipientCount;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
