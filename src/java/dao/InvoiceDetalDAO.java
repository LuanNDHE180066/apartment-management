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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Floor;
import model.MonthlyService;
import util.Util;

/**
 *
 * @author thanh
 */
public class InvoiceDetalDAO extends DBContext {

    public List<InvoiceDetail> getByInvoiceId(String id) {
        String sql = "select * from invoicedetail where invoiceid =?";
        ServiceDAO sd = new ServiceDAO();
        InvoiceDAO ivdao = new InvoiceDAO();
        List<InvoiceDetail> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String invoiceId = rs.getString("invoiceId");
                String serviceId = rs.getString("serviceId");
                int quantity = rs.getInt("quantity");
                float amount = rs.getFloat("amount");
                InvoiceDetail ivd = new InvoiceDetail(ivdao.getById(invoiceId), sd.getById(serviceId), quantity, amount);
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
        for (int i = 0; i < listUsingSerivce.size(); i++) {
            MonthlyService ms = listUsingSerivce.get(i);
            String sid = ms.getService().getId();
            int quantity = ms.getQuantity();
            float amount = ms.getQuantity() * (float) ms.getService().getUnitPrice();
            String sql = "insert into invoicedetail values(?,?,?,?)";
            try {
                PreparedStatement st =connection.prepareStatement(sql);
                st.setString(1, invoiceId);
                st.setString(2, sid);
                st.setInt(3, quantity);
                st.setFloat(4, amount);
                st.executeUpdate();
                
            } catch (SQLException e) {
            }
        }
    }
}
