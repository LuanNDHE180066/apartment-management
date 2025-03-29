/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CompanyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Company;
import util.Util;
import validation.CompanyValidation;

/**
 *
 * @author PC
 */
public class AddNewCompany extends HttpServlet {

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
            out.println("<title>Servlet AddNewCompany</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewCompany at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        Util u= new Util();

        String name =u.stringNomalize(request.getParameter("name")) ;
        String phone = u.stringNomalize(request.getParameter("phone"));
        String contactPhone = u.stringNomalize(request.getParameter("contactPhone"));
        String fax = u.stringNomalize(request.getParameter("fax"));
        String email = request.getParameter("email");
        String contactEmail = request.getParameter("contactemail");
        String website = u.stringNomalize(request.getParameter("website"));
        String taxCode = u.stringNomalize(request.getParameter("taxCode"));
        String bank = request.getParameter("bank");
        String address =u.stringNomalize(request.getParameter("address")) ;
        String description =u.stringNomalize(request.getParameter("description")) ;

        boolean hasError = false;
        

        CompanyDAO cd = new CompanyDAO();
        

        Company newC = new Company(name, phone, contactPhone, fax, email, contactEmail, website, taxCode, bank, description, address);
        CompanyValidation companyValidation = new CompanyValidation(newC);
        if (name.trim().isEmpty()) {
            request.setAttribute("nameError", "Tên không được trống");
            hasError = true;
        }
        if (!phone.matches("0[0-9]{9}")) {
            request.setAttribute("phoneError", "Số điện thoại phải đủ 10 số");
            hasError = true;
        }
        if (!contactPhone.matches("0[0-9]{9}")) {
            request.setAttribute("contactPhoneError", "Số điện thoại phải đủ 10 số");
            hasError = true;
        }
        if (!fax.matches("[0-9]{10}")) {
            request.setAttribute("faxError", "Số fax phải đủ 10 số");
            hasError = true;
        }
        if (!taxCode.matches("[0-9]{10}")) { 
            request.setAttribute("taxCodeError", "Mã số thuế phải đủ 10 số");
            hasError = true;
        }
        if(email.trim().isEmpty()){
            request.setAttribute("emailError", "Email không được trống");
            hasError = true;
        }
        if (companyValidation.isExistEmail(email)) {
            request.setAttribute("emailError", "Email đã tồn tại");
            hasError = true;
        }
        if(address.trim().isEmpty()){
            request.setAttribute("addressError", "Địa chỉ không được trống");
            hasError = true;
        }
        if(bank.trim().isEmpty()){
            request.setAttribute("bankError", "Ngân hàng không được trống");
            hasError = true;
        }
        if (companyValidation.isExistBank(bank)) {
            request.setAttribute("bankError", "Ngân hàng đã tồn tại");
            hasError = true;
        }
        if(contactEmail.trim().isEmpty()){
            request.setAttribute("contactEmailError", "Email không được trống");
            hasError = true;
        }
        if (companyValidation.isExistContactEmail(contactEmail)) {
            request.setAttribute("contactEmailError", "Email đã tồn tại");
            hasError = true;
        }
        if (companyValidation.isExistContactPhone(contactPhone)) {
            request.setAttribute("contactPhoneError", "Số điện thoại đã tồn tại");
            hasError = true;
        }
        if (companyValidation.isExistFax(fax)) {
            request.setAttribute("faxError", "Số fax đã tồn tại");
            hasError = true;
        }
        if (companyValidation.isExistName(name)) {
            request.setAttribute("nameError", "Tên đã tồn tại");
            hasError = true;
        }
        if (companyValidation.isExistPhone(phone)) {
            request.setAttribute("phoneError", "Số điện thoại đã tồn tại");
            hasError = true;
        }
        if (companyValidation.isExistTaxCode(taxCode)) {
            request.setAttribute("taxCodeError", "Mã số thuế đã tồn tại");
            hasError = true;
        }
        if(website.trim().isEmpty()){
            request.setAttribute("webError", "WebSite Không được trống");
            hasError = true;
        }
        if (companyValidation.isExistWebsite(website)) {
            request.setAttribute("webError", "Website đã tồn tại");
            hasError = true;
        }
        if (hasError) {
            request.getRequestDispatcher("addnewcompany.jsp").forward(request, response);
            return;
        }
        if (cd.insertNewCompany(newC)) {
            request.setAttribute("status", "true");
            request.setAttribute("message", "Thêm thành công");

        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Thêm thất bại");
        }
        request.getRequestDispatcher("addnewcompany.jsp").forward(request, response);
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
