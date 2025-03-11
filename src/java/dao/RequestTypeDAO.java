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
import java.sql.SQLException;
import java.util.ArrayList;
import model.Role;
import util.Util;

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

    public List<RequestType> getRequestByFilter(String roleFilter) {
        String sql = " select * from TypeRequest where 1=1 ";
        if (!roleFilter.isBlank()) {
            System.out.println("black" + roleFilter);
            sql += " and destination ='" + roleFilter + "'";
        }
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

    public boolean addRequestType(String name, String detail, String destination) {
        String sql = "insert into TypeRequest(id,name,detail,destination) values(?,?,?,?)";
        Util u = new Util();
        String newId = "";
        List<RequestType> list = this.getAll();
        if (list.isEmpty()) {
            newId = "R001";
        } else {
            int lastIdNum = u.getNumberFromText(list.get(list.size() - 1).getId()) + 1;
            if (lastIdNum <= 9) {
                newId = "R00" + lastIdNum;
            } else if (lastIdNum >= 10 && lastIdNum <= 99) {
                newId = "R0" + lastIdNum;
            } else {
                newId = "R" + lastIdNum;
            }
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newId);
            st.setString(2, name);
            st.setString(3, detail);
            st.setString(4, destination);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean updateRequestType(String id, String name, String detail, String destination) {
        String sql = "update TypeRequest set name=?,detail=?,destination=? where id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, detail);
            st.setString(3, destination);
            st.setString(4, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean checkExistedName(String name) {
        String sql = "select name from TypeRequest where name =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkExistedNameExceptSeft(String id, String name) {
        String sql = "select name from TypeRequest where name =? and id != ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public RequestType getById(String id) {
        List<RequestType> list = this.getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equalsIgnoreCase(id)) {
                return list.get(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        RequestTypeDAO dao = new RequestTypeDAO();
        //System.out.println(dao.getById("R003"));
        System.out.println("" + dao.getRequestByFilter(""));
        System.out.println("" + dao.addRequestType("Tao bi sao y", "hahaha", "4"));
    }
}
