package com.mycompany.clientapihealth.dao;

import com.mycompany.clientapihealth.models.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorDao {

    private List<Doctor> doctors = new ArrayList<>();

    public DoctorDao() {
        initializeDoctors();
    }

    private void initializeDoctors() {
        doctors.add(new Doctor("Dr. Hussain", "6544", "Shrewsberry Health Center", 1, "General Practitioners"));
        doctors.add(new Doctor("Dr. Dalwar", "8767", "Newham Hospital", 2, "Public health specialists"));
        doctors.add(new Doctor("Dr. John", "9089", "Redbridge Hospital", 3, "Dermatology"));
        doctors.add(new Doctor("Dr. Nila", "8989", "Tower Hamlet Hospital", 4, "Otolaryngologist"));
        doctors.add(new Doctor("Dr. Mokta", "7777", "Birmingham Hospital", 5, "Public health specialists"));
    }
    
    public void addDoctor(Doctor doctor) {
        validateDoctor(doctor);  // Validate doctor before adding
        if (doctors.stream().anyMatch(d -> d.getId() == doctor.getId())) {
            throw new IllegalArgumentException("Doctor already exists with the same ID");
        }
        doctors.add(doctor);
    }

    public Doctor getDoctor(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateDoctor(Doctor updatedDoctor) {
        validateDoctor(updatedDoctor);  // Validate doctor before updating
        int index = doctors.indexOf(doctors.stream()
                    .filter(doctor -> doctor.getId() == updatedDoctor.getId())
                    .findFirst()
                    .orElse(null));
        if (index != -1) {
            doctors.set(index, updatedDoctor);
        } else {
            throw new IllegalArgumentException("No existing doctor with ID: " + updatedDoctor.getId());
        }
    }

    private void validateDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null.");
        }
        if (doctor.getName() == null || doctor.getName().isEmpty()) {
            throw new IllegalArgumentException("Doctor name cannot be empty.");
        }
        if (doctor.getSpecialization() == null || doctor.getSpecialization().isEmpty()) {
            throw new IllegalArgumentException("Doctor specialization cannot be empty.");
        }
    }

    public void deleteDoctor(int id) {
        boolean removed = doctors.removeIf(doctor -> doctor.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("Failed to delete doctor with ID: " + id + ". No such doctor found.");
        }
    }

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }
}
