/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Timestamp;
import jdbc.DBContext;
import jdbc.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import model.SendEmail;
import model.TokenForgetPassword;
import jdbc.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;

/**
 *
 * @author admin1711
 */
public class TokenForgetPassDAO extends DBContext {

    public String getFormatDate(LocalDateTime myDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDate = myDate.format(format);
        return formatDate;
    }

    public boolean addToken(TokenForgetPassword token) {
        String sql = "INSERT INTO tokenForgetPassword(token, expiryTime, isUsed, userID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, token.getToken());
            ps.setTimestamp(2, Timestamp.valueOf(getFormatDate(token.getExpireTime()))); // Chuyển đổi LocalDateTime thành Timestamp
            ps.setBoolean(3, token.isIsUsed()); // Đảm bảo gọi đúng phương thức getter
            ps.setString(4, token.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(TokenForgetPassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public TokenForgetPassword getTokenPassword(String token) {
        String sql = "select * from tokenForgetPassword where token = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TokenForgetPassword newToken = new TokenForgetPassword(rs.getInt(1), rs.getString(2), rs.getString(5), rs.getBoolean(4),
                        rs.getTimestamp(3).toLocalDateTime());
                return newToken;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TokenForgetPassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getTokenByUsername(String username) {
        String sql = "select * from tokenForgetPassword where isUsed = ? and userId = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TokenForgetPassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateStatusToken(String token) {
        String sql = "update tokenForgetPassword set isUsed = 1 where token = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, token);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TokenForgetPassDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        TokenForgetPassDAO dao = new TokenForgetPassDAO();
        SendEmail send = new SendEmail();
        Timestamp time = Timestamp.valueOf(send.expireDateTime());
        TokenForgetPassword token = new TokenForgetPassword(send.generateToken(), "quang", false, send.expireDateTime());
        System.out.println(dao.getTokenByUsername("quang"));
    }
}
