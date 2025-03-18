/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff.accountant;

import dao.FundDAO;
import dao.FundHistoryDAO;
import java.io.IOException;
import java.util.List;
import model.Fund;
import model.FundHistory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ViewFund", urlPatterns = {"/fund"})
public class ViewFund extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FundDAO fd = new FundDAO();
        FundHistoryDAO fhd = new FundHistoryDAO();

        List<Fund> listFund = fd.getAll();
        if (!listFund.isEmpty()) {
            // Get the first fund as default
            Fund defaultFund = listFund.get(0);
            List<FundHistory> listFundHistory = fhd.getByFundID(defaultFund.getId());

            request.setAttribute("listFund", listFund);
            request.setAttribute("selectedFund", defaultFund); // Set the default fund
            request.setAttribute("listFundHistory", listFundHistory);
        }

        request.getRequestDispatcher("fund.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FundDAO fd = new FundDAO();
        FundHistoryDAO fhd = new FundHistoryDAO();

        String fundIdStr = request.getParameter("fund");
        int fundId = fundIdStr != null ? Integer.parseInt(fundIdStr) : 1; // Default to 1 if null
        List<Fund> listFund = fd.getAll();
        Fund selectedFund = fd.getById(String.valueOf(fundId)); // Assume this method exists in FundDAO
        List<FundHistory> listFundHistory = fhd.getByFundID(fundId);

        request.setAttribute("listFund", listFund);
        request.setAttribute("selectedFund", selectedFund);
        request.setAttribute("listFundHistory", listFundHistory);

        request.getRequestDispatcher("fund.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to view fund details and history";
    }
}
