/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff.accountant;

import dao.CompanyDAO;
import dao.ExpenditureDAO;
import dao.ExpenseCategoryDAO;
import dao.FundDAO;
import dao.HistoryExpenditureDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Account;
import model.Company;
import model.ExpenseCategory;
import model.Fund;
import model.HistoryExpenditure;
import model.SendEmail;
import model.Staff;
import util.Util;

/**
 *
 * @author quang
 */
public class AddExpenditure extends HttpServlet {

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
            out.println("<title>Servlet AddExpenditure</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddExpenditure at " + request.getContextPath() + "</h1>");
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
        StaffDAO daoSt = new StaffDAO();
        request.setAttribute("staff", daoSt.getById(a.getpId()));
        request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
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
        String totalPrice_raw = request.getParameter("totalPrice");
        String title = request.getParameter("title");
        String approveDate_raw = request.getParameter("approveDate");
        String paymentDate_raw = request.getParameter("paymentDate");
        String categoryId_raw = request.getParameter("category");
        String companyId = request.getParameter("company");
        String chiefAccountantId = request.getParameter("chiefAccountant");
        String AdminId = request.getParameter("admin");
        String note = request.getParameter("note");
        String createdById = request.getParameter("createdBy");
        Util u = new Util();

        HistoryExpenditureDAO daoHe = new HistoryExpenditureDAO();
        ExpenseCategoryDAO daoEx = new ExpenseCategoryDAO();
        CompanyDAO daoCp = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        ExpenditureDAO daoE = new ExpenditureDAO();

        if (title.trim().isBlank()) {
            request.setAttribute("message", "Title can not be blank");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }

        title = u.stringNomalize(title);

        if (approveDate_raw.isBlank() || paymentDate_raw.isBlank()) {
            request.setAttribute("message", "Approve date and payment date can not be blank");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }

        if (note.trim().isBlank()) {
            request.setAttribute("message", "Note can not be blank");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }

        note = u.stringNomalize(note);
//        PrintWriter out = response.getWriter();
//        out.print(" admin:" + AdminId + "  chief:" + chiefAccountantId +"Staff creat"+ createdById +  "cate: "+categoryId_raw);
        LocalDateTime lc = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = lc.format(format);
        try {
            int categoryId = Integer.parseInt(categoryId_raw);
            String eid = null;
            float totalPrice = Float.parseFloat(totalPrice_raw);
//            FundDAO fd = new FundDAO();
//            Fund f = fd.getById("1");
//            if (totalPrice > f.getValue()) {
//                request.setAttribute("message", "Total fees is higher than Fund of Apartment!");
//                request.setAttribute("status", "false");
//                request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
//                return;
//            }
            Company company = daoCp.getById(companyId);
            Staff chiefAccountant = daoSt.getById(chiefAccountantId);
            Staff currentAdmin = daoSt.getById(AdminId);
            Staff createBy = daoSt.getById(createdById);
            ExpenseCategory ex = daoEx.getExpenseCategoryById(categoryId);
            String action = "Insert";
            String modifiedDate = formattedDate;
            String createdDate = formattedDate;

//            out.print(currentAdmin.getName() + " " + AdminId + " " + chiefAccountantId + createdById + categoryId_raw);
            HistoryExpenditure he = new HistoryExpenditure(eid, title, 0,
                    0, approveDate_raw, paymentDate_raw, totalPrice, note,
                    ex, company, createBy, chiefAccountant, currentAdmin, action,
                    modifiedDate, createBy, createdDate);

            DecimalFormat df = new DecimalFormat("#,###"); // Định dạng kiểu số với dấu phân cách là dấu phẩy
            String formattedTotalPrice = df.format(totalPrice).replace(",", ".") + " VNĐ"; // Thay dấu phẩy bằng dấu chấm và thêm VNĐ
            String emailContentInsert = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }"
                    + "h2 { color: #333; }"
                    + "table { border-collapse: collapse; width: 100%; background-color: #ffffff; }"
                    + "th, td { border: 1px solid #dddddd; text-align: left; padding: 12px; }"
                    + "th { background-color: #003366; color: white; }" // Màu tiêu đề bảng
                    + "tr:nth-child(even) { background-color: #f2f2f2; }"
                    + "tr:hover { background-color: #ddd; }"
                    + "p { color: #555; font-size: 14px; }"
                    + ".footer { margin-top: 20px; text-align: center; }"
                    + ".contact-info { color: #ff9800; }" // Màu chữ cam cho thông tin liên hệ
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<h2>Thông báo về chi phí</h2>"
                    + "<table>"
                    + "<tr>"
                    + "<th>Thông tin</th>"
                    + "<th>Chi tiết</th>"
                    + "</tr>"
                    // Đã bỏ ID
                    + "<tr><td>Tiêu đề</td>"
                    + "<td>" + he.getTitle() + "</td></tr>"
                    + "<tr><td>Trạng thái phê duyệt</td>"
                    + "<td>" + (he.getChiefAccountantApproveStatus() == 0 ? "Đang xử lý" : "Đã từ chối") + "</td></tr>"
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

//            out.print(currentAdmin.getName() + " " + AdminId + " " + chiefAccountantId + createdById + categoryId_raw);
            if (!daoHe.addNewHistoryExpenditure(he)) {
                request.setAttribute("message", "Can not add expenditure");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
                return;
            } else {
                SendEmail send = new SendEmail();
                send.sendEmail(he.getChiefAccountantId().getEmail(), "Thông báo về chi phí: " + he.getTitle(), emailContentInsert);
                send.sendEmail(he.getCurrentAdmin().getEmail(), "Thông báo về chi phí: " + he.getTitle(), emailContentInsert);
//                send.sendEmail(he.getCurrentAdmin().getEmail(), daoSt.getById(he.getCreatedStaff().getName()) + " has created an expenditure " + he.getTitle(),
//                        "Please check and confirm the expenditure : " + he.getTitle());
                request.setAttribute("staff", daoSt.getById(he.getCreatedStaff().getId()));
                request.setAttribute("message", "Your expenditure has been successfully saved to the waiting list.");
                request.setAttribute("status", "true");
                request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {

        }
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
