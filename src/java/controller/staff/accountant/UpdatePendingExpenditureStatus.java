/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff.accountant;

import dao.ExpenditureDAO;
import dao.HistoryExpenditureDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Expenditure;
import model.HistoryExpenditure;
import model.SendEmail;

/**
 *
 * @author quang
 */
@WebServlet(name = "UpdatePendingExpenditureStatus", urlPatterns = {"/update-pending-expenditure"})
public class UpdatePendingExpenditureStatus extends HttpServlet {

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
            out.println("<title>Servlet UpdatePendingExpenditureStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePendingExpenditureStatus at " + request.getContextPath() + "</h1>");
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
        Account a = (Account) session.getAttribute("account");
        String id = request.getParameter("id");
        String approve = request.getParameter("approve");
        HistoryExpenditureDAO daoHe = new HistoryExpenditureDAO();
        StaffDAO daoSt = new StaffDAO();
        daoHe.updateApproveStatus(id, a.getRoleId() + "", approve);

        HistoryExpenditure he = daoHe.getExpenditureById(id);
        ExpenditureDAO daoE = new ExpenditureDAO();
        if (daoE.checkExistId(he.getId()) && he.getAction().equalsIgnoreCase("insert")) {
            response.sendRedirect("index.jsp");
            return;
        }
        
        
        if (he.getChiefAccountantApproveStatus() == 1 && he.getCurrentAdminApproveStatus() == 1 && he.getAction().equalsIgnoreCase("update")) {
            String newId = daoE.generateExpenditureId();
            SendEmail send = new SendEmail();
            send.sendEmail(he.getModifiedBy().getEmail(), "Your expenditure has been accepted an expenditure " + he.getTitle(),
                    "Please check and confirm the expenditure" + he.getTitle());
            daoE.updateExpenditure(he);
        } else if (he.getChiefAccountantApproveStatus() == -1 || he.getCurrentAdminApproveStatus() == -1) {
            SendEmail send = new SendEmail();
            if (he.getModifiedBy().getId().equalsIgnoreCase(he.getCreatedStaff().getId())) {
                send.sendEmail(he.getModifiedBy().getEmail(), "Your update expenditure request has been deny expenditure: " + he.getTitle(),
                        "Please check and review the update expenditure request: " + he.getTitle());
            }
        }

      

        if (he.getChiefAccountantApproveStatus() == 1 && he.getCurrentAdminApproveStatus() == 1 && he.getAction().equalsIgnoreCase("insert")) {
            String newId = daoE.generateExpenditureId();
            SendEmail send = new SendEmail();
            send.sendEmail(he.getModifiedBy().getEmail(), "Your expenditure has been accepted an expenditure " + he.getTitle(),
                    "Please check and confirm the expenditure" + he.getTitle());
            he.setId(newId);
            daoHe.updateEidAfterInsert(he);
            daoE.addExpenditure(he);
        } else if (he.getChiefAccountantApproveStatus() == -1 || he.getCurrentAdminApproveStatus() == -1) {
            SendEmail send = new SendEmail();
            if (he.getModifiedBy().getId().equalsIgnoreCase(he.getCreatedStaff().getId())) {
                send.sendEmail(he.getModifiedBy().getEmail(), "Your expenditure request has been deny expenditure: " + he.getTitle(),
                        "Please check and review the expenditure" + he.getTitle());
            }
        }

        request.setAttribute("staffId", a.getpId());
        request.getRequestDispatcher("view-pending-expenditure").forward(request, response);
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
        processRequest(request, response);
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
