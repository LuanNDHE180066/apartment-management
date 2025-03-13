/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.RoomTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.RoomType;
import util.Util;

/**
 *
 * @author pc
 */
@WebServlet(name = "ViewRoomTypeServlet", urlPatterns = {"/view-roomtype"})
public class ViewRoomTypeServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewRoomTypeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewRoomTypeServlet at " + request.getContextPath() + "</h1>");
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
        String searchName = request.getParameter("searchName");
        String page = request.getParameter("page");
        if (searchName == null) {
            searchName = "";
        }
        Util u = new Util();
        searchName = u.stringNomalize(searchName);
        RoomTypeDAO RT = new RoomTypeDAO();
        List<RoomType> listRoomType = RT.filterRoomType(searchName);

        if (listRoomType.size() != 0) {
            int totalPage = u.getTotalPage(listRoomType, 5);
            listRoomType = u.getListPerPage(listRoomType, 5, page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", page == null ? "1" : page);
            request.setAttribute("roomtype", listRoomType);
        } else {
            request.setAttribute("totalPage", 1);
            request.setAttribute("currentPage", page == null ? "1" : page);
        }

        request.getRequestDispatcher("viewroomtype.jsp").forward(request, response);
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
