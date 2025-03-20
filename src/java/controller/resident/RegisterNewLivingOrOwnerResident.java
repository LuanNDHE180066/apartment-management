/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.RequestChangeResidentDAO;
import dao.ResidentDAO;
import dao.RoleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Account;
import model.RequestChangeResident;
import model.Resident;
import model.SendEmail;
import model.Staff;

/**
 *
 * @author quang
 */
@WebServlet(name = "RegisterNewLivingOrOwnerResident", urlPatterns = {"/register-new-living-or-owner-resident"})
public class RegisterNewLivingOrOwnerResident extends HttpServlet {

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
            out.println("<title>Servlet RegisterNewLivingOrOwnerResident</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterNewLivingOrOwnerResident at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
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
        ResidentDAO reDAO = new ResidentDAO();
        ApartmentDAO aDAO = new ApartmentDAO();
        StaffDAO stDAO = new StaffDAO();
        OwnerApartmentDAO oDAO = new OwnerApartmentDAO();
        LivingApartmentDAO lDAO = new LivingApartmentDAO();
        RequestChangeResidentDAO requestChangeDAO = new RequestChangeResidentDAO();
        RoleDAO rDAO = new RoleDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        
        String aid = request.getParameter("apartment");
        String changeResident = request.getParameter("residentType");
        String residentExists = request.getParameter("residentExists");
        
        LocalDateTime lc = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = lc.format(format);
        
        String emailContent = "";
        
        Resident owner = oDAO.getOwnerByApartmentID(aid).getRid();
        if (residentExists != null) {
            String newResidentId = request.getParameter("newResidentId");
            Resident newRe = reDAO.getById(newResidentId);
            if (newRe == null) {
                request.setAttribute("status", "false");
                request.setAttribute("message", "Resident ID is not exist");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            
            RequestChangeResident re = new RequestChangeResident(owner, newRe, aid, 0, 1, 0, formattedDate);
            if (changeResident.equals("living")) {
                re.setChangeType(1);
            }
            emailContent = "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; padding: 20px; background-color: #f4f4f4; color: #333; }"
                    + "h2 { color: #007BFF; }"
                    + ".container { background: #fff; padding: 15px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }"
                    + ".section { margin-bottom: 15px; }"
                    + ".label { font-weight: bold; }"
                    + ".info { margin-left: 10px; }"
                    + "</style></head><body>"
                    + "<div class='container'>"
                    + "<h2>Change Request</h2>"
                    + "<div class='section'><span class='label'>Request change:</span><span class='info'>"
                    + (re.getChangeType() == 1 ? "Living person" : "Owner") + "</span></div>"
                    + "<div class='section'><span class='label'>From apartment:</span><span class='info'>"
                    + re.getRoomNumber() + "</span></div>"
                    + "<div class='section'><span class='label'>Change person:</span><span class='info'>"
                    + re.getNewPerson().getName() + " (ID: " + (re.getNewPerson().getpId() != null ? re.getNewPerson().getpId() : "Not exist") + ")</span></div>"
                    + "<div class='section'><span class='label'>Owner:</span><span class='info'>"
                    + re.getOwner().getName() + " (ID: " + re.getOwner().getpId() + ")</span></div>"
                    + "<h3>Details of New Person:</h3>"
                    + "<div class='section'><span class='label'>Date of Birth:</span><span class='info'>"
                    + re.getNewPerson().getBod() + "</span></div>"
                    + "<div class='section'><span class='label'>Address:</span><span class='info'>"
                    + re.getNewPerson().getAddress() + "</span></div>"
                    + "<div class='section'><span class='label'>Phone:</span><span class='info'>"
                    + re.getNewPerson().getPhone() + "</span></div>"
                    + "<div class='section'><span class='label'>Email:</span><span class='info'>"
                    + re.getNewPerson().getEmail() + "</span></div>"
                    + "<div class='section'><span class='label'>CCCD:</span><span class='info'>"
                    + re.getNewPerson().getCccd() + "</span></div>"
                    + "<div class='section'><span class='label'>Username:</span><span class='info'>"
                    + re.getNewPerson().getUsername() + "</span></div>"
                    + "<div class='section'><span class='label'>Gender:</span><span class='info'>"
                    + re.getNewPerson().getGender() + "</span></div>"
                    + "<div class='section'><span class='label'>Created At:</span><span class='info'>"
                    + re.getCreatedAt() + "</span></div>"
                    + "</div></body></html>";
            re.setNewPersonExists(1);
            requestChangeDAO.addNewRequestChange(re);
        } else {
            String name = request.getParameter("name").trim();
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone").trim();
            String email = request.getParameter("email").trim();
            String id = request.getParameter("id").trim();
            String username = request.getParameter("username");
            
            if (!phone.matches("\\d{10}")) {
                request.setAttribute("message", "Phone number must be exactly 10 digits.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            if (!id.matches("\\d{12}")) {
                request.setAttribute("message", "ID must be exactly 12 digits.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            
            if (reDAO.checkDuplicatePhone(phone, a.getpId())) {
                request.setAttribute("message", "Phone is existed.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            if (reDAO.checkDuplicateEmail(email, a.getpId())) {
                request.setAttribute("message", "Email is existed..");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            if (reDAO.checkDuplicateID(id, a.getpId())) {
                request.setAttribute("message", "ID is existed..");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            
            if (reDAO.checkDuplicateUser(username, a.getpId())) {
                request.setAttribute("message", "Username is existed.");
                request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);
                return;
            }
            
            Resident newRe = new Resident(name, id, phone, email, dob, address, username, null, rDAO.getById("1"), gender.equals("M")?"Nam":"Nữ");
            RequestChangeResident re = new RequestChangeResident(owner, newRe, aid, 0, 1, 0, formattedDate);
            if (changeResident.equals("living")) {
                re.setChangeType(1);
            } else {
                newRe.setUsername(username.trim());
            }
            PrintWriter out = response.getWriter();
//            out.print(re.getOwner() + " new: " + re.getNewPerson() + " " +re.getRoomNumber() +" " + re.getCreatedAt());
            re.setNewPersonExists(0);
            requestChangeDAO.addNewRequestChange(re);
//            String message = "Request change " + (re.getChangeType() == 1 ? "Living person" : "Owner") + " from apartment " + re.getRoomNumber() + ". "
//                    + "Change person " + re.getNewPerson().getName() +;
            emailContent = "<html><head><style>"
                    + "body { font-family: Arial, sans-serif; line-height: 1.6; margin: 20px; padding: 20px; background-color: #f4f4f4; color: #333; }"
                    + "h2 { color: #007BFF; }"
                    + ".container { background: #fff; padding: 15px; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }"
                    + ".section { margin-bottom: 15px; }"
                    + ".label { font-weight: bold; }"
                    + ".info { margin-left: 10px; }"
                    + "</style></head><body>"
                    + "<div class='container'>"
                    + "<h2>Change Request</h2>"
                    + "<div class='section'><span class='label'>Owner:</span><span class='info'>"
                    + re.getOwner().getName() + " (ID: " + re.getOwner().getpId() + ")</span></div>"
                    + "<div class='section'><span class='label'>From apartment:</span><span class='info'>"
                    + re.getRoomNumber() + "</span></div>"
                    + "<div class='section'><span class='label'>Request change:</span><span class='info'>"
                    + (re.getChangeType() == 1 ? "Living person" : "Owner") + "</span></div>"
                    + "<div class='section'><span class='label'>Change person:</span><span class='info'>"
                    + re.getNewPerson().getName() + " (ID: " + (re.getNewPerson().getpId() != null ? re.getNewPerson().getpId() : "Not exist") + ")</span></div>"
                    + "<h3>Details of New Person:</h3>"
                    + "<div class='section'><span class='label'>Date of Birth:</span><span class='info'>"
                    + re.getNewPerson().getBod() + "</span></div>"
                    + "<div class='section'><span class='label'>Address:</span><span class='info'>"
                    + re.getNewPerson().getAddress() + "</span></div>"
                    + "<div class='section'><span class='label'>Phone:</span><span class='info'>"
                    + re.getNewPerson().getPhone() + "</span></div>"
                    + "<div class='section'><span class='label'>Email:</span><span class='info'>"
                    + re.getNewPerson().getEmail() + "</span></div>"
                    + "<div class='section'><span class='label'>CCCD:</span><span class='info'>"
                    + re.getNewPerson().getCccd() + "</span></div>"
                    + "<div class='section'><span class='label'>Username:</span><span class='info'>"
                    + re.getNewPerson().getUsername() + "</span></div>"
                    + "<div class='section'><span class='label'>Gender:</span><span class='info'>"
                    + re.getNewPerson().getGender() + "</span></div>"
                    + "<div class='section'><span class='label'>Created At:</span><span class='info'>"
                    + re.getCreatedAt() + "</span></div>"
                    + "</div></body></html>";
        }
        List<Staff> listAdmin = stDAO.getAllAdmin();
        SendEmail send = new SendEmail();
        send.sendEmailToWorkingAdmin(listAdmin, emailContent);
        request.setAttribute("status", "true");
        request.setAttribute("message", "Add request successful");
        request.getRequestDispatcher("registerNewLivingOrOwnerResident.jsp").forward(request, response);

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
