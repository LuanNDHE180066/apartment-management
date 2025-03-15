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
 * @author admin1711
 */
public class Staff {

    private String id;
    private String name;
    private String bod; // Date of birth as String
    private String email;
    private String phone;
    private String address;
    private String cccd; // Citizen ID
    private int salary;
    private String education;
    private String bank;
    private int status;
    private String username;
    private String password;
    private Role role; // Use Role object
    private Company company; // Foreign key reference
    private String startDate; // Start date as String
    private String endDate; // End date as String
    private String gender,image;
    private int ischief;

    public Staff(String id, String email, String phone, String bank, String address) {
        this.id= id;
        this.email=email;
        this.phone=phone;
        this.bank=bank;
        this.address=address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Staff(String id, String name, String bod, String email, String phone, String address, String cccd, int salary, String education, String bank, int status, String username, String password, Role role, Company company, String startDate, String endDate, String gender,int ischief) {
        this.id = id;
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.salary = salary;
        this.education = education;
        this.bank = bank;
        this.status = status;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gender = gender;
        this.ischief=ischief;
    }
    public Staff(String id, String name, String bod, String email, String phone, String address, String cccd, int salary, String education, String bank, int status, String username, String password, Role role, Company company, String startDate, String endDate, String gender,String image,int ischief) {
        this.id = id;
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.salary = salary;
        this.education = education;
        this.bank = bank;
        this.status = status;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.gender = gender;
        this.image = image;
        this.ischief=ischief;
    }
    public Staff(String name, String bod, String email, String phone, String address, String cccd, int salary, String education, String bank, String username, String password, Role role, Company company, String startDate, String gender) {
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.salary = salary;
        this.education = education;
        this.bank = bank;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
        this.startDate = startDate;
        this.gender = gender;
    }


    @Override
    public String toString() {
        return "Staff{" + "id=" + id + ", name=" + name + ", bod=" + bod + ", email=" + email + ", phone=" + phone + ", address=" + address + ", cccd=" + cccd + ", salary=" + salary + ", education=" + education + ", bank=" + bank + ", status=" + status + ", username=" + username + ", password=" + password + ", role=" + role + ", company=" + company + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

    public Staff() {
    }

    public Staff(String id, String name, String bod, String email, String phone, String address, String cccd, int salary, String education, String bank, int status, Role role, Company company, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.salary = salary;
        this.education = education;
        this.bank = bank;
        this.status = status;
        this.role = role;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Staff(String id, String name, String bod, String email, String phone, String address, String cccd, int salary, String education, String bank, int status, String username, String password, Role role, Company company, String startDate, String endDate) {
        this.id = id;
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.salary = salary;
        this.education = education;
        this.bank = bank;
        this.status = status;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Staff(String name, String bod, String email, String phone, String address, String cccd, int salary, String education, String bank, String username, String password, Role role, Company company, String startDate) {
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.salary = salary;
        this.education = education;
        this.bank = bank;
        this.username = username;
        this.password = password;
        this.role = role;
        this.company = company;
        this.startDate = startDate;
    }

   
    
    

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public Staff(String id, String name, String bod, String email, String phone, String address, String cccd, Company company, String startDate, String endDate, int status, String username, String password, Role role) {
        this.id = id;
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.company = company;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.username = username;
        this.password = password;
        this.role = role;
    }
//name, dob, email, phone, address, cccd, sp, startDate, username, password

    public Staff(String name, String bod, String email, String phone, String address, String cccd, Company company, String startDate, String username, String password) {
        this.name = name;
        this.bod = bod;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cccd = cccd;
        this.company = company;
        this.startDate = startDate;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
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

    public String getBod() {
        return bod;
    }
    public String formatBod() {
        if (bod == null || bod.isEmpty()) {
            return "";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = inputFormat.parse(bod);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(date);
        } catch (ParseException e) {
            return "";
        }
    }
    

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getEndDate() {
        return endDate;
    }
    public String formatEndate() {
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
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIschief() {
        return ischief;
    }

    public void setIschief(int ischief) {
        this.ischief = ischief;
    }
    
    
}
