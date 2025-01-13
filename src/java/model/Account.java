/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin1711
 */
public class Account {

//CREATE TABLE Person (
//  Id       varchar(10) NOT NULL, 
//  Fullname nvarchar(255) NOT NULL, 
//  Bod      date NOT NULL, 
//  Email    varchar(255) unique NOT NULL, 
//  Sđt      varchar(11) unique NOT NULL, 
//  Address  nvarchar(255) NOT NULL, 
//  CCCD     varchar(12) unique NOT NULL, 
//  PRIMARY KEY (Id));
//  --2
//CREATE TABLE Account (
//  username varchar(20) NOT NULL, 
//  [password] varchar(255) NOT NULL, 
//  pId      varchar(10) NOT NULL FOREIGN KEY (pId) REFERENCES Person (Id), 
//  [Role]     int NOT NULL FOREIGN KEY ([Role]) REFERENCES [Role] (Id), 
//  PRIMARY KEY (username));
    private String username;
    private String password;
    private String pId;
    private int role;

    public Account(String username, String password, String pId, int role) {
        this.username = username;
        this.password = password;
        this.pId = pId;
        this.role = role;
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

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
