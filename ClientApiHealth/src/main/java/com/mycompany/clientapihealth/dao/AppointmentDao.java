package com.mycompany.clientapihealth.dao;

import com.mycompany.clientapihealth.models.Appointment;
import com.mycompany.clientapihealth.models.Doctor;
import com.mycompany.clientapihealth.models.Patient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentDao {

    private List<Appointment> appointments = new ArrayList<>();

    public AppointmentDao() {
        initializeAppointments(); // Initialize appointments
    }

    private void initializeAppointments() {
        // Doctor and Patient initialization
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

        // Adding appointments to the list
        appointments.add(new Appointment(1, "2024/06/15", "09:00", patient1, doctor1));
        appointments.add(new Appointment(2, "2024/10/16", "10:00", patient2, doctor2));
        appointments.add(new Appointment(3, "2024/09/17", "11:00", patient3, doctor3));
        appointments.add(new Appointment(4, "2024/07/18", "12:00", patient4, doctor4));
        appointments.add(new Appointment(5, "2024/08/19", "13:00", patient5, doctor5));
    }

    public void addAppointment(Appointment appointment) {
        validateAppointment(appointment);
        if (appointments.stream().anyMatch(a -> a.getId() == appointment.getId())) {
            throw new IllegalArgumentException("Appointment already exists with the same ID");
        }
        appointments.add(appointment);
    }
    
    private void validateAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate appointmentDate;
        try {
            appointmentDate = LocalDate.parse(appointment.getDate(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: yyyy/MM/dd.");
        }

        if (appointmentDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date must be in the future.");
        }
        
        if (!appointment.getTime().matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Invalid time format. Expected HH:mm.");
        }
    }

    public Appointment getAppointment(int id) {
        return appointments.stream()
                .filter(appointment -> appointment.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateAppointment(Appointment updatedAppointment) {
        validateAppointment(updatedAppointment);
        int index = appointments.indexOf(appointments.stream()
                          .filter(appointment -> appointment.getId() == updatedAppointment.getId())
                          .findFirst()
                          .orElse(null));
        if (index != -1) {
            appointments.set(index, updatedAppointment);
        } else {
            throw new IllegalArgumentException("No existing appointment with ID: " + updatedAppointment.getId());
        }
    }

    public void deleteAppointment(int id) {
        appointments.removeIf(appointment -> appointment.getId() == id);
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }
}
