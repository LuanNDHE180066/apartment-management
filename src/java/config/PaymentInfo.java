/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import jakarta.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author thanh
 */
public class PaymentInfo {
    public static String vnp_PayUrl="https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public String vnp_ReturnUrl="http://localhost:6969/TestVNPay/return.html";
    public static  String vnp_TmnCode="KKDK3533" ;
    public static  String vnp_HashSecret="B22FRM04PUI7RWQ60QCTODJ2J2VB0HTV";
    public static  String vnp_Version="2.1.0";
    public static  String vnp_Command="pay";
    public static  String orderType="other";
     public static String hmacSHA512(final String key, final String data) {
    try {
        if (key == null || data == null) {
            throw new NullPointerException("Key or data cannot be null");
        }
        final Mac hmac512 = Mac.getInstance("HmacSHA512");
        byte[] hmacKeyBytes = key.getBytes(StandardCharsets.UTF_8);
        final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
        hmac512.init(secretKey);
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
        byte[] result = hmac512.doFinal(dataBytes);
        StringBuilder sb = new StringBuilder(2 * result.length);
        for (byte b : result) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    } catch (Exception ex) {
        ex.printStackTrace(); // Thêm log để biết chính xác lỗi
        throw new RuntimeException("Error generating HMAC SHA512", ex); // Ném ngoại lệ để cảnh báo rõ ràng
    }
}

     public static String getRandomNumber(int len) {
    if (len <= 0) {
        throw new IllegalArgumentException("Length must be greater than 0");
    }
    Random rnd = new Random();
    String chars = "0123456789";
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
        sb.append(chars.charAt(rnd.nextInt(chars.length())));
    }
    return sb.toString();
}

   public static String getIpAddress(HttpServletRequest request) {
    String ipAddress = null;
    try {
        ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        } else {
            // Lấy IP đầu tiên nếu có nhiều IP trong X-FORWARDED-FOR
            ipAddress = ipAddress.split(",")[0].trim();
        }
    } catch (Exception e) {
        e.printStackTrace(); // Log lỗi để biết chính xác vấn đề
        ipAddress = "127.0.0.1"; // Giá trị mặc định nếu không lấy được IP
    }
    return ipAddress;
}
}
