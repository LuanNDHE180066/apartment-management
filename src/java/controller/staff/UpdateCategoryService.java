/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.staff;

import dao.CategoryServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CategoryService;
import util.Util;
import validation.CategoryServiceValidation;

/**
 *
 * @author PC
 */
@WebServlet(name="UpdateCategoryService", urlPatterns={"/update-categoryservice-staff"})
public class UpdateCategoryService extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet UpdateCategoryService</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateCategoryService at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        CategoryServiceDAO dao = new CategoryServiceDAO();
        CategoryService categoryservices = dao.getByCategoryId(id);
        request.setAttribute("categoryservice", categoryservices);
        request.getRequestDispatcher("updatecategoryservice.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        CategoryServiceDAO csd = new CategoryServiceDAO();
        CategoryServiceValidation val = new CategoryServiceValidation();
        name = Util.stringNomalize(name);
        note = Util.stringNomalize(note);              
        if(name.isBlank()){
            request.setAttribute("categoryservice", csd.getByCategoryId(id));
            request.setAttribute("status", false);
            request.setAttribute("error", "Name is not allow blank ");
            request.getRequestDispatcher("updatecategoryservice.jsp").forward(request, response);
            return;
        }                
        if(!name.matches("^\\s[a-zA-Z0-9]+$")){
            request.setAttribute("categoryservice", csd.getByCategoryId(id));
            request.setAttribute("status", false);
            request.setAttribute("error", "Name is only allow world a-z and 0-9");
            request.getRequestDispatcher("updatecategoryservice.jsp").forward(request, response);
            return;
        }
        if(val.isExistedNameExceptSeft(Util.stringNomalize(name),id)){
            request.setAttribute("categoryservice", csd.getByCategoryId(id));
            request.setAttribute("status", false);
            request.setAttribute("error", "Name is existed");
            request.getRequestDispatcher("updatecategoryservice.jsp").forward(request, response);
            return;
        }     
        CategoryService c = new CategoryService(id, name, note); 
        csd.updateCategoryService(c);        
        request.setAttribute("categoryservice", c);
        request.setAttribute("status", true);
        request.setAttribute("error", "Update sucess");
        request.getRequestDispatcher("updatecategoryservice.jsp").forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
