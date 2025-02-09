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
        request.setAttribute("pageName", "Update");
        request.setAttribute("url", "update-company");
        request.getRequestDispatcher("addnewcompany.jsp").forward(request, response);
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

        String id = request.getParameter("id");
        String name = getSafeParameter(request, "name");
        String phone = getSafeParameter(request, "phone");
        String contactPhone = getSafeParameter(request, "contactPhone");
        String fax = getSafeParameter(request, "fax");
        String email = getSafeParameter(request, "email");
        String contactEmail = getSafeParameter(request, "contactemail");
        String website = getSafeParameter(request, "website");
        String taxCode = getSafeParameter(request, "taxCode");
        String bank = getSafeParameter(request, "bank");
        String address = getSafeParameter(request, "address");
        String description = getSafeParameter(request, "description");

        boolean hasError = false;
                Company company = new Company(id, name, phone, contactPhone, fax, email, contactEmail, website, taxCode, bank, description, address);

        CompanyValidation companyValidation = new CompanyValidation(company);
        // Validation errors
        if (name.isEmpty()) {
            request.setAttribute("nameError", "Name cannot be blank.");
            hasError = true;
        }
        if (!phone.matches("^\\d{11}$")) {
            request.setAttribute("phoneError", "Phone number must be exactly 11 digits.");
            hasError = true;
        }
        if (!contactPhone.matches("^\\d{11}$")) {
            request.setAttribute("contactPhoneError", "Contact phone must be exactly 11 digits.");
            hasError = true;
        }
        if (!fax.matches("^\\d{10}$")) {
            request.setAttribute("faxError", "Fax must be exactly 10 digits.");
            hasError = true;
        }
        if (!taxCode.matches("^\\d{10}$")) {
            request.setAttribute("taxCodeError", "Tax code must be exactly 10 digits.");
            hasError = true;
        }
        if (companyValidation.isExistEmail(email)) {
            request.setAttribute("emailError", "Email is existed");
            hasError = true;
        }
        if (companyValidation.isExistAddress(address)) {
            request.setAttribute("addressError", "address is existed");
            hasError = true;
        }
        if (companyValidation.isExistBank(bank)) {
            request.setAttribute("bankError", "Bank is existed");
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
        if (companyValidation.isExistWebsite(website)) {
            request.setAttribute("webError", "Web Site is existed");
            hasError = true;
        }
        if (hasError) {
            request.setAttribute("company", new Company(id, name, phone, contactPhone, fax, email, contactEmail, website, taxCode, bank, description, address));
            request.setAttribute("pageName", "Update");
            request.setAttribute("url", "update-company?id=" + id);
            request.getRequestDispatcher("addnewcompany.jsp").forward(request, response);
            return;
        }

        CompanyDAO cd = new CompanyDAO();
        cd.updateCompany(company);
        List<Company> list = cd.getAll();
        HttpSession session = request.getSession();
        session.setAttribute("companies", list);
        response.sendRedirect("view-all-company");
    }

    /**
     * Utility method to safely get request parameters and avoid
     * NullPointerException
     */
    /**
     * Utility method to safely get request parameters and avoid
     * NullPointerException
     */
    private String getSafeParameter(HttpServletRequest request, String paramName) {
        String value = request.getParameter(paramName);
        return (value != null) ? value.trim() : "";
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
