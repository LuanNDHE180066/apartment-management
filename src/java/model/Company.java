/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quang
 */
public class Company {

    private String id;
    private String name;
    private String phone;

    public Company(String name, String phone, String contactPhone, String fax, String email, String contactemail, String website, String taxCode, String bank, String description, String address) {
        this.name = name;
        this.phone = phone;
        this.contactPhone = contactPhone;
        this.fax = fax;
        this.email = email;
        this.contactemail = contactemail;
        this.website = website;
        this.taxCode = taxCode;
        this.bank = bank;
        this.description = description;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name=" + name + ", phone=" + phone + ", contactPhone=" + contactPhone + ", fax=" + fax + ", email=" + email + ", contactemail=" + contactemail + ", website=" + website + ", taxCode=" + taxCode + ", bank=" + bank + ", description=" + description + ", address=" + address + '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private String contactPhone;
    private String fax;
    private String email;
    private String contactemail;
    private String website;
    private String taxCode;
    private String bank;
    private String description;
    private String address;
    private int status;

    public Company() {
    }

    public String getContactemail() {
        return contactemail;
    }

    public void setContactemail(String contactemail) {
        this.contactemail = contactemail;
    }

    public Company(String id) {
        this.id = id;
    }

    public Company(String id, String name, String phone, String contactPhone, String fax, String email, String contactemail, String website, String taxCode, String bank, String description, String address, int status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.contactPhone = contactPhone;
        this.fax = fax;
        this.email = email;
        this.contactemail = contactemail;
        this.website = website;
        this.taxCode = taxCode;
        this.bank = bank;
        this.description = description;
        this.address = address;
        this.status = status;
    }

    public Company(String name, String phone, String contactPhone, String fax, String email, String contactemail, String website, String taxCode, String bank, String description, String address, int status) {
        this.name = name;
        this.phone = phone;
        this.contactPhone = contactPhone;
        this.fax = fax;
        this.email = email;
        this.contactemail = contactemail;
        this.website = website;
        this.taxCode = taxCode;
        this.bank = bank;
        this.description = description;
        this.address = address;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String desription) {
        this.description = desription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
