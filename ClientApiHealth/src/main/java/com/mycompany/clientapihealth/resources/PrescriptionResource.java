package com.mycompany.clientapihealth.resources;

import com.mycompany.clientapihealth.dao.PrescriptionDao;
import com.mycompany.clientapihealth.exception.UserNotFoundException;
import com.mycompany.clientapihealth.models.Prescription;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/prescriptions")
public class PrescriptionResource {

    private static final Logger logger = LoggerFactory.getLogger(PrescriptionResource.class);
    private PrescriptionDao prescriptionDao = new PrescriptionDao();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescription(@PathParam("id") int id) {
        Prescription prescription = prescriptionDao.getPrescription(id);
        if (prescription == null) {
            logger.error("Prescription not found with ID: {}", id);
            throw new UserNotFoundException("Prescription not found with ID: " + id);
        }
        return Response.ok(prescription).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPrescription(Prescription prescription) {
        try {
            prescriptionDao.addPrescription(prescription);
            return Response.status(Response.Status.CREATED).entity(prescription).build();
        } catch (Exception e) {
            logger.error("Error creating prescription: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating prescription").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("id") int id, Prescription updatedPrescription) {
        Prescription existingPrescription = prescriptionDao.getPrescription(id);
        if (existingPrescription == null) {
            logger.error("Prescription not found with ID: {}", id);
            throw new UserNotFoundException("Prescription not found with ID: " + id);
        }
        updatedPrescription.setId(id);  // Set ID of updated prescription to match ID in URL
        prescriptionDao.updatePrescription(updatedPrescription);
        return Response.ok(updatedPrescription).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePrescription(@PathParam("id") int id) {
        Prescription prescription = prescriptionDao.getPrescription(id);
        if (prescription == null) {
            logger.error("Prescription not found with ID: {}", id);
            throw new UserNotFoundException("Prescription not found with ID: " + id);
        }
        prescriptionDao.deletePrescription(id);
        return Response.ok().entity("Prescription deleted").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        return Response.ok(prescriptionDao.getAllPrescriptions()).build();
    }
}
