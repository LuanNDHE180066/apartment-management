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
        List<MonthlyService> listUsingService = md.getByApartmentId(aid);

        String sql = "INSERT INTO invoicedetail (invoiceId, serviceName, PriceUnit, quantity, date, amount) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) { // Mở 1 PreparedStatement duy nhất
            for (MonthlyService ms : listUsingService) {
                Service sv = ms.getService();
                int quantity = ms.getQuantity();
                float unitprice = (float) sv.getUnitPrice();
                float amount;
                if(sv.getName().equals("Cung cấp điện")){
                    amount = calculateElectricityBill(quantity);
                }
                else{
                    amount = quantity * unitprice;
                }
                LocalDateTime time = LocalDateTime.now();

                st.setString(1, invoiceId);
                st.setString(2, sv.getName());
                st.setFloat(3, unitprice);
                st.setInt(4, quantity);
                st.setTimestamp(5, Timestamp.valueOf(time));
                st.setFloat(6, amount);
                st.addBatch(); // Thêm vào batch
            }

            st.executeBatch(); // Chạy batch một lần duy nhất
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public  int calculateElectricityBill(int consumption) {
        int totalCost = 0;
        int remainingConsumption = consumption;

        // Giá điện theo bậc (VNĐ/kWh) và giới hạn mỗi bậc
        int[] thresholds = {50, 50, 100, 100, 100}; // Giới hạn từng bậc
        double[] rates = {1806,  1866, 2167, 2729 , 3050, 3151}; // Giá từng bậc

        for (int i = 0; i < thresholds.length; i++) {
            if (remainingConsumption <= 0) break;

            int used = Math.min(remainingConsumption, thresholds[i]);
            totalCost += used * rates[i];
            remainingConsumption -= used;
        }

        // Nếu vượt quá bậc 5 thì tính theo bậc 6
        if (remainingConsumption > 0) {
            totalCost += remainingConsumption * rates[5];
        }

        return totalCost;
    }

}
