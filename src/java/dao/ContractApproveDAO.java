package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                        rs.getInt("status"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        daoSt.getById(rs.getString("adminId"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ContractApprove getById(int Id) {
        ContractDAO ctd = new ContractDAO();
        StaffDAO daoSt = new StaffDAO();
        String sql = "SELECT * FROM ContractApproval WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ContractApprove(
                        ctd.getById(rs.getString("contractId")),
                        rs.getObject("adminApproval") != null ? rs.getInt("adminApproval") : null,
                        rs.getInt("status"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        daoSt.getById(rs.getString("adminId"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean addApprove(ContractApprove c) {
        String sql = "INSERT INTO ContractApproval (contractId, adminId, adminApproval, status, createdAt, updatedAt) "
                + "VALUES (?, ?, ?, ?, GETDATE(), GETDATE())";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getContractId().getId());
            ps.setString(2, c.getAdmin().getId());
            ps.setObject(3, c.getAdminApproval());
            ps.setInt(4, c.getStatus());

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
        String sql = "SELECT * FROM ContractApproval WHERE adminApproval IS NULL AND status = 1";

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new ContractApprove(
                        rs.getInt("id"),
                        ctd.getById(rs.getString("contractId")),
                        rs.getObject("adminApproval") != null ? rs.getInt("adminApproval") : null,
                        rs.getInt("status"),
                        rs.getString("createdAt"),
                        rs.getString("updatedAt"),
                        daoSt.getById(rs.getString("adminId"))
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ContractApprove> getPendingContractApprovalsByStaffId(String staffId) {
        String sql = "SELECT * FROM ContractApproval\n"
                + "WHERE ((adminApproval = 0 OR adminApproval IS NULL))\n"
                + "AND (adminId = ?);";
        StaffDAO daoSt = new StaffDAO();
        ContractDAO ctd = new ContractDAO();
        List<ContractApprove> list = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setString(1, staffId);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    list.add(new ContractApprove(
                            rs.getInt("id"),
                            ctd.getById(rs.getString("contractId")),
                            rs.getObject("adminApproval") != null ? rs.getInt("adminApproval") : null,
                            rs.getInt("status"),
                            rs.getString("createdAt"),
                            rs.getString("updatedAt"),
                            daoSt.getById(rs.getString("adminId"))
                    ));
                }
            }
        } catch (Exception ex) {

        }
        return list;
    }

    public boolean updateApprovalStatus(String id, String approvalStatus) {
    
    String sql = "UPDATE [ContractApproval] SET [adminApproval] = ?, updatedAt = GETDATE() WHERE id = ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, approvalStatus);
        ps.setString(2, id);

        int rows = ps.executeUpdate();
        System.out.println("Rows affected: " + rows);
        return rows > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    public static void main(String[] args) {
        ContractApproveDAO dao = new ContractApproveDAO();
        System.out.println(dao.updateApprovalStatus("2", "2"));

    }
}
