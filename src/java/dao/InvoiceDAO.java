/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jdbc.DBContext;
import java.util.List;
import jdbc.DBContext;
import model.InvoiceDetail;
import dto.response.FloorResponseDTO;
import java.sql.Date;
import jdbc.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Floor;
import model.Invoice;
import model.LivingApartment;
import model.MonthlyService;
import model.Resident;
import model.Service;
import util.Util;
/**
 *
 * @author thanh
 */
public class InvoiceDAO extends DBContext{
    public Invoice getById(String id){
        String sql = "select * from invoice where id=?";
        ResidentDAO rd= new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs =st.executeQuery();
            if(rs.next()){
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des= rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(id, total, invoicedate, duedate, status, des, re, a);
                return invoice;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public List<Invoice> getByResidentId(String rid){
        String sql = "select * from invoice where rid=?";
        List<Invoice> list = new ArrayList<>();
        ResidentDAO rd= new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, rid);
            ResultSet rs =st.executeQuery();
            while(rs.next()){
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des= rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<Invoice> getNonPaidInvoice(){
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from invoice where status =0";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs =st.executeQuery();
            while(rs.next()){
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des= rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public List<Invoice> searchByYearAndApartment(int year,String aid){
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Invoice> list = new ArrayList<>();
        String sql="select * from invoice where aid =? and year(invoicedate) =?";
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            st.setString(1, aid);
            st.setInt(2, year);
            ResultSet rs =st.executeQuery();
            while(rs.next()){
                 String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des= rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    public void generateInvoice(){
        InvoiceDetalDAO idd=new InvoiceDetalDAO();
        LivingApartmentDAO ld =new LivingApartmentDAO();
        List<LivingApartment> listAciveApartment = ld.getAllActiveLivingApartmentObejct();
        for (int i = 0; i < listAciveApartment.size(); i++) {
            LivingApartment la = listAciveApartment.get(i);
            String aid = la.getAid().getId();
            String rid = la.getRid().getpId();
            float total =this.getTotalByApartmentId(aid);
            String newInvoiceId = this.addInvoiceByApartmentIdResidentIdAndTotal(aid, rid, total);
            idd.addInvoiceDetailByApartmentIdAndInvoiceId(newInvoiceId, aid);
        }
    }
    public String addInvoiceByApartmentIdResidentIdAndTotal(String aid,String rid,float total){
        String sql="insert into invoice values(?,?,?,?,0,?,?,?)";
        String newInvoiceId = "IV"+ (this.getNumberInvoice()+1);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,newInvoiceId);
            st.setFloat(2,total);
            st.setDate(3,Date.valueOf(LocalDate.now()));
            st.setDate(4,Date.valueOf(LocalDate.now().plusDays(7)));
            st.setString(5, "Dich vu thang "+LocalDate.now().getMonthValue());
            st.setString(6, rid);
            st.setString(7, aid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return "Not ok";
        }
        return newInvoiceId;
    }
    public float getTotalByApartmentId(String id){
        float total=0;
        MonthlyServiceDAO md = new MonthlyServiceDAO();
        List<MonthlyService> listService = md.getByApartmentId(id);
        for (int i = 0; i < listService.size(); i++) {
            MonthlyService t = listService.get(i);
            Service s = t.getService();
            total+= s.getUnitPrice()*t.getQuantity();
        }
        return total;
    }
    public int getNumberInvoice(){
        String sql ="select count(*) as no from invoice";
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            ResultSet rs =st.executeQuery();
            if(rs.next()) return rs.getInt("no");
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public void switchToPaidStatusById(String id){
        String sql = "update invoice set status =1 where id =?";
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        InvoiceDAO id = new InvoiceDAO();
        System.out.println(id.getByResidentId("P101").size());
        System.out.println(id.getNonPaidInvoice().size());
        System.out.println(id.searchByYearAndApartment(2025, "A10_04").size());
    }
}
