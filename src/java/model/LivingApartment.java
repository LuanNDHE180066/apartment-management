/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thanh
 */
public class LivingApartment {
//    [id] [varchar](10) NOT NULL,
//	[rId] [varchar](10) NOT NULL,
//	[aId] [varchar](10) NOT NULL,
//	[Startdate] [date] NOT NULL,
//	[Enddate] [date] NULL,
//	[status] [int] NOT NULL,
    private String id;
    private Resident rid;
    private Apartment aid;
    private String startDate,endDate;
    private int status;

    public LivingApartment() {
    }

    public LivingApartment(String id, Resident rid, Apartment aid, String startDate, String endDate, int status) {
        this.id = id;
        this.rid = rid;
        this.aid = aid;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Resident getRid() {
        return rid;
    }

    public void setRid(Resident rid) {
        this.rid = rid;
    }

    public Apartment getAid() {
        return aid;
    }

    public void setAid(Apartment aid) {
        this.aid = aid;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
