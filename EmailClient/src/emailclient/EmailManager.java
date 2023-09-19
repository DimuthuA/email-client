package emailclient;

import java.io.*;

class EmailManager {

    public static void saveEmail(Object SerializableMessage) {
        File file = new File("sentEmails.ser");
        boolean append = file.exists(); // if file exists then append, otherwise create new
        try (
                FileOutputStream fout = new FileOutputStream(file, append);
                AppendableObjectOutputStream oout = new AppendableObjectOutputStream(fout, append);
        ) {
            oout.writeObject(SerializableMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SerializableMessage loadEmail() {
        SerializableMessage m = null;
        try {
            FileInputStream fileIn = new FileInputStream("sentEmails.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            m = (SerializableMessage) in.readObject();

            in.close();
            fileIn.close();


            return m;

        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
            return null;
        }
    }

    public static void printEmailDetails(String givenDate) {
        ObjectInputStream in;
        try {
            in = new ObjectInputStream(new FileInputStream(
                    "sentEmails.ser"));
        } catch (FileNotFoundException i) {
            System.out.println("No saved emails");
            return;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = 0;
        while (true) {

            try {
                SerializableMessage email = (SerializableMessage) in.readObject();

                if (givenDate.equals(email.getSentDate())) {
                    System.out.println("Recipient: " + email.getRecipient() + ", Subject: " +
                            email.getSubject());
                    count++;
                }
            } catch (EOFException ef) {
                if (count == 0) System.out.println("No emails sent on the given date");
                break;
            } catch (IOException | ClassNotFoundException e) {
                break;
            }

        }
    }
}