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

/**
 *
 * @author Lenovo
 */
public class ApartmentDAO extends DBContext {

    public List<Apartment> getAll() {
        String sql = "select * from Apartment";
        List<Apartment> list = new ArrayList<>();
        RoomTypeDAO rtd = new RoomTypeDAO();
        FloorDAO fd = new FloorDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int noPerson = rs.getInt("Noperson");
                Floor floor = fd.getByNumber(rs.getInt("floor"));
                String information = rs.getString("information");
                RoomType rt = rtd.getRoomTypeById(rs.getString("rtId"));
                int status = rs.getInt("status");
                Apartment a = new Apartment(id, noPerson, floor, information, rt, status);
                list.add(a);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Apartment> getViewApartment(String apartmentNumber,String floor, String type, String status) {
        String sql = "select * from Apartment where 1 = 1";
        if (!floor.equals("") || !type.equals("") || !status.equals("")) {
            sql += " where id <> 'A00_00' ";
        }
        if (!floor.equals("")) {
            sql += "and floor = " + floor;
        }
        if (!type.equals("")) {
            sql += "and rtid = " + "'" + type + "'";
        }
        if (!status.equals("")) {
            sql += "and status = " + status;
        }
        if(apartmentNumber != ""){
            sql += " and id like '%" + apartmentNumber+"%'";
        }
        List<Apartment> list = new ArrayList<>();
        FloorDAO fdao = new FloorDAO();
        RoomTypeDAO rdao = new RoomTypeDAO();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Apartment(rs.getString("id"), rs.getInt("noperson"),
                        fdao.getByNumber(rs.getInt("floor")), rs.getString("information"),
                        rdao.getRoomTypeById(rs.getString("rtid")), rs.getInt("status")));
            }
        } catch (SQLException e) {
            System.out.println(e + "need repair 1");
        }
        return list;
    }

    public void deleteApartment(String id) {
        String sql = "delete from Apartment where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e + "need repair 1");
        }
    }

    public boolean getApartmentByRoomType(int id) {
        String sql = "select * from RoomType rt join Apartment a on a.rtId=rt.Id where rt.Id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;

    }

    public Apartment getById(String id) {
        String sql = "select * from Apartment where id=?";
        List<Apartment> list = new ArrayList<>();
        RoomTypeDAO rtd = new RoomTypeDAO();
        FloorDAO fd = new FloorDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int noPerson = rs.getInt("Noperson");
                Floor floor = fd.getByNumber(rs.getInt("floor"));
                String information = rs.getString("information");
                RoomType rt = rtd.getRoomTypeById(rs.getString("rtId"));
                int status = rs.getInt("status");
                Apartment a = new Apartment(id, noPerson, floor, information, rt, status);
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean insertNewApartment(Apartment newApartment) {
        String sql = "INSERT INTO [dbo].[Apartment]\n"
                + "           ([Id]\n"
                + "           ,[NoPerson]\n"
                + "           ,[floor]\n"
                + "           ,[information]\n"
                + "           ,[rtId]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newApartment.getId());
            st.setInt(2, newApartment.getNumberOfPerson());
            st.setInt(3, newApartment.getFloor().getNumber());
            st.setString(4, newApartment.getInfor());
            st.setString(5, newApartment.getRoomtype().getId());
            st.setInt(6, newApartment.getStatus());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean checkExistAptNumber(String number) {
        String sql = "Select * from Apartment where Id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, number);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean updateApartment(Apartment a) {
        String sql = "update Apartment set information = ?, status = ?, rtId = ?  where Id = ?";
        try {
            PreparedStatement ps = connection.prepareCall(sql);
            ps.setString(1, a.getInfor());
            ps.setInt(2, a.getStatus());
            ps.setString(3, a.getRoomtype().getId());
            ps.setString(4, a.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Apartment> GetAllApartmentfromOwnerAndLivingByRId(String rid){
        String sql = " select aid from AparmentOwner where rid = '"+rid+"'\n" +
        "	union \n" +
        "  select aid from LivingAparment where rid ='"+rid+"'";
        List<Apartment> aparments = new ArrayList<>();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                aparments.add(this.getById(rs.getString("aid")));
            }            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return aparments;
    }
    
    
    public List<Apartment> GetApartmentisLivingByResidentIDisRepresent(String rid){
        String sql = " select distinct Apartment.* from LivingAparment "
                + "join Apartment on LivingAparment.aId = Apartment.Id "
                + "where LivingAparment.status = 1 "
                + "and LivingAparment.isRepresent = 1 "
                + "and Apartment.status = 1 and LivingAparment.rId = '"+rid+"'";
        List<Apartment> aparments = new ArrayList<>();
        FloorDAO fd = new FloorDAO();
        RoomTypeDAO rtd = new RoomTypeDAO();
        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                int noPerson = rs.getInt("Noperson");
                Floor floor = fd.getByNumber(rs.getInt("floor"));
                String information = rs.getString("information");
                RoomType rt = rtd.getRoomTypeById(rs.getString("rtId"));
                int status = rs.getInt("status");
                Apartment a = new Apartment(id, noPerson, floor, information, rt, status);
                aparments.add(a);
            }            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return aparments;
    }
    
    public List<Apartment> GetREApartment(String reId) {
        String sql = "SELECT A.*, RT.*\n"
                + "FROM AparmentOwner AO\n"
                + "JOIN Apartment A ON AO.aId = A.Id\n"
                + "JOIN RoomType RT ON A.rtId = RT.Id\n"
                + "WHERE AO.rId = ? ";

        RoomTypeDAO rt = new RoomTypeDAO();
        List<Apartment> list = new ArrayList<>();

        try {
            PreparedStatement pre = connection.prepareStatement(sql);
            pre.setString(1, reId);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                System.out.println("Number Of Person: " + rs.getInt("NoPerson"));
                System.out.println("Floor: " + rs.getInt("floor"));
                System.out.println("Information: " + rs.getString("information"));

                RoomType roomtype = rt.getRoomTypeByApartmentId(rs.getString("id"));

                Floor floor = new Floor();
                floor.setNumber(rs.getInt("floor"));

                Apartment apartment = new Apartment(rs.getString("Id"),
                        rs.getInt("NoPerson"),
                        floor,
                        rs.getString("information"), roomtype
                );
                apartment.setRoomtype(roomtype);
                list.add(apartment);
            }

            rs.close();
            pre.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateApartmentInforamtion(Apartment a) {
        String sql = "update Apartment set NoPerson =?, information = ?  where id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, a.getNumberOfPerson());
            ps.setString(2, a.getInfor());
            ps.setString(3, a.getId());
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;

    }

    public void updateRoomType(int oldRoomTypeId, int newRoomTypeId) {
        String sql = "update Apartment set rtId = ? where rtId = ?";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, newRoomTypeId);
            ps.setInt(2, oldRoomTypeId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ApartmentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        ApartmentDAO dao = new ApartmentDAO();
        RoomTypeDAO daoRT = new RoomTypeDAO();
        ResidentDAO daoR = new ResidentDAO();
////        RoomType rt = daoRT.getRoomTypeById("4");
//        Apartment a = dao.getById("A001");
//        a.setRoomtype(rt);
//        a.setInfor("Abc");
//        dao.updateApartment(a);
//Apartment a=new Apartment();
//a.setId("A01_01");
//a.setNumberOfPerson(4);
//
////        System.out.println(dao.updatenoperson(a));
//        System.out.println(dao.getById("A10_04").getFloor().getSquare());
//        System.out.println(dao.getAll().size());
//        
System.out.println(dao.getViewApartment("a001", "", "", ""));

    }
}
