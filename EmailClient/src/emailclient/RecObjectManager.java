package emailclient;

import java.io.*;

class RecObjectManager {

    private static final RecObjectCreator objectCreator = new RecObjectCreator();
    private static final RecObjectDatabase database = new RecObjectDatabase();

    public static void loadDetails() {
        // this method loads all recipients' details from the text file, then creates objects using
        // RecObjectCreator, finally puts them into two arrays (allRecipients & allCloseRecipients) using
        // RecObjectDatabase.
        {
            try {
                File file = new File("clientList.txt");

                if (!file.exists()) {
                    // if the file doesn't exist yet
                    return;
                }

                FileReader fileReader = new FileReader(file);
                BufferedReader reader = new BufferedReader(fileReader);

                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    // split at ':' to separate recipient type & details
                    String[] entry = line.trim().split(":");

                    if (entry.length == 2) {
                        String recipientType = entry[0].trim();
                        String recipientDetails = entry[1].trim();

                        // create a recipient object with given details
                        var object = objectCreator.createObject(recipientType, recipientDetails,
                                                                        lineNumber);

                        // object could be null if the recipient type or details were invalid
                        if (object != null) {
                            database.addRecipient(object); // add object to allRecipients array

                            if (object.getRecipientType().equals("Personal") ||
                                    object.getRecipientType().equals("Office_friend")) {
                                database.addCloseRecipient((CloseRecipient) object); // add to close recipient array
                            }
                        }
                    }

                }
            } catch (IOException e) {
                System.out.println("\nERROR! : Failed to read file");
                throw new RuntimeException(e);
            }
        }
    }

    public static void printHaveBirthdays(String givenDate) {
        // this method prints the names of recipients who have birthdays on the given date, by checking each
        // recipient in allCloseRecipients array

        int count = 0;

        for (var obj : database.getAllCloseRecipients()) {
            String[] splitBirthday = obj.getBirthday().split("/", 2);

            if (splitBirthday[1].trim().equals(givenDate)) {
                System.out.println(obj.getName());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("\nNo recipients have birthdays on the given date");
        }
    }

    public static void sendBirthdayGreetings() {
        // this method sends an email to every recipient having their birthday today, using
        // allCloseRecipients array

        String consoleMessage = "Birthday greeting sent to : ";

        String today = DateToday.dateTodayMD(); // date today in MM/dd format

        for (var obj : database.getAllCloseRecipients()) {
            String[] splitBirthday = obj.getBirthday().split("/", 2);

            if (splitBirthday[1].trim().equals(today)) {
                EmailSender.sendEmail(obj.getEmailAddress(),
                                        obj.getGreetingSubject(),
                                        obj.getBirthdayGreeting(), consoleMessage);


            }
        }

    }
}