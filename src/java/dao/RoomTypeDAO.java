/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jdbc.DBContext;
import java.util.List;
import model.Resident;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Account;
import model.Role;
import util.Util;
import model.Account;
import model.Role;

import model.Role;
import model.RoomType;

/**
 *
 * @author quang
 */
public class RoomTypeDAO extends DBContext {

    public String generateID() {
        String sql = "select id from roomtype";
        List<Integer> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(Integer.parseInt(rs.getString(1)));
            }
            return (Collections.max(list) + 1) + "";
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<RoomType> getAll() {
        String sql = "select * from Roomtype";
        RoomTypeDAO dao = new RoomTypeDAO();
        List<RoomType> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoomType(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("maxperson"),
                        rs.getInt("bedroom"),
                        rs.getInt("livingroom"),
                        rs.getInt("bathroom"),
                        rs.getInt("balcony"),
                        rs.getFloat("square")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<RoomType> filterRoomType(String searchName) {
        String sql = "select * from Roomtype where 1 = 1 ";
        RoomTypeDAO dao = new RoomTypeDAO();
        List<RoomType> list = new ArrayList<>();
        if (searchName != "") {
            sql += "  and Name like '%" + searchName + "%'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RoomType(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("maxperson"),
                        rs.getInt("bedroom"),
                        rs.getInt("livingroom"),
                        rs.getInt("bathroom"),
                        rs.getInt("balcony"),
                        rs.getFloat("square")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public RoomType getRoomTypeById(String id) {
        String sql = "select * from Roomtype where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new RoomType(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("maxperson"),
                        rs.getInt("bedroom"),
                        rs.getInt("livingroom"),
                        rs.getInt("bathroom"),
                        rs.getInt("balcony"),
                        rs.getFloat("square"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkExistNameRoomTypeExceptSeft(String name, String id) {
        String sql = "select * from RoomType where name = ? and id <> ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistNameRoomType(String name) {
        String sql = "select * from RoomType where name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateRoomType(RoomType r) {
        String sql = "update RoomType set Name = ? , maxperson = ? , Square = ? , bedroom = ? , livingroom = ? , bathroom = ? , balcony = ? "
                + "where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, r.getName());
            ps.setInt(2, r.getLimitPerson());
            ps.setFloat(3, r.getSquare());
            ps.setInt(4, r.getBedroom());
            ps.setInt(5, r.getLivingRoom());
            ps.setInt(6, r.getBathRoom());
            ps.setInt(7, r.getBalcony());
            ps.setString(8, r.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertRoomType(RoomType r) {
        String sql = "insert into RoomType (id, name,maxperson, square, bedroom, livingroom, bathroom, balcony)\n"
                + "values(?,?,?,?,?,?,?,?)";
        RoomTypeDAO dao = new RoomTypeDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, dao.generateID());
            ps.setString(2, r.getName());
            ps.setInt(3, r.getLimitPerson());
            ps.setFloat(4, r.getSquare());
            ps.setInt(5, r.getBedroom());
            ps.setInt(6, r.getLivingRoom());
            ps.setInt(7, r.getBathRoom());
            ps.setInt(8, r.getBalcony());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteRoomType(int id) {
        String sql = "DELETE FROM [dbo].[RoomType]\n"
                + "      WHERE Id=?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public RoomType getRoomTypeByApartmentId(String apartmentId) {
        String sql = "SELECT rt.* "
                + "FROM RoomType rt "
                + "JOIN Apartment ap ON ap.rtId = rt.Id "
                + "WHERE ap.Id = ?";

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, apartmentId);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                return new RoomType(rs.getString("id"),
                        rs.getString("name"),
                        rs.getInt("maxperson"),
                        rs.getInt("bedroom"),
                        rs.getInt("livingroom"),
                        rs.getInt("bathroom"),
                        rs.getInt("balcony"),
                        rs.getFloat("square"));

            }

            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        RoomTypeDAO dao = new RoomTypeDAO();
        RoomType r = new RoomType("1", "Deluxe Suite", 4, 2, 1, 2, 1, 500.0f);
        System.out.println(dao.filterRoomType("st").size());

    }
}
