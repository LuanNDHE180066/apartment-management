package controller.resident;

import dao.FeedbackDAO;
import dao.ServiceDAO;
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
import model.SendEmail;
import model.Service;
import model.Staff;
import validation.BadWordFilter;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB per file
        maxRequestSize = 1024 * 1024 * 50 // 50MB total
)
@WebServlet(name = "SendFeedback", urlPatterns = {"/sendfeedback"})
public class SendFeedback extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Gửi Phản Hồi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Gửi Phản Hồi tại " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServiceDAO rt = new ServiceDAO();
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        List<Service> listTypeOfRequest = rt.getAll();
        String rID = account.getpId();
        request.setAttribute("listOfTypeRequest", listTypeOfRequest);
        request.setAttribute("rID", rID);
        request.getRequestDispatcher("sendfeedback.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        ServiceDAO rt = new ServiceDAO();
        String rID = request.getParameter("rID");
        String tID = request.getParameter("typeOfRequest");
        String detail = request.getParameter("content");
        String rate_raw = request.getParameter("rate");
        int rate = 0;

        try {
            rate = Integer.parseInt(rate_raw);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi phân tích đánh giá: " + e);
        }

        String realPath = getServletContext().getRealPath("/asset/badwords.txt");
        BadWordFilter bwf = new BadWordFilter(realPath);

        if (bwf.containsBadWord(detail.toLowerCase())) {
            List<Service> listTypeOfRequest = rt.getAll();
            request.setAttribute("rID", rID);
            request.setAttribute("listOfTypeRequest", listTypeOfRequest);
            request.setAttribute("errorMessage", "Nội dung phản hồi của bạn không được chứa từ ngữ xúc phạm!");
            request.getRequestDispatcher("sendfeedback.jsp").forward(request, response);
            return;
        }

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
                    System.out.println("Lỗi khi lưu tệp: " + e.getMessage());
                }
            }
        }

        FeedbackDAO fd = new FeedbackDAO();
        if (hasUploadedImages) {
            fd.sendFeedback(detail, rID, tID, rate, imagePaths);
        } else {
            int success = fd.sendFeedback(detail, rID, tID, rate, null);
            if (success != 0) {
                List<Service> listTypeOfRequest = rt.getAll();
                request.setAttribute("rID", rID);
                request.setAttribute("listOfTypeRequest", listTypeOfRequest);
                request.setAttribute("errorMessage", "Lỗi xảy ra khi gửi phản hồi!");
                request.getRequestDispatcher("sendfeedback.jsp").forward(request, response);
            }
        }

        if (rate < 3) {
            SendEmail email = new SendEmail();
            StaffDAO s = new StaffDAO();
            List<Staff> staffs = s.getActiveStaffbyRole("2");
            List<String> emails = new ArrayList<>();
            for (Staff staff : staffs) {
                emails.add(staff.getEmail());
            }
            email.sendFeedbackMail(emails, tID, rate, detail);
        }

        response.sendRedirect("view-feed-back-user");
    }
}