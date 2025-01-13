package model;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Class to handle sending confirmation emails for orders and password recovery.
 */
public class SendEmail {

    private final int LIMIT_MINUS = 10;
    private final String from = "playunknowquangoc@gmail.com"; // Địa chỉ email gửi
    private final String password = "tven yxhi haaw wrnk"; // Mật khẩu email

    /**
     * Tạo mã xác nhận ngẫu nhiên.
     */
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public LocalDateTime expireDateTime() {
        return LocalDateTime.now().plusMinutes(LIMIT_MINUS);
    }

    public boolean isExpired(LocalDateTime time) {
        return LocalDateTime.now().isAfter(time);
    }

    /**
     * Gửi email chứa mật khẩu cũ hoặc thông tin xác nhận đơn hàng.
     */
    public boolean sendEmail(String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Sử dụng 587 cho TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Kích hoạt TLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject(subject, "UTF-8"); // Thiết lập chủ đề email

            // Tạo nội dung email
            msg.setContent(content, "text/html; charset=UTF-8");
            Transport.send(msg);
            System.out.println("Email đã được gửi thành công.");
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi khi gửi email:");
            e.printStackTrace(); // Hiển thị lỗi chi tiết
        }
        return false;
    }
}
