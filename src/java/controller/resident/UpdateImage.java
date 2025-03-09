/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.AccountDAO;
import dao.ResidentDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Part;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.OutputStream;
import model.Account;
import model.Resident;
import model.Staff;
import util.Util;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateImage", urlPatterns = {"/update-image"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateImage extends HttpServlet {

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
            out.println("<title>Servlet UpdateImage</title>");
            out.println("</head>");
            out.println("<body>");
            String message = (String) request.getAttribute("message");
            String fileName = request.getAttribute("message").toString().replace("Upload thành công: ", "");
            String imagePath = "images/avatar/" + fileName;
            out.println("<h3><%= message %></h3> <img src=\"" + imagePath + "\" alt=\"Ảnh đã upload\" style=\"max-width:300px;\">");
            out.println("<h1>Servlet UpdateImage at " + request.getContextPath() + "</h1>");
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
        Account ac = (Account) session.getAttribute("account");
        Part fileImage = request.getPart("file");
        String fileName = fileImage.getSubmittedFileName();
        if (!fileName.endsWith(".jpg")) {
            request.setAttribute("status", false);
            request.setAttribute("message", "Only upload file .jpg");
        } else {
            String source = "images/avatar/" + fileName;
            AccountDAO dao = new AccountDAO();
            dao.changeImageByAccount(ac, source);
            request.setAttribute("status", true);
            request.setAttribute("message", "Upload success: " + source);
        }
        Util util = new Util();
        if (ac.getRoleId() == 1) {
            ResidentDAO rd = new ResidentDAO();
            Resident re = rd.getById(ac.getpId());
            session.setAttribute("person", re);
        } else {
            StaffDAO sd = new StaffDAO();
            Staff staff = sd.getById(ac.getpId());
            session.setAttribute("person", staff);
        }
        request.getRequestDispatcher(util.getTableNameByRoleId(ac.getRoleId())).forward(request, response);
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
