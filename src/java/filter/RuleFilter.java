package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
//
@WebFilter(filterName = "RuleFilter", urlPatterns = {
    "/add-new-rule", "/update-rule", "/view-rule-admin", "/delete-rule", "/view-rule-resident"
})
public class RuleFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public RuleFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (debug) {
            log("RuleFilter:doFilter()");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        Account a = (session != null) ? (Account) session.getAttribute("account") : null;
        String uri = req.getServletPath();

        if (a == null) {  // Handle case where user is not logged in
            res.sendRedirect("login.jsp");  // Redirect to login page
            return;
        }

        if (uri.contains("view-rule-resident")) {
            if (a.getRoleId() != 1 && a.getRoleId()!=6) {
                res.sendRedirect("404_error.jsp");
                return;
            }
        } else {
            // Only allow roles 0 and 2 to access other pages
            if (a.getRoleId() != 0 && a.getRoleId() != 2) {
                res.sendRedirect("404_error.jsp");
                return;
            }
        }

        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            handleProcessingError(t, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null && debug) {
            log("RuleFilter:Initializing filter");
        }
    }

    @Override
    public void destroy() {
    }

    private void handleProcessingError(Throwable t, ServletResponse response) throws IOException {
        String stackTrace = getStackTrace(t);
        response.setContentType("text/html");
        try (PrintStream ps = new PrintStream(response.getOutputStream()); PrintWriter pw = new PrintWriter(ps)) {
            pw.print("<html><head><title>Error</title></head><body>");
            pw.print("<h1>The resource did not process correctly</h1><pre>");
            pw.print(stackTrace);
            pw.print("</pre></body></html>");
        }
    }

    public static String getStackTrace(Throwable t) {
        try (StringWriter sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw)) {
            t.printStackTrace(pw);
            return sw.toString();
        } catch (Exception ex) {
            return "";
        }
    }

    public void log(String msg) {
        if (filterConfig != null) {
            filterConfig.getServletContext().log(msg);
        }
    }
}
