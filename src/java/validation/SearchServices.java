package validation;

import com.google.gson.Gson;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Service;

@WebServlet(name = "SearchServices", urlPatterns = {"/search-services"})
public class SearchServices extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("searchName");
        ServiceDAO sd = new ServiceDAO();

        // Ensure searchName is properly trimmed and checked
        if (searchName == null) {
            searchName = "";
        }

        // Fetch services matching the search term
        List<Service> filteredServices = sd.searchByName(searchName);

        // Convert the list to JSON and return it
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(filteredServices);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }

    @Override
    public String getServletInfo() {
        return "Servlet that handles search requests for services.";
    }
}
