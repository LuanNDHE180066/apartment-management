/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

/**
 *
 * @author thanh
 */
public class EmailInvoice {
    private String aid;
    private String email;

    public EmailInvoice() {
    }

    public EmailInvoice(String aid, String email) {
        this.aid = aid;
        this.email = email;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
