/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quang
 */
public class RepresentResidentChangeRequest {

    private int id;
    private Resident oldRepresentPerson;
    private Resident newRepresentPerson;
    private String aId;
    private int adminApprove;
    private String requestDate;
    private int isExistAccount;
    private String newUsername;
    private Resident owner;

    public String getNewUsername() {
        return newUsername;
    }

    @Override
    public String toString() {
        return "RepresentResidentChangeRequest{" + "id=" + id + ", oldRepresentPerson=" + oldRepresentPerson + ", newRepresentPerson=" + newRepresentPerson + ", aId=" + aId + ", adminApprove=" + adminApprove + ", requestDate=" + requestDate + ", isExistAccount=" + isExistAccount + ", newUsername=" + newUsername + ", owner=" + owner + '}';
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public Resident getOwner() {
        return owner;
    }

    public void setOwner(Resident owner) {
        this.owner = owner;
    }

    public RepresentResidentChangeRequest(int id, Resident oldRepresentPerson, Resident newRepresentPerson, String aId, int adminApprove, String requestDate, int isExistAccount, String newUsername, Resident owner) {
        this.id = id;
        this.oldRepresentPerson = oldRepresentPerson;
        this.newRepresentPerson = newRepresentPerson;
        this.aId = aId;
        this.adminApprove = adminApprove;
        this.requestDate = requestDate;
        this.isExistAccount = isExistAccount;
        this.newUsername = newUsername;
        this.owner = owner;
    }

    public RepresentResidentChangeRequest(Resident oldRepresentPerson, Resident newRepresentPerson, String aId, int adminApprove, String requestDate, int isExistAccount, String newUsername, Resident owner) {
        this.oldRepresentPerson = oldRepresentPerson;
        this.newRepresentPerson = newRepresentPerson;
        this.aId = aId;
        this.adminApprove = adminApprove;
        this.requestDate = requestDate;
        this.isExistAccount = isExistAccount;
        this.newUsername = newUsername;
        this.owner = owner;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Resident getOldRepresentPerson() {
        return oldRepresentPerson;
    }

    public void setOldRepresentPerson(Resident oldRepresentPerson) {
        this.oldRepresentPerson = oldRepresentPerson;
    }

    public Resident getNewRepresentPerson() {
        return newRepresentPerson;
    }

    public void setNewRepresentPerson(Resident newRepresentPerson) {
        this.newRepresentPerson = newRepresentPerson;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public int getAdminApprove() {
        return adminApprove;
    }

    public void setAdminApprove(int adminApprove) {
        this.adminApprove = adminApprove;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public int getIsExistAccount() {
        return isExistAccount;
    }

    public void setIsExistAccount(int isExistAccount) {
        this.isExistAccount = isExistAccount;
    }

}
