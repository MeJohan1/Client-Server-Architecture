package com.mycompany.clientapihealth.dao;

import com.mycompany.clientapihealth.models.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatientDao {

    private static PatientDao instance;
    private List<Patient> patients = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(PatientDao.class);
    private AtomicInteger nextId = new AtomicInteger(6); // Assuming the highest ID from initial data is 5

    public PatientDao() {
        initializePatients();
    }

    public static synchronized PatientDao getInstance() {
        if (instance == null) {
            instance = new PatientDao();
        }
        return instance;
    }

    private void initializePatients() {
        patients.add(new Patient("No Medical History", "Good", "Johan", "07474653738", "Canning Town", 1));
        patients.add(new Patient("Back-Pain", "Good", "Rafi", "07846637738", "Stratford", 2));
        patients.add(new Patient("Long term suffer fever", "Bad", "Hasan", "0738497383", "Greenwich", 3)); // Corrected spelling of "Greenwich"
        patients.add(new Patient("1 year ago done Surgery", "Good", "Abir", "07537464847", "Oxford Circus", 4));
        patients.add(new Patient("Allergy", "Fine", "Jabir", "0763537383", "Bond Street", 5));
    }

    public void addPatient(Patient patient) {
        validatePatient(patient);
        patient.setId(nextId.getAndIncrement());
        patients.add(patient);
        logger.info("Added new patient: {}", patient);
    }

    public void updatePatient(Patient updatedPatient) {
        logger.debug("Updating patient with ID: {}", updatedPatient.getId());

        validatePatient(updatedPatient); // Validate the new data

        // Find the index of the patient in the list
        int index = IntStream.range(0, patients.size())
                .filter(i -> patients.get(i).getId() == updatedPatient.getId())
                .findFirst().orElse(-1);

        if (index != -1) {
            // Update the patient at the found index
            patients.set(index, updatedPatient);
            logger.info("Patient updated successfully with ID: {}", updatedPatient.getId());
        } else {
            logger.error("No patient found with ID: {}", updatedPatient.getId());
            throw new IllegalArgumentException("Patient not found with ID: " + updatedPatient.getId());
        }
    }

    private void validatePatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (patient.getName() == null || patient.getName().isEmpty()) {
            throw new IllegalArgumentException("Patient name cannot be empty.");
        }
        if (!patient.getNumber().matches("\\d{10}")) { // Validates a 10-digit number
            throw new IllegalArgumentException("Invalid phone number. It should contain exactly 10 digits.");
        }
        if (patient.getAddress() == null || patient.getAddress().isEmpty()) {
            throw new IllegalArgumentException("Patient address cannot be empty.");
        }
    }

    public Patient getPatient(int id) {
        return patients.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deletePatient(int id) {
        boolean removed = patients.removeIf(patient -> patient.getId() == id);
        if (!removed) {
            logger.error("Failed to delete patient with ID {}. No such patient found.", id);
            throw new IllegalArgumentException("Patient not found with ID: " + id);
        } else {
            logger.info("Patient with ID {} was deleted successfully.", id);
        }
    }

    public List<Patient> getAllPatients() {
        return new ArrayList<>(patients);
    }
}
