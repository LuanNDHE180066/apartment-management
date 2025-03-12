/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.InvoiceDAO;
import dao.MonthlyServiceDAO;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import model.Invoice;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author thanh
 */
@MultipartConfig
@WebServlet(name = "SynchronousUsedService", urlPatterns = {"/synchronous-used-service"})
public class SynchronousUsedService extends HttpServlet {

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
            out.println("<title>Servlet SynchronousUsedService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SynchronousUsedService at " + request.getContextPath() + "</h1>");
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
//        <input hidden = "" name = "apartmentSelected" value = "${requestScope.usingApartment}" /
//                > <input hidden = "" name = "from" value = "${requestScope.usingFrom}" /
//                > <input hidden = "" name = "to" value = "${requestScope.usingTo}" / >
        try {
            String apartmentSelected = request.getParameter("apartmentSelected");
            String fromDate = request.getParameter("from");
            String toDate = request.getParameter("to");
            InvoiceDAO ivd = new InvoiceDAO();
            List<Invoice> invoiceList = ivd.searchByTimeAndApartment(fromDate, toDate, apartmentSelected);

            FileOutputStream file = new FileOutputStream("C:\\Users\\thanh\\Downloads\\SP2025\\SWP391\\apartment-management\\report_invoice.xlsx");

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("All");
            XSSFRow row;
            XSSFCell cell1;
            XSSFCell cell2;
            XSSFCell cell3;
            XSSFCell cell4;
            XSSFCell cell5;
            XSSFCell cell6;

            //header cho row cua sheet
            row = sheet.createRow(0);
            cell1 = row.createCell(0);
            cell1.setCellValue("Invoice Date");
            cell2 = row.createCell(1);
            cell2.setCellValue("Due Date");
            cell3 = row.createCell(2);
            cell3.setCellValue("Description");
            cell4 = row.createCell(3);
            cell4.setCellValue("Customer");
            cell5 = row.createCell(4);
            cell5.setCellValue("Apartment");
            cell6 = row.createCell(5);
            cell6.setCellValue("Amount");

            for (int i = 0; i < invoiceList.size(); i++) {
                row = sheet.createRow(i + 1);
                Invoice iv = invoiceList.get(i);
                cell1 = row.createCell(0);
                cell1.setCellValue(iv.getId());
                cell2 = row.createCell(1);
                cell2.setCellValue(iv.getInvoiceDate());
                cell3 = row.createCell(2);
                cell3.setCellValue(iv.getDescription());
                cell4 = row.createCell(3);
                cell4.setCellValue(iv.getResident().getName());
                cell5 = row.createCell(4);
                cell5.setCellValue(iv.getApartment().getId());
                cell6 = row.createCell(5);
                cell6.setCellValue(iv.getTotal());

            }
            row = sheet.createRow(invoiceList.size() + 1);
            cell5 = row.createCell(4);
            cell5.setCellValue("TOTAL");
            cell6 = row.createCell(5);
            cell6.setCellValue(ivd.getTotalByTimeAndApartment(fromDate, toDate, apartmentSelected));
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(file);
            workbook.close();
            file.close();
            response.sendRedirect("view-invoice-staff");
        } catch (IOException e) {
            System.out.println(e);
        }
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
        MonthlyServiceDAO md = new MonthlyServiceDAO();
        ServiceDAO sd = new ServiceDAO();
        Part fileEVN = request.getPart("fileEVN"); // Lấy file từ request
        if (fileEVN != null) {
            InputStream fileContent = fileEVN.getInputStream();
            try (Workbook workbook = WorkbookFactory.create(fileContent)) {
                Sheet sheet = workbook.getSheetAt(0); // Đọc sheet đầu tiên
                int count = 1;
                // Duyệt qua các dòng và cột
                for (Row row : sheet) {
                    if (count == 1) {
                        count = 0;
                        continue;
                    }
                    String aid = row.getCell(0).getStringCellValue();
                    int quantity = (int) row.getCell(1).getNumericCellValue();
                    String sid = sd.getServiceEVNId();
                    md.updateQuantityByServiceAndApartment(sid, aid, quantity);
                }
                workbook.close();
                fileContent.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        Part fileWVN = request.getPart("fileWVN"); // Lấy file từ request
        if (fileWVN != null) {
            InputStream fileContent = fileWVN.getInputStream();

            try (Workbook workbook = WorkbookFactory.create(fileContent)) {
                Sheet sheet = workbook.getSheetAt(0); // Đọc sheet đầu tiên
                int count = 1;
                // Duyệt qua các dòng và cột
                for (Row row : sheet) {
                    if (count == 1) {
                        count = 0;
                        continue;
                    }
                    String aid = row.getCell(0).getStringCellValue();
                    int quantity = (int) row.getCell(1).getNumericCellValue();
                    String sid = sd.getServiceWVNId();
                    md.updateQuantityByServiceAndApartment(sid, aid, quantity);
                }
                workbook.close();
                fileContent.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        response.sendRedirect("view-apartmentservice-staff");
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
