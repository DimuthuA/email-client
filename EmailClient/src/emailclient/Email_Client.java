package emailclient;


import javax.mail.MessagingException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Email_Client {

    public static void main(String[] args) throws IOException, MessagingException {

        // sets the properties, starts new session & authenticates the password
        EmailSender.setEmailService();

        // loads the recipient details from the text file,
        // creates objects for all recipients,
        // stores all_recipients in one arraylist and close_recipients in another arraylist
        // close_recipients = recipients who receive birthday greetings (Personal + Office_friend)
        RecObjectManager.loadDetails();


        System.out.println("\nChecking for recipients having their birthday today...");

        // sends birthday greetings to recipients who have their birthdays today
        RecObjectManager.sendBirthdayGreetings();

        // takes user input to determine preferred action
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");

        int option = 0;
        try {
            option = scanner.nextInt();
        } catch (InputMismatchException mismatch) {
            System.out.println("\nInvalid input. Please enter an integer");
        }

        scanner.nextLine();

        switch(option){
            case 1:
                /* takes input to add a new recipient
                 * input format - Official: nimal,nimal@gmail.com,ceo
                 *                Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12
                 *                Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10
                 */
                System.out.println("Enter recipient details: ");
                String recipientDetails = scanner.nextLine().trim();

                FileWriter fileWriter;
                try {
                    fileWriter = new FileWriter("clientList.txt", true);
                } catch (IOException e) {
                    System.out.println("\nERROR! : File cannot be opened or created\n");
                    throw new RuntimeException(e);
                }

                BufferedWriter writer = new BufferedWriter(fileWriter);
                try {
                    writer.write(recipientDetails + "\n");
                } catch (IOException e) {
                    System.out.println("\nERROR! : Failed to write to the file\n");
                    throw new RuntimeException(e);
                }

                writer.close();
                System.out.println("\nSuccessfully added the new recipient");
                break;

            case 2:
                // input format - email, subject, content
                System.out.println("Enter email address, subject, content: ");
                String[] emailToSend = scanner.nextLine().split(",", 3);

                if (emailToSend.length == 3) {
                    String email = emailToSend[0].trim();
                    String subject = emailToSend[1].trim();
                    String content = emailToSend[2].trim();

                    // sends the email
                    EmailSender.sendEmail(email, subject, content, "Email sent to : ");
                }
                else {
                    System.out.println("\nInvalid input. Please try again\n");
                }

                break;
            case 3:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                System.out.println("Enter date: ");
                String inputDate = scanner.nextLine().trim();

                // make it MM/dd
                String[] splitDate = inputDate.split("/", 2);

                // print the names of recipients who have birthdays on the given date
                RecObjectManager.printHaveBirthdays(splitDate[1].trim());
                break;

            case 4:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                System.out.println("Enter date: ");
                String givenDate = scanner.nextLine().trim();

                // prints the recipient email & subject of all emails sent on the given date
                EmailManager.printEmailDetails(givenDate);
                break;

            case 5:
                // prints the number of recipient objects in the application
                System.out.println("Number of recipient objects in the application: "+
                Recipient.getRecipientCount());
                break;

        }
    }
}