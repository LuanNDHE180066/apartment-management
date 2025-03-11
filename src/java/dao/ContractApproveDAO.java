package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jdbc.DBContext;
import model.ContractApprove;
import model.Contract;
import model.Staff;

/**
 *
 * @author pc
 */
public class ContractApproveDAO extends DBContext {

    public List<ContractApprove> getAll() {
        ContractDAO ctd = new ContractDAO();
        StaffDAO daoSt = new StaffDAO();
        List<ContractApprove> list = new ArrayList<>();
        String sql = "SELECT * FROM ContractApproval";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ContractApprove(
                        rs.getInt("id"),
                        ctd.getById(rs.getString("contractId")),
                        rs.getObject("adminApproval") != null ? rs.getInt("adminApproval") : null,
                        rs.getObject("accountantApproval") != null ? rs.getInt("accountantApproval") : null,
                        rs.getInt("status"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        daoSt.getById(rs.getString("adminId")), // Lấy thông tin Admin
                        daoSt.getById(rs.getString("accountantId")) // Lấy thông tin Accountant
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ContractApprove getById(String contractId) {
        ContractDAO ctd = new ContractDAO();
        StaffDAO daoSt = new StaffDAO();
        String sql = "SELECT * FROM ContractApproval WHERE contractId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, contractId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ContractApprove(
                        rs.getInt("id"),
                        ctd.getById(rs.getString("contractId")),
                        rs.getObject("adminApproval") != null ? rs.getInt("adminApproval") : null,
                        rs.getObject("accountantApproval") != null ? rs.getInt("accountantApproval") : null,
                        rs.getInt("status"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        daoSt.getById(rs.getString("adminId")),
                        daoSt.getById(rs.getString("accountantId"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addApprove(ContractApprove c) {
        String sql = "INSERT INTO ContractApproval (contractId, adminId, accountantId, adminApproval, accountantApproval, status, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, ?, ?, GETDATE(), GETDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getContractId().getId());
            ps.setString(2, c.getAdmin().getId());
            ps.setString(3, c.getAccountant().getId());
            ps.setObject(4, c.getAdminApproval());
            ps.setObject(5, c.getAccountantApproval());
            ps.setInt(6, c.getStatus());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ContractApprove> getPendingContracts() {
        ContractDAO ctd = new ContractDAO();
        StaffDAO daoSt = new StaffDAO();
        List<ContractApprove> list = new ArrayList<>();
        String sql = "SELECT * FROM ContractApproval WHERE (adminApproval IS NULL OR accountantApproval IS NULL) AND status = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new ContractApprove(
                        rs.getInt("id"),
                        ctd.getById(rs.getString("contractId")),
                        rs.getObject("adminApproval") != null ? rs.getInt("adminApproval") : null,
                        rs.getObject("accountantApproval") != null ? rs.getInt("accountantApproval") : null,
                        rs.getInt("status"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        daoSt.getById(rs.getString("adminId")),
                        daoSt.getById(rs.getString("accountantId"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ContractApprove> getPendingContractApprovalsByStaffId(String staffId) {
        String sql = "SELECT * FROM ContractApproval\n" +
"WHERE ((adminApproval = 0 OR adminApproval IS NULL) OR (accountantApproval = 0 OR accountantApproval IS NULL))\n" +
"AND (adminId = ? OR accountantId = ?);";
        StaffDAO daoSt = new StaffDAO();
        ContractDAO ctd=new ContractDAO();
        List<ContractApprove> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, staffId);
            st.setString(2, staffId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new ContractApprove(
                        rs.getInt("id"),
                        ctd.getById(rs.getString("contractId")),
                        rs.getObject("adminApproval") != null ? rs.getInt("adminApproval") : null,
                        rs.getObject("accountantApproval") != null ? rs.getInt("accountantApproval") : null,
                        rs.getInt("status"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        daoSt.getById(rs.getString("adminId")), // Lấy thông tin Admin
                        daoSt.getById(rs.getString("accountantId")) // Lấy thông tin Accountant
                ));
                }
            }
        } catch (Exception ex) {

        }
        return list;
    }
    public boolean updateApprovalStatus(String contractId, String staffId, int approvalStatus) {
        String sql = "UPDATE ContractApproval SET "
                + "(adminApproval = CASE WHEN adminId = ? THEN ? ELSE adminApproval END, "
                + "accountantApproval = CASE WHEN accountantId = ? THEN ? ELSE accountantApproval END), "
                + "updatedAt = GETDATE() "
                + "WHERE contractId = ?";

        try (
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, staffId);
            ps.setInt(2, approvalStatus); 
            ps.setString(3, staffId);
            ps.setInt(4, approvalStatus); 
            ps.setString(5, contractId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void main(String[] args) {
        ContractApproveDAO dao= new ContractApproveDAO();
        System.out.println(dao.getPendingContractApprovalsByStaffId("S1004"));
    }
}
