package controller.resident;

import dao.FeedbackDAO;
import dao.RequestTypeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Feedback;
import model.RequestType;
import util.Util;

@WebServlet(name = "FilterFeedback", urlPatterns = {"/filterfeedback"})
public class FilterFeedback extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestTypeDAO rtd = new RequestTypeDAO();

        List<RequestType> listTypeRequest = rtd.getAll();
        request.setAttribute("listTypeRequest", listTypeRequest);
        request.getRequestDispatcher("filter-user-feedback.jsp").forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        Util u = new Util();

        // Get filter inputs
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        String typeRequest = request.getParameter("typeRequest");

        FeedbackDAO fbd = new FeedbackDAO();
        RequestTypeDAO rtd = new RequestTypeDAO(); // Fetch request types again
        List<RequestType> listTypeRequest = rtd.getAll(); // Get request types
        List<Feedback> listFeedback = new ArrayList<>();

        // Apply filters
        if (from != null && !from.isEmpty() && to != null && !to.isEmpty()) {
            Date fromDate = u.convertStringToDate(from);
            Date toDate = u.convertStringToDate(to);
            if (fromDate.compareTo(toDate) >= 0) {
                listFeedback = fbd.getByResidentIDAndDateAndTypeRequest(acc.getpId(), to, from, typeRequest);
            } else {
                listFeedback = fbd.getByResidentIDAndDateAndTypeRequest(acc.getpId(), from, to, typeRequest);
            }
        } else if (typeRequest != null && !typeRequest.isEmpty()) {
            listFeedback = fbd.getByResidentIDAndDateAndTypeRequest(acc.getpId(), null, null, typeRequest);
        } else {
            listFeedback = fbd.getByResidentIDAndDateAndTypeRequest(acc.getpId(), null, null, null);
        }

        // Store selected filters in session
        session.setAttribute("from", from);
        session.setAttribute("to", to);
        session.setAttribute("selectedType", typeRequest);

        // Pagination settings
        int currentPage = 1;
        int recordsPerPage = 5;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            currentPage = Integer.parseInt(pageParam);
        }

        // Calculate pagination
        int totalRecords = listFeedback.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalRecords);
        List<Feedback> paginatedList = listFeedback.subList(start, end);

        // Pass filtered & paginated results to JSP
        request.setAttribute("listFeedbackU", paginatedList);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("listTypeRequest", listTypeRequest); 

        // Forward back to JSP
        request.getRequestDispatcher("filter-user-feedback.jsp").forward(request, response);
    }

}
