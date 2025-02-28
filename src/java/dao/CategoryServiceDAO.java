/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import jdbc.DBContext;
import model.CategoryService;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author thanh
 */
public class CategoryServiceDAO extends DBContext {
    public List<CategoryService> getAll(){
        String sql="select * from ServiceCategory";
        List<CategoryService> list = new ArrayList<>();
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String detail  =rs.getString("detail");
                CategoryService cs= new CategoryService(id, name, detail);
                list.add(cs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
     public CategoryService getByCategoryId(String ids){
        String sql="select * from ServiceCategory where id = ?";
        try {
            PreparedStatement st= connection.prepareStatement(sql);
            st.setString(1, ids);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return new CategoryService(rs.getString("id"), rs.getString("name"), rs.getString("detail"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public int getNumberCategoryService(){
        String sql=" select count(*) as number from ServiceCategory";
        try {
            PreparedStatement st= connection.prepareCall(sql);
            ResultSet rs= st.executeQuery();
            if(rs.next()){
                return rs.getInt("number");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public void addCategoryService(String name,String note){
        String id = "SV"+ Integer.toString(getNumberCategoryService()+1);
        String sql= "insert into ServiceCategory values(?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, name);
            st.setString(3, note);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void updateCategoryService(CategoryService c){
        String sql= "update ServiceCategory set name = ? , detail = ? where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setString(2, c.getDetail());
            st.setString(3, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public CategoryService getByServiceId(String id){
     String sql="select sc.Id,sc.Name,sc.Detail from ServiceCategory sc join Service s on s.scId=sc.Id where s.Id=? ";
        try {
            PreparedStatement st=connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs=st.executeQuery();
            if(rs.next()){
            CategoryService cs=new CategoryService();
            cs.setId(rs.getString(1));
            cs.setName(rs.getString(2));
            cs.setDetail(rs.getString(3));
            return cs;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    
    }
    public static void main(String[] args) {
        CategoryServiceDAO csd=new CategoryServiceDAO();
        System.out.println(csd.getByServiceId("S1"));
    }
    
}
