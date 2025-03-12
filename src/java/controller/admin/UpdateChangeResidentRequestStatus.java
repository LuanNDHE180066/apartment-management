/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.RequestChangeResidentDAO;
import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.RequestChangeResident;
import model.SendEmail;

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
        RequestChangeResidentDAO rcDAO = new RequestChangeResidentDAO();
        

        rcDAO.updateAdminStatus(id, approve);
        RequestChangeResident r = rcDAO.getRequestChangeById(id);
        String emailContent = "<div class=\"container\">\n"
                + "        <h2>Kính gửi " + r.getOwner().getName() + ",</h2>\n"
                + "        <p>Chúng tôi đã nhận được yêu cầu thay đổi " + (r.getChangeType() == 1 ? "Người ở" : "Chủ căn hộ") + " của bạn với thông tin chi tiết như sau:</p>\n"
                + "        <hr>\n"
                + "        <p><strong>Tên cư dân mới:</strong> " + r.getNewPerson().getName() + "</p>\n"
                + "        <p><strong>Ngày sinh:</strong> " + r.getNewPerson().getBod()+ "</p>\n"
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
        send.sendEmail(r.getOwner().getEmail(), "Phản hồi về đơn yêu cầu thay đổi " + (r.getChangeType() == 1 ? "Người ở" : "Chủ căn hộ"), emailContent);
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
