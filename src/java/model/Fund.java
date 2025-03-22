/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class Fund {
    private int id, status;
    private String name,description,startdate,changedate;
    private float value;

    public Fund() {
    }

    public Fund(int id, String name, String description, String startdate, String changedate, float value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startdate = startdate;
        this.changedate = changedate;
        this.value = value;
    }

    public Fund(int id, String name, String description, String startdate, float value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startdate = startdate;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getChangedate() {
        return changedate;
    }

    public void setChangedate(String changedate) {
        this.changedate = changedate;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
