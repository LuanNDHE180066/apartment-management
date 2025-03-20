/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.FeedbackDAO;
import dao.ServiceDAO;
import dao.ResidentDAO;
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
import model.Feedback;
import model.RequestType;
import java.sql.Timestamp;
import model.Service;
import validation.BadWordFilter;

/**
 *
 * @author Lenovo
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB per file
        maxRequestSize = 1024 * 1024 * 50 // 50MB total
)
@WebServlet(name = "UpdateFeedback", urlPatterns = {"/update-feed-back"})
public class UpdateFeedback extends HttpServlet {

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
            out.println("<title>Servlet UpdateFeedback</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFeedback at " + request.getContextPath() + "</h1>");
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
        ServiceDAO rt = new ServiceDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Service> listServices = rt.getAll();
        String rID = account.getpId();

        String id = request.getParameter("fid");
        FeedbackDAO fd = new FeedbackDAO();
        Feedback f = fd.getById(id);
        request.setAttribute("listOfTypeRequest", listServices);
        request.setAttribute("rID", rID);
        request.setAttribute("feedback", f);
        request.getRequestDispatcher("editFeedback.jsp").forward(request, response);

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServiceDAO rt = new ServiceDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String id = null, tID = null, detail = null, rate_raw = null;

        // Retrieve text fields from form data using Part
        for (Part part : request.getParts()) {
            switch (part.getName()) {
                case "fID":
                    id = new String(part.getInputStream().readAllBytes()).trim();
                    break;
                case "typeOfRequest":
                    tID = new String(part.getInputStream().readAllBytes()).trim();
                    break;
                case "content":
                    detail = new String(part.getInputStream().readAllBytes()).trim();
                    break;
                case "rate":
                    rate_raw = new String(part.getInputStream().readAllBytes()).trim();
                    break;
            }
        }

        // Parse the rating value
        int rate = 0;
        try {
            rate = Integer.parseInt(rate_raw);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing rate: " + e);
        }

        String realPath = getServletContext().getRealPath("/asset/badwords.txt");
        BadWordFilter bwf = new BadWordFilter(realPath);

        if (bwf.containsBadWord(detail.toLowerCase())) {
            List<Service> listServices = rt.getAll();
            String rID = account.getpId();

            String fid = request.getParameter("fID");
            FeedbackDAO fd = new FeedbackDAO();
            Feedback f = fd.getById(fid);
            request.setAttribute("listOfTypeRequest", listServices);
            request.setAttribute("rID", rID);
            request.setAttribute("feedback", f);
            request.setAttribute("errorMessage", "Your feedback content must not contain offensive words!");
            request.getRequestDispatcher("editFeedback.jsp").forward(request, response);
            return;
        }
        // Directory to save uploaded files
        String uploadPath = request.getServletContext().getRealPath("/") + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Retrieve existing images from database
        FeedbackDAO fd = new FeedbackDAO();
        ServiceDAO rtd = new ServiceDAO();
        List<String> existingImages = fd.getFeedbackImgs(id);

        // List to store new uploaded images
        List<String> newImagePaths = new ArrayList<>();

        // Process new images
        for (Part part : request.getParts()) {
            if ("newImages[]".equals(part.getName()) && part.getSize() > 0) {
                String fileName = UUID.randomUUID().toString() + "_" + Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String filePath = uploadPath + File.separator + fileName;
                part.write(filePath);
                newImagePaths.add("uploads/" + fileName);
            }
        }

        // Retrieve existing images that were kept (not deleted by the user)
        List<String> keptImages = new ArrayList<>();
        for (Part part : request.getParts()) {
            if ("existingImages[]".equals(part.getName()) && part.getSize() > 0) {
                keptImages.add(new String(part.getInputStream().readAllBytes()).trim());
            }
        }

        // Final image list (kept + new images)
        List<String> finalImageList = new ArrayList<>(keptImages);
        finalImageList.addAll(newImagePaths);

        // Update database images
        fd.deleteFBImg(id);
        if (!finalImageList.isEmpty()) {
            fd.insertImgFeedback(finalImageList, id);
        }

        // Remove deleted images from server storage
        if (existingImages != null) {
            for (String imgPath : existingImages) {
                if (!keptImages.contains(imgPath)) {
                    File file = new File(request.getServletContext().getRealPath("/") + imgPath);
                    if (file.exists()) {
                        file.delete();
                    }
                }
            }
        }

        // Update feedback details
        Feedback feedback = fd.getById(id);
        feedback.setServices(rtd.getById(tID));
        feedback.setDetail(detail);
        feedback.setRate(rate);
        feedback.setStatus(0);
        fd.editFeedback(feedback);

        response.sendRedirect("view-feed-back-user");
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
