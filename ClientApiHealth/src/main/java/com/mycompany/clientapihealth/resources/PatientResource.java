package com.mycompany.clientapihealth.resources;

import com.mycompany.clientapihealth.dao.PatientDao;
import com.mycompany.clientapihealth.exception.UserNotFoundException;
import com.mycompany.clientapihealth.models.Patient;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/patients")
public class PatientResource {

    private static final Logger logger = LoggerFactory.getLogger(PatientResource.class);
    private PatientDao patientDao = PatientDao.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        List<Patient> patients = patientDao.getAllPatients();
        return Response.ok(patients).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatient(@PathParam("id") int id) {
        Patient patient = patientDao.getPatient(id);
        if (patient == null) {
            logger.error("Patient not found with ID: {}", id);
            throw new UserNotFoundException("Patient not found with ID: " + id);
        }
        return Response.ok(patient).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPatient(Patient patient) {
        try {
            // Check if a patient with the same ID already exists
            Patient existingPatient = patientDao.getPatient(patient.getId());
            if (existingPatient != null) {
                throw new IllegalArgumentException("A patient with the same ID already exists.");
            }

            // Add the new patient
            patientDao.addPatient(patient);

            // Return a success response with status code 201 (Created)
            return Response.status(Response.Status.CREATED)
                    .entity(patient)
                    .build();
        } catch (Exception e) {
            // Log and return a server error response for any errors
            logger.error("Error creating patient: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Failed to create patient. Please try again later.")
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") int id, Patient updatedPatient) {
        try {
            // Fetch the existing patient to verify their existence
            Patient existingPatient = patientDao.getPatient(id);
            if (existingPatient == null) {
                throw new UserNotFoundException("Patient not found with ID: " + id);
            }

            // Update the patient details
            updatedPatient.setId(id); // Ensure the ID matches the one in the URL path
            patientDao.updatePatient(updatedPatient); // Update patient information

            // Retrieve the updated patient from the database
            Patient updatedPatientFromDB = patientDao.getPatient(id);

            // Return the updated patient information as the response
            return Response.ok(updatedPatientFromDB).build();
        } catch (UserNotFoundException ex) {
            // Return a 404 Not Found if the patient ID is not found
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            // Log and return a 500 Internal Server Error for any other errors
            logger.error("Error updating patient: {}", ex.getMessage(), ex);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Internal server error").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePatient(@PathParam("id") int id) {
        Patient existingPatient = patientDao.getPatient(id);
        if (existingPatient == null) {
            logger.error("Patient not found with ID: {}", id);
            throw new UserNotFoundException("Patient not found with ID: " + id);
        }
        patientDao.deletePatient(id);
        return Response.ok().entity("Patient deleted").build();
    }
}
