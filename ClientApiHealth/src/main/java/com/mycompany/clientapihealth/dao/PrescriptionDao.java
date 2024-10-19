package com.mycompany.clientapihealth.dao;

import com.mycompany.clientapihealth.models.Doctor;
import com.mycompany.clientapihealth.models.Patient;
import com.mycompany.clientapihealth.models.Prescription;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class PrescriptionDao {

    private List<Prescription> prescriptions = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private AtomicInteger nextId = new AtomicInteger(1);  // Use AtomicInteger for thread-safe increment

    public PrescriptionDao() {
        initializePrescriptions();
    }

    private void initializePrescriptions() {
        // Initialize doctors and patients
        Doctor doctor1 = new Doctor("Dr. Hussain", "6544", "Shrewsberry Health Center", 1, "General Practitioners");
        Doctor doctor2 = new Doctor("Dr. Dalwar", "8767", "Newham Hospital", 2, "Public health specialists");
        Doctor doctor3 = new Doctor("Dr. John", "9089", "Redbridge Hospital", 3, "Dermatology");
        Doctor doctor4 = new Doctor("Dr. Nila", "8989", "Tower Hamlet Hospital", 4, "Otolaryngologist");
        Doctor doctor5 = new Doctor("Dr. Mokta", "7777", "Birmingham Hospital", 5, "Public health specialists");

        Patient patient1 = new Patient("No Medical History", "Good", "Johan", "07474653738", "Canning Town", 1);
        Patient patient2 = new Patient("Back-Pain", "Good", "Rafi", "07846637738", "Stratford", 2);
        Patient patient3 = new Patient("Long term suffer fever", "Bad", "Hasan", "0738497383", "Greenwich", 3);
        Patient patient4 = new Patient("1 year ago done Surgery", "Good", "Abir", "07537464847", "Oxford Circus", 4);
        Patient patient5 = new Patient("Allergy", "Fine", "Jabir", "0763537383", "Bond Street", 5);

        // Prescriptions initialization
        prescriptions.add(new Prescription(1, patient1, doctor1, "Medication A", "Dosage A", "Instructions A", LocalDate.of(2024, 1, 15)));
        prescriptions.add(new Prescription(2, patient2, doctor2, "Medication B", "Dosage B", "Instructions B", LocalDate.of(2024, 3, 16)));
        prescriptions.add(new Prescription(3, patient3, doctor3, "Medication C", "Dosage C", "Instructions C", LocalDate.of(2024, 5, 17)));
        prescriptions.add(new Prescription(4, patient4, doctor4, "Medication D", "Dosage D", "Instructions D", LocalDate.of(2024, 5, 17)));
        prescriptions.add(new Prescription(5, patient5, doctor5, "Medication E", "Dosage E", "Instructions E", LocalDate.of(2024, 3, 17)));
    }

    // Add a prescription
    public void addPrescription(Prescription prescription) {
        validatePrescription(prescription);
        if (prescriptions.stream().anyMatch(p -> p.getId() == prescription.getId())) {
            throw new IllegalArgumentException("Prescription already exists with the same ID");
        }
        prescriptions.add(prescription);
    }

    // Validate prescription
    private void validatePrescription(Prescription prescription) {
        if (prescription == null) {
            throw new IllegalArgumentException("Prescription cannot be null.");
        }
        if (prescription.getMedication() == null || prescription.getMedication().isEmpty()) {
            throw new IllegalArgumentException("Medication name cannot be empty.");
        }
        if (prescription.getDosage() == null || prescription.getDosage().isEmpty()) {
            throw new IllegalArgumentException("Dosage cannot be empty.");
        }
        if (prescription.getInstructions() == null || prescription.getInstructions().isEmpty()) {
            throw new IllegalArgumentException("Instructions cannot be empty.");
        }
    }

    // Retrieve a prescription by ID
    public Prescription getPrescription(int id) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update a prescription
    public void updatePrescription(Prescription updatedPrescription) {
        validatePrescription(updatedPrescription);
        prescriptions = prescriptions.stream()
                .map(prescription -> prescription.getId() == updatedPrescription.getId() ? updatedPrescription : prescription)
                .collect(Collectors.toList());
    }

    // Delete a prescription
    public void deletePrescription(int id) {
        prescriptions.removeIf(prescription -> prescription.getId() == id);
    }

    // List all prescriptions
    public List<Prescription> getAllPrescriptions() {
        return new ArrayList<>(prescriptions);
    }
}
