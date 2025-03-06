/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author quang
 */
public class Feedback {

    private String id;
    private String detail, date;
    private Resident resident;
    private RequestType requestType;
    private int rate;
    private int status;
    private List<String> img;

    public Feedback() {
    }

    public Feedback(String id, String detail, String date, Resident resident, RequestType requestType, int rate, List<String> img,int status) {
        this.id = id;
        this.detail = detail;
        this.date = date;
        this.resident = resident;
        this.requestType = requestType;
        this.rate = rate;
        this.img = img;
        this.status=status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDate() {
        try {
            // Convert the stored date string to SQL Timestamp
            Timestamp timestamp = Timestamp.valueOf(date);

            // Define the desired date format
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            // Convert and return formatted date
            return sdf.format(timestamp);
        } catch (IllegalArgumentException e) {
            System.out.println("Error parsing date: " + e);
            return date; // Return the original date if there's an error
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }

}
