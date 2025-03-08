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

    public List<ApartmentDetail> getApartmentDetailByOwnerid(String ownerId) {
        String sql = "select a.*,l.rId as living,  ao.rId  as owner, ao.status from AparmentOwner ao\n"
                + "left join Apartment a on a.Id = ao.aId\n"
                + "left join LivingAparment l \n"
                + "on a.Id = l.aId  where ao.status = 1 and ao.rId = ? and l.status = 1 ";
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
                Resident livingPerson = residentDAO.getById(rs.getString("living"));
                Resident owner = residentDAO.getById(rs.getString("owner"));
                ApartmentDetail ad = new ApartmentDetail(aid, numberOfPerson, floor,
                        information, rt, status, owner, livingPerson);
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
        System.out.println(dao.getApartmentDetailByOwnerid("P105").size());
    }
}
