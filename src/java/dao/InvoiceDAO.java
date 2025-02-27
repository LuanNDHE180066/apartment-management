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
import jdbc.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Floor;
import model.Invoice;
import model.Resident;
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
                float total = rs.getFloat("amount");
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
}
