
import dao.ResidentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import model.Resident;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/export-residents")
public class ExportResidentsServlet extends HttpServlet {

    private final ResidentDAO residentDAO = new ResidentDAO();
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String exportType = request.getParameter("exportType");
     

        try {
            List<Resident> residents;
            if ("all".equals(exportType)) {
                residents = residentDAO.getAll();
            
            } else if ("selected".equals(exportType)) {
                String[] selectedIds = request.getParameterValues("selectedResidents");
              
                if (selectedIds == null || selectedIds.length == 0) {
            
                    return;
                }
                residents = residentDAO.getByMultipleID(selectedIds);
      
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid export type");
                return;
            }

            if (residents == null || residents.isEmpty()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No residents found to export");
                return;
            }

            exportToExcel(residents, response);
        } catch (Exception e) {
            Logger.getLogger(ExportResidentsServlet.class.getName()).log(Level.SEVERE, "Export failed", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error exporting residents");
        }
    }

    private void exportToExcel(List<Resident> residents, HttpServletResponse response) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Residents");

        // Create header row
        String[] headers = {"Name", "Phone", "Email", "Status", "BOD", "Address", "CCCD", "Gender"};
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cell.setCellStyle(style);
        }

        // Fill data
        int rowNum = 1;
        for (Resident resident : residents) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(resident.getName());
            row.createCell(1).setCellValue(resident.getPhone() != null ? resident.getPhone() : "None");
            row.createCell(2).setCellValue(resident.getEmail() != null ? resident.getEmail() : "None");
            row.createCell(3).setCellValue(getStatusText(resident.getStatus()));
            row.createCell(4).setCellValue(resident.getBod());
            row.createCell(5).setCellValue(resident.getAddress());
            row.createCell(6).setCellValue(resident.getCccd() != null ? resident.getCccd() : "None");
            row.createCell(7).setCellValue(resident.getGender());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Set response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=residents_export_"
                + java.time.LocalDate.now() + ".xlsx");

        // Write to output stream
        try (OutputStream out = response.getOutputStream()) {
            workbook.write(out);
        }
        workbook.close();
    }

    private String getStatusText(String status) {
        switch (status) {
            case "0":
                return "Inactive";
            case "1":
                return "Active";
            case "2":
                return "Pending";
            default:
                return "Unknown";
        }
    }
}
