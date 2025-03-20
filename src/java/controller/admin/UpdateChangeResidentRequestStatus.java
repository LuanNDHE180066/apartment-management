/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.RequestChangeResidentDAO;
import dao.ResidentDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.LivingApartment;
import model.RequestChangeResident;
import model.Resident;
import model.SendEmail;
import util.Util;
import static util.Util.encryptPassword;

/**
 *
 * @author quang
 */
@WebServlet(name = "UpdateChangeResidentRequestStatus", urlPatterns = {"/update-status-change-resident"})
public class UpdateChangeResidentRequestStatus extends HttpServlet {

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
            out.println("<title>Servlet UpdateChangeResidentRequestStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateChangeResidentRequestStatus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
//1 LA LIVING 0 LAF OWNER
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
        String id = request.getParameter("id");
        String approve = request.getParameter("approve");
        ResidentDAO reDAO = new ResidentDAO();
        ApartmentDAO aDAO = new ApartmentDAO();
        OwnerApartmentDAO oaDAO = new OwnerApartmentDAO();
        LivingApartmentDAO liDAO = new LivingApartmentDAO();
        RequestChangeResidentDAO rcDAO = new RequestChangeResidentDAO();
        RoleDAO roleDAO = new RoleDAO();

        RequestChangeResident r = rcDAO.getRequestChangeById(id);
        String emailContent = "<div class=\"container\">\n"
                + "        <h2>Kính gửi " + r.getOwner().getName() + ",</h2>\n"
                + "        <p>Chúng tôi đã nhận được yêu cầu thay đổi " + (r.getChangeType() == 1 ? "Người ở" : "Chủ căn hộ") + " của bạn với thông tin chi tiết như sau:</p>\n"
                + "        <hr>\n"
                + "        <p><strong>Tên cư dân mới:</strong> " + r.getNewPerson().getName() + "</p>\n"
                + "        <p><strong>Ngày sinh:</strong> " + r.getNewPerson().getBod() + "</p>\n"
                + "        <p><strong>Địa chỉ:</strong> " + r.getNewPerson().getAddress() + "</p>\n"
                + "        <p><strong>Số điện thoại:</strong> " + r.getNewPerson().getPhone() + "</p>\n"
                + "        <p><strong>Email:</strong> " + r.getNewPerson().getEmail() + "</p>\n"
                + "        <p><strong>CCCD:</strong> " + r.getNewPerson().getCccd() + "</p>\n"
                + "        <p><strong>Tên đăng nhập:</strong> " + r.getNewPerson().getUsername() + "</p>\n"
                + "        <p><strong>Giới tính:</strong> " + r.getNewPerson().getGender() + "</p>\n"
                + "        <p><strong>Số phòng:</strong> " + r.getRoomNumber() + "</p>\n"
                + "        <p><strong>Ngày yêu cầu:</strong> " + r.getCreatedAt() + "</p>\n"
                + "        <p><strong>Trạng thái:</strong> " + (r.getAdminStatus() == 1 ? "Đồng ý" : "Từ chối") + "</p>\n"
                + "        <hr>\n"
                + "        <p>Nếu bạn có bất kỳ câu hỏi nào, xin vui lòng liên hệ với chúng tôi.</p>\n"
                + "        <p>Cảm ơn bạn đã hợp tác!</p>\n"
                + "        <div class=\"footer\">\n"
                + "            <p>Trân trọng,<br>Ban Quản Trị cư dân</p>\n"
                + "        </div>\n"
                + "    </div>";
        SendEmail send = new SendEmail();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String date = now.format(formatter);
        send.sendEmail(r.getOwner().getEmail(), "Phản hồi về đơn yêu cầu thay đổi " + (r.getChangeType() == 1 ? "Người ở" : "Chủ căn hộ"), emailContent);
        if (rcDAO.updateAdminStatus(id, approve) && approve.equals("1")) {
            if (r.getNewPersonExists() == 0) {
                Util u = new Util();
                //generate random password then send to new user
//                String password = u.generatePassword();
//                password = encryptPassword(password);
                Resident newResident = new Resident();
                newResident.setName(r.getNewPerson().getName());
                newResident.setBod(r.getNewPerson().getBod());
                newResident.setAddress(r.getNewPerson().getAddress());
                newResident.setPhone(r.getNewPerson().getPhone());
                newResident.setEmail(r.getNewPerson().getEmail());
                newResident.setCccd(r.getNewPerson().getCccd());
                newResident.setRole(roleDAO.getById("6"));
//                newResident.setUsername(r.getNewPerson().getUsername());
//                newResident.setPassword(password);
                newResident.setGender(r.getNewPerson().getGender());

                reDAO.insertNewResident(newResident);
                Resident rNew = reDAO.getResidentById(newResident.getCccd());
                if (r.getChangeType() == 1) {
                    // liDAO.updateEndLivingApartment(date, r.getRoomNumber());
                    liDAO.insertLivingApartment(rNew.getpId(), r.getRoomNumber(), date);
                } else {
                    oaDAO.updateEndOwnerApartment(r.getRoomNumber(), date);
                    oaDAO.insertOwnerApartment(rNew.getpId(), r.getRoomNumber(), date);
                    if (liDAO.checkIsRepresentOfThisApartment(r.getOwner().getpId(), r.getRoomNumber())) {
                        liDAO.changeIsRepresent("0", r.getOwner().getpId(), r.getRoomNumber());
                    }
                    if (!oaDAO.isHomeOwner(r.getOwner().getpId()) && !liDAO.checkIsRepresent(r.getOwner().getpId())) {
                        reDAO.setNullUsernameAndPassword(r.getOwner().getpId());
                        liDAO.insertLivingApartment(rNew.getpId(), r.getRoomNumber(), date);
                        liDAO.changeIsRepresent("1", rNew.getpId(), r.getRoomNumber());
                    }

                }
            } else {
                Resident existedResident = reDAO.getResidentById(r.getNewPerson().getCccd());
                if (r.getChangeType() == 1) {
                    //liDAO.updateEndLivingApartment(date, r.getRoomNumber());
                    liDAO.insertLivingApartment(existedResident.getpId(), r.getRoomNumber(), date);
                } else {
                    oaDAO.updateEndOwnerApartment(r.getRoomNumber(), date);
                    oaDAO.insertOwnerApartment(existedResident.getpId(), r.getRoomNumber(), date);
                }
            }
        }
        response.sendRedirect("view-change-resident-request");
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
        processRequest(request, response);
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
