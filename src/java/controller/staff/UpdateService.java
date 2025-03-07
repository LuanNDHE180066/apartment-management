/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.CategoryServiceDAO;
import dao.CompanyDAO;
import dao.MonthlyServiceDAO;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Service;
import util.Util;

/**
 *
 * @author thanh
 */
@WebServlet(name = "UpdateService", urlPatterns = {"/update-service-staff"})
public class UpdateService extends HttpServlet {

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
            out.println("<title>Servlet UpdateService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateService at " + request.getContextPath() + "</h1>");
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
        CategoryServiceDAO csd = new CategoryServiceDAO();
        CompanyDAO cd = new CompanyDAO();
        ServiceDAO sd = new ServiceDAO();
        String id = request.getParameter("id");
        Service s = sd.getById(id);
        request.setAttribute("service", s);
        request.setAttribute("types", csd.getAll());
        request.setAttribute("companies", cd.getAll());
        request.getRequestDispatcher("updateservice.jsp").forward(request, response);
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
        String unit = Util.stringNomalize(request.getParameter("unit"));
        CategoryServiceDAO csd = new CategoryServiceDAO();
        CompanyDAO cd = new CompanyDAO();
        String id = request.getParameter("id");
        String name = Util.stringNomalize(request.getParameter("name"));
          String price_raw = request.getParameter("price");
        price_raw = price_raw.replace(".", "");
        float price = Float.parseFloat(price_raw);
        String des = Util.stringNomalize(request.getParameter("des"));
        if (name.trim().isBlank() || des.trim().isBlank()) {
            request.setAttribute("error", "Name or description is not a blank");
            request.setAttribute("companies", cd.getAll());
            request.setAttribute("types", csd.getAll());
            request.getRequestDispatcher("updateservice.jsp").forward(request, response);
            return;
        }
        ServiceDAO sd = new ServiceDAO();
        Service sv = sd.getById(id);
        String categoryId = request.getParameter("category");
        String companyId = request.getParameter("company");
        Service input = new Service(id, name, price, des, csd.getByCategoryId(categoryId), cd.getById(companyId), 0, sv.getStartDate(), sv.getEndDate(), unit);
        if(input.equals(sv)){
            request.setAttribute("error", "You doesn't change anything");
            request.setAttribute("service", sv);
            request.setAttribute("companies", cd.getAll());
            request.setAttribute("types", csd.getAll());
            request.getRequestDispatcher("updateservice.jsp").forward(request, response);
            return;
        }
        int status = Integer.parseInt(request.getParameter("status"));
            if (status != sv.getStatus()) {//trường hợp đổi status
            if (status == 1) {// tức là từ không hoạt động lên hoạt động = tạo mới
                sd.addService(name, price, des, categoryId, companyId, status,unit);
            }
            else{ //từ hoạt động xuống dừng thì chỉ đổi status và enddate
                sd.turnToInActive(id);
                MonthlyServiceDAO md = new MonthlyServiceDAO();
                md.deleteWhenTurnOffService(id);//tắt các nơi đã đki dịch vụ
            }
        }
        else{//nếu như status không đổi mà chỉ đổi các thuộc tính khác
            String newServiceId =sd.addService(name, price, des, categoryId, companyId, status,unit);//tạo mới
            sd.turnToInActive(id);//off cũ
            MonthlyServiceDAO md = new MonthlyServiceDAO();
            md.switchService(newServiceId, id);//đổi cũ sang mới
        }
        response.sendRedirect("all-services");
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
