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
        String phone = request.getParameter("phone");
        String cccd = request.getParameter("cccd");
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
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date today = new Date();
            today = sdf.parse(sdf.format(today));
            Date startD = sdf.parse(startDate);
            if (!startDate.equals(staff.getStartDate())) {
                if (startD.before(today)) {
                    request.setAttribute("startdatemessage", "Start date must be today or later");
                    request.setAttribute("staff", staff);
                    request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
                    return;
                }
            }

            String oldEndDate = staff.getEndDate();

            if (endDate != null && !endDate.trim().isEmpty() && !endDate.equals(oldEndDate)) {
                Date endD = sdf.parse(endDate);
                if (endD.before(today)) {
                    request.setAttribute("enddatemessage", "End date must be today or later");
                    request.setAttribute("staff", staff);
                    request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
                    return;
                }
                if (endD.before(startD)) {
                    request.setAttribute("enddatemessage", "End date must be after start date");
                    request.setAttribute("staff", staff);
                    request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
                    return;
                }
            }

            LocalDate birthDate = LocalDate.parse(dob);
            LocalDate todayage = LocalDate.now();
            if (Period.between(birthDate, todayage).getYears() < 18) {
                request.setAttribute("dobmessage", "Staff must be at least 18 years old.");
                request.setAttribute("staff", staff);
                request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (name.trim().isEmpty()) {
            request.setAttribute("namemessage", "Name not empty");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (address.trim().isEmpty()) {
            request.setAttribute("addressmessage", "Address not empty");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (!phone.matches("0[0-9]{9}")) {
            request.setAttribute("phonemessage", "Phone number must be exactly 10 digits.");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (!staff.getPhone().equals(phone) && daoSt.checkDuplicatePhone(phone)) {
            request.setAttribute("phonemessage", "Phone number exist");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (email.trim().isEmpty()) {
            request.setAttribute("emailmessage", "Email is not empty");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (!staff.getEmail().equals(email) && daoSt.checkDupEmail(email)) {
            request.setAttribute("emailmessage", "Email number exist");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (!cccd.matches("[0-9]{12}")) {
            request.setAttribute("cccdmessage", "CCCD must be exactly 12 digits.");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (!staff.getCccd().equals(cccd) && daoSt.checkDuplicateID(cccd)) {
            request.setAttribute("cccdmessage", "CCCD exist");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (education.trim().isEmpty()) {
            request.setAttribute("edumessage", "Education is not empty");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (bank.trim().isEmpty()) {
            request.setAttribute("bankmessage", "Bank is not empty");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
        }
        if (!staff.getBank().equals(bank) && daoSt.checkDuplicateBank(bank)) {
            request.setAttribute("bankmessage", "Bank exist");
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
            return;
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
            request.setAttribute("salaryerror", "Salary must be greater than 0.");
            request.setAttribute("staff", staff);
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
            request.setAttribute("message", "Staff updated successfully!");
        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Failed to update staff.");
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
