/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.ResidentDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import model.Account;
import model.Apartment;
import model.Resident;
import model.SendEmail;
import util.Util;
import static util.Util.encryptPassword;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "AddNewResident", urlPatterns = {"/addNewResident"})
public class AddNewResident extends HttpServlet {

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
            out.println("<title>Servlet AddNewResident</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewResident at " + request.getContextPath() + "</h1>");
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
        ApartmentDAO aptDAO = new ApartmentDAO();
        List<Apartment> apts = aptDAO.getAll();
        request.setAttribute("apts", apts);
        request.getRequestDispatcher("addnewresident.jsp").forward(request, response);
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
        Account acc = (Account) session.getAttribute("account");
        String aptNumber = request.getParameter("apartment");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        String Representative = request.getParameter("isRepresent");

        if (aptNumber == null || aptNumber.isEmpty()) {
            request.setAttribute("error", "Apartment number couldn't empty");
            request.getRequestDispatcher("addnewresident.jsp").forward(request, response);
            return;
        }
        // Validate phone number (11 digits) and ID (12 digits)
        if (!phone.matches("\\d{10}")) {
            request.setAttribute("error", "Phone number must be exactly 10 digits.");
            request.getRequestDispatcher("addnewresident.jsp").forward(request, response);
            return;
        }
        if (!id.matches("\\d{12}")) {
            request.setAttribute("error", "ID must be exactly 12 digits.");
            request.getRequestDispatcher("addnewresident.jsp").forward(request, response);
            return;
        }
        Util u = new Util();
        //generate random password then send to new user
        String password = u.generatePassword();
        //insert to database with encryted password

        ResidentDAO rd = new ResidentDAO();
        RoleDAO roleD = new RoleDAO();
        LivingApartmentDAO lad = new LivingApartmentDAO();

        //insert to living apartment
        String password_encript = encryptPassword(password);

        Resident r = new Resident();
        r.setName(name);
        r.setBod(dob);
        r.setAddress(address);
        r.setPhone(phone);
        r.setCccd(id != null ? id : null);
        r.setRole(roleD.getById("1"));
        r.setPassword(password_encript);
        r.setGender(gender);
        if (Representative!=null) {
            r.setUsername(username);
            r.setEmail(email != null ? email : null);

        }
        //insert to db
        String newResidentID = rd.insertNewResident(r);
        if (newResidentID != null && username != null) {
             //insert resident to apartment
            Date date = new Date(System.currentTimeMillis());
            if (!lad.insertLivingApartment(newResidentID, aptNumber, date.toString())) {
                request.setAttribute("error", "Add to apartment unsuccessful");
                request.getRequestDispatcher("addnewresident.jsp").forward(request, response);
            }
            
            
            SendEmail e = new SendEmail();
            e.sendEmailResidentAccount(email, name, username, password);
        }

        response.sendRedirect("view-resident");
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
