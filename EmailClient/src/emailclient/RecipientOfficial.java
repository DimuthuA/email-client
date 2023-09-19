package emailclient;

class RecipientOfficial extends Recipient {
    private final String designation;

    public RecipientOfficial(String name, String emailAddress, String designation) {
        super(name, emailAddress);
        this.recipientType = "Official";
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

}