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
import java.text.SimpleDateFormat;
import java.util.Date;
import model.RoomType;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "AddNewRoomType", urlPatterns = {"/add-new-room-type"})
public class AddNewRoomType extends HttpServlet {

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
            out.println("<title>Servlet AddNewRoomType</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewRoomType at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("addnewroomtype.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String limitPerson_raw = request.getParameter("limitPerson");
        String square_raw = request.getParameter("square");
        String bedRoom_raw = request.getParameter("bedroom");
        String bathRoom_raw = request.getParameter("bathroom");
        String livingRoom_raw = request.getParameter("livingRoom");
        String balcony_raw = request.getParameter("balcony");

        int limitPerson, bedRoom, bathRoom, livingRoom, balcony = 0;
        float square = 0;
        RoomTypeDAO dao = new RoomTypeDAO();
        try {
            limitPerson = Integer.parseInt(limitPerson_raw);
            square = Float.parseFloat(square_raw);
            bathRoom = Integer.parseInt(bathRoom_raw);
            bedRoom = Integer.parseInt(bedRoom_raw);
            livingRoom = Integer.parseInt(livingRoom_raw);
            balcony = Integer.parseInt(balcony_raw);
            if (name.trim() == "" || name.trim().isEmpty()) {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Name can not be empty");
                request.getRequestDispatcher("addnewroomtype.jsp").forward(request, response);
                return;
            }
            Util u = new Util();
            name = u.stringNomalize(name);
            if (dao.checkExistNameRoomType(name)) {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Name room type is already exist");
                request.getRequestDispatcher("addnewroomtype.jsp").forward(request, response);
                return;
            }

            if (limitPerson < 0 || square < 0 || bathRoom < 0 || bedRoom < 0 || livingRoom < 0 || balcony < 0) {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Number can not smaller than 0");
                request.getRequestDispatcher("addnewroomtype.jsp").forward(request, response);
                return;
            }

            RoomType r = new RoomType(name, limitPerson, bedRoom, livingRoom, bathRoom, balcony, square);

            if (dao.insertRoomType(r)) {
                request.setAttribute("status", "true");
                request.setAttribute("message", "Insert successfully");
                request.getRequestDispatcher("addnewroomtype.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Failed to insert");
                request.getRequestDispatcher("addnewroomtype.jsp").forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {

        }
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
