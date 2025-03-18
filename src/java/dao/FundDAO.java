/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import jdbc.DBContext;
import model.Fund;
import model.FundHistory;
import model.HistoryExpenditure;
import model.Invoice;
import model.InvoiceDetail;

/**
 *
 * @author PC
 */
public class FundDAO extends DBContext{
    public Fund getById(String id){
        String sql = "select * from Fund where id ="+id;
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new Fund(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getString("startdate"),rs.getFloat("value"));
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    public void revenueFundByInvoice(String id){
        InvoiceDAO ivd = new InvoiceDAO();
        InvoiceDetalDAO ivdd = new InvoiceDetalDAO();
        FundHistoryDAO fhd = new FundHistoryDAO();
        Fund f = this.getById("1");
        Invoice iv = ivd.getById(id);
        List<InvoiceDetail> ivds = ivdd.getByInvoiceId(id);
        fhd.insertInvoiceFundHistory(ivds, iv.getResident(), 1);
    }
    public void updaterevenueFundByInvoice(float value){
        String sql = "update Fund set value = ?, ChangeDate=? where id = 1";
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            st.setFloat(1,value);
            st.setDate(2,new java.sql.Date(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void expendFundByInvoice(HistoryExpenditure he){
        FundHistoryDAO fhd = new FundHistoryDAO();
        fhd.insertExpendFundHistory(he, 1);
        String sql = "update Fund set value = ?, ChangeDate=? where id = 1";
        Fund f = this.getById("1");
        try {
            PreparedStatement st =connection.prepareStatement(sql);
            st.setFloat(1,f.getValue() - he.getTotalPrice());
            st.setDate(2,new java.sql.Date(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        FundDAO dao = new FundDAO();
        dao.revenueFundByInvoice("IV10");
        System.out.println("");
    }
}
