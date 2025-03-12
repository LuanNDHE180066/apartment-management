/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author quang
 */
public class News {
    private String id, title, content, source, category, image;
    private Staff staff;
    private String date;

    public String getDate() {
        return date;
    }
    public String formatdate() {
        if (date == null || date.isEmpty()) {
            return "";
        }
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dat = inputFormat.parse(date);

            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            return outputFormat.format(dat);
        } catch (ParseException e) {
            return "";
        }
    }
    public void setDate(String date) {
        this.date = date;
    }

    public News(String title, String content, String source, String category, String image, Staff staff, String date) {
        this.title = title;
        this.content = content;
        this.source = source;
        this.category = category;
        this.image = image;
        this.staff = staff;
        this.date = date;
    }

    public News(String id, String title, String content, String source, String category, String image, Staff staff, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.source = source;
        this.category = category;
        this.image = image;
        this.staff = staff;
        this.date = date;
    }

    public News(String id, String title, String content, String source, String category, String image, Staff staff) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.source = source;
        this.category = category;
        this.image = image;
        this.staff = staff;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    
}
