/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.RepresentResidentChangeRequestDAO;
import dao.ResidentDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import model.LivingApartment;
import model.OwnerApartment;
import model.RepresentResidentChangeRequest;
import model.Resident;
import model.SendEmail;
import model.Staff;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "ChangeRepresentResident", urlPatterns = {"/change-represent-resident"})
public class ChangeRepresentResident extends HttpServlet {

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
            out.println("<title>Servlet ChangeRepresentResident</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeRepresentResident at " + request.getContextPath() + "</h1>");
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
        String aid = request.getParameter("apartment");
        LivingApartmentDAO laDAO = new LivingApartmentDAO();
        OwnerApartmentDAO oaDAO = new OwnerApartmentDAO();
        if (aid != null && aid != "") {
            Resident representResident = laDAO.getRepresentedResidentByAid(aid).getRid();
            List<LivingApartment> listNonRepresentResident = laDAO.getAllNonRepresentResident(aid);
            OwnerApartment owner = oaDAO.getOwnerByApartmentID(aid);
            request.setAttribute("aid", aid);
            request.setAttribute("owner", owner);
            request.setAttribute("representResident", representResident);
            request.setAttribute("listNonRepresent", listNonRepresentResident);
        }

        request.getRequestDispatcher("changeRepresentResident.jsp").forward(request, response);
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
        String aid = request.getParameter("aid");
        String oldRepresentId = request.getParameter("oldRepresent");
        String newRepresentId = request.getParameter("newRepresent");
        String username = request.getParameter("username");
        String accountExist = request.getParameter("accountExist");
        String owner = request.getParameter("owner-id");

        LivingApartmentDAO laDAO = new LivingApartmentDAO();
        ResidentDAO reDAO = new ResidentDAO();
        StaffDAO stDAO = new StaffDAO();

        if (username != null) {
            username = username.trim();
        }
        Util u = new Util();
        if (reDAO.checkDuplicatateUsername(username) || stDAO.checkDuplicatateUsername(username)) {
            request.setAttribute("message", "Username is existed");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("changeRepresentResident.jsp").forward(request, response);
            return;
        } else if (accountExist != null && reDAO.getById_v2(newRepresentId).getUsername() == null) {
            request.setAttribute("message", "New represent person is not has account");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("changeRepresentResident.jsp").forward(request, response);
            return;
        } else if (accountExist == null && reDAO.getById_v2(newRepresentId).getUsername() != null) {
            request.setAttribute("message", "New represent person is already has account");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("changeRepresentResident.jsp").forward(request, response);
            return;
        }

        LocalDate lc = LocalDate.now();
        String stringDate = lc.toString();
        RepresentResidentChangeRequest re = null;
        RepresentResidentChangeRequestDAO rrcDAO = new RepresentResidentChangeRequestDAO();
        if (accountExist == null && reDAO.getById_v2(newRepresentId).getUsername() == null) {
            re = new RepresentResidentChangeRequest(reDAO.getById_v2(oldRepresentId),
                    reDAO.getById_v2(newRepresentId), aid, 0,
                    stringDate, accountExist == null ? 0 : 1, username, reDAO.getById_v2(owner));
        } else {
            re = new RepresentResidentChangeRequest(reDAO.getById_v2(oldRepresentId),
                    reDAO.getById_v2(newRepresentId), aid, 0,
                    stringDate, accountExist == null ? 0 : 1, username, reDAO.getById_v2(owner));
        }
        if (rrcDAO.insertNewRequest(re)) {
            request.setAttribute("message", "Add request successful");
            SendEmail send = new SendEmail();
            List<Staff> eStaff = stDAO.getAllAdmin();
            send.sendEmailToWorkingAdmin(eStaff, "REQUEST CHANGE REPRESENT PERSON FROM " + re.getaId() + " is created at " + re.getRequestDate());
            request.setAttribute("status", "true");
        } else {
            request.setAttribute("message", "Failed to insert request");
            request.setAttribute("status", "false");
        }

        request.getRequestDispatcher("changeRepresentResident.jsp").forward(request, response);

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
