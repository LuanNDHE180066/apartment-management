package controller.resident;

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

@WebServlet(name = "ViewRuleServlet", urlPatterns = {"/view-rule-resident"})
public class ViewRuleServlet extends HttpServlet {

    private static final int RECORDS_PER_PAGE = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RuleDAO rd = new RuleDAO();
        Util u = new Util();

        // Get all rules
        List<Rule> list = rd.getAllRule();

        // Check if list is null or empty
        if (list == null || list.isEmpty()) {
            request.setAttribute("rules", null);
            request.setAttribute("currentPage", 1);
            request.setAttribute("totalPages", 0);
            request.setAttribute("message", "No rules found in the system.");
            request.getRequestDispatcher("viewrule-resident.jsp").forward(request, response);
            return;
        }

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

        request.getRequestDispatcher("viewrule-resident.jsp").forward(request, response);
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

        // Check if list is null or empty
        if (list == null || list.isEmpty()) {
            request.setAttribute("rules", null);
            request.setAttribute("currentPage", 1);
            request.setAttribute("totalPages", 0);
            request.setAttribute("message", "No rules found matching the search criteria.");
            request.getRequestDispatcher("viewrule-resident.jsp").forward(request, response);
            return;
        }

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

        request.getRequestDispatcher("viewrule-resident.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}