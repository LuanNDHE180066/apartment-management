package controller.resident;

import dao.ResidentDAO;
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
import model.Resident;

@WebServlet(name = "EditprofileREServlet", urlPatterns = {"/editprofileREServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class EditprofileREServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "uploads";

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
        Part filePart = request.getPart("profileImage");

        if (eemail == null || !eemail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Invalid email format.");
            request.getRequestDispatcher("editprofileRE.jsp").forward(request, response);
            return;
        }

        if (ephone == null || !ephone.matches("^0[0-9]{9}$")) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Please enter a valid phone number: 10 digits starting with 0.");
            request.getRequestDispatcher("editprofileRE.jsp").forward(request, response);
            return;
        }

        if (eaddress == null || eaddress.trim().isEmpty()) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Address cannot be empty.");
            request.getRequestDispatcher("editprofileRE.jsp").forward(request, response);
            return;
        }

        ResidentDAO re = new ResidentDAO();
        if (re.checkDuplicateEmail(eemail) && !eemail.equals(account.getEmail())) {
            request.setAttribute("status", "false");
            request.setAttribute("msg", "Email already exists. Please use a different email.");
            request.getRequestDispatcher("editprofileRE.jsp").forward(request, response);
            return;
        }

        Resident resident = re.getById(account.getpId());
        // Handle Image Upload
        if (filePart != null && filePart.getSize() > 0) {
            String uploadPath = getServletContext().getRealPath("/avartars");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Generate a unique filename using UUID
            String fileName = UUID.randomUUID().toString() + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File file = new File(uploadPath, fileName);
            String location="avartars/"+fileName;

            // Save the file
            try (InputStream fileContent = filePart.getInputStream()) {
                Files.copy(fileContent, file.toPath());
            }
            resident.setImage(location);

            // Update resident profile image path
        }
        resident.setEmail(eemail);
        resident.setPhone(ephone);
        resident.setAddress(eaddress);

        re.EditProfileRe(resident);
        resident = re.getById(account.getpId());

        session.setAttribute("person", resident);
        request.setAttribute("msg", "Update successfully");
        request.getRequestDispatcher("editprofileRE.jsp").forward(request, response);
    }
}
