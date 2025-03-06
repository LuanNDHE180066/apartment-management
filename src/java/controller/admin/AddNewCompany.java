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
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String contactPhone = request.getParameter("contactPhone");
        String fax = request.getParameter("fax");
        String email = request.getParameter("email");
        String contactEmail = request.getParameter("contactemail");
        String website = request.getParameter("website");
        String taxCode = request.getParameter("taxCode");
        String bank = request.getParameter("bank");
        String address = request.getParameter("address");
        String description = request.getParameter("description");

        boolean hasError = false;
        if (hasError) {
            request.getRequestDispatcher("addnewcompany.jsp").forward(request, response);
            return;
        }

        CompanyDAO cd = new CompanyDAO();
        List<Company> listCompany = cd.getAll();
        if (listCompany == null) {
            listCompany = new ArrayList<>();
        }

        Company newC = new Company(name, phone, contactPhone, fax, email, contactEmail, website, taxCode, bank, description, address);
        CompanyValidation companyValidation = new CompanyValidation(newC);
        if (name.trim().isEmpty()) {
            request.setAttribute("nameError", "Name cannot be blank.");
            hasError = true;
        }
        if (!phone.matches("0[0-9]{9}")) {
            request.setAttribute("phoneError", "Phone number must be exactly 10 digits.");
            hasError = true;
        }
        if (!contactPhone.matches("0[0-9]{9}")) {
            request.setAttribute("contactPhoneError", "Contact phone must be exactly 10 digits.");
            hasError = true;
        }
        if (!fax.matches("[0-9]{10}")) {
            request.setAttribute("faxError", "Fax must be exactly 10 digits.");
            hasError = true;
        }
        if (!taxCode.matches("[0-9]{10}")) {
            request.setAttribute("taxCodeError", "Tax code must be exactly 10 digits.");
            hasError = true;
        }
        if(email.trim().isEmpty()){
            request.setAttribute("emailError", "Email must not empty");
            hasError = true;
        }
        if (companyValidation.isExistEmail(email)) {
            request.setAttribute("emailError", "Email is existed");
            hasError = true;
        }
        if(address.trim().isEmpty()){
            request.setAttribute("addressError", "address must not empty");
            hasError = true;
        }
        if (companyValidation.isExistAddress(address)) {
            request.setAttribute("addressError", "address is existed");
            hasError = true;
        }
        if(bank.trim().isEmpty()){
            request.setAttribute("bankError", "Bank must not empty");
            hasError = true;
        }
        if (companyValidation.isExistBank(bank)) {
            request.setAttribute("bankError", "Bank is existed");
            hasError = true;
        }
        if(contactEmail.trim().isEmpty()){
            request.setAttribute("contactEmailError", "ContactEmail must not empty");
            hasError = true;
        }
        if (companyValidation.isExistContactEmail(contactEmail)) {
            request.setAttribute("contactEmailError", "ContactEmail is existed");
            hasError = true;
        }
        if (companyValidation.isExistContactPhone(contactPhone)) {
            request.setAttribute("contactPhoneError", "ContactPhone is existed");
            hasError = true;
        }
        if (companyValidation.isExistFax(fax)) {
            request.setAttribute("faxError", "Fax is existed");
            hasError = true;
        }
        if (companyValidation.isExistName(name)) {
            request.setAttribute("nameError", "Name is existed");
            hasError = true;
        }
        if (companyValidation.isExistPhone(phone)) {
            request.setAttribute("phoneError", "Phone is existed");
            hasError = true;
        }
        if (companyValidation.isExistTaxCode(taxCode)) {
            request.setAttribute("taxCodeError", "TaxCode is existed");
            hasError = true;
        }
        if(website.trim().isEmpty()){
            request.setAttribute("webError", "Web Site must not empty");
            hasError = true;
        }
        if (companyValidation.isExistWebsite(website)) {
            request.setAttribute("webError", "Web Site is existed");
            hasError = true;
        }
        if (cd.insertNewCompany(newC)) {
            request.setAttribute("status", "true");
            request.setAttribute("message", "New company added successfully!");
            session.removeAttribute("companies");
        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Failed to add new company!");
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
