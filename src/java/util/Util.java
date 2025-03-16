/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import dao.ResidentDAO;
import dao.StaffDAO;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import model.SendEmail;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author thanh
 */
public class Util {

    public String getTableNameByRoleId(int role) {
        String destination = "";
        switch (role) {
            case 1:
                destination = "resident";
                break;
            case 6:
                destination = "resident";
                break;
            default:
                destination = "staff";
                break;
        }
        return "view-profile-" + destination;
    }

    public String getTableNameByRoleIdEdit(int role) {
        if (role == 1 || role ==6) {
            return "editprofileRE.jsp";
        }
        return "editprofileST.jsp";
    }

    public String getSiteToViewRule(int role) {
        if (role == 1 || role ==6) {
            return "view-rule-resident";
        }
        return "view-rule-admin";
    }

    public String getSiteToViewApartment(int role) {
        if (role == 1 || role ==6) {
            return "view-apartment-resident";
        }
        return "view-apartment-admin";
    }

    public String getSiteToViewRequest(int role) {
        if (role == 1 || role ==6) {
            return "viewrequest_history";
        }
        return "view-all-request";
    }

    public String getSiteToViewFeedBack(int role) {
        if (role == 1 || role ==6) {
            return "view-feed-back-user";
        }
        return "view-all-feedback";
    }

    public int getNumberFromText(String str) {
        return Integer.parseInt(str.substring(1));
    }

    public int getNumberFromTextOnlyNumber(String str) {
        return Integer.parseInt(str);
    }

    public int getNumberFromTextPlusOne(String str) {
        return Integer.parseInt(str.substring(1)) + 1;
    }

    public Date convertStringToDate(String str) {
        String[] number = str.split("-");
        Date date = new Date(Integer.parseInt(number[0]), Integer.parseInt(number[1]), Integer.parseInt(number[2]));
        return date;
    }

    public List<String> getAllEmail() {
        StaffDAO sd = new StaffDAO();
        ResidentDAO rd = new ResidentDAO();
        List<String> rs = new ArrayList<>();
        for (int i = 0; i < sd.getAll().size(); i++) {
            rs.add(sd.getAll().get(i).getEmail());
        }
        for (int i = 0; i < rd.getAll().size(); i++) {
            rs.add(rd.getAll().get(i).getEmail());
        }
        return rs;
    }

    public static String stringNomalize(String str) {
        if (str == null || str.trim().isEmpty()) {
            return "";
        }

        String strNormalize = str.trim();

        strNormalize = strNormalize.replaceAll("[\n\r\t]", " ");

        while (strNormalize.contains("  ")) {
            strNormalize = strNormalize.replace("  ", " ");
        }

        return strNormalize;
    }

    public <T> int getTotalPage(List<T> list, int numberPerPape) {
        int totalPage;
        if (list.size() % numberPerPape == 0) {
            totalPage = list.size() / numberPerPape;
        } else {
            totalPage = list.size() / numberPerPape + 1;
        }
        return totalPage;
    }

    public <T> List<T> getListPerPage(List<T> list, int numberPerPape, String page) {
        if (page == null || page == "") {
            page = "1";
        }
        int index = Integer.parseInt(page);
        int start = numberPerPape * (index - 1);
        int end = numberPerPape * index - 1;
        List<T> listPage = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            listPage.add(list.get(i));
            if (i == list.size() - 1) {
                break;
            }
        }
        return listPage;
    }

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean isCorrectFormatPassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        if (!password.matches(".*[^a-zA-Z0-9].*")) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[a-zA-Z].*")) {
            return false;
        }
        return true;
    }

    public String generatePassword() {
        Random random = new Random();
        String password = "";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialCharacter = "!@#$%^&*()-_=+<>?";
        String allCharacter = upperCase + lowerCase + digits + specialCharacter;

        password += upperCase.charAt(random.nextInt(0, upperCase.length()));
        password += lowerCase.charAt(random.nextInt(0, lowerCase.length()));
        password += digits.charAt(random.nextInt(0, digits.length()));
        password += specialCharacter.charAt(random.nextInt(0, specialCharacter.length()));
        for (int i = 0; i < 2; i++) {
            password += allCharacter.charAt(random.nextInt(0, allCharacter.length()));
        }
        return password;
    }

    public static boolean isCorrectPassword(String input, String password) {
        return BCrypt.checkpw(input, password);
    }

    public static void main(String[] args) {
        Util u = new Util();
    }

    public static boolean compareFeedbackDateToCurrentTime(String date, int distance) {
        try {

            if (date.matches(".*\\.\\d{1,2}$")) {
                date += "0";
            } else if (!date.contains(".")) {
                date += ".000";
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            LocalDateTime testDateTime = LocalDateTime.parse(date, formatter);
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime dayBefore = currentDateTime.minusDays(distance);

            return dayBefore.isBefore(testDateTime);
        } catch (Exception e) {
            System.err.println("Error parsing date: " + date + " -> " + e.getMessage());
            return false;
        }
    }

    public static String formatDate(String date) {
        if (date == null) {
            return null;
        }
        String[] token = date.split("-");
        String temp = token[0];
        token[0] = token[2];
        token[2] = temp;
        return String.join("-", token);
    }

    public static String FormatDateTime(String date) {
        // Convert the stored date string to SQL Timestamp
        Timestamp timestamp = Timestamp.valueOf(date);

        // Define the desired date format
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // Convert and return formatted date
        return sdf.format(timestamp);
    }

}
