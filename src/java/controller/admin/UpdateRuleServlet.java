package controller.admin;

import dao.RuleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import model.Rule;

@WebServlet(name = "UpdateRuleServlet", urlPatterns = {"/update-rule"})
public class UpdateRuleServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Cập Nhật Quy Tắc</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Cập Nhật Quy Tắc tại " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            RuleDAO daoR = new RuleDAO();
            Rule rule = daoR.getById(id);

            if (rule != null) {
                request.setAttribute("rule", rule);
                request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Không tìm thấy quy tắc!");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "ID không hợp lệ!");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updateRule.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String effectiveDateStr = request.getParameter("effectiveDate");
        String status = request.getParameter("status");

        if (id == null || title == null || description == null) {
            request.setAttribute("message", "Thiếu các trường bắt buộc!");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            return;
        }

        RuleDAO daoR = new RuleDAO();
        Rule rule = daoR.getById(id);

        if (rule == null) {
            request.setAttribute("rule", rule);
            request.setAttribute("message", "Không tìm thấy quy tắc!");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            return;
        }

        rule.setTitle(title);
        rule.setDescription(description);

        if (status != null) {
            rule.setStatus(status);
        }

        if (effectiveDateStr != null && !effectiveDateStr.isEmpty()) {
            Date effectiveDate = Date.valueOf(effectiveDateStr);

            LocalDate today = LocalDate.now();
            LocalDate effectiveLocalDate = effectiveDate.toLocalDate();

            if (effectiveLocalDate.isBefore(today)) {
                request.setAttribute("message", "Ngày hiệu lực phải sau ngày hiện tại!");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("updateRule.jsp").forward(request, response);
                return;
            }

            rule.setEffectiveDate(effectiveDate.toString());
        }

        boolean isUpdated = daoR.updateRule(rule);

        if (isUpdated) {
            request.setAttribute("rule", rule);
            request.setAttribute("message", "Cập nhật quy tắc thành công!");
            request.setAttribute("status", "true");
        } else {
            request.setAttribute("rule", rule);
            request.setAttribute("message", "Cập nhật quy tắc thất bại!");
            request.setAttribute("status", "false");
        }

        request.getRequestDispatcher("updateRule.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet để cập nhật quy tắc";
    }
}
