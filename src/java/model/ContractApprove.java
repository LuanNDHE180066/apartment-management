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
    private Integer accountantApproval; 
    private int status;                 
    private String createdAt;
    private String updatedAt;
    private Staff admin;
    private Staff accountant;

    public ContractApprove() {
    }

    public ContractApprove(int id, Contract contractId, Integer adminApproval, Integer accountantApproval, int status, String createdAt, String updatedAt, Staff admin, Staff accountant) {
        this.id = id;
        this.contractId = contractId;
        this.adminApproval = adminApproval;
        this.accountantApproval = accountantApproval;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.admin = admin;
        this.accountant = accountant;
    }

    public ContractApprove(Contract contractId, Integer adminApproval, Integer accountantApproval, int status, String createdAt, String updatedAt,Staff accountant,Staff admin) {
        this.contractId = contractId;
        this.adminApproval = adminApproval;
        this.accountantApproval = accountantApproval;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.accountant = accountant;
        this.admin = admin;
        
    }

    public ContractApprove(int id, Contract contractId, Staff admin, Staff accountant, Integer adminApproval, Integer accountantApproval, int status, String createdAt, String updatedAt) {
        this.id = id;
        this.contractId = contractId;
        this.admin = admin;
        this.accountant = accountant;
        this.adminApproval = adminApproval;
        this.accountantApproval = accountantApproval;
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

    public Integer getAccountantApproval() {
        return accountantApproval;
    }

    public void setAccountantApproval(Integer accountantApproval) {
        this.accountantApproval = accountantApproval;
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

    public Staff getAccountant() {
        return accountant;
    }

    public void setAccountant(Staff accountant) {
        this.accountant = accountant;
    }

    
    
}
