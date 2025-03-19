/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto.response;

/**
 *
 * @author Lenovo
 */
import java.util.List;
import model.Resident;

public class ResidentPreviewData {
    private List<Resident> residents;
    private List<String> apartmentIds;
    private List<String> errors;  // To store any validation errors
    
    public ResidentPreviewData() {
    }
    
    public ResidentPreviewData(List<Resident> residents, List<String> apartmentIds, List<String> errors) {
        this.residents = residents;
        this.apartmentIds = apartmentIds;
        this.errors = errors;
    }

    // Getters and setters
    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public List<String> getApartmentIds() {
        return apartmentIds;
    }

    public void setApartmentIds(List<String> apartmentIds) {
        this.apartmentIds = apartmentIds;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
