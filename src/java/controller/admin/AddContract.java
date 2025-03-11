/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.CompanyDAO;
import dao.ContractApproveDAO;
import dao.ContractDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Company;
import model.Contract;
import model.ContractApprove;
import model.Staff;
import util.Util;
import validation.CommonValidation;

/**
 *
 * @author pc
 */
@WebServlet(name = "AddContract", urlPatterns = {"/add-new-contract"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class AddContract extends HttpServlet {

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
            out.println("<title>Servlet AddContract</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddContract at " + request.getContextPath() + "</h1>");
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
        CompanyDAO cpd = new CompanyDAO();
        StaffDAO std = new StaffDAO();
        List<Company> listcompany = cpd.getAll();
        List<Staff> listaccountant = std.getActiveStaffbyRole("3");
        List<Staff> listadmin = std.getActiveStaffbyRole("0");
        session.setAttribute("listcompany", listcompany);
        session.setAttribute("listaccountant", listaccountant);
        session.setAttribute("listadmin", listadmin);
        request.getRequestDispatcher("addcontract.jsp").forward(request, response);
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
        Util u = new Util();
        String title = u.stringNomalize(request.getParameter("title"));
        String description = u.stringNomalize(request.getParameter("description"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String paydate = request.getParameter("paydate");
        String signdate = request.getParameter("signdate");
        String company = request.getParameter("company");
        String admin = request.getParameter("admin");
        String accountant = request.getParameter("accountant");
        String sid = request.getParameter("sid");
        Part filePart = request.getPart("file");
        String image = "";
        if (filePart != null && filePart.getSize() > 0) {
            String filename = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fileExtention = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            if (!fileExtention.matches("jpg|jpeg")) {
                request.setAttribute("fileerror", "Only jpg");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            String uploadpath = getServletContext().getRealPath("/") + "images/contract";
            File uploadDir = new File(uploadpath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            File file = new File(uploadDir, filename);
            try (InputStream fileContent = filePart.getInputStream(); FileOutputStream outputStream = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int byteread;
                while ((byteread = fileContent.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, byteread);
                }

            }
            image = "images/contract/" + filename;
        }
        if (company == null) {
            request.setAttribute("companyerror", "Company not found");
            request.getRequestDispatcher("addcontract.jsp").forward(request, response);
            return;
        }
        if (admin == null) {
            request.setAttribute("adminerror", "Admin not found");
            request.getRequestDispatcher("addcontract.jsp").forward(request, response);
            return;
        }
        if (accountant == null) {
            request.setAttribute("accountanterror", "Accountant not found");
            request.getRequestDispatcher("addcontract.jsp").forward(request, response);
            return;
        }
        try {
            if (!CommonValidation.isValidNewsDate(signdate)) {
                request.setAttribute("signdateerror", "Signdate need to later current date");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            if (!CommonValidation.validendateafterstartdate(paydate, signdate)) {
                request.setAttribute("paydateerror", "Paydate need to later signdate");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            if (!CommonValidation.isValidNewsDate(paydate)) {
                request.setAttribute("paydateerror", "Paydate need to later current date");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            if (!CommonValidation.validendateafterstartdate(startDate, signdate)) {
                request.setAttribute("startdateerror", "Startdate need to later signdate");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            if (!CommonValidation.isValidNewsDate(startDate)) {
                request.setAttribute("startdateerror", "Startdate need to later current date");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            if (!CommonValidation.isValidNewsDate(endDate)) {
                request.setAttribute("enddateerror", "Enddate need to later current date");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            if (!CommonValidation.validendateafterstartdate(endDate, startDate)) {
                request.setAttribute("enddateerror", "Enddate need to later startdate");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
            if (image.isEmpty()) {
                request.setAttribute("fileerror", "Please upload an image");
                request.getRequestDispatcher("addcontract.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CompanyDAO cpd = new CompanyDAO();
        StaffDAO std = new StaffDAO();
        Contract contract = new Contract(std.getById(sid), cpd.getById(company), startDate, endDate, paydate, signdate, title, description, std.getById(accountant), std.getById(admin), image);
        ContractDAO ctd = new ContractDAO();
        LocalDateTime lc = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = lc.format(format);
        String created = formattedDate;
        String updated = formattedDate;
        if (ctd.addContract(contract)) {
            Contract latestContract = ctd.getLastInsertedContract();

            ContractApprove contractApprove = new ContractApprove(
                    latestContract,
                    null,
                    null,
                    1,
                    created,
                    updated,
                    std.getById(accountant),
                    std.getById(admin)
            );
            ContractApproveDAO ctdApprove = new ContractApproveDAO();
            ctdApprove.addApprove(contractApprove);

            request.setAttribute("message", "Add new contract successfully");

            request.getRequestDispatcher("addcontract.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("message", "Add new contract failed");

            request.getRequestDispatcher("addcontract.jsp").forward(request, response);
        }
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
