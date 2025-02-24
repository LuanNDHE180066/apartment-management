/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Expenditure {
    String id;
    int amount;
    float price;
    String aprroveddate;
    String paymentdate;
    String note;
    String category;
    String cid;
    String sid;

    public Expenditure() {
    }

    public Expenditure(String id, int amount, float price, String aprroveddate, String paymentdate, String note, String category, String cid, String sid) {
        this.id = id;
        this.amount = amount;
        this.price = price;
        this.aprroveddate = aprroveddate;
        this.paymentdate = paymentdate;
        this.note = note;
        this.category = category;
        this.cid = cid;
        this.sid = sid;
    }

    public Expenditure(int amount, float price, String aprroveddate, String paymentdate, String note, String category, String cid, String sid) {
        this.amount = amount;
        this.price = price;
        this.aprroveddate = aprroveddate;
        this.paymentdate = paymentdate;
        this.note = note;
        this.category = category;
        this.cid = cid;
        this.sid = sid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAprroveddate() {
        return aprroveddate;
    }

    public void setAprroveddate(String aprroveddate) {
        this.aprroveddate = aprroveddate;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
    
}
