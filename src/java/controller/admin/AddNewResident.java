package controller.admin;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.ResidentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import model.Apartment;
import model.LivingApartment;
import model.OwnerApartment;
import model.Resident;
import model.Role;
import model.SendEmail;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import util.Util;
import static util.Util.encryptPassword;

@WebServlet("/addNewResident")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class AddNewResident extends HttpServlet {

    private ResidentDAO residentDAO = new ResidentDAO();
    private LivingApartmentDAO lvd = new LivingApartmentDAO();

    @Override
    public void init() throws ServletException {
        residentDAO = new ResidentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Apartment> apartments = new ApartmentDAO().getAll();
        request.setAttribute("apts", apartments);
        request.getRequestDispatcher("/addnewresident.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String contentType = request.getContentType();
        if (contentType != null && contentType.contains("application/json")) {
            handleMultipleResidents(request, response);
        } else {
            handleSingleResident(request, response);
        }
    }

    private void handleSingleResident(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Extract form data
            String name = request.getParameter("name");
            String dob = request.getParameter("bod");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String apartment = request.getParameter("apartment");
            String roleId = request.getParameter("role");
            String cccd = request.getParameter("cccd");
            String isRepresent = request.getParameter("isRepresent");
            String username = request.getParameter("username");
            String email = request.getParameter("email");

            // Validate required fields
            if (name == null || name.trim().isEmpty() || dob == null || dob.trim().isEmpty()
                    || gender == null || gender.trim().isEmpty() || phone == null || phone.trim().isEmpty()
                    || address == null || address.trim().isEmpty() || apartment == null || apartment.trim().isEmpty()
                    || roleId == null || roleId.trim().isEmpty()) {
                request.setAttribute("error", "All required fields must be filled.");
                forwardToForm(request, response);
                return;
            }

            // New constraint: If isRepresent is "yes", username and email must be provided
            if ("yes".equalsIgnoreCase(isRepresent)) {
                if (username == null || username.trim().isEmpty() || email == null || email.trim().isEmpty()) {
                    request.setAttribute("error", "Username and email are required when the resident is a representative.");
                    forwardToForm(request, response);
                    return;
                }
            }

            // Validate phone, CCCD, and username (if provided)
            if (!phone.matches("\\d{10}")) {
                request.setAttribute("error", "Phone number must be exactly 10 digits.");
                forwardToForm(request, response);
                return;
            }
            if (cccd != null && !cccd.trim().isEmpty() && !cccd.matches("\\d{12}")) {
                request.setAttribute("error", "CCCD must be exactly 12 digits.");
                forwardToForm(request, response);
                return;
            }
            if (username != null && !username.trim().isEmpty() && username.contains(" ")) {
                request.setAttribute("error", "Username cannot contain spaces.");
                forwardToForm(request, response);
                return;
            }

            // Check for duplicates
            if (email != null && !email.trim().isEmpty() && residentDAO.checkDuplicateEmail(email)) {
                request.setAttribute("error", "Email already exists.");
                forwardToForm(request, response);
                return;
            }
            if (residentDAO.checkDuplicatePhone(phone)) {
                request.setAttribute("error", "Phone number already exists.");
                forwardToForm(request, response);
                return;
            }
            if (cccd != null && !cccd.trim().isEmpty() && residentDAO.checkDuplicateID(cccd)) {
                request.setAttribute("error", "CCCD already exists.");
                forwardToForm(request, response);
                return;
            }
            if (username != null && !username.trim().isEmpty() && residentDAO.checkDuplicatateUsername(username)) {
                request.setAttribute("error", "Username already exists.");
                forwardToForm(request, response);
                return;
            }

            SendEmail e = new SendEmail();
            // Create Resident object
            Resident resident = new Resident();
            resident.setName(name);
            resident.setBod(dob);
            resident.setGender(gender);
            resident.setPhone(phone);
            resident.setAddress(address);
            resident.setCccd(cccd);
            resident.setUsername(username);
            resident.setEmail(email);

            Util u = new Util();
            String password = u.generatePassword();
            if (email != null && !email.trim().isEmpty()) {
                resident.setPassword(encryptPassword(password));
            }

            Role role = new Role();
            role.setId(roleId);
            resident.setRole(role);
            String insertedId = residentDAO.insertNewResident(resident);

            if (insertedId != null) {
                // new role = 6 then insert directly into the apartment
                if (role.getId().equals("6")) {
                    lvd.insertLivingApartment(insertedId, apartment, new Date(System.currentTimeMillis()).toString());

                    LivingApartment la = lvd.getRepresentedResidentByAid(apartment);
                    if (la != null) {
                        Resident representative = residentDAO.getById(la.getRid().getpId());
                        e.sendEmailNewResidentAdded(representative.getEmail(), resident.getName());
                    }
                } else if (role.getId().equals("1")) {
                    OwnerApartmentDAO oad = new OwnerApartmentDAO();
                    OwnerApartment owner = oad.getOwnerByApartmentID(apartment);
                    if (owner != null) {
                        e.sendRequestResidentToTransferApartment(owner.getRid().getEmail(), resident.getName(), apartment);
                    } else {
                        lvd.insertLivingApartment(insertedId, apartment, new Date(System.currentTimeMillis()).toString());
                    }
                }

                if (email != null && !email.trim().isEmpty()) {
                    e.sendEmailResidentAccount(email, name, username, password);
                }

                request.setAttribute("successMessage", "Resident added successfully!");
            } else {
                System.out.println("DEBUG: Failed to insert resident: " + resident.toString());
                request.setAttribute("error", "Failed to add resident.");
            }

        } catch (Exception e) {
            System.out.println("DEBUG: Exception in handleSingleResident: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
        }

        forwardToForm(request, response);
    }

    private void handleMultipleResidents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        try {
            StringBuilder sb = new StringBuilder();
            String line;
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String jsonString = sb.toString();

            JSONObject jsonRequest = new JSONObject(jsonString);
            JSONArray residentsArray = jsonRequest.getJSONArray("residents");

            List<Resident> residents = new ArrayList<>();
            List<String> apartmentIds = new ArrayList<>();
            List<String> errors = new ArrayList<>();

            for (int i = 0; i < residentsArray.length(); i++) {
                JSONObject residentJson = residentsArray.getJSONObject(i);
                int rowNum = i + 1;

                String name = residentJson.optString("name", null);
                String dob = residentJson.optString("dob", null);
                String gender = residentJson.optString("gender", null);
                String phone = residentJson.optString("phone", null);
                String address = residentJson.optString("address", null);
                String apartment = residentJson.optString("apartment", null);
                String cccd = residentJson.optString("cccd", null);

                // Validate required fields
                if (name == null || name.trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Name is required.");
                }
                if (dob == null || dob.trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Date of Birth (DOB) is required.");
                }
                if (gender == null || gender.trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Gender is required.");
                }
                if (phone == null || phone.trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Phone number is required.");
                }
                if (address == null || address.trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Address is required.");
                }
                if (apartment == null || apartment.trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Apartment is required.");
                }

                // Validate phone and CCCD formats
                if (phone != null && !phone.trim().isEmpty() && !phone.matches("\\d{10}")) {
                    errors.add("Row " + rowNum + ": Phone number must be exactly 10 digits.");
                }
                if (cccd != null && !cccd.trim().isEmpty() && !cccd.matches("\\d{12}")) {
                    errors.add("Row " + rowNum + ": CCCD must be exactly 12 digits.");
                }

                // Check for duplicates
                if (phone != null && !phone.trim().isEmpty() && residentDAO.checkDuplicatePhone(phone)) {
                    errors.add("Row " + rowNum + ": Phone number '" + phone + "' already exists.");
                }
                if (cccd != null && !cccd.trim().isEmpty() && residentDAO.checkDuplicateID(cccd)) {
                    errors.add("Row " + rowNum + ": CCCD '" + cccd + "' already exists.");
                }

                Resident resident = new Resident();
                resident.setName(name);
                resident.setBod(dob);
                resident.setGender(gender);
                resident.setPhone(phone);
                resident.setAddress(address);
                resident.setCccd(cccd);

                Role roleObj = new Role();
                roleObj.setId("6"); // Hardcode role to "6" for Excel imports
                resident.setRole(roleObj);

                apartmentIds.add(apartment);
                residents.add(resident);
            }

            if (!errors.isEmpty()) {
                jsonResponse.put("success", false);
                jsonResponse.put("errors", new JSONArray(errors));
                response.getWriter().write(jsonResponse.toString());
                return;
            }

            List<String> insertedIds = residentDAO.insertMultipleResidents(residents);
            SendEmail e = new SendEmail();
            if (insertedIds != null) {
                for (int i = 0; i < insertedIds.size(); i++) {
                    lvd.insertLivingApartment(insertedIds.get(i), apartmentIds.get(i), new Date(System.currentTimeMillis()).toString());
                    LivingApartment la = lvd.getRepresentedResidentByAid(apartmentIds.get(i));
                    if (la != null) {

                        Resident representative = residentDAO.getById(la.getRid().getpId());
                        e.sendEmailNewResidentAdded(representative.getEmail(), residents.get(i).getName());
                    }
                }
                jsonResponse.put("success", true);
                jsonResponse.put("insertedIds", new JSONArray(insertedIds));
            } else {
                System.out.println("DEBUG: Failed to insert multiple residents: " + residents.toString());
                jsonResponse.put("success", false);
                jsonResponse.put("error", "Failed to insert residents into the database.");
            }

        } catch (JSONException e) {
            System.out.println("DEBUG: JSONException in handleMultipleResidents: " + e.getMessage());
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("error", e.getMessage());
        } catch (Exception e) {
            System.out.println("DEBUG: Exception in handleMultipleResidents: " + e.getMessage());
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("error", "An unexpected error occurred: " + e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }

    private void forwardToForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Apartment> apartments = new ApartmentDAO().getAll();
        request.setAttribute("apts", apartments);
        request.getRequestDispatcher("/addnewresident.jsp").forward(request, response);
    }
}
