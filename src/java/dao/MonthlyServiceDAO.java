/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jdbc.DBContext;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import jdbc.DBContext;
import model.Apartment;
import model.CategoryService;
import model.Company;
import model.MonthlyService;
import model.Service;

/**
 *
 * @author thanh
 */
public class MonthlyServiceDAO extends DBContext {
    public List<MonthlyService> getByApartmentId(String id){
        String sql = "select * from MonthlyInvoice where aid =?";
        ServiceDAO sd = new ServiceDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<MonthlyService> list = new ArrayList<>();
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Service s = sd.getById(rs.getString("sid"));
                int quantity = rs.getInt("quantity");
                Apartment a = ad.getById(rs.getString("aid"));
                MonthlyService ms = new MonthlyService(s, quantity, a);
                list.add(ms);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public void updateQuantityByServiceAndApartment(String sid,String aid,int quantity){
        String sql ="update MonthlyInvoice set quantity= ? where sid = ? and aid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setString(2, sid);
            st.setString(3, aid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteMonthlyService(String sid,String aid){
        String sql ="delete MonthlyInvoice where sid = ? and aid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sid);
            st.setString(2, aid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<Service> getNotUsingServiceByApartmentId(String aid){
        String sql="select id from Service where status =1 except select sid from MonthlyInvoice where aid=?";
        List<Service> list = new ArrayList<>();
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            st.setString(1, aid);
            ServiceDAO sd= new ServiceDAO();
            ResultSet rs= st.executeQuery();
            while(rs.next()){
                list.add(sd.getById(rs.getString("id")));
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public void addServiceToApartment(String sid,String aid){
        String sql="insert into MonthlyInvoice values(?,1,?)";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, sid);
            st.setString(2, aid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void deleteWhenTurnOffService(String sid){
        String sql ="delete MonthlyInvoice where sid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void switchService(String newIs,String oldId){
        String sql="update MonthlyInvoice set sid =? where sid =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newIs);
            st.setString(2, oldId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void addServiceToAllApartment(String sid){
        LivingApartmentDAO ld = new  LivingApartmentDAO();
        List<String> listApartment = ld.getAllActiveApartment();
        for (int i = 0; i < listApartment.size(); i++) {
            String aid = listApartment.get(i);
            String sql="insert into MonthlyInvoice values(?,1,?)";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, sid);
                st.setString(2, aid);
                st.executeUpdate();
            } catch (SQLException e) {
            }
        }
    }
    public void addServiceToAllResidentNoUsing(String sid) {
        List<String> listApartment = this.getApartmentIdNoUsingServiceId(sid);
        for (String aid : listApartment) {
            this.addServiceToApartment(sid, aid);
        }
    }
    public void synchronousServiceByApartment(String aid,int quantity,String sid){
        String sql = "update MonthlyInvoice set quantity=? where aid=? and sid=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setString(2, aid);
            st.setString(3, sid);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }
    public List<String> getApartmentIdNoUsingServiceId(String sid){
         String sql = "select aid from LivingAparment where status =1 except\n"
                + "select aid from MonthlyInvoice where sid =?";
         List<String> list = new ArrayList<>();
         try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sid);
            ResultSet rs =st.executeQuery();
            while(rs.next()){
                list.add(rs.getString("aid"));
            }
        } catch (SQLException e) {
        }
         return list;
    }
    public static void main(String[] args) {
        MonthlyServiceDAO dao = new MonthlyServiceDAO();
        System.out.println(dao.getByApartmentId("A01_01").size());
    }
}
