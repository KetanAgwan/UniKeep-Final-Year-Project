import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void main(String[] args) {
        // Sender's email address and password
        String from = "agwanketan7517@gmail.com"; // Replace with your email address
//        String password = "Ketan@7517"; // Replace with your email password
        String password = "bevn dybt ruyh zukg";

        // Recipient's email address
        String to = "ketanagwan7597@gmail.com"; // Replace with recipient's email address

        // SMTP server host and port
        String host = "smtp.gmail.com"; // Gmail SMTP server
        int port = 587; // TLS port for Gmail SMTP

        // Enable TLS for secure communication
        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a session with authentication
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);

            // Set the sender's email address
            message.setFrom(new InternetAddress(from));

            // Set the recipient's email address
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set the email subject
            message.setSubject("Test Email from Java");

            // Set the email content
            message.setText("This is a test email sent from Java using the JavaMail API.");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
