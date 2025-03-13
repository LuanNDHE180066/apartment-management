package model;

import dto.response.EmailInvoice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
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
    private final String from = "baviapartment88@gmail.com";
    private final String password = "nong aqji krlu xvue";

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

    public void sendEmailInvoiceToAll(List<EmailInvoice> list) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (EmailInvoice ei : list) {
            executor.execute(() -> sendEmailInvoiceToOne(ei));
        }
        executor.shutdown(); // Đóng ExecutorService sau khi gửi xong
    }

    public void sendEmailInvoiceToOne(EmailInvoice emailInvoice) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailInvoice.getEmail()));
            message.setSubject("Hóa đơn dịch vụ chung cư", "UTF-8");
            String dataText = "Bạn có hóa đơn phòng " + emailInvoice.getAid() + " cần thanh toán, xem chi tiết tại ứng dụng";
            message.setText(dataText, "UTF-8");

            Transport.send(message);
            System.out.println("Đã gửi email đến: " + emailInvoice.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public void sendEmailToWorkingStaff(List<Staff> list,String detail,String aid){
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (Staff ei : list) {
            executor.execute(() -> sendEmailStaffToOne(ei,detail,aid));
        }
        executor.shutdown();
    }
    
    public void sendEmailStaffToOne(Staff estaff,String detail,String aid){
         try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(estaff.getEmail()));
            message.setSubject("Công việc yêu cầu từ dân cư","UTF-8");
            String dataText = "Bạn có yêu cầu từ "+estaff.getName()+"tại phòng "+aid +" cần giải quyết: "+detail+", xem chi tiết tại ứng dụng";
            message.setText(dataText,"UTF-8");
            Transport.send(message);
            System.out.println("Đã gửi email đến: " + estaff.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailAdminToOne(Staff estaff) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(estaff.getEmail()));
            message.setSubject("Công việc yêu cầu từ dân cư", "UTF-8");
            String dataText = "Bạn có yêu cầu từ " + estaff.getName() + " cần giải quyết, xem chi tiết tại ứng dụng";
            message.setText(dataText, "UTF-8");
            Transport.send(message);
            System.out.println("Đã gửi email đến: " + estaff.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

//    public void sendEmailStaffToOne(Staff estaff) {
//        try {
//            Properties props = new Properties();
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.port", "587");
//            Session session = Session.getInstance(props, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(from, password);
//                }
//            });
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(estaff.getEmail()));
//            message.setSubject("Công việc yêu cầu từ dân cư", "UTF-8");
//            String dataText = "Bạn có yêu cầu từ " + estaff.getName() + " cần giải quyết, xem chi tiết tại ứng dụng";
//            message.setText(dataText, "UTF-8");
//            Transport.send(message);
//            System.out.println("Đã gửi email đến: " + estaff.getEmail());
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }

    public void sendEmailInvoiceDebtToAll(List<EmailInvoice> list) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (EmailInvoice ei : list) {
            executor.execute(() -> sendEmailInvoiceDebtToOne(ei));
        }
        executor.shutdown(); // Đóng ExecutorService sau khi gửi xong
    }

    public void sendEmailInvoiceDebtToOne(EmailInvoice emailInvoice) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailInvoice.getEmail()));
            message.setSubject("Hóa đơn dịch vụ chung cư", "UTF-8");
            String dataText = "Bạn có hóa đơn phòng " + emailInvoice.getAid() + " chưa thanh toán, xem chi tiết tại ứng dụng";
            message.setText(dataText, "UTF-8");

            Transport.send(message);
            System.out.println("Đã gửi email đến: " + emailInvoice.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendFeedbackMail(List<String> emailList, String type, int rate, String content) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            for (String email : emailList) {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("Feedback Notification", "UTF-8");

                // Creating email content  
                String emailContent = "<html><body>"
                        + "<h3>Feedback Notification</h3>"
                        + "<p><strong>Type of Request:</strong> " + type + "</p>"
                        + "<p><strong>Rating:</strong> " + rate + " / 5</p>"
                        + "<p><strong>Feedback Details:</strong></p>"
                        + "<p>" + content + "</p>"
                        + "<p>Please check your account for more details.</p>"
                        + "</body></html>";

                // Set the email content as HTML  
                message.setContent(emailContent, "text/html; charset=UTF-8");

                Transport.send(message);
                System.out.println("Email sent to: " + email);
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean sendEmail(String to, String subject, String content) {
        // Kiểm tra xem địa chỉ email có tồn tại không trước khi gửi\

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

    public void sendEmail(String to, String residentName, String username, String residentPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Use 587 for TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject("Your New Resident Account Details", "UTF-8");

            String emailContent = "<p>Dear " + residentName + ",</p>"
                    + "<p>Your account has been created successfully.</p>"
                    + "<p>Username: " + username + "<br></p>"
                    + "<p>Password: " + residentPassword + "</p>"
                    + "<p>Please keep this information safe.</p>"
                    + "<p>Best Regards,<br>Your Community</p>";

            message.setContent(emailContent, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            System.out.println("Failed to send email to: " + to);
            e.printStackTrace();
        }
    }

    public void sendEmailStaff(String to, String residentName, String username, String residentPassword) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Use 587 for TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject("Your New Staff Account Details", "UTF-8");

            String emailContent = "<p>Dear " + residentName + ",</p>"
                    + "<p>Your account has been created successfully.</p>"
                    + "<p>Username: " + username + "<br></p>"
                    + "<p>Password: " + residentPassword + "</p>"
                    + "<p>Please keep this information safe.</p>"
                    + "<p>Best Regards,<br>Your Community</p>";

            message.setContent(emailContent, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            System.out.println("Failed to send email to: " + to);
            e.printStackTrace();
        }
    }

    public void sendEmailToWorkingAdmin(List<Staff> list, String detail) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (Staff ei : list) {
            executor.execute(() -> sendEmailResidentToAdminList(ei, detail));
        }
        executor.shutdown();
    }

    public void sendEmailResidentToAdminList(Staff estaff, String detail) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(estaff.getEmail()));
            message.setSubject("Công việc yêu cầu từ dân cư", "UTF-8");

            // Set the content to be HTML
            message.setContent(detail, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Đã gửi email đến: " + estaff.getEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

//    public void sendRequestEmail(String to, String username, String requestType, String content) {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587"); // Use 587 for TLS
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS
//
//        Authenticator auth = new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(from, password);
//            }
//        };
//
//        Session session = Session.getInstance(props, auth);
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
//            message.setSubject("Feed about" + feedbackType, "UTF-8");
//
//            String emailContent = "<p>A Resident has sent a feedback about " + feedbackType + "</p>"
//                    + "<p>" + content + "<br></p>";
//
//            message.setContent(emailContent, "text/html; charset=UTF-8");
//
//            Transport.send(message);
//            System.out.println("Email sent successfully to " + to);
//        } catch (MessagingException e) {
//            System.out.println("Failed to send email to: " + to);
//            e.printStackTrace();
//        }
//
//    }

    public void sendRequestEmail(String to, String username, String requestType, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); // Use 587 for TLS
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // Enable TLS

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
            message.setSubject("Request about " + requestType, "UTF-8");

            message.setSubject("Request about " + requestType, "UTF-8");

            String emailContent = "<p>A Resident with username " + username + " has sent a request about " + requestType + "</p>"
                    + "<p>" + content + "<br></p>";

            message.setContent(emailContent, "text/html; charset=UTF-8");

            Transport.send(message);
            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            System.out.println("Failed to send email to: " + to);
            e.printStackTrace();
        }

    }

    /**
     * Kiểm tra xem địa chỉ email có tồn tại hay không.
     */
    public static void main(String[] args) {
        SendEmail emailSender = new SendEmail();
        List<String> emails = new ArrayList<>();
        emails.add("kophaithanhhui@gmail.com");
        emails.add("kophaithanhhui123@gmail.com");

        emailSender.sendFeedbackMail(emails, "123", 2, "ngu");
    }
}
