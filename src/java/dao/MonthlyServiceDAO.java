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
    public static void main(String[] args) {
        MonthlyServiceDAO dao = new MonthlyServiceDAO();
        System.out.println(dao.getByApartmentId("A01_01").size());
    }
}
