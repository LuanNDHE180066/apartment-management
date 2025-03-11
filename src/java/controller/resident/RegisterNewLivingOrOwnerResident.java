/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.RequestChangeResidentDAO;
import dao.ResidentDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.RequestChangeResident;
import model.Resident;
import static util.Util.encryptPassword;

/**
 *
 * @author quang
 */
@WebServlet(name = "RegisterNewLivingOrOwnerResident", urlPatterns = {"/register-new-living-or-owner-resident"})
public class RegisterNewLivingOrOwnerResident extends HttpServlet {

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
            out.println("<title>Servlet RegisterNewLivingOrOwnerResident</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterNewLivingOrOwnerResident at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
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
        ResidentDAO reDAO = new ResidentDAO();
        ApartmentDAO aDAO = new ApartmentDAO();
        OwnerApartmentDAO oDAO = new OwnerApartmentDAO();
        LivingApartmentDAO lDAO = new LivingApartmentDAO();
        RequestChangeResidentDAO requestChangeDAO = new RequestChangeResidentDAO();
        RoleDAO rDAO = new RoleDAO();

        String aid = request.getParameter("apartment");
        String changeResident = request.getParameter("residentType");
        String residentExists = request.getParameter("residentExists");

        LocalDateTime lc = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = lc.format(format);

        Resident owner = oDAO.getOwnerByApartmentID(aid).getRid();
        if (residentExists != null) {
            String newResidentId = request.getParameter("newResidentId");
            Resident newRe = reDAO.getById_v2(newResidentId);
            if (newRe == null) {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Resident ID is not exist");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }

            RequestChangeResident re = new RequestChangeResident(owner, newRe, aid, 0, 1, 0, formattedDate);
            if (changeResident.equals("living")) {
                re.setChangeType(1);
            }
            if (!requestChangeDAO.addNewRequestChange(re)) {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Failed to add");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
        } else {
            String name = request.getParameter("name").trim();
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone").trim();
            String email = request.getParameter("email").trim();
            String id = request.getParameter("id").trim();
            String username = request.getParameter("username").trim();

            if (!phone.matches("\\d{10}")) {
                request.setAttribute("message", "Phone number must be exactly 10 digits.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            if (!id.matches("\\d{12}")) {
                request.setAttribute("message", "ID must be exactly 12 digits.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }

            if (reDAO.checkDuplicatePhone(phone)) {
                request.setAttribute("message", "Phone is existed.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            if (reDAO.checkDuplicateEmail(email)) {
                request.setAttribute("message", "Email is existed..");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            if (reDAO.checkDuplicateID(id)) {
                request.setAttribute("message", "ID is existed..");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }

            if (reDAO.checkDuplicateUser(username)) {
                request.setAttribute("message", "Username is existed.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            ResidentDAO rd = new ResidentDAO();
            RoleDAO roleD = new RoleDAO();

            Resident r = new Resident();
            r.setName(name);
            r.setBod(dob);
            r.setAddress(address);
            r.setPhone(phone);
            r.setEmail(email);
            r.setCccd(id);
            r.setRole(roleD.getById("1"));
            r.setUsername(username);
            r.setPassword(null);
            if(gender.equals("M")){
                r.setGender("Nam");
            } else{
                r.setGender("Nữ");
            }

            RequestChangeResident re = new RequestChangeResident(owner, r, aid, 0, 1, 0, formattedDate);
            if (changeResident.equals("living")) {
                re.setChangeType(1);
            }
            if (!requestChangeDAO.addNewRequestChange(re)) {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Failed to add");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
        }

        request.setAttribute("status", "true");
        request.setAttribute("message", "Add request successful");
        request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);

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
