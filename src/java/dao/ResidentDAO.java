/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Resident;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.DBContext;
import model.Person;
/**
 *
 * @author thanh
 */
public class ResidentDAO extends DBContext{
    public boolean checkConnection(){
        return connection==null;
    }
    public List<Resident> getAll(){
        String sql ="select  * from resident";
        List<Resident> list = new ArrayList<>();
        PersonDAO pd = new PersonDAO();
        String test = "xin chao";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String bank = rs.getString("bank");
                String subemail = rs.getString("subemail");
                String status = rs.getString("status");
                Person p = pd.getById(rs.getString("pId"));
                Resident r= new Resident(p, bank, subemail, status);
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResidentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public Resident getById(String pid){
        List<Resident> all = this.getAll();
        for (int i = 0; i < all.size(); i++) {
            if(all.get(i).getpId().getId().equals(pid)) return all.get(i);
        }
        return null;
    }
}
