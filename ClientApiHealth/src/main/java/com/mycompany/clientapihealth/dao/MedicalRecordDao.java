package com.mycompany.clientapihealth.dao;

import com.mycompany.clientapihealth.models.MedicalRecord;
import com.mycompany.clientapihealth.models.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecordDao {

    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    public MedicalRecordDao() {
        initializeMedicalRecords();
    }

    private void initializeMedicalRecords() {
        Patient patient1 = new Patient("No Medical History", "Good", "Johan", "07474653738", "Canning Town", 1);
        Patient patient2 = new Patient("Back-Pain", "Good", "Rafi", "07846637738", "Stratford", 2);
        Patient patient3 = new Patient("Long term suffer fever", "Bad", "Hasan", "0738497383", "Greenwich", 3);
        Patient patient4 = new Patient("1 year ago done Surgery", "Good", "Abir", "07537464847", "Oxford Circus", 4);
        Patient patient5 = new Patient("Allergy", "Fine", "Jabir", "0763537383", "Bond Street", 5);

        medicalRecords.add(new MedicalRecord(1, patient1, "Arthritis", "Nothing"));
        medicalRecords.add(new MedicalRecord(2, patient2, "Chronic kidney disease", "Regular check"));
        medicalRecords.add(new MedicalRecord(3, patient3, "Diabetes", "Control to Eat"));
        medicalRecords.add(new MedicalRecord(4, patient4, "Blood Pressure", "Regular Check"));
        medicalRecords.add(new MedicalRecord(5, patient5, "Arthritis", "Regular Exercise"));
    }

    public void addMedicalRecord(MedicalRecord medicalRecord) {
        validateMedicalRecord(medicalRecord);
        if (medicalRecords.stream().anyMatch(mr -> mr.getId() == medicalRecord.getId())) {
            throw new IllegalArgumentException("Medical record already exists with the same ID");
        }
        medicalRecords.add(medicalRecord);
    }

    public MedicalRecord getMedicalRecord(int id) {
        return medicalRecords.stream()
                .filter(medicalRecord -> medicalRecord.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateMedicalRecord(MedicalRecord updatedMedicalRecord) {
        validateMedicalRecord(updatedMedicalRecord);
        int index = medicalRecords.indexOf(medicalRecords.stream()
                    .filter(medicalRecord -> medicalRecord.getId() == updatedMedicalRecord.getId())
                    .findFirst()
                    .orElse(null));
        if (index != -1) {
            medicalRecords.set(index, updatedMedicalRecord);
        } else {
            throw new IllegalArgumentException("No existing medical record with ID: " + updatedMedicalRecord.getId());
        }
    }

    private void validateMedicalRecord(MedicalRecord medicalRecord) {
        if (medicalRecord == null) {
            throw new IllegalArgumentException("Medical record cannot be null.");
        }
        if (medicalRecord.getDiagnose() == null || medicalRecord.getDiagnose().isEmpty()) {
            throw new IllegalArgumentException("Diagnosis cannot be empty.");
        }
        if (medicalRecord.getTreatment() == null || medicalRecord.getTreatment().isEmpty()) {
            throw new IllegalArgumentException("Treatment cannot be empty.");
        }
    }

    public void deleteMedicalRecord(int id) {
        boolean removed = medicalRecords.removeIf(medicalRecord -> medicalRecord.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Failed to delete medical record with ID: " + id + ". No such medical record found.");
        }
    }

    public List<MedicalRecord> getAllMedicalRecords() {
        return new ArrayList<>(medicalRecords);
    }
}
