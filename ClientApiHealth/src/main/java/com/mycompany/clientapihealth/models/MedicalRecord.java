package com.mycompany.clientapihealth.models;

/**
 *
 * @author johanahmedchowdhury
 */
public class MedicalRecord {

    private int id;
    private Patient patient;
    private String diagnose;
    private String treatment;
    
    public MedicalRecord (){
        
    }
    public MedicalRecord(int id, Patient patient, String diagnose, String treatment) {
        this.id = id;
        this.patient = patient;
        this.diagnose = diagnose;
        this.treatment = treatment;
    }
    
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

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }    
  

}
