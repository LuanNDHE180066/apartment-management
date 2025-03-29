/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.CompanyDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Company;
import util.Util;
import validation.CompanyValidation;

/**
 *
 * @author thanh
 */
@WebServlet(name = "UpdateCompanyServlet", urlPatterns = {"/update-company"})
public class UpdateCompanyServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateCompanyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCompanyServlet at " + request.getContextPath() + "</h1>");
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
        CompanyDAO cd = new CompanyDAO();
        String id = request.getParameter("id");
        Company company = cd.getById(id);
        request.setAttribute("company", company);
        request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String name = u.stringNomalize(request.getParameter("name"));
        String phone = request.getParameter("phone");
        String contactPhone = request.getParameter("contactPhone");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String contactEmail = request.getParameter("contactemail");
        String website = request.getParameter("website");
        String taxCode = request.getParameter("taxCode");
        String bank = request.getParameter("bank");
        String address = u.stringNomalize(request.getParameter("address"));
        String description = u.stringNomalize(request.getParameter("description"));
        String status_raw = request.getParameter("status");
        int status = Integer.parseInt(status_raw);
        boolean hasError = false;
        CompanyDAO daoCompany = new CompanyDAO();
        Company company = daoCompany.getById(id);
        CompanyValidation companyValidation = new CompanyValidation(company);
        if (name.trim().isEmpty()) {
            request.setAttribute("nameError", "Tên khôgn thể trống");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!phone.matches("0[0-9]{9}")) {
            request.setAttribute("phoneError", "Số điện thoại phải đủ 10 số và bắt đầu bằng 0");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getPhone().equals(phone) && companyValidation.isExistPhone(phone)) {
            request.setAttribute("phoneError", "Số điện thoại đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!contactPhone.matches("0[0-9]{9}")) {
            request.setAttribute("contactPhoneError", "Số điện thoại phải đủ 10 số và bắt đầu bằng 0");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getContactPhone().equals(contactPhone) && companyValidation.isExistContactPhone(contactPhone)) {
            request.setAttribute("contactPhoneError", "Số điện thoại đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!fax.matches("[0-9]{10}")) {
            request.setAttribute("faxError", "Số fax phải đủ 10 số");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getFax().equals(fax) && companyValidation.isExistFax(fax)) {
            request.setAttribute("faxError", "Số fax đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email không thể trống");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getEmail().equals(email) && companyValidation.isExistEmail(email)) {
            request.setAttribute("emailError", "Đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }
        if(contactEmail.trim().isEmpty()) {
            request.setAttribute("contactEmailError", "Email không thể trống");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getContactemail().equals(contactEmail) && companyValidation.isExistContactEmail(contactEmail)) {
            request.setAttribute("contactEmailError", "Email đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }

        if (website.trim().isEmpty()) {
            request.setAttribute("webError", "Website Không thể trống");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getWebsite().equals(website) && companyValidation.isExistWebsite(website)) {
            request.setAttribute("webError", "Website đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }

        if (!taxCode.matches("[0-9]{10}")) {
            request.setAttribute("taxCodeError", "Mã số thuế phải đu 10 số");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getTaxCode().equals(taxCode) && companyValidation.isExistTaxCode(taxCode)) {
            request.setAttribute("taxCodeError", "Mã số thuế đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }

        if (bank.trim().isEmpty()) {
            request.setAttribute("bankError", "Ngân hàng không thể trống");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (!company.getBank().equals(bank) && companyValidation.isExistBank(bank)) {
            request.setAttribute("bankError", "Ngân hàng đã tồn tại");
            request.setAttribute("company", company);
            hasError = true;
        }
        if (hasError) {
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        Company updatedCompany = new Company();
        updatedCompany.setId(id);
        updatedCompany.setName(name);
        updatedCompany.setPhone(phone);
        updatedCompany.setContactPhone(contactPhone);
        updatedCompany.setFax(fax);
        updatedCompany.setEmail(email);
        updatedCompany.setContactemail(contactEmail);
        updatedCompany.setWebsite(website);
        updatedCompany.setTaxCode(taxCode);
        updatedCompany.setBank(bank);
        updatedCompany.setAddress(address);
        updatedCompany.setDescription(description);
        updatedCompany.setStatus(status);

        if (daoCompany.updateCompany(updatedCompany)) {
            request.setAttribute("status", "true");
            request.setAttribute("message", "Cập nhật thành công");
            StaffDAO std= new StaffDAO();
            Company c= daoCompany.getById(id);
            std.updatewhenchangecompanystatus(c.getStatus(), c.getId());
        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Cập nhật thất bại");
        }
        company = daoCompany.getById(id);
        request.setAttribute("company", company);
        request.getRequestDispatcher("updatecompany.jsp").forward(request, response);

    }

    /**
     * Utility method to safely get request parameters and avoid
     * NullPointerException
     */
    /**
     * Utility method to safely get request parameters and avoid
     * NullPointerException
     */
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
