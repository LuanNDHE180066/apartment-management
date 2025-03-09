package controller.resident;

import dao.RequestDAO;
import dao.RequestTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Request;
import model.RequestType;

@WebServlet(name = "ViewRequestHistory", urlPatterns = {"/viewrequest_history"})
public class ViewRequestHistory extends HttpServlet {

    private static final int PAGE_SIZE = 8; // Number of rows per page

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }
        RequestDAO rd = new RequestDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();
        List<Request> allRequests = rd.getByResidentID(acc.getpId());
        int totalRequests = allRequests.size();
        int totalPages = (int) Math.ceil((double) totalRequests / PAGE_SIZE);
        int start = (page - 1) * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, totalRequests);
        List<Request> paginatedRequests = allRequests.subList(start, end);
        List<RequestType> listTypeRequest = rtd.getAll();
        request.setAttribute("listType", listTypeRequest);
        request.setAttribute("rid", acc.getpId());
        request.setAttribute("listRequest", paginatedRequests);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.getRequestDispatcher("view_request_history.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle POST requests if needed
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for viewing request history with pagination";
    }
}
