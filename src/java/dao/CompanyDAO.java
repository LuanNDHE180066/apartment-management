/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;
import model.Company;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;

/**
 *
 * @author thanh
 */
public class CompanyDAO extends DBContext {

    public List<Company> getAll() {
        List<Company> list = new ArrayList<>();
        String sql = "select * from Company";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String contactphone = rs.getString("contactphone");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String contactEmail = rs.getString("contactemail");
                String website = rs.getString("website");
                String taxCode = rs.getString("taxcode");
                String bank = rs.getString("bank");
                String description = rs.getString("description");
                String address = rs.getString("address");
                int status=rs.getInt("status");
                Company cp = new Company(id, name, phone, contactphone, fax, email, contactEmail, website, taxCode, bank, description, address,status);
                list.add(cp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Company getById(String idc) {
        String sql="select * from company where id=?";
        try {
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setString(1, idc);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String contactphone = rs.getString("contactphone");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String contactEmail = rs.getString("contactemail");
                String website = rs.getString("website");
                String taxCode = rs.getString("taxcode");
                String bank = rs.getString("bank");
                String description = rs.getString("description");
                String address = rs.getString("address");
                int status=rs.getInt("status");
                Company cp = new Company(id, name, phone, contactphone, fax, email, contactEmail, website, taxCode, bank, description, address,status);
                return cp;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Company> searchCompaniesbyName(String names) {
        List<Company> list = new ArrayList<>();
        String sql = "select * from Company where name like '%"+names+"%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String contactphone = rs.getString("contactphone");
                String fax = rs.getString("fax");
                String email = rs.getString("email");
                String contactEmail = rs.getString("contactemail");
                String website = rs.getString("website");
                String taxCode = rs.getString("taxcode");
                String bank = rs.getString("bank");
                String description = rs.getString("description");
                String address = rs.getString("address");
                int status=rs.getInt("status");
                Company cp = new Company(id, name, phone, contactphone, fax, email, contactEmail, website, taxCode, bank, description, address,status);
                list.add(cp);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Company> getPageByNumber(List<Company> list, int page, int number) {
        List<Company> listpage = new ArrayList<>();
        int start = number * (page - 1);
        int end = number * page - 1;
        for (int i = start; i <= end; i++) {
            listpage.add(list.get(i));
            if (i == list.size() - 1) {
                break;
            }
        }
        return listpage;
    }

//    id, name, phone, contactphone, fax, email, website, taxCode, bank, description, address
    public boolean insertNewCompany(Company com) {
        String sql = "insert into Company(id,name,phone,contactphone,fax,email,contactemail,website,taxcode,bank,description,address,status)\n"
                + "Values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        List<Company> list = this.getAll();
        Util u = new Util();
        String newId = "";
        if (list.isEmpty()) {
            newId = "C001";
        } else {
            int lastIdNum = u.getNumberFromText(list.get(list.size() - 1).getId()) + 1;
            if (lastIdNum <= 9) {
                newId = "C00" + lastIdNum;
            } else if (lastIdNum >= 10 && lastIdNum <= 99) {
                newId = "C0" + lastIdNum;
            } else {
                newId = "C" + lastIdNum;
            }
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, newId);
            ps.setString(2, com.getName());
            ps.setString(3, com.getPhone());
            ps.setString(4, com.getContactPhone());
            ps.setString(5, com.getFax());
            ps.setString(6, com.getEmail());
            ps.setString(7, com.getContactemail());
            ps.setString(8, com.getWebsite());
            ps.setString(9, com.getTaxCode());
            ps.setString(10, com.getBank());
            ps.setString(11, com.getdescription());
            ps.setString(12, com.getAddress());
            ps.setInt(13, 1);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CompanyDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCompany(Company company) {
        String sql = "update company set name=?, phone=?,contactphone=?,fax=?,email=?,contactemail=?,"
                + "website=?,taxcode=?,bank=?,description=?,address=?,status=?"
                + " where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, company.getName());
            st.setString(2, company.getPhone());
            st.setString(3, company.getContactPhone());
            st.setString(4, company.getFax());
            st.setString(5, company.getEmail());
            st.setString(6, company.getContactemail());
            st.setString(7, company.getWebsite());
            st.setString(8, company.getTaxCode());
            st.setString(9, company.getBank());
            st.setString(10, company.getdescription());
            st.setString(11, company.getAddress());
            st.setInt(12, company.getStatus());
            st.setString(13, company.getId());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public Company getByServiceId(String id) {
        String sql = "select c.id,c.name,c.phone,c.contactphone,c.fax,c.email,c.contactemail,"
                + "c.website,c.taxcode,c.bank,c.Description,c.address,c.status "
                + "from Company c join Service s on s.cId=c.id where s.Id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Company c = new Company();
                c.setId(rs.getString(1));
                c.setName(rs.getString(2));
                c.setPhone(rs.getString(3));
                c.setContactPhone(rs.getString(4));
                c.setFax(rs.getString(5));
                c.setEmail(rs.getString(6));
                c.setContactemail(rs.getString(7));
                c.setWebsite(rs.getString(8));
                c.setTaxCode(rs.getString(9));
                c.setBank(rs.getString(10));
                c.setdescription(rs.getString(11));
                c.setAddress(rs.getString(12));
                c.setStatus(rs.getInt(13));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;

    }

    public static void main(String[] args) {
        CompanyDAO dao = new CompanyDAO();
//        Company cp = new Company("324ss", "0911346700", "0912312470", "1423223102", "caon4cass@gmail.com", "caon2s4ca@gmail.com", "xx",
//                "c4scccwc",
//                "12143813", "123", "agfffsf",1);
        System.out.println(dao.getById("C006"));
    }
}
