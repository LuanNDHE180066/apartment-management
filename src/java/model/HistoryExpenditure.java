/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quang
 */
public class HistoryExpenditure {

    private int heid;
    private String id;
    private String title;
    private int chiefAccountantApproveStatus;
    private int currentAdminApproveStatus;
    private String approveddate;
    private String paymentdate;
    private float totalPrice;
    private String note;
    private ExpenseCategory category;
    private Company company;
    private Staff createdStaff;
    private Staff chiefAccountantId;
    private Staff currentAdmin;
    private String action;
    private String modifiedDate;
    private Staff modifiedBy;
    private int chiefAccountantApprove, currentAdminApprove;
    private String createdDate;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Staff getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Staff modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getChiefAccountantApprove() {
        return chiefAccountantApprove;
    }

    public void setChiefAccountantApprove(int chiefAccountantApprove) {
        this.chiefAccountantApprove = chiefAccountantApprove;
    }

    public int getCurrentAdminApprove() {
        return currentAdminApprove;
    }

    public void setCurrentAdminApprove(int currentAdminApprove) {
        this.currentAdminApprove = currentAdminApprove;
    }

    public HistoryExpenditure(int heid, String id, String title, int chiefAccountantApproveStatus, int currentAdminApproveStatus, String approveddate, String paymentdate, float totalPrice, String note, ExpenseCategory category, Company company, Staff createdStaff, Staff chiefAccountantId, Staff currentAdmin, String action, String modifiedDate, Staff modifiedBy, int chiefAccountantApprove, int currentAdminApprove, String createdDate) {
        this.heid = heid;
        this.id = id;
        this.title = title;
        this.chiefAccountantApproveStatus = chiefAccountantApproveStatus;
        this.currentAdminApproveStatus = currentAdminApproveStatus;
        this.approveddate = approveddate;
        this.paymentdate = paymentdate;
        this.totalPrice = totalPrice;
        this.note = note;
        this.category = category;
        this.company = company;
        this.createdStaff = createdStaff;
        this.chiefAccountantId = chiefAccountantId;
        this.currentAdmin = currentAdmin;
        this.action = action;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.chiefAccountantApprove = chiefAccountantApprove;
        this.currentAdminApprove = currentAdminApprove;
        this.createdDate = createdDate;
    }

    

   



    public int getHeid() {
        return heid;
    }

    public void setHeid(int heid) {
        this.heid = heid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChiefAccountantApproveStatus() {
        return chiefAccountantApproveStatus;
    }

    public void setChiefAccountantApproveStatus(int chiefAccountantApproveStatus) {
        this.chiefAccountantApproveStatus = chiefAccountantApproveStatus;
    }

    public int getCurrentAdminApproveStatus() {
        return currentAdminApproveStatus;
    }

    public void setCurrentAdminApproveStatus(int currentAdminApproveStatus) {
        this.currentAdminApproveStatus = currentAdminApproveStatus;
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

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
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
