/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Resident;
import model.SendEmail;
import util.Util;
import static util.Util.encryptPassword;

/**
 *
 * @author NCPC
 */
@WebServlet(name = "UpdateResident", urlPatterns = {"/updateRE"})
public class UpdateResident extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateResident</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateResident at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("rid");
        ResidentDAO rd = new ResidentDAO();
        Resident r = rd.getById(id);
        request.setAttribute("resident", r);
        request.getRequestDispatcher("updateRE.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        // Retrieve form parameters from JSP
        String id = request.getParameter("rid");
        String name = request.getParameter("name");
        String dob = request.getParameter("bod");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String cccd = request.getParameter("id");  // This might be empty

        String phone = request.getParameter("phone");
        String username = request.getParameter("username");  // This might be empty
        String email = request.getParameter("email");  // This might be empty

        // Basic validation (you can expand this)
        try {
            // Check required fields
            if (name == null || name.trim().isEmpty()
                    || dob == null || dob.trim().isEmpty()
                    || gender == null || gender.trim().isEmpty()
                    || address == null || address.trim().isEmpty()
                    || phone == null || phone.trim().isEmpty()) {
                request.setAttribute("error", "All required fields must be filled.");
                request.getRequestDispatcher("updateRE.jsp").forward(request, response);
                return;
            }

            // Additional validation for optional fields
            if (cccd != null && !cccd.trim().isEmpty() && !cccd.matches("\\d{12}")) {
                request.setAttribute("error", "ID must be 12 digits.");
                request.getRequestDispatcher("updateRE.jsp").forward(request, response);
                return;
            }

            if (!phone.matches("\\d{10}")) {
                request.setAttribute("error", "Phone must be 10 digits.");
                request.getRequestDispatcher("updateRE.jsp").forward(request, response);
                return;
            }

            if (username != null && !username.trim().isEmpty() && username.length() < 4) {
                request.setAttribute("error", "Username must be at least 4 characters.");
                request.getRequestDispatcher("updateRE.jsp").forward(request, response);
                return;
            }

            if (email != null && !email.trim().isEmpty() && !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
                request.setAttribute("error", "Invalid email format.");
                request.getRequestDispatcher("updateRE.jsp").forward(request, response);
                return;
            }

            ResidentDAO rd = new ResidentDAO();
            Util u = new Util();

            Resident resident = rd.getById(id);
            resident.setName(name);
            resident.setBod(dob);
            resident.setGender(gender);
            resident.setAddress(address);
            resident.setCccd(cccd != null ? cccd.trim() : null);
            resident.setPhone(phone);
            boolean success = rd.updateRE(resident);
            if (success) {
                request.setAttribute("success", "Update Successfull");
                request.setAttribute("resident", rd.getById(id));
                request.getRequestDispatcher("updateRE.jsp").forward(request, response);
            }

        } catch (IOException e) {
            // Handle any unexpected errors
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("updateRE.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
