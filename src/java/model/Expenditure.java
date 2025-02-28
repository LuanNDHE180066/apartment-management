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
    String title;
    String approveddate;
    String paymentdate;
    float totalPrice;
    String note;
    String category;
    Company company;
    Staff createdStaff;
    Staff chiefAccountantId;
    Staff currentAdmin;    

    public Expenditure(String id, String title, String approveddate, String paymentdate, float totalPrice, String note, String category, Company company, Staff createdStaff, Staff chiefAccountantId, Staff currentAdmin) {
        this.id = id;
        this.title = title;
        this.approveddate = approveddate;
        this.paymentdate = paymentdate;
        this.totalPrice = totalPrice;
        this.note = note;
        this.category = category;
        this.company = company;
        this.createdStaff = createdStaff;
        this.chiefAccountantId = chiefAccountantId;
        this.currentAdmin = currentAdmin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApproveddate() {
        return approveddate;
    }

    public void setApproveddate(String approveddate) {
        this.approveddate = approveddate;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Staff getCreatedStaff() {
        return createdStaff;
    }

    public void setCreatedStaff(Staff createdStaff) {
        this.createdStaff = createdStaff;
    }

    public Staff getChiefAccountantId() {
        return chiefAccountantId;
    }

    public void setChiefAccountantId(Staff chiefAccountantId) {
        this.chiefAccountantId = chiefAccountantId;
    }

    public Staff getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(Staff currentAdmin) {
        this.currentAdmin = currentAdmin;
    }

    
}
