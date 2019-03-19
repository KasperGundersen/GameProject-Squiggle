package Components;

import Scenes.LogIn;
import Scenes.SignUp;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Email {
    private static final String username = "calfskingames@gmail.com";
    private static final String password = "admin123admin";

    public static void sendEmail(String to) {
        String host = "smtp.gmail.com";

        //Connecting to the email server
        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.trust", host);
        properties.put("mail.smtp.user", username);
        properties.put("mail.smtp.password", password);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");

        //Creating a session in order to be able to send email
        Session session = Session.getDefaultInstance(properties,null);

        try {
            MimeMessage message = new MimeMessage(session);

            //Defining which email we're sending from
            message.setFrom(new InternetAddress(username));

            //Defining which email we're sending to
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            //Setting the subject of the email
            message.setSubject("Welcome to Calfskin Games");

            //Setting the message of the email
            message.setText("Hello. You are now registered.\n" +
                            "username: " +
                            SignUp.getName() + "\n" +
                            ".Best regards" + "\n" +
                            "Calfskin Games"
                    );

            //Sending the messaage
            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        }catch(MessagingException me) {
            me.printStackTrace();
        }
    }
}
