/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.ResidentDAO;
import dao.RoomTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Apartment;
import model.LivingApartment;
import model.OwnerApartment;
import model.Resident;
import model.RoomType;

/**
 *
 * @author quang
 */
@WebServlet(name = "UpdateApartmentServlet", urlPatterns = {"/update-apartment"})
public class UpdateApartmentServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateApartmentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateApartmentServlet at " + request.getContextPath() + "</h1>");
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
//        HttpSession session = request.getSession();
        String aid = request.getParameter("id");
        ApartmentDAO dao = new ApartmentDAO();
        LivingApartmentDAO daoLA = new LivingApartmentDAO();
        OwnerApartmentDAO daoAO = new OwnerApartmentDAO();
        RoomTypeDAO daoRT = new RoomTypeDAO();
        ResidentDAO daoR = new ResidentDAO();

        List<Resident> listResident = daoR.getAllResident();
        List<RoomType> listRoomType = daoRT.getAll();
        Apartment a = dao.getById(aid);
        LivingApartment la = daoLA.getLivingResidentByApartmentID(aid);
        OwnerApartment oa = daoAO.getOwnerByApartmentID(aid);
        String livingResidentName = daoLA.getLivingResidentName(aid);
        String livingResidentIds = daoLA.getLivingResidentId(aid);
        List<Resident> listLivingResident = daoLA.getLivingResidentList(aid);

        request.setAttribute("livingResidents", listLivingResident);
        request.setAttribute("apartment", a);
        request.setAttribute("livingResidentNames", livingResidentName);
        request.setAttribute("livingResidentIds", livingResidentIds);
        request.setAttribute("livingResident", la);
        request.setAttribute("ownerApartment", oa);
//        session.setAttribute("roomTypes", listRoomType);
        request.setAttribute("listResident", listResident);

        request.getRequestDispatcher("updateapartment.jsp").forward(request, response);

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
        String aid = request.getParameter("aid");
        String livingId = request.getParameter("livingResidentId"); //mới
        String ownerId = request.getParameter("apartmentOwnerId");// mới
        String livingApartmentId = request.getParameter("livingApartmentID");
        String apartmentOwnerId = request.getParameter("ApartmentID");
        String roomTypeId = request.getParameter("roomtype");
        String infor = request.getParameter("infor");
        String status = request.getParameter("status");

        ApartmentDAO dao = new ApartmentDAO();
        LivingApartmentDAO daoLA = new LivingApartmentDAO();
        OwnerApartmentDAO daoAO = new OwnerApartmentDAO();
        RoomTypeDAO daoRT = new RoomTypeDAO();
        ResidentDAO daoR = new ResidentDAO();

        OwnerApartment oa = daoAO.getOwnerByApartmentID(aid); // chủ cũ
        LivingApartment la = daoLA.getLivingResidentByApartmentID(aid); // người ở cũ

        Resident livingResident = daoR.getById(livingId); //người mới
        Resident ownerResident = daoR.getById(ownerId); // chủ mới

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        String date = now.format(formatter);

        RoomType rt = daoRT.getRoomTypeById(roomTypeId);
        Apartment a = dao.getById(aid);
        a.setRoomtype(rt);
        a.setInfor(infor);
        a.setStatus(Integer.parseInt(status));

//        PrintWriter out = response.getWriter();
//        out.println(ownerId + " " + date + " " + livingId + " " + aid);
        if (infor.trim() == "" || infor.trim().isEmpty()) {
            request.setAttribute("apartment", a);
            request.setAttribute("status", "false");
            request.setAttribute("message", "Apartment information can not be null");
            request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
            return;
        }

        if (oa == null && ownerResident == null && livingResident != null) {
            request.setAttribute("apartment", a);
            request.setAttribute("status", "false");
            request.setAttribute("message", "Please update owner apartment first!!");
            request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
            return;
        }

        if (!dao.updateApartment(a)) {
            request.setAttribute("apartment", a);
            request.setAttribute("status", "false");
            request.setAttribute("message", "False to update 0");
            request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
            return;
        }

        if (oa != null || ownerResident != null) {
            if (oa == null && ownerResident != null) {
                if (!daoAO.insertOwnerApartment(ownerId, aid, date)) {
                    request.setAttribute("apartment", a);
                    request.setAttribute("status", "false");
                    request.setAttribute("message", "Failed to update 2");
                    request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
                    return;
                }
            } else if (!oa.getRid().getpId().equals(ownerResident.getpId())) {
                if (!daoAO.updateEndOwnerApartment(aid, date)) {
                    request.setAttribute("apartment", a);
                    request.setAttribute("status", "false");
                    request.setAttribute("message", "Failed to update 3");
                    request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
                    return;
                }

                if (!daoAO.insertOwnerApartment(ownerId, aid, date)) {
                    request.setAttribute("apartment", a);
                    request.setAttribute("status", "false");
                    request.setAttribute("message", "Failed to update 4");
                    request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
                    return;
                }
            }
        }

        if (la != null || livingResident != null) {
            if (la == null && livingResident != null) {
                if (!daoLA.insertLivingApartment(livingId, aid, date)) {
                    request.setAttribute("apartment", a);
                    request.setAttribute("status", "false");
                    request.setAttribute("message", "Failed to update 5");
                    request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
                    return;
                }
            } else if (!la.getRid().getpId().equals(livingResident.getpId())) {
                if (!daoLA.updateEndLivingApartment(date, aid)) {
                    request.setAttribute("apartment", a);
                    request.setAttribute("status", "false");
                    request.setAttribute("message", "Failed to update 6 ");
//                out.println(ownerId + " " + date + " " + livingId + " " + aid);
                    request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
                    return;
                }
                if (!daoLA.insertLivingApartment(livingId, aid, date)) {
                    request.setAttribute("apartment", a);
                    request.setAttribute("status", "false");
                    request.setAttribute("message", "Failed to update 7");
                    request.getRequestDispatcher("updateapartment.jsp").forward(request, response);
                    return;
                }
            }
        }
        List<Resident> listResident = daoR.getAllResident();
        List<RoomType> listRoomType = daoRT.getAll();

        la = daoLA.getLivingResidentByApartmentID(aid);
        oa = daoAO.getOwnerByApartmentID(aid);
        request.setAttribute("apartment", a);
        request.setAttribute("livingResident", la);
        request.setAttribute("ownerApartment", oa);
        request.setAttribute("roomTypes", listRoomType);
        request.setAttribute("listResident", listResident);
        request.setAttribute("status", "true");
        request.setAttribute("message", "Update successfully");
        request.getRequestDispatcher("updateapartment.jsp").forward(request, response);

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
