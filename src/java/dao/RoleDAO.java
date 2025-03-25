/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import jdbc.DBContext;
import model.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author thanh
 */
public class RoleDAO extends DBContext {

    public List<Role> getAll() {
        String sql = "select * from role";
        List<Role> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Role r = new Role(id, name, description);
                list.add(r);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Role getById(String id) {
        List<Role> list = this.getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }
    public Role getByIdIncludeResident(String id) {
        List<Role> list = this.getAllExcludeResident();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(id)) {
                return list.get(i);
            }
        }
        return null;
    }


    public List<Role> getAllExcludeResident() {
        String sql = "SELECT * FROM role WHERE id NOT IN ('1', '6')";
        List<Role> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Role r = new Role(id, name, description);
                list.add(r);
            }
        } catch (SQLException e) {
        }
        return list;
    }
  

}
