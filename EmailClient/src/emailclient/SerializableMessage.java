package emailclient;

class SerializableMessage implements java.io.Serializable {
    private final String recipient;
    private final String subject;
    private final String text;
    private final String sentDate;

    public SerializableMessage(String recipients, String subject, String text, String sentDate) {
        this.recipient = recipients;
        this.subject = subject;
        this.text = text;
        this.sentDate = sentDate;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getSentDate() {
        return sentDate;
    }
}
