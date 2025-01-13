/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author admin1711
 */
public class TokenForgetPassword {

    private int id;
    private String token, userId;
    private boolean isUsed;
    private LocalDateTime expireTime;

    public TokenForgetPassword() {
    }

    public int getId() {
        return id;
    }

    public TokenForgetPassword(int id, String token, String userId, boolean isUsed, LocalDateTime expireTime) {
        this.id = id;
        this.token = token;
        this.userId = userId;
        this.isUsed = isUsed;
        this.expireTime = expireTime;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public TokenForgetPassword(String token, String userId, boolean isUsed, LocalDateTime expireTime) {
        this.token = token;
        this.userId = userId;
        this.isUsed = isUsed;
        this.expireTime = expireTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

}
