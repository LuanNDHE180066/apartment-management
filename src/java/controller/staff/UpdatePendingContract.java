/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.staff;

import dao.ContractApproveDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author pc
 */
@WebServlet(name="UpdatePendingContract", urlPatterns={"/update-pending-contract"})
public class UpdatePendingContract extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet UpdatePendingContract</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePendingContract at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    // Lấy tham số từ request
    String contractId = request.getParameter("contractId");
    String approveStatus = request.getParameter("approve");
    HttpSession session = request.getSession();
    Account account = (Account) session.getAttribute("account"); // Lấy thông tin người dùng đăng nhập

    // Kiểm tra tham số đầu vào
    if (contractId == null || contractId.isEmpty() || approveStatus == null || approveStatus.isEmpty() || account == null) {
        request.setAttribute("error", "Invalid parameters.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }

    // Chuyển đổi approveStatus sang số
    int approvalStatus;
    try {
        approvalStatus = Integer.parseInt(approveStatus);
        if (approvalStatus != 1 && approvalStatus != -1) {
            request.setAttribute("error", "Invalid approval status value. Expected 1 (approve) or -1 (reject).");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
    } catch (NumberFormatException e) {
        e.printStackTrace();
        request.setAttribute("error", "Invalid approval status value.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }

    // Kiểm tra quyền truy cập
    if (!isAdmin(account) && !isAccountant(account)) {
        request.setAttribute("error", "You do not have permission to perform this action.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }

    // Cập nhật trạng thái phê duyệt trong cơ sở dữ liệu
    ContractApproveDAO contractApproveDAO = new ContractApproveDAO();
    boolean isUpdated = false;
    try {


        // Cập nhật trạng thái phê duyệt
        isUpdated = contractApproveDAO.updateApprovalStatus(contractId, account.getpId(), approvalStatus);
    } catch (Exception e) {
        e.printStackTrace();
        request.setAttribute("error", "An error occurred while updating the contract approval status.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
        return;
    }

    // Xử lý kết quả cập nhật
    if (isUpdated) {
        // Cập nhật thành công, chuyển hướng đến trang danh sách hợp đồng
        response.sendRedirect("viewapprovecontrat.jsp");
    } else {
        // Cập nhật thất bại, hiển thị thông báo lỗi
        request.setAttribute("error", "Failed to update the contract approval status.");
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}

// Kiểm tra xem người dùng có phải là admin không
private boolean isAdmin(Account account) {
    return account.getRoleId() == 0; // Giả sử roleId = 0 là admin
}

// Kiểm tra xem người dùng có phải là accountant không
private boolean isAccountant(Account account) {
    return account.getRoleId() == 3; // Giả sử roleId = 3 là accountant
}
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
