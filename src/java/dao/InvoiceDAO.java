/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.response.EmailInvoice;
import jdbc.DBContext;
import java.util.List;
import jdbc.DBContext;
import model.InvoiceDetail;
import dto.response.FloorResponseDTO;
import java.sql.Date;
import jdbc.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Floor;
import model.Invoice;
import model.LivingApartment;
import model.MonthlyService;
import model.News;
import model.Resident;
import model.Service;
import model.Staff;
import util.Util;

/**
 *
 * @author thanh
 */
public class InvoiceDAO extends DBContext {

    public Invoice getById(String id) {
        String sql = "select * from invoice where id=?";
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(id, total, invoicedate, duedate, status, des, re, a);
                return invoice;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Invoice> getByResidentId(String rid) {
        String sql = "select * from invoice where rid=?";
        List<Invoice> list = new ArrayList<>();
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, rid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Invoice> getNonPaidInvoice() {
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from invoice where status =0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Invoice> getPaidInvoice() {
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from invoice where status =1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Invoice> searchByTimeAndApartment(String fromDate, String toDate, String aid) {
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from invoice where status = 1 and invoicedate between ? and ? ";
        boolean isAllApartment = aid.equals("all");
        if (!isAllApartment) {
            sql += "and aid =?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fromDate);
            st.setString(2, toDate);
            if (!isAllApartment) {
                st.setString(3, aid);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
        }
        System.out.println(sql);
        return list;
    }

    public float getTotalByTimeAndApartment(String fromDate, String toDate, String aid) {
        String sql = "select sum(total) as total from invoice where status = 1 and invoicedate between ? and ? ";
        boolean isAllApartment = aid.equals("all");
        if (!isAllApartment) {
            sql += "and aid =?";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fromDate);
            st.setString(2, toDate);
            if (!isAllApartment) {
                st.setString(3, aid);
            }
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getFloat("total");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public Invoice getByApartmentIdNow(String aid) {
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        String sql = "select * from invoice where status=1 and aid=? and MONTH(invoicedate) =? and year(invoicedate) = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, aid);
            st.setInt(2, LocalDate.now().getMonthValue());
            st.setInt(3, LocalDate.now().getYear());
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                return invoice;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public List<Invoice> searchPaidByTimeAndResidentId(String fromDate, String toDate, String sid) {
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from invoice where status =1 and invoicedate between ? and ?  and rid =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fromDate);
            st.setString(2, toDate);
            st.setString(3, sid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
        }
        System.out.println(sql);
        return list;
    }

    public List<Invoice> searchNotPaidByTimeAndResidentId(String sid) {
        ResidentDAO rd = new ResidentDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Invoice> list = new ArrayList<>();
        String sql = "select * from invoice where  status=0  and rid =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, sid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String i = rs.getString("id");
                float total = rs.getFloat("total");
                String invoicedate = rs.getDate("invoicedate").toString();
                String duedate = rs.getDate("duedate").toString();
                int status = rs.getInt("status");
                String des = rs.getString("description");
                Resident re = rd.getById(rs.getString("rid"));
                Apartment a = ad.getById(rs.getString("aid"));
                Invoice invoice = new Invoice(i, total, invoicedate, duedate, status, des, re, a);
                list.add(invoice);
            }
        } catch (SQLException e) {
        }
        System.out.println(sql);
        return list;
    }

    public void generateInvoice() {
        InvoiceDetalDAO idd = new InvoiceDetalDAO();
        LivingApartmentDAO ld = new LivingApartmentDAO();
        List<LivingApartment> listAciveApartment = ld.getAllActiveOwnerLivingApartmentObejct();
        for (int i = 0; i < listAciveApartment.size(); i++) {
            LivingApartment la = listAciveApartment.get(i);
            String aid = la.getAid().getId();
            String rid = la.getRid().getpId();
            float total = this.getTotalByApartmentId(aid);
            String newInvoiceId = this.addInvoiceByApartmentIdResidentIdAndTotal(aid, rid, total);
            idd.addInvoiceDetailByApartmentIdAndInvoiceId(newInvoiceId, aid);
        }
    }

    public String addInvoiceByApartmentIdResidentIdAndTotal(String aid, String rid, float total) {
        String sql = "insert into invoice values(?,?,?,?,0,?,?,?)";
        String newInvoiceId = "IV" + (this.getNumberInvoice() + 1);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newInvoiceId);
            st.setFloat(2, total);
            st.setDate(3, Date.valueOf(LocalDate.now()));
            st.setDate(4, Date.valueOf(LocalDate.now().plusDays(7)));
            st.setString(5, "Dich vu thang " + LocalDate.now().getMonthValue());
            st.setString(6, rid);
            st.setString(7, aid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return "Not ok";
        }
        return newInvoiceId;
    }

    public float getTotalByApartmentId(String id) {
        float total = 0;
        MonthlyServiceDAO md = new MonthlyServiceDAO();
        List<MonthlyService> listService = md.getByApartmentId(id);
        for (int i = 0; i < listService.size(); i++) {
            MonthlyService t = listService.get(i);
            Service s = t.getService();
            total += s.getUnitPrice() * t.getQuantity();
        }
        return total;
    }

    public int getNumberInvoice() {
        String sql = "select count(*) as no from invoice";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("no");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public void switchToPaidStatusById(String id) {
        String sql = "update invoice set status =1 where id =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Integer> getMonthlyRevenueByYear(int year) {
        String sql = "select MONTH(invoicedate) as month, sum(total) as sum from invoice where year(invoicedate) = ? and status =1 group by month(invoicedate) order by month";
        float[] array = new float[12];
        Arrays.fill(array, 0);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                int index = rs.getInt("month");
                float val = rs.getFloat("sum");
                array[index - 1] = val;
            }
        } catch (SQLException e) {
        }
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            list.add((int) array[i]);
        }
        return list;
    }

    public float getRevenueByYear(int year) {
        String sql = "select sum(total) as sum from invoice where year(invoicedate) =? and status=1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, year);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getFloat("sum");
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    public boolean isCreatedInvoice(Date date) {
        String sql = "select * from invoice where invoicedate =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, date);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
        }
        return false;
    }

    public List<EmailInvoice> getEmailInvoiceDebt() {
        List<Invoice> listInvoice = this.getNonPaidInvoice();
        List<EmailInvoice> rs = new ArrayList<>();
        for (int i = 0; i < listInvoice.size(); i++) {
            Apartment a = listInvoice.get(i).getApartment();
            String email = listInvoice.get(i).getResident().getEmail();
            EmailInvoice ei = new EmailInvoice(a.getId(), email);
            rs.add(ei);
        }
        return rs;
    }

    public boolean createNewsNotifyInvoice(String staffId) {
        int month = LocalDate.now().getMonthValue();
        String tittle = "Hóa đơn tháng " + month;
        String content = "Hóa đơn tháng " + month + " đã được phát hành";
        Date date = Date.valueOf(LocalDate.now());
        String img = "/images/logo/notify.jpg";
        String category = "Dịch vụ tháng";
        NewDAO nd = new NewDAO();
        StaffDAO sd = new StaffDAO();
        Staff staff = sd.getById(staffId);
        News n = new News(tittle, content, "", category, img, staff, date.toString());
        return nd.insertNews(n);
    }

    public int getMaxPage(List<Invoice> list, int number) {
        int count = list.size();
        if (count % number == 0) {
            return count / number;
        } else {
            return count / number + 1;
        }
    }

    public List<Invoice> getByPaing(List<Invoice> list, int number, int page) {
        List<Invoice> rs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i >= (page - 1) * number && i <= (page * number - 1)) {
                rs.add(list.get(i));
            }
            if (i > (page * number - 1)) {
                break;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        InvoiceDAO id = new InvoiceDAO();
//        System.out.println(id.getByResidentId("P101").size());
//        System.out.println(id.getNonPaidInvoice().size());
//        System.out.println(id.getMonthlyRevenueByYear(2025).get(1));
        System.out.println(id.searchNotPaidByTimeAndResidentId("P101").size());
    }
}
