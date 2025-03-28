/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.CategoryServiceDAO;
import dao.CompanyDAO;
import dao.MonthlyServiceDAO;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Util;

/**
 *
 * @author thanh
 */
@WebServlet(name = "AddNewServiceServlet", urlPatterns = {"/add-service-staff"})
public class AddNewServiceServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewServiceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewServiceServlet at " + request.getContextPath() + "</h1>");
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
        CategoryServiceDAO csd = new CategoryServiceDAO();
        CompanyDAO cd = new CompanyDAO();
        request.setAttribute("companies", cd.getAll());
        request.setAttribute("types", csd.getAll());
        request.getRequestDispatcher("addnewservice.jsp").forward(request, response);
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
        String unit = Util.stringNomalize(request.getParameter("unit"));
        CategoryServiceDAO csd = new CategoryServiceDAO();
        CompanyDAO cd = new CompanyDAO();
        String name = Util.stringNomalize(request.getParameter("name"));
        String price_raw = request.getParameter("price");
        price_raw = price_raw.replace(".", "");
        int price = Integer.parseInt(price_raw);
        String des = Util.stringNomalize(request.getParameter("des"));
        if (name.trim().isBlank() || des.trim().isBlank()) {
            request.setAttribute("error", "Tên dịch vụ không được để trống");
            request.setAttribute("companies", cd.getAll());
            request.setAttribute("types", csd.getAll());
            request.getRequestDispatcher("addnewservice.jsp").forward(request, response);
            return;
        }
        ServiceDAO sd = new ServiceDAO();
        if (sd.isExistName(name)) {
            request.setAttribute("error", "Tên dịch vụ đã tồn tại");
            request.setAttribute("companies", cd.getAll());
            request.setAttribute("types", csd.getAll());
            request.getRequestDispatcher("addnewservice.jsp").forward(request, response);
            return;
        }
        String categoryId = request.getParameter("category");
        String companyId = request.getParameter("company");
        int status = Integer.parseInt(request.getParameter("status"));
        String newServiceID =sd.addService(name, price, des, categoryId, companyId, status,unit);
        if(categoryId.equals("SV001")){//khi thêm 1 service bắt buộc mới thì tất các phòng sẽ tự động thêm
            MonthlyServiceDAO md = new MonthlyServiceDAO();
            md.addServiceToAllApartment(newServiceID);
        }
        response.sendRedirect("all-services");
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
