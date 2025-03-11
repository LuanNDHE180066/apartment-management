/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.ApartmentDAO;
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
import model.Apartment;
import model.RoomType;
import util.Util;

/**
 *
 * @author pc
 */
@WebServlet(name = "UpdatenopersonRE", urlPatterns = {"/updatenopersonre"})
public class UpdatenopersonRE extends HttpServlet {

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
            out.println("<title>Servlet UpdatenopersonRE</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatenopersonRE at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String id = request.getParameter("id");

        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();

        List<RoomType> listRoomType = roomTypeDAO.getAll();
        session.setAttribute("listRoomType", listRoomType);
        if (id != null && !id.isEmpty()) {
            ApartmentDAO daoA = new ApartmentDAO();
            Apartment apartment = daoA.getById(id);
            if (apartment != null) {

                request.setAttribute("apartment", apartment);
                request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Apartment not found");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Invalid ID");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String noperson = request.getParameter("numberOfPerson");
        String information = request.getParameter("infor");

        if (information.trim().isBlank()) {
            request.setAttribute("message", "Information can not be blank");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
            return;
        }

        if (id == null || id.trim().isEmpty()) {
            request.setAttribute("message", "Invalid ID");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
            return;
        }

        ApartmentDAO apd = new ApartmentDAO();
        Apartment apartment = apd.getById(id); 
        request.setAttribute("apartment", apartment);

        if (apartment == null) {
            request.setAttribute("message", "Apartment not found");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
            return;
        }


        if (noperson == null || noperson.trim().isEmpty()) {
            request.setAttribute("message", "Noperson cannot be empty");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
            return;
        }

        int person;
        try {
            person = Integer.parseInt(noperson.trim());
            if (person < 0) {
                request.setAttribute("message", "Invalid number format! Please enter a valid positive number.");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid number format! Please enter a valid number.");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
            return;
        }


        apartment.setNumberOfPerson(person);
        apartment.setInfor(Util.stringNomalize(information));
        boolean isUpdated = apd.updateApartmentInforamtion(apartment);

        if (isUpdated) {
            request.setAttribute("message", "Update noperson successfully");
            request.setAttribute("status", "true");
        } else {
            request.setAttribute("message", "Update noperson failed");
            request.setAttribute("status", "false");
        }

        request.getRequestDispatcher("updatenopersonapartment.jsp").forward(request, response);
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
