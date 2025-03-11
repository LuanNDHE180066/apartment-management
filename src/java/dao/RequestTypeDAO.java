/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import jdbc.DBContext;
import model.RequestType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Role;

/**
 *
 * @author thanh
 */
public class RequestTypeDAO extends DBContext {

    public List<RequestType> getAll() {
        String sql = " select * from TypeRequest";
        RoleDAO rd = new RoleDAO();
        List<RequestType> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                Role r = rd.getById(rs.getString("destination"));
                String detail = rs.getString("detail");
                RequestType rt = new RequestType(id, name, r, detail);
                list.add(rt);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public RequestType getById(String id){
        List<RequestType> list = this.getAll();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getId().equalsIgnoreCase(id)) return list.get(i);
        }
        return null;
    }
    public static void main(String[] args) {
        RequestTypeDAO dao = new RequestTypeDAO();
        System.out.println(dao.getById("R003"));
    }
}
