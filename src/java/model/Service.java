/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.Util;

/**
 *
 * @author Lenovo
 */
public class Service {
    private String id;
    private String name;
    private double unitPrice;
    private String description;
    private CategoryService categoryService;
    private Company company;
    private int status;
    private String startDate,endDate;
    private String unit;

    public Service(String id, String name, double unitPrice, String description, CategoryService categoryService, Company company, int status, String startDate, String endDate, String unit) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.categoryService = categoryService;
        this.company = company;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public String getStartDateFormat(){
        return Util.formatDate(startDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getEndDateFormat() {
        return Util.formatDate(endDate);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Service() {
    }

    public boolean equals(Service obj) {
//         private String id;
//    private String name;
//    private double unitPrice;
//    private String description;
//    private CategoryService categoryService;
//    private Company company;
//    private int status;
//    private String startDate,endDate;
//    private String unit;
            return name.equalsIgnoreCase(obj.getName())
                    && unitPrice== obj.getUnitPrice() 
                    && description.equalsIgnoreCase(obj.getDescription())
                    && categoryService.getId().equalsIgnoreCase(obj.getCategoryService().getId())
                    && company.getId().equalsIgnoreCase(obj.getCompany().getId())
                    && status == obj.getStatus()
                    && unit.equalsIgnoreCase(obj.getUnit());
    }
    
    public Service(String id, String name, double unitPrice, String description, CategoryService categoryService, Company company, int status) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
        this.categoryService = categoryService;
        this.company = company;
        this.status = status;
    }
    
}
