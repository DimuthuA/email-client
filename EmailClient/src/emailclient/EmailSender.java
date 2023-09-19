package emailclient;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;


class EmailSender {
    private static Session session;
    private static final String myUsername = "dimuthu.200035N@gmail.com";
    private static final String myPassword = "tmmjoqlcibmuuzgh";


    // this method sets the properties, starts new session & authenticates the password
    public static void setEmailService() {

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(myUsername, myPassword);
                    }
                });

    }

    // this method takes recipients email, subject and the content and sends an email
    public static void sendEmail(String toEmail, String subject, String content, String consoleMessage) {
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dimuthu.200035N@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject(subject);
            message.setText(content);

            //message.setSentDate(new SimpleDateFormat("yyyy/MM/dd").parse(sentDate));

            Transport.send(message);
            System.out.println(consoleMessage + toEmail);

            String sentDate = DateToday.dateTodayYMD();
            var serializableMessage = new SerializableMessage(toEmail, subject, content, sentDate);

            EmailManager.saveEmail(serializableMessage);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
