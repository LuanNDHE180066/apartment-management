/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thanh
 */
public class Resident {

    /*CREATE TABLE Resident (
  pId    varchar(10) NOT NULL FOREIGN KEY (pId) REFERENCES Person (Id), 
  Bank   varchar(255) unique NOT NULL, 
  Subemail    varchar(255) unique NOT NULL, 
  [Status] varchar(255) NOT NULL, 
  PRIMARY KEY (pId));*/
    private String pId;
    private String name, cccd;
    private String phone, email, bod, address, username, password, status, note;
    private Role role;
    private String gender,image;

    public Resident() {
    }

    public Resident(String pId, String name, String cccd, String phone, String email, String bod, String address, String username, String password, String status, String note, Role role) {
        this.pId = pId;
        this.name = name;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.bod = bod;
        this.address = address;
        this.username = username;
        this.password = password;
        this.status = status;
        this.note = note;
        this.role = role;
    }
    public Resident(String pId, String name, String cccd, String phone, String email, String bod, String address, String username, String password, String status, String note, Role role,String image) {
        this.pId = pId;
        this.name = name;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.bod = bod;
        this.address = address;
        this.username = username;
        this.password = password;
        this.status = status;
        this.note = note;
        this.role = role;
        this.image = image;
    }

    public Resident(String pId, String name, String cccd, String phone, String email, String bod, String address, String status) {
        this.pId = pId;
        this.name = name;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.bod = bod;
        this.address = address;
        this.status = status;
    }

    public Resident(String name, String cccd, String phone, String email, String bod, String address, String username, String password, Role role, String gender) {
        this.name = name;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.bod = bod;
        this.address = address;
        this.username = username;
        this.password = password;
        this.role = role;
        this.gender = gender;
    }

    public Resident(String pId, String name, String cccd, String phone, String email, String bod, String address, String status, String gender) {
        this.pId = pId;
        this.name = name;
        this.cccd = cccd;
        this.phone = phone;
        this.email = email;
        this.bod = bod;
        this.address = address;
        this.status = status;
        this.gender = gender;
    }

    public Resident(String pId, String email, String phone, String address) {
        this.pId=pId;
        this.email=email;
        this.phone=phone;
        this.address=address;
        
    }

    public Resident(String pId) {
        this.pId=pId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Resident{" + "pId=" + pId + ", name=" + name + ", cccd=" + cccd + ", phone=" + phone + ", email=" + email + ", bod=" + bod + ", address=" + address + ", username=" + username + ", password=" + password + ", status=" + status + ", note=" + note + ", role=" + role + '}';
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBod() {
        return bod;
    }

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

}
