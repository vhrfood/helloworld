import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSendingUtility {

	public  static boolean  sendMail(final String from, final String to, final String subject) {
		try {
			String mailBody = "hello user";
			Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "nsmail.mmt.mmt");
		props.put("mail.smtp.port", 25);
		props.put("mail.smtp.auth", "true");
		Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        };
	Session	session=Session.getDefaultInstance(props, auth);		
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(mailBody, "utf-8", "html");
			Transport.send(msg);
			System.out.println("mail sent successfully");
			return true;
		} catch (Exception e) {
			System.out.println("Exception while sending mail to user:{}, exception " + e);
			return false;
		}
	}
	
	public static void main(String args[]){
		String to=args[0];
		sendMail("EN@hoteltravel.com", to, "Test mail");
	}

}