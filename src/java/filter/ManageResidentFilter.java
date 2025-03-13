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

@WebFilter(filterName = "ManageResidentFilter", urlPatterns = {"/addNewResident", "/view-resident", "/deleteRe", "/updateResidentStatus"})
public class ManageResidentFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public ManageResidentFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (debug) {
            log("ManageResidentFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (debug) {
            log("ManageResidentFilter:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (debug) {
            log("ManageResidentFilter:doFilter()");
        }

        doBeforeProcessing(request, response);
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Avoid creating a new session

        Account a = (session != null) ? (Account) session.getAttribute("account") : null;
        if ( a.getRoleId() != 0) {
            res.sendRedirect("404_error.jsp");
            return;
        }

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    public FilterConfig getFilterConfig() {
        return this.filterConfig;
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null && debug) {
            log("ManageResidentFilter:Initializing filter");
        }
    }

    @Override
    public String toString() {
        return (filterConfig == null) ? "ManageResidentFilter()" : "ManageResidentFilter(" + filterConfig + ")";
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);
        try {
            response.setContentType("text/html");
            PrintStream ps = new PrintStream(response.getOutputStream());
            PrintWriter pw = new PrintWriter(ps);
            pw.print("<html><head><title>Error</title></head><body>");
            pw.print("<h1>The resource did not process correctly</h1><pre>");
            pw.print(stackTrace);
            pw.print("</pre></body></html>");
            pw.close();
            ps.close();
            response.getOutputStream().close();
        } catch (Exception ignored) {
        }
    }

    public static String getStackTrace(Throwable t) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            return sw.getBuffer().toString();
        } catch (Exception ignored) {
            return "";
        }
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
