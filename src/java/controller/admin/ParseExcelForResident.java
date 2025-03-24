package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Resident;
import model.Role;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/parseExcelForResident")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class ParseExcelForResident extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();
        List<Resident> residents = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        List<String> apartmentIds = new ArrayList<>();

        try {
            Workbook workbook = WorkbookFactory.create(request.getPart("excelFile").getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null || isRowEffectivelyEmpty(row)) {
                    continue;
                }

                Resident resident = new Resident();
                resident.setName(getCellValue(row.getCell(0)));
                resident.setBod(getCellValue(row.getCell(1)));
                resident.setGender(getCellValue(row.getCell(2)));
                resident.setPhone(getCellValue(row.getCell(3)));
                resident.setAddress(getCellValue(row.getCell(4)));
                apartmentIds.add(getCellValue(row.getCell(5)));
                resident.setCccd(getCellValue(row.getCell(6)));

                Role role = new Role();
                role.setId("6"); // Hardcode role to "6" (Render)
                resident.setRole(role);
                residents.add(resident);
            }

            workbook.close();

            for (Resident r : residents) {
                int rowNum = residents.indexOf(r) + 2;
                if (r.getName() == null || r.getName().trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Name is required.");
                }
                if (r.getBod() == null || r.getBod().trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Date of birth is required.");
                }
                if (r.getPhone() == null || r.getPhone().trim().isEmpty()) {
                    errors.add("Row " + rowNum + ": Phone number is required.");
                }
            }

            JSONArray residentsArray = new JSONArray();
            for (int i = 0; i < residents.size(); i++) {
                Resident r = residents.get(i);
                JSONObject residentJson = new JSONObject();
                residentJson.put("name", r.getName());
                residentJson.put("dob", r.getBod());
                residentJson.put("gender", r.getGender());
                residentJson.put("phone", r.getPhone());
                residentJson.put("address", r.getAddress());
                residentJson.put("apartment", apartmentIds.get(i));
                residentJson.put("cccd", r.getCccd());
                residentsArray.put(residentJson);
            }

            HttpSession session = request.getSession();
            session.setAttribute("excelData", residentsArray.toString());
            session.setAttribute("excelErrors", new JSONArray(errors).toString());

            jsonResponse.put("success", true);
            jsonResponse.put("data", residentsArray);
            jsonResponse.put("errors", errors);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResponse.put("success", false);
            jsonResponse.put("error", "Failed to parse Excel file: " + e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    return dateFormat.format(cell.getDateCellValue());
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

    private boolean isRowEffectivelyEmpty(Row row) {
        for (int j = 0; j <= 6; j++) { // Adjusted to 6 since "isRepresent", "username", and "email" are removed
            String value = getCellValue(row.getCell(j));
            if (value != null && !value.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}