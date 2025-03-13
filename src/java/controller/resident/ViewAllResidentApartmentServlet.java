/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.ApartmentDAO;
import dao.ApartmentDetailDAO;
import dao.FloorDAO;
import dao.ResidentDAO;
import dao.RoomTypeDAO;
import dto.response.FloorResponseDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Apartment;
import model.ApartmentDetail;
import model.Floor;
import model.Resident;
import model.RoomType;
import util.Util;

/**
 *
 * @author pc
 */
@WebServlet(name = "ViewAllResidentApartmentServlet", urlPatterns = {"/view-all-resident-apartment"})
public class ViewAllResidentApartmentServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewAllApartmentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAllApartmentServlet at " + request.getContextPath() + "</h1>");
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
        RoomTypeDAO roomTypeDAO = new RoomTypeDAO();
        Account account = (Account) session.getAttribute("account");
        List<RoomType> listRoomType = roomTypeDAO.getAll();

        String floor = request.getParameter("floor");
        String filterType = request.getParameter("filterType");

        if (floor == null) {
            floor = "";
        }

        if (filterType == null) {
            filterType = "";
        }
        Util u = new Util();

        FloorDAO fld = new FloorDAO();
        List<FloorResponseDTO> listFloor = fld.getAll();
        request.setAttribute("floor", listFloor);

        ResidentDAO rd = new ResidentDAO();
        Resident resident = rd.getById(account.getpId());
        String page = request.getParameter("page");

        ApartmentDetailDAO apd = new ApartmentDetailDAO();
        List<ApartmentDetail> listapartment = apd.getApartmentDetailByOwnerid(resident.getpId(), floor, filterType);

        if (listapartment.size() != 0) {
            int totalPage = u.getTotalPage(listapartment, 1);
            listapartment = u.getListPerPage(listapartment, 1, page);
            request.setAttribute("currentPage", page != null ? page : "1");
            request.setAttribute("totalPage", totalPage);
        } else {
            request.setAttribute("currentPage", 1);
            request.setAttribute("totalPage", 1);
        }

        session.setAttribute("listapartment", listapartment);
        session.setAttribute("types", listRoomType);
        request.getRequestDispatcher("viewresidentapartment.jsp").forward(request, response);
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
    
//    <!DOCTYPE html>
//<html>
//<head>
//    <title>Date Picker Example</title>
//    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
//    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
//    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
//    <script src="https://code.jquery.com/ui/1.12.1/i18n/datepicker-vi.js"></script>
//</head>
//<body>
//    <label for="datepicker">Chọn ngày:</label>
//    <input type="text" id="datepicker" placeholder="dd/MM/yyyy">
//
//    <script>
//        $(function() {
//            $.datepicker.setDefaults($.datepicker.regional['vi']);
//            $("#datepicker").datepicker({
//                dateFormat: 'dd/mm/yy',
//                onClose: function(dateText, inst) {
//                    if (!dateText) {
//                        $(this).val(''); // Nếu không chọn ngày, xóa ô input
//                    }
//                }
//            });
//        });
//    </script>
//</body>
//</html>

}
