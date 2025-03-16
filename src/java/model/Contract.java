/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author quang
 */
public class Contract {
    private String id;
    private Staff staff;
    private Staff admin;
    private Staff accountant;
    private Company company;
    private String endDate, startDate, paymentTems, signDate, title, description, image;
    int status;

    public Contract(Staff staff, Company company, String startDate, String endDate, String paymentTems, String signDate, String title, String description, Staff accountant , Staff admin, String image) {
        this.staff=staff;
        this.company=company;
        this.startDate=startDate;
        this.endDate=endDate;
        this.paymentTems=paymentTems;
        this.signDate=signDate;
        this.title=title;
        this.description=description;
        this.accountant=accountant;
        this.admin=admin;
        this.image=image;
    }

    public Contract(String valueOf, Staff byId) {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Staff getAdmin() {
        return admin;
    }

    public void setAdmin(Staff admin) {
        this.admin = admin;
    }

    public Staff getAccountant() {
        return accountant;
    }

    public void setAccountant(Staff accountant) {
        this.accountant = accountant;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Contract(String id, Staff staff, Staff admin, Staff accountant, Company company, String endDate, String startDate, String paymentTems, String signDate, String title, String description, String image, int status) {
        this.id = id;
        this.staff = staff;
        this.admin = admin;
        this.accountant = accountant;
        this.company = company;
        this.endDate = endDate;
        this.startDate = startDate;
        this.paymentTems = paymentTems;
        this.signDate = signDate;
        this.title = title;
        this.description = description;
        this.image = image;
        this.status = status;
    }

    
    
    public Contract(Staff staff, Company company, String endDate, String startDate, String paymentTems, String signDate, String title, String description, int status) {
        this.staff = staff;
        this.company = company;
        this.endDate = endDate;
        this.startDate = startDate;
        this.paymentTems = paymentTems;
        this.signDate = signDate;
        this.title = title;
        this.description = description;
        this.status = status;
    }


    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getEndDate() {
        return endDate;
    }
    public String formatEnddate() {
        if (endDate == null || endDate.isEmpty()) {
            return "";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(endDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
    @Override
    public String toString() {
        return "Company: "+ company ;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }
    public String formatStartdate() {
        if (startDate == null || startDate.isEmpty()) {
            return "";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(startDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getPaymentTems() {
        return paymentTems;
    }
    public String formatPaydate() {
        if (paymentTems == null || paymentTems.isEmpty()) {
            return "";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(paymentTems);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
    public void setPaymentTems(String paymentTems) {
        this.paymentTems = paymentTems;
    }

    public String getSignDate() {
        return signDate;
    }
    public String formatSigndate() {
        if (signDate == null || signDate.isEmpty()) {
            return "";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(signDate);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
