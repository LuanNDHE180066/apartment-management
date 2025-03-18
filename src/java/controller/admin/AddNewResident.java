package controller.admin;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.ResidentDAO;
import dao.RoleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import model.Account;
import model.Apartment;
import model.Resident;
import model.SendEmail;
import util.Util;
import static util.Util.encryptPassword;

@WebServlet(name = "AddNewResident", urlPatterns = {"/addNewResident"})
public class AddNewResident extends HttpServlet {

    private static final String JSP_PAGE = "addnewresident.jsp";
    private static final String VIEW_RESIDENT_REDIRECT = "view-resident";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ApartmentDAO aptDAO = new ApartmentDAO();
            List<Apartment> apts = aptDAO.getAll();
            request.setAttribute("apts", apts);
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred while loading the page.");
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");

        // Parameter extraction and validation
        String aptNumber = request.getParameter("apartment");
        String name = request.getParameter("name");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String gender = request.getParameter("gender");
        String isRepresent = request.getParameter("isRepresent");
        String role = request.getParameter("role");

        // Input validation
        if (isInvalidInput(aptNumber, name, dob, phone, id, isRepresent, role)) {
            handleValidationError(request, response, "All required fields must be provided.");
            return;
        }

        if (!phone.matches("\\d{10}")) {
            handleValidationError(request, response, "Phone number must be exactly 10 digits.");
            return;
        }

        if (!id.matches("\\d{12}")) {
            handleValidationError(request, response, "ID must be exactly 12 digits.");
            return;
        }

        Util util = new Util();
        ResidentDAO residentDAO = new ResidentDAO();
        RoleDAO roleDAO = new RoleDAO();
        LivingApartmentDAO livingApartmentDAO = new LivingApartmentDAO();

        // Prepare resident object
        Resident resident = new Resident();
        resident.setName(name);
        resident.setBod(dob); // Assuming dob is in a parseable format
        resident.setAddress(address);
        resident.setPhone(phone);
        resident.setCccd(id);
        resident.setRole(roleDAO.getById(role));
        resident.setGender(gender);

        // Handle representative-specific fields
        String password = util.generatePassword();
        if ("yes".equalsIgnoreCase(isRepresent)) {
            if (username == null || username.isEmpty() || email == null || email.isEmpty()) {
                handleValidationError(request, response, "Username and email are required for representatives.");
                return;
            }
            resident.setUsername(username);
            resident.setPassword(encryptPassword(password));
            resident.setEmail(email);
        }

        try {
            // Insert resident and get new ID
            String newResidentId = residentDAO.insertNewResident(resident);
            if (newResidentId == null) {
                handleValidationError(request, response, "Failed to add new resident.");
                return;
            }

            // Insert living apartment association
            Date currentDate = new Date(System.currentTimeMillis());
            if (!livingApartmentDAO.insertLivingApartment(newResidentId, aptNumber, currentDate.toString())) {
                residentDAO.deleteResident(newResidentId); // Rollback resident insertion
                handleValidationError(request, response, "Failed to associate resident with apartment.");
                return;
            }

            // Send email if representative
            if ("yes".equalsIgnoreCase(isRepresent) && email != null) {
                SendEmail sendEmail = new SendEmail();
                sendEmail.sendEmailResidentAccount(email, name, username, password);
            }

            // Set success flag and forward to JSP to show modal
            request.setAttribute("successMessage", "Resident added successfully!");
            ApartmentDAO aptDAO = new ApartmentDAO();
            request.setAttribute("apts", aptDAO.getAll());
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred during processing: " + e.getMessage());
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
        }
    }

    private boolean isInvalidInput(String aptNumber, String name, String dob, String phone, String id, String isRepresent, String role) {
        return aptNumber == null || aptNumber.isEmpty()
                || name == null || name.isEmpty()
                || dob == null || dob.isEmpty()
                || phone == null || phone.isEmpty()
                || id == null || id.isEmpty()
                || isRepresent == null || isRepresent.isEmpty()
                || role == null || role.isEmpty();
    }

    private void handleValidationError(HttpServletRequest request, HttpServletResponse response, String errorMessage)
            throws ServletException, IOException {
        ApartmentDAO aptDAO = new ApartmentDAO();
        request.setAttribute("apts", aptDAO.getAll());
        request.setAttribute("error", errorMessage);
        request.getRequestDispatcher(JSP_PAGE).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles adding a new resident and associating them with an apartment.";
    }
}
