/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quang
 */
public class RequestChangeResident {

    private int requestId;
    private Resident owner;
    private Resident newPerson;
//    private String dob;
//    private String address;
//    private String phone;
//    private String email;
//    private String cccd;
//    private String username;
//    private String password;
//    private String gender;
    private String roomNumber;
    private int changeType;
    private int newPersonExists;
    private int adminStatus;
    private String createdAt;

    public RequestChangeResident(int requestId, Resident owner, Resident newPerson, String roomNumber, int changeType, int newPersonExists, int adminStatus, String createdAt) {
        this.requestId = requestId;
        this.owner = owner;
        this.newPerson = newPerson;
        this.roomNumber = roomNumber;
        this.changeType = changeType;
        this.newPersonExists = newPersonExists;
        this.adminStatus = adminStatus;
        this.createdAt = createdAt;
    }

    public RequestChangeResident(Resident owner, Resident newPerson, String roomNumber, int changeType, int newPersonExists, int adminStatus, String createdAt) {
        this.owner = owner;
        this.newPerson = newPerson;
        this.roomNumber = roomNumber;
        this.changeType = changeType;
        this.newPersonExists = newPersonExists;
        this.adminStatus = adminStatus;
        this.createdAt = createdAt;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Resident getOwner() {
        return owner;
    }

    public void setOwner(Resident owner) {
        this.owner = owner;
    }

    public Resident getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(Resident newPerson) {
        this.newPerson = newPerson;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public int getNewPersonExists() {
        return newPersonExists;
    }

    public void setNewPersonExists(int newPersonExists) {
        this.newPersonExists = newPersonExists;
    }

    public int getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(int adminStatus) {
        this.adminStatus = adminStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

   

}
