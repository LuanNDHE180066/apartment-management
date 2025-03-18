/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.RepresentResidentChangeRequestDAO;
import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RepresentResidentChangeRequest;
import model.SendEmail;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "UpdatePendingChangeRepresentRequestStatus", urlPatterns = {"/update-pending-change-represent-request-status"})
public class UpdatePendingChangeRepresentRequestStatus extends HttpServlet {

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
            out.println("<title>Servlet UpdatePendingChangeRepresentRequestStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePendingChangeRepresentRequestStatus at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        String approve = request.getParameter("approve");

        RepresentResidentChangeRequestDAO rrcDAO = new RepresentResidentChangeRequestDAO();
        LivingApartmentDAO laDAO = new LivingApartmentDAO();
        OwnerApartmentDAO oaDAO = new OwnerApartmentDAO();
        ResidentDAO reDAO = new ResidentDAO();

        int admin_approve = rrcDAO.checkStatus(id);
        if (admin_approve != 0) {
            response.sendRedirect("index.jsp");
        }
        rrcDAO.updateStatus(id, approve);

        RepresentResidentChangeRequest re = rrcDAO.getById(id);
        if (re.getAdminApprove() == 1) {
            System.out.println(re.getNewRepresentPerson().getpId() + "  r: " + re.getaId());
            laDAO.changeIsRepresent("1", re.getNewRepresentPerson().getpId(), re.getaId());
            laDAO.changeIsRepresent("0", re.getOldRepresentPerson().getpId(), re.getaId());

            if (!laDAO.checkIsRepresent(re.getOldRepresentPerson().getpId()) && !oaDAO.isHomeOwner(re.getOldRepresentPerson().getpId())) {
                reDAO.setNullUsernameAndPassword(re.getOldRepresentPerson().getpId());
            }

            if (re.getIsExistAccount() == 0 && re.getAdminApprove() == 1) {
                String username = re.getNewRepresentPerson().getUsername();
                Util u = new Util();
                String password = u.generatePassword();

                SendEmail send = new SendEmail();
                reDAO.setUsernameAndPassword(rrcDAO.getNewUsername(re.getId() + ""), password, re.getNewRepresentPerson().getpId());
                send.sendEmailResidentAccount(re.getNewRepresentPerson().getEmail(), re.getNewRepresentPerson().getName(),
                        rrcDAO.getNewUsername(re.getId() + ""), password);
                password = u.encryptPassword(password);

            } else {
                SendEmail send = new SendEmail();
                send.sendEmail(oaDAO.getOwnerByApartmentID(re.getaId()).getRid().getEmail(), "Reject change represent person", "ban da bi tu choi kikiki");
            }
        }
        request.getRequestDispatcher("view-pending-change-represent-preson").forward(request, response);
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
