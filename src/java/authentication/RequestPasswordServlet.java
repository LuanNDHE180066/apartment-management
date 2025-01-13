/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package authentication;

import dao.AccountDao;
import dao.TokenForgetPassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.SendEmail;
import model.TokenForgetPassword;

/**
 *
 * @author admin1711
 */
public class RequestPasswordServlet extends HttpServlet {

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
            out.println("<title>Servlet RequestPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestPasswordServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        AccountDao daoA = new AccountDao();
        String username = request.getParameter("username");
        try {
//            String email = daoA.getEmailByUsername(username);
//            String fullName = daoA.getFullNameByUsername(username);
//            SendEmail send = new SendEmail();
//            send.sendEmail(email, " RETRIEVE OLD PASSWORD ", "Hello " + fullName + ", your old password is: " + daoA.getAccountByUsername(username).getPassword());
//            request.setAttribute("message", "Your password has been sent to your email!");
//            request.setAttribute("status", "true");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            return;

            String email = daoA.getEmailByUsername(username);
            String fullName = daoA.getFullNameByUsername(username);
            if (email == null) {
                throw new Exception();
            }
            SendEmail send = new SendEmail();
            String token = send.generateToken();
            String link = "http://localhost:8080/apartment-management-2/reset-pass?token=" + token;
            TokenForgetPassword newToken = new TokenForgetPassword(token, username, false, send.expireDateTime());
            TokenForgetPassDAO daoT = new TokenForgetPassDAO();
            daoT.addToken(newToken);
            send.sendEmail(email, "APARTMENT MANAGEMENT SYSTEM: RESET PASSWORD", "<h2>Please click the link to "
                    + "reset your password: <a href=" + link + ">Click here</a></h2>");
            request.setAttribute("message", "Your password has been sent to your email!");
            request.setAttribute("status", "true");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            request.setAttribute("message", "Can not found Username!");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
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
