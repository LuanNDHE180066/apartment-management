/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;
import java.util.List;
import jdbc.DBContext;
import model.CategoryService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
/**
 *
 * @author thanh
 */
public class CategoryServiceValidation extends DBContext{
    public boolean isExistedName(String name){
        String sql =" select * from ServiceCategory where name =?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean isExistedNameExceptSeft(String name,String id){
        String sql =" select * from ServiceCategory where name =? and id <> ?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
