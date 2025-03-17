/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Apartment;

import model.Resident;
import util.Util;

/**
 *
 * @author quang
 */
public class ViewAllResident extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResidentDAO daoR = new ResidentDAO();
        ApartmentDAO aptDao = new ApartmentDAO(); // Add this to get apartment list
        Util u = new Util();

        // Retrieve filter parameters
        String name = request.getParameter("searchName");
        String status = request.getParameter("filterStatus");
        String aptNumber = request.getParameter("aptNumber"); // Changed from aptNumber to match JSP
        String page = request.getParameter("page");

        // Normalize name input
        name = u.stringNomalize(name);

        // Store filters in session only if provided, else remove
        if (name != null && !name.trim().isEmpty()) {
            session.setAttribute("searchName", name);
        } else {
            session.removeAttribute("searchName");
            name = "";
        }

        if (status != null && !status.trim().isEmpty()) {
            session.setAttribute("filterStatus", status);
        } else {
            session.removeAttribute("filterStatus");
            status = "";
        }

        if (aptNumber != null && !aptNumber.trim().isEmpty()) {
            session.setAttribute("aptNumber", aptNumber);
        } else {
            session.removeAttribute("aptNumber");
            aptNumber = "";
        }

        // Always set the apartment list
        List<Apartment> listApt = aptDao.getAll();
        request.setAttribute("listApt", listApt);

        // Fetch residents based on filters
        List<Resident> listResident = daoR.filterListResident(name, status, aptNumber);

        // Handle pagination
        if (page == null) {
            page = "1";
        }
        int totalPage = u.getTotalPage(listResident, 5);

        // Paginate results
        if (!listResident.isEmpty()) {
            listResident = u.getListPerPage(listResident, 5, page);
            request.setAttribute("listResident", listResident);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", Integer.parseInt(page));
            request.setAttribute("isFilter", "true");
        } else {
            request.setAttribute("totalPage", 1);
            request.setAttribute("currentPage", 1);
            request.setAttribute("message", "No result");
        }

        request.getRequestDispatcher("viewallresident.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResidentDAO daoR = new ResidentDAO();
        ApartmentDAO aptDao = new ApartmentDAO();
        List<Apartment> listApt = aptDao.getAll();
        List<Resident> listResident = daoR.getAll();
        request.setAttribute("listApt", listApt);
        request.setAttribute("listResident", listResident);
        request.getRequestDispatcher("viewallresident.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
