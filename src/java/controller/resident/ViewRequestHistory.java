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
import util.Util;

@WebServlet(name = "ViewRequestHistory", urlPatterns = {"/viewrequest_history"})
public class ViewRequestHistory extends HttpServlet {

    private static final int PAGE_SIZE = 8; // Number of rows per page

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String page = request.getParameter("page");
        if (null == page) {
            page = "1";
        }
        String from = request.getParameter("from");
        if(null == from) from ="";
        String to = request.getParameter("to");
        if(null == to) to ="";
        String typeRequest = request.getParameter("typeRequest");
        if(null == typeRequest) typeRequest ="";
        int numberPerPage = 8;
        Util u = new Util();
        RequestDAO rd = new RequestDAO();
        RequestTypeDAO rtd = new RequestTypeDAO();
        List<Request> allRequests = rd.getByResidentIDAndDate(acc.getpId(), from, to, typeRequest);
        if(!allRequests.isEmpty()){
            int totalPage_waiting = u.getTotalPage(allRequests, numberPerPage);
            request.setAttribute("totalPage", totalPage_waiting);
            request.setAttribute("currentPage", Integer.parseInt(page));
        }else{
            request.setAttribute("totalPage", 1);
            request.setAttribute("currentPage", 1);
        }
        allRequests = rd.getPageByNumber(allRequests, Integer.parseInt(page), numberPerPage);
        List<RequestType> listTypeRequest = rtd.getAll();
        request.setAttribute("listType", listTypeRequest);
        request.setAttribute("rid", acc.getpId());
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("selectedType", typeRequest);
        request.setAttribute("listRequest", allRequests);
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
