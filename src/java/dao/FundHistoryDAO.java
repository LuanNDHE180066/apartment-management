/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import jdbc.DBContext;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.FundHistory;
import model.InvoiceDetail;
import model.Resident;

/**
 *
 * @author PC
 */
public class FundHistoryDAO extends DBContext {

    public void insertFundHistory(List<InvoiceDetail> ivds, Resident rs, int fundid) {
        for (InvoiceDetail ivd : ivds) {
            String sql = "insert into FundHistory(value_befor,value_after,name,date,fundid)\n"
                    + "  values(?,?,?,?,?)";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setFloat(1, ivd.getAmount());
                st.setDate(4, new java.sql.Date(System.currentTimeMillis()));
                st.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    public List<FundHistory> getByFundID(int id) {
        String sql = "select * from FundHistory where fundid=? ";
        FundDAO fd = new FundDAO();
        List<FundHistory> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                FundHistory fh = new FundHistory();
                fh.setId(rs.getInt(1));
                fh.setValue_befor(rs.getFloat(2));
                fh.setValue_after(rs.getFloat(3));
                fh.setDate(rs.getString(4));
                fh.setName(rs.getString(5));
                fh.setFundid(fd.getById(rs.getString(6)));
                list.add(fh);

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) {
        FundHistoryDAO fd = new FundHistoryDAO();
        System.out.println(fd.getByFundID(1));
    }
}
