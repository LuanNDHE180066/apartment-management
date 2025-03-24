/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import jdbc.DBContext;
import model.InvoiceDetail;
import dto.response.FloorResponseDTO;
import jdbc.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Floor;
import model.MonthlyService;
import model.Service;
import util.Util;

/**
 *
 * @author thanh
 */
public class InvoiceDetalDAO extends DBContext {

    public List<InvoiceDetail> getByInvoiceId(String id) {
        String sql = "select * from invoicedetail where invoiceid =?";
        InvoiceDAO ivdao = new InvoiceDAO();
        List<InvoiceDetail> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String invoiceId = rs.getString("invoiceId");
                String serviceName = rs.getString("servicename");
                float unitprice = rs.getFloat("priceunit");
                int quantity = rs.getInt("quantity");
                LocalDateTime time = rs.getTimestamp("date").toLocalDateTime();
                float amount = rs.getFloat("amount");
                InvoiceDetail ivd = new InvoiceDetail(ivdao.getById(invoiceId), serviceName, unitprice, quantity, time, amount);
                list.add(ivd);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void addInvoiceDetailByApartmentIdAndInvoiceId(String invoiceId, String aid) {
        MonthlyServiceDAO md = new MonthlyServiceDAO();
        List<MonthlyService> listUsingSerivce = md.getByApartmentId(aid);

        String sql = "insert into invoicedetail values(?,?,?,?,?,?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) { // Mở 1 PreparedStatement duy nhất
            for (MonthlyService ms : listUsingSerivce) {
                Service sv = ms.getService();
                int quantity = ms.getQuantity();
                float amount = quantity * (float) sv.getUnitPrice();
                float unitprice = (float) sv.getUnitPrice();
                LocalDateTime time = LocalDateTime.now();

                st.setString(1, invoiceId);
                st.setString(2, sv.getName());
                st.setFloat(3, unitprice);
                st.setInt(4, quantity);
                st.setTimestamp(5, Timestamp.valueOf(time));
                st.setFloat(6, amount);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
