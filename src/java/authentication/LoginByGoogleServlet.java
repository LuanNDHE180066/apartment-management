/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package authentication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import config.GoogleConfig;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import util.Util;

/**
 *
 * @author thanh
 */
public class LoginByGoogleServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginByGoogleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginByGoogleServlet at " + request.getContextPath() + "</h1>");
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
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        String email = getEmailUser(accessToken);
        Util util = new Util();
        if (!util.getAllEmail().contains(email)) {
            request.setAttribute("error", "Email is not exist");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        AccountDAO ad = new AccountDAO();
        Account account = ad.getByEmail(email);
        HttpSession session = request.getSession();
        session.setAttribute("account", account);
        response.sendRedirect("index.jsp");
    }

    public String getToken(String code) throws ClientProtocolException, IOException {
        String CIENT_ID = GoogleConfig.CLIENT_ID;
        String CLIENT_SECRET = GoogleConfig.CLIENT_SECRET;
        String REDIRECT_URI = "http://localhost:8080/apartment-management/login-google";
        String LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
        String GRANT_TYPE = "authorization_code";
        String response = Request.Post(LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", CIENT_ID)
                        .add("client_secret", CLIENT_SECRET)
                        .add("redirect_uri", REDIRECT_URI).add("code", code)
                        .add("grant_type", GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public String getEmailUser(String accessToken) throws ClientProtocolException, IOException {
        String LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
        String link = LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
        String email = jsonObject.get("email").getAsString();
        return email;
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
