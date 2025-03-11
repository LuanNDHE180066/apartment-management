/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.NewDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.News;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "ViewNewServlet", urlPatterns = {"/view-news"})
public class ViewNewServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewNewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewNewServlet at " + request.getContextPath() + "</h1>");
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
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String title = request.getParameter("title");
        Util u = new Util();

        if (title == null || title.trim().isEmpty()) {
            title = "";
        } else {
            title = u.stringNomalize(title);
        }
        if (startDate == null || startDate.trim().isEmpty()) {
            startDate = "";
        }
        if (endDate == null || endDate.trim().isEmpty()) {
            endDate = "";
        }

        NewDAO dao = new NewDAO();
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        List<News> listNews = dao.filterNews(title, startDate, endDate);
        int totalPage = u.getTotalPage(listNews, 3);
        if (!listNews.isEmpty()) {
            listNews = u.getListPerPage(listNews, 3, page);
        }
        session.setAttribute("listNews", listNews);
        request.setAttribute("totalPage", totalPage > 0 ? totalPage : 1);
        request.setAttribute("currentPage", Integer.parseInt(page));
        request.setAttribute("title", title);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        if (listNews.isEmpty()) {
            request.setAttribute("message", "No result found.");
        }
        request.getRequestDispatcher("viewallnews.jsp").forward(request, response);
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
        doGet(request, response);
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
