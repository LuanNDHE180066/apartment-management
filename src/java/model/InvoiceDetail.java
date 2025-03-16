/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import util.Util;

/**
 *
 * @author thanh
 */
public class InvoiceDetail {
//create table InvoiceDetail(
//	InvoiceId varchar(10) references Invoice(id),
//	ServiceName nvarchar(255),
//	PriceUnit float,
//	Quantity int,
//	Date datetime,
//	Amount float
//)
    private Invoice invocie;
    private String serviceName;
    private float unitPrice;
    private int quantity;
    private LocalDateTime date;
    private float amount;

    public InvoiceDetail() {
    }

    public InvoiceDetail(Invoice invocie, String serviceName, float unitPrice, int quantity, LocalDateTime date, float amount) {
        this.invocie = invocie;
        this.serviceName = serviceName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.date = date;
        this.amount = amount;
    }

    public Invoice getInvocie() {
        return invocie;
    }

    public void setInvocie(Invoice invocie) {
        this.invocie = invocie;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public String getDateFormat() {
        return Util.formatDate(date.toLocalDate().toString());
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
    
}
