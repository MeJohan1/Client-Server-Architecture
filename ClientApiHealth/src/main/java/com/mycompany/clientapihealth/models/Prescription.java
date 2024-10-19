package com.mycompany.clientapihealth.models;

import java.time.LocalDate;

/**
 *
 * @author johanahmedchowdhury
 */
public class Prescription {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String medication;
    private String dosage;
    private String instructions;
    private LocalDate datePrescribed;
    
    public Prescription (){
        
    }
    // Constructor 
    public Prescription(int id, Patient patient, Doctor doctor, String medication, String dosage, String instructions, LocalDate datePrescribed) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.datePrescribed = datePrescribed;
    }

    //get and set 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public LocalDate getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(LocalDate datePrescribed) {
        this.datePrescribed = datePrescribed;
    }
    
    

}