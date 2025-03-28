/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Apartment;
import model.Floor;
import model.RoomType;
import jdbc.DBContext;
import model.ApartmentDetail;
import model.Resident;

/**
 *
 * @author quang
 */
public class ApartmentDetailDAO extends DBContext {

    private ResidentDAO residentDAO = new ResidentDAO();
    private RoomTypeDAO roomtypeDAO = new RoomTypeDAO();
    private FloorDAO floorDAO = new FloorDAO();

    public List<ApartmentDetail> getApartmentDetailByOwnerid(String ownerId, String floorN, String rtId) {
        String sql = "select a.*, ao.rid as owner, ao.status \n"
                + "from AparmentOwner ao\n"
                + "left join apartment a on a.id = ao.aid\n"
                + "where ao.status = 1 and ao.rid = ?";
        List<ApartmentDetail> list = new ArrayList<>();
        if (floorN != "") {
            sql += " and a.rtId = " + floorN;
        }

        if (rtId != "") {
            sql += "  and a.rtId = " + rtId;
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid = rs.getString("id");
                int numberOfPerson = rs.getInt("Noperson");
                Floor floor = floorDAO.getByNumber(rs.getInt("floor"));
                String information = rs.getString("information");
                RoomType rt = roomtypeDAO.getRoomTypeById(rs.getString("rtid"));
                int status = rs.getInt("status");
                Resident owner = residentDAO.getById(rs.getString("owner"));
                ApartmentDetail ad = new ApartmentDetail(aid, numberOfPerson, floor,
                        information, rt, status, owner, null);
                list.add(ad);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ApartmentDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ApartmentDetail> getApartmentDetailByOwnerid(String ownerId) {
        String sql = "select a.*, ao.rid as owner, ao.status \n"
                + "from AparmentOwner ao\n"
                + "left join apartment a on a.id = ao.aid\n"
                + "where ao.status = 1 and ao.rid = ?";
        List<ApartmentDetail> list = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String aid = rs.getString("id");
                int numberOfPerson = rs.getInt("Noperson");
                Floor floor = floorDAO.getByNumber(rs.getInt("floor"));
                String information = rs.getString("information");
                RoomType rt = roomtypeDAO.getRoomTypeById(rs.getString("rtid"));
                int status = rs.getInt("status");
               
                Resident owner = residentDAO.getById(rs.getString("owner"));
                ApartmentDetail ad = new ApartmentDetail(aid, numberOfPerson, floor,
                        information, rt, status, owner, null);
                list.add(ad);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ApartmentDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ApartmentDetailDAO dao = new ApartmentDetailDAO();
        System.out.println(dao.getApartmentDetailByOwnerid("P110").size());
    }
}
