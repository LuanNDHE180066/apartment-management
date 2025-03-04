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
import java.util.UUID;
import model.Account;
import model.RequestType;
import model.SendEmail;
import model.Staff;

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

        // Define the upload directory correctly (inside deployed app folder)
        String uploadPath = request.getServletContext().getRealPath("/") + "uploads";

        File uploadDir = new File(uploadPath);

        // Ensure directory exists
        if (!uploadDir.exists()) {
            boolean dirCreated = uploadDir.mkdirs();
        }

        // List to store image paths
        List<String> imagePaths = new ArrayList<>();
        boolean hasUploadedImages = false;

        // Retrieve uploaded images
        Collection<Part> parts = request.getParts();
        for (Part part : parts) {
            // Check if the part is a file (it should have content and be from the correct field)
            if (part.getName().equals("images[]") && part.getSize() > 0) {
                String originalFileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

                // Ensure unique filename
                String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
                String filePath = uploadPath + File.separator + fileName;

                // Save file safely
                try {
                    part.write(filePath);
                    imagePaths.add("uploads/" + fileName); // Store relative path for database
                    hasUploadedImages = true;
                    System.out.println("Saved file: " + filePath);
                } catch (IOException e) {
                    System.out.println("Error saving file: " + e.getMessage());
                }
            }
        }

        // Debugging: Print final image paths
        System.out.println("Final image paths: " + imagePaths);

        // Save feedback and image paths to the database
        FeedbackDAO fd = new FeedbackDAO();
        if (hasUploadedImages) {
            fd.sendFeedback(detail, rID, tID, rate, imagePaths);
        } else {
            fd.sendFeedback(detail, rID, tID, rate, null);
        }

        // Redirect after successful submission
        response.sendRedirect("view-feed-back-user");
    }
}

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
