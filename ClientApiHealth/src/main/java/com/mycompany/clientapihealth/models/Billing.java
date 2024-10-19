package com.mycompany.clientapihealth.models;

import java.time.LocalDate;

/**
 *
 * @author johanahmedchowdhury
 */
public class Billing {
    private int id;
    private Patient patient;
    private double amountCharged;
    private double amountPaid;
    private double OutstandingBalance;
    private LocalDate billingDate;

    public Billing(){
        
    }
    public Billing(int id, Patient patient, double amountCharged, double amountPaid, double OutstandingBalance, LocalDate billingDate) {
        this.id = id;
        this.patient = patient;
        this.amountCharged = amountCharged;
        this.amountPaid = amountPaid;
        this.OutstandingBalance = OutstandingBalance;
        this.billingDate = billingDate;
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

    public double getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(double amountCharged) {
        this.amountCharged = amountCharged;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public double getOutstandingBalance() {
        return OutstandingBalance;
    }

    public void setOutstandingBalance(double OutstandingBalance) {
        this.OutstandingBalance = OutstandingBalance;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(LocalDate billingDate) {
        this.billingDate = billingDate;
    }
    

}
