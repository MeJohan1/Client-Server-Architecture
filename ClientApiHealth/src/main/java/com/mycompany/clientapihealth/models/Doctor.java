package com.mycompany.clientapihealth.models;

/**
 *
 * @author johanahmedchowdhury
 */
public class Doctor extends Person {
    
    private String specialization;
    
    public Doctor(){
        
    }
    
    public Doctor(String name, String number, String address, int id, String specialization) {
        super(name, number, address, id);
        this.specialization = specialization;
    }

    
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

}

