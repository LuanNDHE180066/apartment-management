/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.FeedbackDAO;
import dao.RequestTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Feedback;
import model.RequestType;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "ViewAllFeedback", urlPatterns = {"/view-all-feedback"})
public class ViewAllFeedback extends HttpServlet {

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
            out.println("<title>Servlet ViewAllFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAllFeedback at " + request.getContextPath() + "</h1>");
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
        List<Feedback> listFeedback;
        RequestTypeDAO daoRT = new RequestTypeDAO();
        Account acc = (Account) session.getAttribute("account");
        FeedbackDAO daoF = new FeedbackDAO();
        listFeedback = daoF.getFeedbackByRole(String.valueOf(acc.getRoleId()));
        String searchName = request.getParameter("searchName");
        String serviceType = request.getParameter("serviceType");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        Util u = new Util();

        searchName = u.stringNomalize(searchName);

        if (serviceType == null || serviceType.trim().isEmpty()) {
            serviceType = "";
        }

        if (startDate == null || startDate.trim().isEmpty()) {
            startDate = "";
        }
        if (endDate == null || endDate.trim().isEmpty()) {
            endDate = "";
        }

        listFeedback = daoF.filterFeedback(searchName, serviceType, startDate, endDate, String.valueOf(acc.getRoleId()));

        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        List<RequestType> listRequestType = daoRT.getAll();
        int totalPage = u.getTotalPage(listFeedback, 3);
        if (listFeedback.size() != 0) {
            listFeedback = u.getListPerPage(listFeedback, 3, page);
            session.setAttribute("listRequestType", listRequestType);
            session.setAttribute("listFeedback", listFeedback);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", Integer.parseInt(page));
            request.getRequestDispatcher("viewallfeedback.jsp").forward(request, response);
        } else {
            request.setAttribute("totalPage", 1);
            request.setAttribute("currentPage", 1);
            session.setAttribute("listRequestType", listRequestType);
            session.setAttribute("listFeedback", listFeedback);
            request.setAttribute("message", "No result");
            request.getRequestDispatcher("viewallfeedback.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        FeedbackDAO daoF = new FeedbackDAO();
        List<Feedback> listFeedback = daoF.getFeedbackByRole(String.valueOf(acc.getRoleId()));
        session.setAttribute("listFeedback", listFeedback);
        request.getRequestDispatcher("viewallfeedback.jsp").forward(request, response);
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
