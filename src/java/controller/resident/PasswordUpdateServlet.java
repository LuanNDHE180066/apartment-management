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
        // Clear any existing error message on initial load (GET request)
        HttpSession session = request.getSession();
        session.removeAttribute("errorMessage");
        request.getRequestDispatcher(JSP_PAGE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Ensure account exists in session
        if (account == null) {
            session.setAttribute("errorMessage", "Session expired. Please log in again.");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
            return;
        }

        String newpw = request.getParameter("newPassword").trim();
        String cfnewpw = request.getParameter("cfnewPassword").trim();
        ResidentDAO rd = new ResidentDAO();

        // Validate old password if account is not in initial state (active != 2)
        if (account.getActive() != 2) {
            String oldpw = request.getParameter("oldPassword").trim();
            if (!Util.isCorrectPassword(oldpw, account.getPassword())) {
                session.setAttribute("errorMessage", "Old password is not correct");
                request.getRequestDispatcher(JSP_PAGE).forward(request, response);
                return;
            }
        }

        // Check for blank/empty password
        if (newpw.isBlank() || newpw.isEmpty()) {
            session.setAttribute("errorMessage", "Password must not be blank");
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
            return;
        }

        // Check if new password matches confirm password
        if (!newpw.equals(cfnewpw)) {
            session.setAttribute("errorMessage", "New password does not match confirm password");
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
            return;
        }

        // Validate password format
        if (!Util.isCorrectFormatPassword(newpw)) {
            session.setAttribute("errorMessage", "The password must have at least 6 characters, including at least 1 lowercase letter, 1 uppercase letter, 1 number, and 1 special character.");
            request.getRequestDispatcher(JSP_PAGE).forward(request, response);
            return;
        }

        // Update password in the database
        AccountDAO ad = new AccountDAO();
        ad.changePassword(account.getUsername(), newpw, account.getRoleId());

        // Update resident status if account is in initial state
        if (account.getActive() == 2 && (account.getRoleId() == 1 || account.getRoleId() == 6)) {
            rd.editResidentStatus(account.getpId(), "1");
        }

        session.removeAttribute("errorMessage");
        request.setAttribute("message", "Password has changed");
        session.removeAttribute("account");
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Handles password updates for residents";
    }
}
