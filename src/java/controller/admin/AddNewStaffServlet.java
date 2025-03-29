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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Company;
import model.Employee;
import model.Role;
import model.SendEmail;
import model.Staff;
import util.Util;
import static util.Util.encryptPassword;
import validation.CommonValidation;

/**
 *
 * @author pc
 */
@WebServlet(name = "AddNewStaffServlet", urlPatterns = {"/add-new-staff"})
public class AddNewStaffServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewStaffServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewStaffServlet at " + request.getContextPath() + "</h1>");
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
        RoleDAO daoR = new RoleDAO();
        CompanyDAO daoCp = new CompanyDAO();
        List<Company> listCompany = daoCp.getAll();
        List<Role> listRole = daoR.getAllExcludeResident();
        session.setAttribute("listCompany", listCompany);
        session.setAttribute("listRole", listRole);
        request.getRequestDispatcher("addnewstaff.jsp").forward(request, response);
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
        RoleDAO daoR = new RoleDAO();
        CompanyDAO daoCp = new CompanyDAO();
        Util u = new Util();
        String name = u.stringNomalize(request.getParameter("name"));
        String dob = request.getParameter("dob");
        String address = u.stringNomalize(request.getParameter("address")) ;
        String phone = request.getParameter("phone");
        String email = u.stringNomalize( request.getParameter("email"));
        String cccd = request.getParameter("cccd");
        String education = request.getParameter("education");
        String salary_raw = request.getParameter("salary");
        String bank = request.getParameter("bank");
        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        String company = request.getParameter("company");
        String startDate = request.getParameter("startDate");
        String roleId = request.getParameter("role");
        String gender = request.getParameter("gender");
        StaffDAO stDao = new StaffDAO();
        List<Staff> listStaff = stDao.getAll();
        HttpSession session = request.getSession();

        if (listStaff == null) {
            listStaff = new ArrayList<>();
        }
        
        CommonValidation valid=new CommonValidation();
        String password = u.generatePassword();
        String password2 = password;

        //insert to database with encryted password
        password = encryptPassword(password);
        Staff s = null;
        boolean hasError = false;
        try {
            salary_raw = salary_raw.replaceAll("\\.", "");
            int salary = Integer.parseInt(salary_raw);
            if (salary <= 0) {
                request.setAttribute("salaryerror", "Lương không thể âm");
                hasError = true;
            }
            Role role = daoR.getById(roleId);

            if (role == null) {
                request.setAttribute("roleerror", "Không có vai trò nào được chọn");
                hasError = true;
            }
            s = new Staff(name, dob, email, phone, address, cccd, salary, education, bank, username, password, role,
                    daoCp.getById(company), startDate, gender);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            ZoneId zone = ZoneId.systemDefault();

            for (Staff st : listStaff) {
                try {
                    if (st.getCccd().equals(s.getCccd())) {
                        request.setAttribute("cccderror", "Căn cước đã tồn tại");
                        hasError = true;
                    }
                    Date start=format.parse(startDate);
                    Date today=new Date();
                    today= format.parse(format.format(today));
                    Date dateOfBirth = format.parse(dob);
                    LocalDate birthDate = dateOfBirth.toInstant().atZone(zone).toLocalDate();
                    LocalDate currentDate = LocalDate.now();

                    int age = currentDate.getYear() - birthDate.getYear();
                    if (currentDate.getDayOfYear() < birthDate.getDayOfYear()) {
                        age--;
                    }
                    if (name.trim().isEmpty()) {
                        request.setAttribute("nameerror", "Tên không được trống");
                        hasError = true;
                    }
                    if (age <= 18) {
                        request.setAttribute("ageerror", "Tuổi phải lớn hơn 18");
                        hasError = true;
                    }
                    if(dob.trim().isEmpty()){
                        request.setAttribute("ageerror", "Phải chọn ngày tháng năm sinh");
                        hasError = true;
                    }
                    if(address.trim().isEmpty()){
                        request.setAttribute("addresserror", "Địa chỉ không trống");
                        hasError = true;
                    }
                    if (st.getPhone().equals(s.getPhone())) {
                        request.setAttribute("phoneerror", "Số điện thoại đã tồn tại");
                        hasError = true;
                    }
                    if(email.trim().isEmpty()){
                        request.setAttribute("emailerror", "Email không trống");
                        hasError = true;
                    }
                    if (st.getEmail().equals(s.getEmail())) {
                        request.setAttribute("emailerror", "Email đã tồnt tại");
                        hasError = true;
                    }
                    if(username.trim().isEmpty()){
                        request.setAttribute("usernameerror", "Tên đăng nhập không trống");
                        hasError = true;
                    }
                    if(!valid.isValidUsername(username)){
                        request.setAttribute("usernameerror", "Tên đăng nhập tối thiểu 6 kí tự");
                        hasError = true;
                    }
                    if (st.getUsername().equals(s.getUsername())) {
                        request.setAttribute("usernameerror", "Tên đăng nhập đã tồn tại");
                        hasError = true;
                    }
                    if(bank.trim().isEmpty()){
                        request.setAttribute("bankerror", "Ngân hàng không trống");
                        hasError = true;
                    }
                    if (st.getBank().equals(s.getBank())) {
                        request.setAttribute("bankerror", "Ngân hàng đã tồn tại");
                        hasError = true;
                    }if(start.before(today)){
                        request.setAttribute("startdateerror", "Ngày bắt đầu từ hôm nay trở đi");
                        hasError = true;
                    }if(company==null){
                        request.setAttribute("companyrror", "Công ty không thể trống");
                        hasError = true;
                    }
                } catch (ParseException ex) {
                    
                }
            }
            if (!s.getPhone().matches("0[0-9]{9}")) {
                request.setAttribute("phoneerror", "Số điện thoại phải bắt đầu bằng 0 và đủ 10 số");
                hasError = true;
            }
            if (!s.getCccd().matches("[0-9]{12}")) {
                request.setAttribute("cccderror", "Căn cước phải đủ 12 số");
                hasError = true;
            }
            
        } catch (NumberFormatException st) {
            request.setAttribute("salaryerror", "Lỗi lương");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("addnewstaff.jsp").forward(request, response);
            return;
        }
        
        if (stDao.insertStaff(s)) {
            
            SendEmail e = new SendEmail();
            e.sendEmailStaff(email, name, username, password2);
            session.setAttribute("staffs", stDao.getAll());
            request.setAttribute("status", "true");
            request.setAttribute("message", "Thêm thành công");
        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Thêm thất bại");
        }
        response.sendRedirect("view-all-staff");
//        request.getRequestDispatcher("view-all-staff").forward(request, response);
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
