/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author quang
 */
public class ApartmentDetail {

    private String id;
    private int numberOfPerson;
    private Floor floor;
    private String infor;
    private RoomType roomtype;
    private int status;
    private Resident owner;
    private Resident livingPerson;

    public ApartmentDetail(String id, int numberOfPerson, Floor floor, String infor, RoomType roomtype, int status, Resident owner, Resident livingPerson) {
        this.id = id;
        this.numberOfPerson = numberOfPerson;
        this.floor = floor;
        this.infor = infor;
        this.roomtype = roomtype;
        this.status = status;
        this.owner = owner;
        this.livingPerson = livingPerson;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumberOfPerson() {
        return numberOfPerson;
    }

    public void setNumberOfPerson(int numberOfPerson) {
        this.numberOfPerson = numberOfPerson;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Resident getOwner() {
        return owner;
    }

    public void setOwner(Resident owner) {
        this.owner = owner;
    }

    public Resident getLivingPerson() {
        return livingPerson;
    }

    public void setLivingPerson(Resident livingPerson) {
        this.livingPerson = livingPerson;
    }

}
