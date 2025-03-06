/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import model.Resident;
import util.Util;

/**
 *
 * @author quang
 */
public class ViewAllResident extends HttpServlet {

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
            out.println("<title>Servlet ViewAllResident</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAllResident at " + request.getContextPath() + "</h1>");
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
        String name = request.getParameter("searchName");
        String status = request.getParameter("filterStatus");
        ResidentDAO daoR = new ResidentDAO();
        Util u = new Util();

        if (status == null || status.trim().isEmpty()) {
            status = "";
        }

        name = u.stringNomalize(name);

        List<Resident> listResident = daoR.filterListResident(name, status);
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        int totalPage = u.getTotalPage(listResident, 5);

        if (listResident.size() != 0) {
            listResident = u.getListPerPage(listResident, 5, page);
            request.setAttribute("listResident", listResident);
            for (int i = 0; i < listResident.size(); i++) {
                System.out.println(listResident.get(i).getImage());
            }
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", Integer.parseInt(page));
            request.setAttribute("isFilter", "true");
            request.getRequestDispatcher("viewallresident.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("totalPage", 1);
            request.setAttribute("currentPage", 1);
            request.setAttribute("message", "No result");
            request.getRequestDispatcher("viewallresident.jsp").forward(request, response);
        }

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
        ResidentDAO daoR = new ResidentDAO();
        List<Resident> listResident = daoR.getAll();
        request.setAttribute("listResident", listResident);
        request.getRequestDispatcher("viewallresident.jsp").forward(request, response);
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
