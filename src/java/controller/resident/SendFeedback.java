/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.FeedbackDAO;
import dao.RequestTypeDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import model.Account;
import model.RequestType;
import model.SendEmail;
import model.Staff;
import validation.BadWordFilter;

/**
 *
 * @author NCPC
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB per file
        maxRequestSize = 1024 * 1024 * 50 // 50MB total
)
@WebServlet(name = "SendFeedback", urlPatterns = {"/sendfeedback"})
public class SendFeedback extends HttpServlet {

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
            out.println("<title>Servlet SendFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendFeedback at " + request.getContextPath() + "</h1>");
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
        RequestTypeDAO rt = new RequestTypeDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<RequestType> listTypeOfRequest = rt.getAll();
        String rID = account.getpId();
        request.setAttribute("listOfTypeRequest", listTypeOfRequest);
        request.setAttribute("rID", rID);
        request.getRequestDispatcher("sendfeedback.jsp").forward(request, response);

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

        // Fetch form fields
        String rID = request.getParameter("rID");
        String tID = request.getParameter("typeOfRequest");
        String detail = request.getParameter("content");
        String rate_raw = request.getParameter("rate");
        int rate = 0;

        try {
            rate = Integer.parseInt(rate_raw);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing rate: " + e);
        }

        // Check for bad words before proceeding
        // Use ServletContext to get the absolute path
        String realPath = getServletContext().getRealPath("/asset/badwords.txt");
        BadWordFilter bwf = new BadWordFilter(realPath);

        if (bwf.containsBadWord(detail.toLowerCase())) {
            request.setAttribute("errorMessage", "Your feedback content must not contain offensive words!");
            request.getRequestDispatcher("sendfeedback.jsp").forward(request, response);
            return;
        }

        // ----------- File upload handling ----------------
        String uploadPath = request.getServletContext().getRealPath("/") + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        List<String> imagePaths = new ArrayList<>();
        boolean hasUploadedImages = false;
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            if (part.getName().equals("images[]") && part.getSize() > 0) {
                String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
                String filePath = uploadPath + File.separator + fileName;
                try {
                    part.write(filePath);
                    imagePaths.add("uploads/" + fileName);
                    hasUploadedImages = true;
                } catch (IOException e) {
                    System.out.println("Error saving file: " + e.getMessage());
                }
            }
        }

        // Save feedback to database
        FeedbackDAO fd = new FeedbackDAO();
        if (hasUploadedImages) {
            fd.sendFeedback(detail, rID, tID, rate, imagePaths);
        } else {
            fd.sendFeedback(detail, rID, tID, rate, null);
        }

        // Send email alert if rate < 3
        if (rate < 3) {
            SendEmail email = new SendEmail();
            StaffDAO s = new StaffDAO();
            RequestTypeDAO rtd = new RequestTypeDAO();
            List<Staff> staffs = s.getActiveStaffbyRole(rtd.getById(tID).getDestination().getId());
            List<String> emails = new ArrayList<>();
            for (Staff staff : staffs) {
                emails.add(staff.getEmail());
            }
            email.sendFeedbackMail(emails, tID, rate, detail);
        }

        // Redirect after successful feedback submission
        response.sendRedirect("view-feed-back-user");
    }

}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
