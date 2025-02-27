/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thanh
 */
public class InvoiceDetail {
//    	[invoiceId] [varchar](10) NULL,
//	[serviceId] [varchar](10) NULL,
//	[Quantity] [int] NULL,
//	[Amount] [float] NULL
    private Invoice invocie;
    private Service service;
    private int quantity;
    private float amount;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Invoice invocie, Service service, int quantity, float amount) {
        this.invocie = invocie;
        this.service = service;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Invoice getInvocie() {
        return invocie;
    }

    public void setInvocie(Invoice invocie) {
        this.invocie = invocie;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
}
