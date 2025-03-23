/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff.accountant;

import dao.ExpenditureDAO;
import dao.HistoryExpenditureDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import model.Account;
import model.Expenditure;
import model.HistoryExpenditure;
import model.SendEmail;

/**
 *
 * @author quang
 */
@WebServlet(name = "UpdatePendingExpenditureStatus", urlPatterns = {"/update-pending-expenditure"})
public class UpdatePendingExpenditureStatus extends HttpServlet {

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
            out.println("<title>Servlet UpdatePendingExpenditureStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePendingExpenditureStatus at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String id = request.getParameter("id");
        String approve = request.getParameter("approve");
        HistoryExpenditureDAO daoHe = new HistoryExpenditureDAO();
        StaffDAO daoSt = new StaffDAO();
        daoHe.updateApproveStatus(id, a.getRoleId() + "", approve);

        HistoryExpenditure he = daoHe.getExpenditureById(id);
        ExpenditureDAO daoE = new ExpenditureDAO();
        if (daoE.checkExistId(he.getId()) && he.getAction().equalsIgnoreCase("insert")) {
            response.sendRedirect("index.jsp");
            return;
        }
        DecimalFormat df = new DecimalFormat("#,###"); // Định dạng kiểu số với dấu phân cách là dấu phẩy
        String formattedTotalPrice = df.format(he.getTotalPrice()).replace(",", ".") + " VNĐ"; // Thay dấu phẩy bằng dấu chấm và thêm VNĐ

        String emailContentInsert = "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }"
                + "h2 { color: #333; }"
                + "table { border-collapse: collapse; width: 100%; background-color: #ffffff; }"
                + "th, td { border: 1px solid #dddddd; text-align: left; padding: 12px; }"
                + "th { background-color: #003366; color: white; }"
                + "tr:nth-child(even) { background-color: #f2f2f2; }"
                + "tr:hover { background-color: #ddd; }"
                + "p { color: #555; font-size: 14px; }"
                + ".footer { margin-top: 20px; text-align: center; }"
                + ".contact-info { color: #ff9800; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h2>Thông báo về chi phí</h2>"
                + "<table>"
                + "<tr>"
                + "<th>Thông tin</th>"
                + "<th>Chi tiết</th>"
                + "</tr>"
                + "<tr><td>Tiêu đề</td>"
                + "<td>" + he.getTitle() + "</td></tr>"
                + "<tr><td>Trạng thái phê duyệt</td>"
                + "<td>" + ((he.getChiefAccountantApproveStatus() == 1 && he.getCurrentAdminApproveStatus() == 1) ? "Đã phê duyệt" : "Đã từ chối") + "</td></tr>"
                + "<tr><td>Tổng giá</td>"
                + "<td>" + formattedTotalPrice + "</td></tr>"
                + "<tr><td>Ghi chú</td>"
                + "<td>" + he.getNote() + "</td></tr>"
                + "<tr><td>Danh mục chi phí</td>"
                + "<td>" + he.getCategory().getCategoryName() + "</td></tr>"
                + "<tr><td>Công ty</td>"
                + "<td>" + he.getCompany().getName() + "</td></tr>"
                + "<tr><td>Ngày tạo</td>"
                + "<td>" + he.getCreatedDate() + "</td></tr>"
                + "</table>"
                + "<p>Vui lòng kiểm tra và xác nhận chi phí này.</p>"
                + "<div class='footer'>"
                + "<p>Đây là email gửi từ động từ hệ thống của BaViApartment.</p>"
                + "<p class='contact-info'>Mọi thông tin cần hỗ trợ từ hệ thống BaViApartment, vui lòng liên hệ:</p>"
                + "<p class='contact-info'>Hotline: 0877165299 | Email: baviapartment88@gmail.com</p>"
                + "</div>"
                + "</body>"
                + "</html>";

        String emailContentUpdate = "<html>"
                + "<head>"
                + "<style>"
                + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }"
                + "h2 { color: #333; }"
                + "table { border-collapse: collapse; width: 100%; background-color: #ffffff; }"
                + "th, td { border: 1px solid #dddddd; text-align: left; padding: 12px; }"
                + "th { background-color: #003366; color: white; }"
                + "tr:nth-child(even) { background-color: #f2f2f2; }"
                + "tr:hover { background-color: #ddd; }"
                + "p { color: #555; font-size: 14px; }"
                + ".footer { margin-top: 20px; text-align: center; }"
                + ".contact-info { color: #ff9800; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<h2>Cập nhật thông tin chi phí</h2>"
                + "<table>"
                + "<tr>"
                + "<th>Thông tin</th>"
                + "<th>Chi tiết</th>"
                + "</tr>"
                + "<tr><td>Tiêu đề</td>"
                + "<td>" + he.getTitle() + "</td></tr>"
                + "<tr><td>Ngày cập nhật</td>"
                + "<td>" + he.getModifiedDate() + "</td></tr>"
                + "<tr><td>Trạng thái phê duyệt</td>"
                + "<td>" + ((he.getChiefAccountantApproveStatus() == 1 && he.getCurrentAdminApproveStatus() == 1) ? "Đã chấp nhận" : "Đã từ chối") + "</td></tr>"
                + "<tr><td>Tổng giá</td>"
                + "<td>" + formattedTotalPrice + " VNĐ</td></tr>"
                + "<tr><td>Ghi chú</td>"
                + "<td>" + he.getNote() + "</td></tr>"
                + "<tr><td>Danh mục chi phí</td>"
                + "<td>" + he.getCategory().getCategoryName() + "</td></tr>"
                + "<tr><td>Công ty</td>"
                + "<td>" + he.getCompany().getName() + "</td></tr>"
                + "<tr><td>Người tạo</td>"
                + "<td>" + he.getCreatedStaff().getEmail() + "</td></tr>"
                + "<tr><td>Người sửa đổi</td>"
                + "<td>" + he.getModifiedBy().getEmail() + "</td></tr>"
                + "<tr><td>Ngày tạo</td>"
                + "<td>" + he.getCreatedDate() + "</td></tr>"
                + "</table>"
                + "<p>Vui lòng kiểm tra và xác nhận thông tin chi phí đã cập nhật.</p>"
                + "<div class='footer'>"
                + "<p>Đây là email gửi từ động từ hệ thống của BaViApartment.</p>"
                + "<p class='contact-info'>Mọi thông tin cần hỗ trợ từ hệ thống BaViApartment, vui lòng liên hệ:</p>"
                + "<p class='contact-info'>Hotline: 0877165299 | Email: baviapartment88@gmail.com</p>"
                + "</div>"
                + "</body>"
                + "</html>";
        SendEmail send = new SendEmail();
        if (he.getAction().equalsIgnoreCase("update")) {
            if (he.getChiefAccountantApproveStatus() == 1 && he.getCurrentAdminApproveStatus() == 1) {
                send.sendEmail(he.getModifiedBy().getEmail(), "Thông báo về chi phí: " + he.getTitle(), emailContentInsert);
                daoE.updateExpenditure(he);
            } else if (he.getChiefAccountantApproveStatus() == -1 || he.getCurrentAdminApproveStatus() == -1) {
                if (he.getModifiedBy().getId().equalsIgnoreCase(he.getCreatedStaff().getId())) {
                    String denialSubject = "Your update expenditure request has been denied: " + he.getTitle();
                    String denialMessage = "Please check and review the update expenditure request: " + he.getTitle() + "<br/>" + emailContentInsert;
                    send.sendEmail(he.getModifiedBy().getEmail(), denialSubject, denialMessage);
                }
            }
        } else if (he.getAction().equalsIgnoreCase("insert")) {
            if (he.getChiefAccountantApproveStatus() == 1 && he.getCurrentAdminApproveStatus() == 1) {
                String newId = daoE.generateExpenditureId();
                he.setId(newId);
                send.sendEmail(he.getModifiedBy().getEmail(), "Thông báo về chi phí: " + he.getTitle(), emailContentUpdate);
                daoHe.updateEidAfterInsert(he);
                daoE.addExpenditure(he);
            } else if (he.getChiefAccountantApproveStatus() == -1 || he.getCurrentAdminApproveStatus() == -1) {
                if (he.getModifiedBy().getId().equalsIgnoreCase(he.getCreatedStaff().getId())) {
                    String denialSubject = "Your expenditure request has been denied: " + he.getTitle();
                    String denialMessage = "Please check and review the expenditure: " + he.getTitle() + "<br/>" + emailContentUpdate;
                    send.sendEmail(he.getModifiedBy().getEmail(), denialSubject, denialMessage);
                }
            }
        }

        request.setAttribute("staffId", a.getpId());
        request.getRequestDispatcher("view-pending-expenditure").forward(request, response);
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
