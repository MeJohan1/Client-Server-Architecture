package com.mycompany.clientapihealth.models;

/**
 *
 * @author johanahmedchowdhury
 */

public class Person {
    private int id;
    private String name;
    private String number;
    private String address;
    
    public Person (){
        
    }
   

    // Constructor initializing fields
    protected Person(String name, String number, String address, int id) {
        this.name = name;
        this.number = number;
        this.address = address;
        this.id = id;
    }

    // Getters and setters for fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
