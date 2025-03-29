package controller.resident;

import dao.AccountDAO;
import dao.ResidentDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.Util;

public class PasswordUpdateServlet extends HttpServlet {

    private static final String JSP_PAGE = "changepassword.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xóa thông báo lỗi nếu có khi tải trang lần đầu (GET request)
        HttpSession session = request.getSession();
        session.removeAttribute("errorMessage");
        request.getRequestDispatcher(JSP_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Kiểm tra xem tài khoản có tồn tại trong phiên không
        if (account == null) {
            session.setAttribute("errorMessage", "Phiên đăng nhập đã hết hạn. Vui lòng đăng nhập lại.");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }

        String newpw = request.getParameter("newPassword").trim();
        String cfnewpw = request.getParameter("cfnewPassword").trim();
        ResidentDAO rd = new ResidentDAO();

        // Xác thực mật khẩu cũ nếu tài khoản không ở trạng thái ban đầu (active != 2)
        if (account.getActive() != 2) {
            String oldpw = request.getParameter("oldPassword").trim();
            if (!Util.isCorrectPassword(oldpw, account.getPassword())) {
                session.setAttribute("errorMessage", "Mật khẩu cũ không chính xác.");
                request.getRequestDispatcher(JSP_PAGE).forward(request, response);
                return;
            }
        }

        // Kiểm tra mật khẩu không được để trống
        if (newpw.isBlank() || newpw.isEmpty()) {
            session.setAttribute("errorMessage", "Mật khẩu không được để trống.");
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
            return;
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu có trùng khớp không
        if (!newpw.equals(cfnewpw)) {
            session.setAttribute("errorMessage", "Mật khẩu mới và mật khẩu xác nhận không khớp.");
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
            return;
        }

        // Kiểm tra định dạng mật khẩu
        if (!Util.isCorrectFormatPassword(newpw)) {
            session.setAttribute("errorMessage", "Mật khẩu phải có ít nhất 6 ký tự, bao gồm ít nhất 1 chữ thường, 1 chữ hoa, 1 số và 1 ký tự đặc biệt.");
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
            return;
        }

        // Cập nhật mật khẩu trong cơ sở dữ liệu
        AccountDAO ad = new AccountDAO();
        ad.changePassword(account.getUsername(), newpw, account.getRoleId());

        // Cập nhật trạng thái cư dân nếu tài khoản đang ở trạng thái ban đầu
        if (account.getActive() == 2 && (account.getRoleId() == 1 || account.getRoleId() == 6)) {
            rd.editResidentStatus(account.getpId(), "1");
        }

        session.removeAttribute("errorMessage");
        request.setAttribute("message", "Mật khẩu đã được thay đổi thành công.");
        session.removeAttribute("account");
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Xử lý cập nhật mật khẩu cho cư dân";
    }
}