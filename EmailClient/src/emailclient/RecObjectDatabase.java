package emailclient;

import java.util.ArrayList;
import java.util.List;

class RecObjectDatabase {
    private final List<Recipient> allRecipients; // All three types of recipients
    private final List<CloseRecipient> allCloseRecipients; // Personal + Office_friend

    public RecObjectDatabase() {
        this.allRecipients = new ArrayList<>();
        this.allCloseRecipients = new ArrayList<>();
    }

    public void addRecipient (Recipient recipient) {
        this.getAllRecipients().add(recipient);
    }

    public void addCloseRecipient (CloseRecipient closeRecipient) {
        this.getAllCloseRecipients().add(closeRecipient);
    }


    public List<Recipient> getAllRecipients() {
        return allRecipients;
    }

    public List<CloseRecipient> getAllCloseRecipients() {
        return allCloseRecipients;
    }




}
