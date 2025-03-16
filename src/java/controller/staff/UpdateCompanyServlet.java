/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.CompanyDAO;
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

        CompanyDAO daoCompany = new CompanyDAO();
        Company company = daoCompany.getById(id);
        CompanyValidation companyValidation = new CompanyValidation(company);
        if (name.trim().isEmpty()) {
            request.setAttribute("nameError", "Name cannot be blank.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!phone.matches("0[0-9]{9}")) {
            request.setAttribute("phoneError", "Phone number must be exactly 10 digits.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getPhone().equals(phone) && companyValidation.isExistPhone(phone)) {
            request.setAttribute("phoneError", "Phone number exists.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!contactPhone.matches("0[0-9]{9}")) {
            request.setAttribute("contactPhoneError", "Contact phone number must be exactly 10 digits.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getContactPhone().equals(contactPhone) && companyValidation.isExistContactPhone(contactPhone)) {
            request.setAttribute("contactPhoneError", "Phone number exists.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!fax.matches("[0-9]{10}")) {
            request.setAttribute("faxError", "Contact phone number must be exactly 10 digits.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getFax().equals(fax) && companyValidation.isExistFax(fax)) {
            request.setAttribute("faxError", "Fax is existed");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (email.trim().isEmpty()) {
            request.setAttribute("emailError", "Email cannot be empty.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getEmail().equals(email) && companyValidation.isExistEmail(email)) {
            request.setAttribute("emailError", "Email exists.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if(contactEmail.trim().isEmpty()) {
            request.setAttribute("contactEmailError", "Contact Email must not be empty.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getContactemail().equals(contactEmail) && companyValidation.isExistContactEmail(contactEmail)) {
            request.setAttribute("contactEmailError", "Contact Email already exists.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }

        if (website.trim().isEmpty()) {
            request.setAttribute("webError", "Website must not be empty.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getWebsite().equals(website) && companyValidation.isExistWebsite(website)) {
            request.setAttribute("webError", "Website already exists.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }

        if (!taxCode.matches("[0-9]{10}")) {
            request.setAttribute("taxCodeError", "Tax Code must be exactly 10 digits.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getTaxCode().equals(taxCode) && companyValidation.isExistTaxCode(taxCode)) {
            request.setAttribute("taxCodeError", "Tax Code already exists.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }

        if (bank.trim().isEmpty()) {
            request.setAttribute("bankError", "Bank must not be empty.");
            request.setAttribute("company", company);
            request.getRequestDispatcher("updatecompany.jsp").forward(request, response);
            return;
        }
        if (!company.getBank().equals(bank) && companyValidation.isExistBank(bank)) {
            request.setAttribute("bankError", "Bank already exists.");
            request.setAttribute("company", company);
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
            request.setAttribute("message", "Company updated successfully!");
        } else {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Failed to update company.");
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
