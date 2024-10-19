package com.mycompany.clientapihealth.models;

public class Patient extends Person {

    private String medicalHistory;

    private String currentHealthStatus;

    public Patient() {
        
    }

    public Patient(String medicalHistory, String currentHealthStatus, String name, String number, String address, int id) {
        super(name, number, address, id);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
    }
}
