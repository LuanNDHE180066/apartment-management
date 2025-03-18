/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jdbc.DBContext;
import model.Fund;
import model.HistoryExpenditure;
import model.InvoiceDetail;
import model.Resident;

/**
 *
 * @author PC
 */
public class FundHistoryDAO extends DBContext {

    public void insertInvoiceFundHistory(List<InvoiceDetail> ivds, Resident rs, int fundid) {
        FundDAO fd = new FundDAO();
        for (InvoiceDetail ivd : ivds) {
            Fund f = fd.getById("1");
            String sql = "insert into FundHistory(value_befor,value_after,name,date,fundid)\n"
                    + "  values(?,?,?,?,?)";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setFloat(1, f.getValue());
                st.setFloat(2, f.getValue() + ivd.getAmount());
                fd.updaterevenueFundByInvoice(f.getValue() + ivd.getAmount());
                st.setString(3, rs.getName());
                st.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                st.setInt(5, 1);
                st.executeUpdate();
                st.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public void insertExpendFundHistory(HistoryExpenditure he, int fundid) {
        String sql = "insert into FundHistory(value_befor,value_after,name,date,fundid)\n"
                + "  values(?,?,?,?,?)";
        FundDAO fd = new FundDAO();
        Fund f = fd.getById("1");
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setFloat(1, f.getValue());
            st.setFloat(2, f.getValue() - he.getTotalPrice());
            st.setString(3, he.getCreatedStaff().getName());
            st.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            st.setInt(5, 1);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
