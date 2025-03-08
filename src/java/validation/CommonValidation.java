/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author thanh
 */
public class CommonValidation {
    public boolean isValidPhone(String phone){
        for (int i = 0; i < phone.length(); i++) {
            if(!Character.isDigit(phone.charAt(i))) return false;
        }
        if(phone.length()!=11) return false;
        return true;
    }
    public boolean isValidCCCD(String cccd){
        for (int i = 0; i < cccd.length(); i++) {
            if(!Character.isDigit(cccd.charAt(i))) return false;
        }
        if(cccd.length()!=12) return false;
        return true;
    }
    public static boolean isValidStartDate(String dateStr) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date = df.parse(dateStr);
        
        Date currentDate = new Date();
        
        return !date.after(currentDate);
    }
    public static boolean validendateafterstartdate(String edatestr, String sdatestr) throws ParseException{
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date enddate=sdf.parse(edatestr);
        Date startdate=sdf.parse(sdatestr);
        return !enddate.before(startdate);
    }
    public static boolean isValidNewsDate(String dateStr) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");      
        Date date = df.parse(dateStr);        
        LocalDate cDate = LocalDate.now();
        Date currentDate = df.parse(cDate.toString());        
        return !date.before(currentDate);
    }
    public  boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
    public boolean isValidUsername(String username){
        return username.length()>3;
    }
    public boolean isValidPassword(String password){
        return password.length()>=3;
    }
}
