/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import jdbc.DBContext;
import java.sql.Date;
import jdbc.DBContext;
import model.Expenditure;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;
import model.Company;
import model.Staff;

import java.sql.SQLException;
import java.sql.SQLException;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale.Category;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ExpenseCategory;
import model.HistoryExpenditure;

/**
 *
 * @author quang
 */
public class HistoryExpenditureDAO extends DBContext {

    public List<HistoryExpenditure> getAll() {
        List<HistoryExpenditure> list = new ArrayList<>();
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        String sql = "select * from ExpenditureHistory";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int heid = rs.getInt("HistoryId");
                String id = rs.getString("expenditureid");
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
                String action = rs.getString("actiontype");
                String modifiedDate = rs.getString("modifiedDate");
                Staff modifiedBy = sdao.getById(rs.getString("ModifiedBy"));
                String createdDate = rs.getString("createdDate");
                HistoryExpenditure ne = new HistoryExpenditure(heid, id, titleE,
                        accountChiefApprove, currentAdminApprove,
                        approveddate, paymentdate, totalPrice, note, category, company, createdStaff,
                        chiefAccountant, currentAdminId, action, modifiedDate, modifiedBy, createdDate);

                list.add(ne);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<HistoryExpenditure> getViewHistoryExpenditure(String title, String startDate, String endDate, String categories) {
        List<HistoryExpenditure> list = new ArrayList<>();
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        String sql = "select * from ExpenditureHistory where HistoryId <> '0'";
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
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int heid = rs.getInt("HistoryId");
                String id = rs.getString("expenditureid");
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
                String action = rs.getString("actiontype");
                String modifiedDate = rs.getString("modifiedDate");
                Staff modifiedBy = sdao.getById(rs.getString("ModifiedBy"));
                String createdDate = rs.getString("createdDate");
                HistoryExpenditure ne = new HistoryExpenditure(heid, id, titleE,
                        accountChiefApprove, currentAdminApprove,
                        approveddate, paymentdate, totalPrice, note, category, company, createdStaff,
                        chiefAccountant, currentAdminId, action, modifiedDate, modifiedBy, createdDate);

                list.add(ne);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public boolean updateApproveStatus(String id, String role, String approve) {
        String sql = "";
        if (role.equalsIgnoreCase("3")) {
            sql = "update ExpenditureHistory set accountantChiefApprove = " + approve + " where historyId = ?";
        } else {
            sql = "update ExpenditureHistory set adminApprove = " + approve + " where historyId = ?";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HistoryExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    public boolean updateApproveStatus(String id, String role) {
//        String sql = "update ExpenditureHistory set adminApprove = ?, accountantChiefApprove = ? where historyId = ?";
//        boolean isAccountantChief = role.equalsIgnoreCase("3");
//
//        try (PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, isAccountantChief ? 0 : 1); // adminApprove
//            ps.setInt(2, isAccountantChief ? 1 : 0); // accountantChiefApprove
//            ps.setString(3, id);
//
//            return ps.executeUpdate() > 0;
//        } catch (SQLException ex) {
//            Logger.getLogger(HistoryExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return false;
//    }
    public HistoryExpenditure getExpenditureById(String hid) {
        String sql = "select * from ExpenditureHistory where historyid = '" + hid + "'";
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int heid = rs.getInt("HistoryId");
                String id = rs.getString("expenditureid");
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
                String action = rs.getString("actiontype");
                String modifiedDate = rs.getString("modifiedDate");
                Staff modifiedBy = sdao.getById(rs.getString("ModifiedBy"));
                String createdDate = rs.getString("createdDate");
                HistoryExpenditure ne = new HistoryExpenditure(heid, id, titleE,
                        accountChiefApprove, currentAdminApprove,
                        approveddate, paymentdate, totalPrice, note, category, company, createdStaff,
                        chiefAccountant, currentAdminId, action, modifiedDate, modifiedBy, createdDate);
                return ne;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //   this.action = action;
//        this.modifiedDate = modifiedDate;
//        this.modifiedBy = modifiedBy;

    public boolean addNewHistoryExpenditure(HistoryExpenditure he) {
        String sql = "INSERT INTO [dbo].[ExpenditureHistory]\n"
                + "           ([ExpenditureId]\n"
                + "           ,[ApprovedDate]\n"
                + "           ,[PaymentDate]\n"
                + "           ,[Note]\n"
                + "           ,[Cid]\n"
                + "           ,[sId]\n"
                + "           ,[ChiefAccountantId]\n"
                + "           ,[CurrentAdminId]\n"
                + "           ,[TotalPrice]\n"
                + "           ,[Title]\n"
                + "           ,[CategoryId]\n"
                + "           ,[ActionType]\n"
                + "           ,[ModifiedDate]\n"
                + "           ,[ModifiedBy]\n"
                + "           ,[accountantChiefApprove]\n"
                + "           ,[adminApprove]\n"
                + "           ,[createdDate])\n"
                + "     VALUES\n"
                + "           (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0, ?)";

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
            ps.setString(12, he.getAction());
            ps.setString(13, he.getModifiedDate());
            ps.setString(14, he.getModifiedBy().getId());
            ps.setString(15, he.getCreatedDate());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HistoryExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<HistoryExpenditure> getListWaitingExpenditure() {
        String sql = "select * from ExpenditureHIstory where (accountantChiefApprove =0  or adminApprove = 0) \n"
                + "and accountantChiefApprove >= 0 and adminApprove >= 0";
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        List<HistoryExpenditure> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int heid = rs.getInt("HistoryId");
                String id = rs.getString("expenditureid");
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
                String action = rs.getString("actiontype");
                String modifiedDate = rs.getString("modifiedDate");
                Staff modifiedBy = sdao.getById(rs.getString("ModifiedBy"));
                String createdDate = rs.getString("createdDate");
                HistoryExpenditure ne = new HistoryExpenditure(heid, id, titleE,
                        accountChiefApprove, currentAdminApprove,
                        approveddate, paymentdate, totalPrice, note, category, company, createdStaff,
                        chiefAccountant, currentAdminId, action, modifiedDate, modifiedBy, createdDate);

                list.add(ne);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<HistoryExpenditure> getListWaitingExpenditureByStaffId(String staffId) {
        String sql = "select * from ExpenditureHIstory where (accountantChiefApprove =0  or adminApprove = 0) \n"
                + "and accountantChiefApprove >= 0 and adminApprove >= 0 and (sId = ? \n"
                + "or modifiedby = ? or chiefaccountantid = ? or currentadminid = ?) ";
        ExpenseCategoryDAO dao = new ExpenseCategoryDAO();
        List<HistoryExpenditure> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, staffId);
            st.setString(2, staffId);
            st.setString(3, staffId);
            st.setString(4, staffId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int heid = rs.getInt("HistoryId");
                String id = rs.getString("expenditureid");
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
                String action = rs.getString("actiontype");
                String modifiedDate = rs.getString("modifiedDate");
                Staff modifiedBy = sdao.getById(rs.getString("ModifiedBy"));
                String createdDate = rs.getString("createdDate");
                HistoryExpenditure ne = new HistoryExpenditure(heid, id, titleE,
                        accountChiefApprove, currentAdminApprove,
                        approveddate, paymentdate, totalPrice, note, category, company, createdStaff,
                        chiefAccountant, currentAdminId, action, modifiedDate, modifiedBy, createdDate);

                list.add(ne);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean updateEidAfterInsert(HistoryExpenditure he) {
        String sql = "Update ExpenditureHistory set expenditureid = ? where historyid = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, he.getId());
            ps.setInt(2, he.getHeid());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(HistoryExpenditureDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        HistoryExpenditureDAO dao = new HistoryExpenditureDAO();
        ExpenseCategoryDAO daoEc = new ExpenseCategoryDAO();
        CompanyDAO daoC = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();

        HistoryExpenditure he = new HistoryExpenditure(
                // heid
                "e011", // id
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

        System.out.println(dao.updateEidAfterInsert(he));

    }
}
