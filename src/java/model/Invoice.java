/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dao.InvoiceDetalDAO;
import java.util.List;

/**
 *
 * @author thanh
 */
public class Invoice {
//	[id] [varchar](10) NOT NULL,
//	[total] [float] NOT NULL,
//	[invoicedate] [date] NOT NULL,
//	[duedate] [date] NOT NULL,
//	[status] [int] NOT NULL,
//	[description] [nvarchar](4000) NOT NULL,
//	[rId] [varchar](10) NOT NULL,
//	[aid] [varchar](10) NULL,
    private String id;
    private float total;
    private String invoiceDate;
    private String dueDate;
    private int status;
    private String description;
    private Resident resident;
    private Apartment apartment;

    public Invoice() {
    }

    public Invoice(String id, float total, String invoiceDate, String dueDate, int status, String description, Resident resident, Apartment apartment) {
        this.id = id;
        this.total = total;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.status = status;
        this.description = description;
        this.resident = resident;
        this.apartment = apartment;
    }
    public List<InvoiceDetail> getInvoiceDetail(){
        InvoiceDetalDAO idd = new InvoiceDetalDAO();
        return idd.getByInvoiceId(this.id);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
    
    
}
