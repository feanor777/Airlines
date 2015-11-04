package ua.nure.sharov.Airlines.web.email;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ua.nure.sharov.Airlines.exception.ApplicationException;
import ua.nure.sharov.Airlines.exception.Messages;
 /**
  * Class to send email
  * @author Max
  *
  */
public class Sender {
 
    private String username;
    private String password;
    private Properties props;
 
    public Sender(String username, String password) {
        this.username = username;
        this.password = password;
 
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }
 
    /**
     * Send email
     * @param subject - email subject
     * @param text - email text
     * @param toEmail - email destination
     * @throws ApplicationException 
     */
    public void send(String subject, String text, String toEmail) throws ApplicationException{
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
 
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new ApplicationException(Messages.ERR_CANT_SEND_MESSAGE, e);
        }
    }
}