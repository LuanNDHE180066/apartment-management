/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;
import model.Company;
import model.Expenditure;
import model.Staff;
import util.Util;

import java.sql.SQLException;
import java.sql.SQLException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ExpenseCategory;
import model.HistoryExpenditure;
import util.Util;

public class ExpenditureDAO extends DBContext {

//    public boolean updateExpenditure(Expenditure e) {
//        String sql = "UPDATE [dbo].[Expenditure]\n"
//                + "   SET[amount] = ?\n"
//                + "      ,[Price] =?\n"
//                + "      ,[Approveddate] = ?\n"
//                + "      ,[Paymentdate] = ?\n"
//                + "      ,[note] = ?\n"
//                + "      ,[category] = ?\n"
//                + "      ,[cid] = ?\n"
//                + "      ,[sId] = ?\n"
//                + " WHERE id=?";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, e.getAmount());
//            st.setFloat(2, e.getPrice());
//            st.setString(3, e.getApproveddate());
//            st.setString(4, e.getPaymentdate());
//            st.setString(5, e.getNote());
//            st.setString(6, e.getCategory());
//            st.setString(7, e.getCid().getId());
//            st.setString(8, e.getSid().getId());
//            st.setString(9, e.getId());
//            st.executeUpdate();
//            return true;
//
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//        return false;
//    }
//    public boolean updateExpenditure(Expenditure e){
//        
//    }  
    public List<Expenditure> getAll() {
        List<Expenditure> list = new ArrayList<>();
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        String sql = "select * from Expenditure";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                float totalPrice = rs.getFloat("totalPrice");
                int accountChiefApprove = rs.getInt("accountantChiefApprove");
                int currentAdminApprove = rs.getInt("adminApprove");
                String approveddate = rs.getString("approveddate");
                String paymentdate = rs.getString("paymentdate");
                String note = rs.getString("note");
                ExpenseCategory category = dao.getExpenseCategoryById(rs.getInt("categoryid"));
                CompanyDAO cdao = new CompanyDAO();
                StaffDAO sdao = new StaffDAO();
                Company company = cdao.getById(rs.getString("cid"));
                Staff createdStaff = sdao.getById(rs.getString("sid"));
                Staff chiefAccountant = sdao.getById(rs.getString("chiefAccountantId"));
                Staff currentAdminId = sdao.getById(rs.getString("currentAdminId"));
                String createdDate = rs.getString("createdDate");
                Expenditure ne = new Expenditure(id, title,
                        accountChiefApprove,
                        currentAdminApprove, approveddate, paymentdate,
                        totalPrice, note, category, company, createdStaff, chiefAccountant,
                        currentAdminId, createdDate);
                list.add(ne);
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<String> getListCategory(){
        List<String> list = new ArrayList<>();
        String sql = "select distinct category from Expenditure";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {     
                String category = rs.getString("category");
                list.add(category);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public boolean addExpenditure(HistoryExpenditure he) {
        String sql = "INSERT INTO [dbo].[Expenditure]\n"
                + "           ([Id]\n"
                + "           ,[Approveddate]\n"
                + "           ,[Paymentdate]\n"
                + "           ,[note]\n"
                + "           ,[cid]\n"
                + "           ,[sId]\n"
                + "           ,[chiefAccountantId]\n"
                + "           ,[currentAdminId]\n"
                + "           ,[totalPrice]\n"
                + "           ,[title]\n"
                + "           ,[CategoryId]\n"
                + "           ,[accountantChiefApprove]\n"
                + "           ,[adminApprove]\n"
                + "           ,[createdDate])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, he.getId());
            ps.setString(2, he.getApproveddate());
            ps.setString(3, he.getPaymentdate());
            ps.setString(4, he.getNote());
            ps.setString(5, he.getCompany().getId());
            ps.setString(6, he.getCreatedStaff().getId());
            ps.setString(7, he.getChiefAccountantId().getId());
            ps.setString(8, he.getCurrentAdmin().getId());
            ps.setFloat(9, he.getTotalPrice());
            ps.setString(10, he.getTitle());
            ps.setInt(11, he.getCategory().getId());
            ps.setInt(12, he.getChiefAccountantApproveStatus());
            ps.setInt(13, he.getCurrentAdminApproveStatus());
            ps.setString(14, he.getCreatedDate());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateExpenditure(HistoryExpenditure he) {
        String sql = "UPDATE dbo.Expenditure SET Approveddate = ?, Paymentdate = ?, "
                + "note = ?, cid = ?, totalPrice = ?, title = ?, "
                + "CategoryId = ?, accountantChiefApprove = ?, "
                + "adminApprove = ? where id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, he.getApproveddate());
            ps.setString(2, he.getPaymentdate());
            ps.setString(3, he.getNote());
            ps.setString(4, he.getCompany().getId());
            ps.setFloat(5, he.getTotalPrice());
            ps.setString(6, he.getTitle());
            ps.setInt(7, he.getCategory().getId());
            ps.setInt(8, 1);
            ps.setInt(9, 1);
            ps.setString(10, he.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean checkExistId(String id) {
        String sql = "select * from expenditure where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getLargestId() {
        List<Integer> list = new ArrayList<>();
        String sql = "select id from Expenditure";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Util u = new Util();
            while (rs.next()) {
                list.add(u.getNumberFromText(rs.getString(1)));
            }
            if (list.size() == 0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Collections.max(list) + 1;
    }

    public String generateExpenditureId() {
        int newNumberId = this.getLargestId();
        String newId = "";
        if (newNumberId < 10) {
            return newId += "e00" + newNumberId;
        }
        return newId += "e0" + newNumberId;
    }

    public List<Expenditure> getViewExpenditure(String title, String startDate, String endDate, String categories) {
        List<Expenditure> list = new ArrayList<>();
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        String sql = "select * from Expenditure where id <> '0'";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (title != "") {
            sql += " and title collate Latin1_General_CI_AI like N'%" + title + "%'";
        }
        if (startDate != "") {
            Date date = Date.valueOf(startDate);
            String formatDate = format.format(date);
            sql += " and approveddate >= '" + formatDate + "'";
        }

        if (endDate != "") {
            Date date = Date.valueOf(endDate);
            String formatDate = format.format(date);
            sql += " and approveddate <= '" + formatDate + "'";
        }
        if (categories != "") {
            sql += " and categoryid = " + categories;
        } 
        sql += "   order by createdDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String titleE = rs.getString("title");
                float totalPrice = rs.getFloat("totalPrice");
                int accountChiefApprove = rs.getInt("accountantChiefApprove");
                int currentAdminApprove = rs.getInt("adminApprove");
                String approveddate = rs.getString("approveddate");
                String paymentdate = rs.getString("paymentdate");
                String note = rs.getString("note");
                ExpenseCategory category = dao.getExpenseCategoryById(rs.getInt("categoryid"));
                CompanyDAO cdao = new CompanyDAO();
                StaffDAO sdao = new StaffDAO();
                Company company = cdao.getById(rs.getString("cid"));
                Staff createdStaff = sdao.getById(rs.getString("sid"));
                Staff chiefAccountant = sdao.getById(rs.getString("chiefAccountantId"));
                Staff currentAdminId = sdao.getById(rs.getString("currentAdminId"));
                String createdDate = rs.getString("createdDate");
                Expenditure ne = new Expenditure(id, titleE,
                        accountChiefApprove,
                        currentAdminApprove, approveddate, paymentdate,
                        totalPrice, note, category, company, createdStaff, chiefAccountant,
                        currentAdminId, createdDate);
                list.add(ne);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Expenditure getExpenditureById(String id) {
        String sql = "select * from Expenditure where id = '" + id + "'";
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String idE = rs.getString("id");
                String titleE = rs.getString("title");
                float totalPrice = rs.getFloat("totalPrice");
                int accountChiefApprove = rs.getInt("accountantChiefApprove");
                int currentAdminApprove = rs.getInt("adminApprove");
                String approveddate = rs.getString("approveddate");
                String paymentdate = rs.getString("paymentdate");
                String note = rs.getString("note");
                ExpenseCategory category = dao.getExpenseCategoryById(rs.getInt("categoryid"));
                CompanyDAO cdao = new CompanyDAO();
                StaffDAO sdao = new StaffDAO();
                Company company = cdao.getById(rs.getString("cid"));
                Staff createdStaff = sdao.getById(rs.getString("sid"));
                Staff chiefAccountant = sdao.getById(rs.getString("chiefAccountantId"));
                Staff currentAdminId = sdao.getById(rs.getString("currentAdminId"));
                String createdDate = rs.getString("createdDate");
                Expenditure ne = new Expenditure(id, titleE,
                        accountChiefApprove,
                        currentAdminApprove, approveddate, paymentdate,
                        totalPrice, note, category, company, createdStaff, chiefAccountant,
                        currentAdminId, createdDate);
                return ne;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        ExpenditureDAO dao = new ExpenditureDAO();
        CompanyDAO cp = new CompanyDAO();
        HistoryExpenditureDAO daoHe = new HistoryExpenditureDAO();
        ExpenseCategoryDAO daoEc = new ExpenseCategoryDAO();
        CompanyDAO daoC = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();

        HistoryExpenditure he = new HistoryExpenditure(
                // heid
                "e004", // id
                "Travel Expenses", // title
                1, // chiefAccountantApproveStatus
                1, // currentAdminApproveStatus
                "2023-01-01", // approveddate
                "2023-01-10", // paymentdate
                2500.0f, // totalPrice
                "Business trip to Paris", // note
                daoEc.getExpenseCategoryById(1), // category
                daoC.getById("C002"), // company
                daoSt.getById("S1014"), // createdStaff
                daoSt.getById("S1014"), // chiefAccountantId
                daoSt.getById("S1017"), // currentAdmin
                "Insert", // action
                "2023-01-01", // modifiedDate
                daoSt.getById("S1014"), // modifiedBy
                "2023-01-01" // createdDate
        );

        System.out.println(dao.updateExpenditure(he));
    }
}
