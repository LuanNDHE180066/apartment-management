/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class FundHistory {
    private int id;
    private String name,date;
    private float value_befor,value_after;
    private Fund fundid;
    public FundHistory() {
    }

    public FundHistory(int id, String name, String date, float value_befor, float value_after, Fund fundid) {
        this.id = id;
        this.fundid = fundid;
        this.name = name;
        this.date = date;
        this.value_befor = value_befor;
        this.value_after = value_after;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fund getFundid() {
        return fundid;
    }

    public void setFundid(Fund fundid) {
        this.fundid = fundid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getValue_befor() {
        return value_befor;
    }

    public void setValue_befor(float value_befor) {
        this.value_befor = value_befor;
    }

    public float getValue_after() {
        return value_after;
    }

    public void setValue_after(float value_after) {
        this.value_after = value_after;
    }
    
}
