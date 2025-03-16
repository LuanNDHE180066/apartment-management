package filter;

import dao.ResidentDAO;
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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Resident;

public class AuthorizedFilter implements Filter {

    private static final boolean debug = true;
    private FilterConfig filterConfig = null;

    public AuthorizedFilter() {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizedFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizedFilter:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (debug) {
            log("AuthorizedFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String uri = req.getServletPath();
        Account a = (session != null) ? (Account) session.getAttribute("account") : null;

        // Bypass filter for static resources
        if (uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".jpg") || uri.endsWith(".png")
                || uri.endsWith(".gif") || uri.endsWith(".jpeg") || uri.endsWith(".svg")) {
            chain.doFilter(request, response);
            return;
        }

        // Redirect unauthenticated users to 401_error.jsp, except for allowed pages
        if (!uri.contains("login.jsp") && !uri.contains("requestpassword.jsp") && !uri.contains("reset-password")
                && a == null && !uri.contains("request-password") && !uri.contains("login-google")
                && !uri.contains("login") && !uri.contains("logout") && !uri.contains("401_error.jsp")
                && !uri.contains("404_error.jsp")) {
            res.sendRedirect("401_error.jsp");
            return;
        }

        // Handle authenticated users with roleId == 1 and status == "2"
        if (a != null && (a.getRoleId() == 1 || a.getRoleId()==6) ) {
            ResidentDAO rd = new ResidentDAO();
            Resident r = rd.getById(a.getpId());
            if (r != null && "2".equals(r.getStatus())) {
       
                if (uri.contains("changepassword.jsp") ||uri.contains("logout")) {
                    chain.doFilter(request, response);
                } else {
                    // Redirect all other requests to changepassword.jsp
                    res.sendRedirect("changepassword.jsp");
                }
                return; // Stop further processing
            }
        }

        // Proceed with the filter chain for all other cases
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
        if (filterConfig != null) {
            if (debug) {
                log("AuthorizedFilter:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthorizedFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthorizedFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n");
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>");
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
