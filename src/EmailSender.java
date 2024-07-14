import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    EmailSender(String recipient,String password){
        String from = "UniKeepManagementSystem@gmail.com";
        String passkey = "yxls ozpa wfoj inrz";

        String to = recipient;


        String host = "smtp.gmail.com";
        int port = 587;

        Properties props = new Properties();
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, passkey);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Login Key to UniKeep");
            message.setContent("<html><b>" + password + "</b></html> : This is the Password for your UniKeep Teacher Login.Please Store it confidentially","text/html");

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new EmailSender("ketanagwan7597@gmail.com","thisispassword");
    }
}
