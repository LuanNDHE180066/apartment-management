/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CategoryServiceDAO;
import dao.CompanyDAO;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.CategoryService;
import model.Company;
import model.Service;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ViewAllServices", urlPatterns = {"/all-services"})
public class ViewAllServices extends HttpServlet {

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
            out.println("<title>Servlet ViewAllServices</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAllServices at " + request.getContextPath() + "</h1>");
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
        ServiceDAO sd = new ServiceDAO();
        CategoryServiceDAO csd = new CategoryServiceDAO();
        CompanyDAO cd = new CompanyDAO();
        List<Company> listCompany = cd.getAll();
        List<Service> listServices = sd.getAll();
        List<CategoryService> listCategory = csd.getAll();

        request.setAttribute("listServices", listServices);
        request.setAttribute("listCategories", listCategory);
        request.setAttribute("listCompanies", listCompany);
        request.getRequestDispatcher("viewallservices.jsp").forward(request, response);
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
        String status = request.getParameter("status");
        String category = request.getParameter("category");
        String company = request.getParameter("company");

        if (status == null) status = "";
        if (category == null) category = "";
        if (company == null) company = "";

        ServiceDAO sd = new ServiceDAO();
        CategoryServiceDAO csd = new CategoryServiceDAO();
        CompanyDAO cd = new CompanyDAO();
        
        List<Service> listServices = sd.filterByCompanyAndCategoryAndStatus(category, company, status);

        HttpSession session = request.getSession();
        session.setAttribute("status", status.isEmpty() ? null : status);
        session.setAttribute("category", category.isEmpty() ? null : category);
        session.setAttribute("company", company.isEmpty() ? null : company);
        
        request.setAttribute("listServices", listServices);
        request.setAttribute("listCategories", csd.getAll());
        request.setAttribute("listCompanies", cd.getAll());
        request.getRequestDispatcher("viewallservices.jsp").forward(request, response);
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
