/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author pc
 */
public class ContractApprove {
    private int id;
    private Contract contractId;
    private Integer adminApproval;      
    private int status;                 
    private String createdAt;
    private String updatedAt;
    private Staff admin;


    public ContractApprove() {
    }

    public ContractApprove(int id, Contract contractId, Integer adminApproval, int status, String createdAt, String updatedAt, Staff admin) {
        this.id = id;
        this.contractId = contractId;
        this.adminApproval = adminApproval;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.admin = admin;
    }

    public ContractApprove( Contract contractId, Integer adminApproval,  int status, String createdAt, String updatedAt, Staff admin) {

        this.contractId = contractId;
        this.adminApproval = adminApproval;

        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.admin = admin;

    }


    public ContractApprove(int id, Contract contractId, Staff admin, Integer adminApproval, int status, String createdAt, String updatedAt) {
        this.id = id;
        this.contractId = contractId;
        this.admin = admin;

        this.adminApproval = adminApproval;

        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        
        
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contract getContractId() {
        return contractId;
    }

    public void setContractId(Contract contractId) {
        this.contractId = contractId;
    }

    public Integer getAdminApproval() {
        return adminApproval;
    }

    public void setAdminApproval(Integer adminApproval) {
        this.adminApproval = adminApproval;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Staff getAdmin() {
        return admin;
    }

    public void setAdmin(Staff admin) {
        this.admin = admin;
    }
    
}
