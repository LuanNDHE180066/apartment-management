import dao.ResidentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import model.Resident;

import java.io.ByteArrayOutputStream;
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
                    response.setContentType("text/html");
                    response.getWriter().write("<script>alert('Please select at least one resident to export'); window.history.back();</script>");
                    return;
                }
                residents = residentDAO.getByMultipleID(selectedIds);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid export type");
                return;
            }

            if (residents == null || residents.isEmpty()) {
                response.setContentType("text/html");
                response.getWriter().write("<script>alert('No residents found to export'); window.history.back();</script>");
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

        String[] headers = {"Name", "Phone", "Email", "Status", "BOD", "Address", "CCCD", "Gender", "Apartment Number"};
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

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
            StringBuilder aptNumbers = new StringBuilder();
            if (resident.getLivingApartment() != null) {
                resident.getLivingApartment().forEach(apt -> {
                    if (aptNumbers.length() > 0) aptNumbers.append(", ");
                    aptNumbers.append(apt.getId());
                });
            }
            row.createCell(8).setCellValue(aptNumbers.length() > 0 ? aptNumbers.toString() : "None");
        }

        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        byte[] bytes = baos.toByteArray();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=residents_export_"
                + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
                + ".xlsx");
        response.setContentLength(bytes.length);

        try (OutputStream out = response.getOutputStream()) {
            out.write(bytes);
            out.flush();
        }
        workbook.close();
    }

    private String getStatusText(String status) {
        switch (status) {
            case "0": return "Inactive";
            case "1": return "Active";
            case "2": return "Pending";
            default: return "Unknown";
        }
    }
}