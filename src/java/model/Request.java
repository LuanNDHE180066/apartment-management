/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author thanh
 */
public class Request {
    private String id;
    private Resident residentId;
    private Staff staffId;
    private String detail;
    private String response;
    private String date;
    private String responseDate;
    private String status;
    private String shift;
    private RequestType requestType;

    public Request() {
    }

    public Request(String id, Resident residentId, Staff staffId, String detail, String response, String date, String responseDate, String status, String shift, RequestType requestType) {
        this.id = id;
        this.residentId = residentId;
        this.staffId = staffId;
        this.detail = detail;
        this.response = response;
        this.date = date;
        this.responseDate = responseDate;
        this.status = status;
        this.shift = shift;
        this.requestType = requestType;
    }

    public Request(String id, Resident residentId, Staff staffId, String detail, String response, String date, String responseDate, String status, RequestType requestType) {
        this.id = id;
        this.residentId = residentId;
        this.staffId = staffId;
        this.detail = detail;
        this.response = response;
        this.date = date;
        this.responseDate = responseDate;
        this.status = status;
        this.requestType = requestType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Resident getResidentId() {
        return residentId;
    }

    public void setResidentId(Resident residentId) {
        this.residentId = residentId;
    }

    public Staff getStaffId() {
        return staffId;
    }

    public void setStaffId(Staff staffId) {
        this.staffId = staffId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
    
}
