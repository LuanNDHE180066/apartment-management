/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CompanyDAO;
import dao.RoleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import model.Company;
import model.Role;
import model.Staff;
import util.Util;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "UpdateStaffInfor", urlPatterns = {"/updateStaffInfor"})
public class UpdateStaffInfor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateStaffInfor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateStaffInfor at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        StaffDAO sd = new StaffDAO();
        Staff staff = sd.getById(id);
        RoleDAO daoR = new RoleDAO();
        CompanyDAO daoCp = new CompanyDAO();
        List<Company> listCompany = daoCp.getAll();
        List<Role> listRole = daoR.getAllExcludeResident();
        session.setAttribute("listCompany", listCompany);
        session.setAttribute("listRole", listRole);
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Util u = new Util();
        String id = request.getParameter("staffID");
        String name = u.stringNomalize(request.getParameter("name"));
        String dob = request.getParameter("dob");
        String email = request.getParameter("email");
        String address = u.stringNomalize(request.getParameter("address"));
        String phone = u.stringNomalize(request.getParameter("phone"));
        String cccd = u.stringNomalize(request.getParameter("cccd"));
        String education = u.stringNomalize(request.getParameter("education"));
        String bank = request.getParameter("bank");
        String salary_raw = request.getParameter("salary");
        String companyId = request.getParameter("company");
        String status_raw = request.getParameter("status");
        String role = request.getParameter("role");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String ischief_raw = request.getParameter("ischief");
        StaffDAO daoSt = new StaffDAO();
        CompanyDAO daoCp = new CompanyDAO();
        RoleDAO daoR = new RoleDAO();
        Staff staff = daoSt.getById(id);
        boolean hasError = false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            today = sdf.parse(sdf.format(today));
            Date startD = sdf.parse(startDate);
            if (!startDate.equals(staff.getStartDate())) {
                if (startD.before(today)) {
                    request.setAttribute("startdatemessage", "Ngày bắt đầu phải từ hôm nay trở đi");
                    request.setAttribute("staff", staff);
                    hasError = true;
                }
            }

            String oldEndDate = staff.getEndDate();

            if (endDate != null && !endDate.trim().isEmpty() && !endDate.equals(oldEndDate)) {
                Date endD = sdf.parse(endDate);
                if (endD.before(today)) {
                    request.setAttribute("enddatemessage", "Ngày kết thúc phải từ hôm nay trở đi");
                    request.setAttribute("staff", staff);
                    hasError = true;
                }
                if (endD.before(startD)) {
                    request.setAttribute("enddatemessage", "Ngày kết thúc phải sau ngày bắt đầu");
                    request.setAttribute("staff", staff);
                    hasError = true;
                }
            }

            LocalDate birthDate = LocalDate.parse(dob);
            LocalDate todayage = LocalDate.now();
            if (Period.between(birthDate, todayage).getYears() < 18) {
                request.setAttribute("dobmessage", "Nhân viên phải đủ 18 tuổi");
                request.setAttribute("staff", staff);
                hasError = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (name.trim().isEmpty()) {
            request.setAttribute("namemessage", "Tên không thể trống");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (address.trim().isEmpty()) {
            request.setAttribute("addressmessage", "Địa chỉ không thể trống");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (!phone.matches("0[0-9]{9}")) {
            request.setAttribute("phonemessage", "số điện thoại phải đủ 10 số bắt đầu bằng 0");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (!staff.getPhone().equals(phone) && daoSt.checkDuplicatePhone(phone)) {
            request.setAttribute("phonemessage", "Số điện thoại đã tồn tại");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (email.trim().isEmpty()) {
            request.setAttribute("emailmessage", "Email không thể trống");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (!staff.getEmail().equals(email) && daoSt.checkDupEmail(email)) {
            request.setAttribute("emailmessage", "Email đã tồn tại");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (!cccd.matches("[0-9]{12}")) {
            request.setAttribute("cccdmessage", "Căn cước phải đủ 12 số");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (!staff.getCccd().equals(cccd) && daoSt.checkDuplicateID(cccd)) {
            request.setAttribute("cccdmessage", "Căn cước đã tồn tại");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (education.trim().isEmpty()) {
            request.setAttribute("edumessage", "Học vấn không thể trống");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (bank.trim().isEmpty()) {
            request.setAttribute("bankmessage", "Ngân hàng không thể trống");
            request.setAttribute("staff", staff);
           hasError = true;
        }
        if (!staff.getBank().equals(bank) && daoSt.checkDuplicateBank(bank)) {
            request.setAttribute("bankmessage", "Ngân hàng đã tồn tại");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        salary_raw = salary_raw.replaceAll("\\.", "");
        int salary = Integer.parseInt(salary_raw);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = sdf.format(new Date());

            if ("0".equals(status_raw)) {
                endDate = todayStr;
            } else {
                endDate = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int status = Integer.parseInt(status_raw);
        if (salary <= 0) {
            request.setAttribute("salaryerror", "Lương phải lón hơn 0");
            request.setAttribute("staff", staff);
            hasError = true;
        }
        if (hasError) {
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        int ischief = Integer.parseInt(ischief_raw);
        Staff st = new Staff();
        st.setId(id);
        st.setAddress(address);
        st.setBank(bank);
        st.setBod(dob);
        st.setCccd(cccd);
        st.setCompany(daoCp.getById(companyId));
        st.setEducation(education);
        st.setEmail(email);
        st.setEndDate((endDate == null || endDate.isEmpty()) ? null : endDate);
        st.setName(name);
        st.setPhone(phone);
        st.setRole(daoR.getById(role));
        st.setSalary(salary);
        if (startDate == null || startDate.trim().isEmpty()) {
            startDate = staff.getStartDate();
        }
        st.setStartDate(startDate);
        st.setStatus(status);
        st.setIschief(ischief);
        if (daoSt.updateStaffInfor(st)) {
            request.setAttribute("status", "true");
            request.setAttribute("message", "Cập nhật thành công");
        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Cập nhật thất bại");
        }
        staff = daoSt.getById(id);
        request.setAttribute("staff", staff);
        request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
