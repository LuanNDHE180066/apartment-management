/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thanh
 */
public class MonthlyService {
//    [sid] [varchar](10) NOT NULL,
//	[quantity] [int] NOT NULL,
//	[aid] [varchar](10) NOT NULL,
    private Service service;
    private int quantity;
    private Apartment apartment;

    public MonthlyService() {
    }

    public MonthlyService(Service service, int quantity, Apartment apartment) {
        this.service = service;
        this.quantity = quantity;
        this.apartment = apartment;
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

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
    
}
