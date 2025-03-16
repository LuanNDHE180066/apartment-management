/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jdbc.DBContext;
import model.InvoiceDetail;
import model.Resident;

/**
 *
 * @author PC
 */
public class FundHistoryDAO extends DBContext{
    public void insertFundHistory(List<InvoiceDetail> ivds, Resident rs, int fundid){
        for (InvoiceDetail ivd : ivds) {
            String sql = "insert into FundHistory(value_befor,value_after,name,date,fundid)\n" +
            "  values(?,?,?,?,?)";
            try {
            PreparedStatement st =connection.prepareStatement(sql);
            st.setFloat(1, ivd.getAmount());
            st.setDate(4,new java.sql.Date(System.currentTimeMillis()));
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        }
    }
}
