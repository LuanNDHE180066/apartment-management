/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.StaffDAO;
import java.io.PrintWriter;
import model.Staff;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.Account;

/**
 *
 * @author pc
 */
@WebServlet(name = "EditprofileSTServlet", urlPatterns = {"/editprofileSTServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditprofileSTServlet extends HttpServlet {

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
            out.println("<title>Servlet EditprofileSTServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditprofileSTServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String eemail = request.getParameter("editProfileEmail");
        String ephone = request.getParameter("editProfilePhone");
        String eaddress = request.getParameter("editProfileAddress");
        String ebank = request.getParameter("editProfileBank");
        Part filePart = request.getPart("profileImage");

        if (eemail == null || !eemail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Invalid email format.");
            request.getRequestDispatcher("editprofileST.jsp").forward(request, response);
            return;
        }

        if (ephone == null || !ephone.matches("^0[0-9]{9}$")) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Please enter a valid phone number: 10 digits starting with 0.");
            request.getRequestDispatcher("editprofileST.jsp").forward(request, response);
            return;
        }

        if (eaddress == null || eaddress.trim().isEmpty()) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Address cannot be empty.");
            request.getRequestDispatcher("editprofileST.jsp").forward(request, response);
            return;
        }

        if (ebank == null || ebank.trim().isEmpty()) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Bank cannot be empty.");
            request.getRequestDispatcher("editprofileST.jsp").forward(request, response);
            return;
        }
        StaffDAO st = new StaffDAO();
        if (st.checkDupEmail(eemail) && !eemail.equals(account.getEmail())) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Email already exists. Please use a different email.");
            request.getRequestDispatcher("editprofileST.jsp").forward(request, response);
            return;
        }
        Staff staff = new Staff(account.getpId(), eemail, ephone, ebank, eaddress);
        if (filePart != null && filePart.getSize() > 0) {
            String uploadPath = getServletContext().getRealPath("/avartars");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Generate a unique filename using UUID
            String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File file = new File(uploadPath, fileName);
            String location = "avartars/" + fileName;

            // Save the file
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath());
            }
            staff.setImage(location);

            // Update resident profile image path
        }

        st.EditProfileSt(staff);
        staff = st.getById(account.getpId());

        session.setAttribute("person", staff);

        request.setAttribute("msg", "Update successfully");
        request.getRequestDispatcher("editprofileST.jsp").forward(request, response);
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
