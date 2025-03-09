package controller.admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import dao.RuleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Rule;
import util.Util;
import static util.Util.stringNomalize;

/**
 *
 * @author thanh
 */
@WebServlet(name = "ViewRuleAdminServlet", urlPatterns = {"/view-rule-admin"})
public class ViewRuleAdminServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 5;

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
            out.println("<title>Servlet ViewRuleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewRuleServlet at " + request.getContextPath() + "</h1>");
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
        RuleDAO rd = new RuleDAO();
        Util u = new Util();

        // Get all rules
        List<Rule> list = rd.getAllRule();

        // Get current page
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Calculate total pages
        int totalRecords = list.size();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / RECORDS_PER_PAGE);

        // Get paginated list
        list = u.getListPerPage(list, RECORDS_PER_PAGE, request.getParameter("page"));

        // Set attributes
        request.setAttribute("rules", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("viewrule-admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RuleDAO rd = new RuleDAO();
        Util u = new Util();
        HttpSession session = request.getSession();

        // Get filters from request
        String title = request.getParameter("title");
        String date = request.getParameter("date");

        // Store or remove session attributes
        if (title != null && !title.isEmpty()) {
            session.setAttribute("title", title);
        } else {
            session.removeAttribute("title");
        }
        if (date != null && !date.isEmpty()) {
            session.setAttribute("date", date);
        } else {
            session.removeAttribute("date");
        }

        // Filter rules
        List<Rule> list = rd.filterRule(stringNomalize(title), date);

        // Get current page
        int page = 1;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        // Calculate total pages
        int totalRecords = list.size();
        int totalPages = (int) Math.ceil(totalRecords * 1.0 / RECORDS_PER_PAGE);

        // Get paginated list
        list = u.getListPerPage(list, RECORDS_PER_PAGE, request.getParameter("page"));

        // Set attributes
        request.setAttribute("rules", list);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("viewrule-admin.jsp").forward(request, response);
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
