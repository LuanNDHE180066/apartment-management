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
    private String oldRepresentPerson;
    private String newRepresentPerson;
    private String aId;
    private int adminApprove;
    private String requestDate;
    private int isExistAccount;

    public RepresentResidentChangeRequest(int id, String oldRepresentPerson, String newRepresentPerson, String aId, int adminApprove, String requestDate, int isExistAccount) {
        this.id = id;
        this.oldRepresentPerson = oldRepresentPerson;
        this.newRepresentPerson = newRepresentPerson;
        this.aId = aId;
        this.adminApprove = adminApprove;
        this.requestDate = requestDate;
        this.isExistAccount = isExistAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOldRepresentPerson() {
        return oldRepresentPerson;
    }

    public void setOldRepresentPerson(String oldRepresentPerson) {
        this.oldRepresentPerson = oldRepresentPerson;
    }

    public String getNewRepresentPerson() {
        return newRepresentPerson;
    }

    public void setNewRepresentPerson(String newRepresentPerson) {
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
