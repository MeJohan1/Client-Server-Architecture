package com.mycompany.clientapihealth.resources;

import com.mycompany.clientapihealth.dao.MedicalRecordDao;
import com.mycompany.clientapihealth.exception.UserNotFoundException;
import com.mycompany.clientapihealth.models.MedicalRecord;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/medicalRecords")
public class MedicalResource {

    private static final Logger logger = LoggerFactory.getLogger(MedicalResource.class);
    private MedicalRecordDao medicalRecordDao = new MedicalRecordDao();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecord(@PathParam("id") int id) {
        MedicalRecord medicalRecord = medicalRecordDao.getMedicalRecord(id);
        if (medicalRecord == null) {
            logger.error("Medical record not found with ID: {}", id);
            throw new UserNotFoundException("Medical record not found with ID: " + id);
        }
        return Response.ok(medicalRecord).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMedicalRecord(MedicalRecord medicalRecord) {
        try {
            medicalRecordDao.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED).entity(medicalRecord).build();
        } catch (Exception e) {
            logger.error("Error creating medical record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating medical record").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord updatedMedicalRecord) {
        MedicalRecord existingMedicalRecord = medicalRecordDao.getMedicalRecord(id);
        if (existingMedicalRecord == null) {
            logger.error("Medical record not found with ID: {}", id);
            throw new UserNotFoundException("Medical record not found with ID: " + id);
        }
        updatedMedicalRecord.setId(id);  // Set ID of updated medical record to match ID in URL
        medicalRecordDao.updateMedicalRecord(updatedMedicalRecord);
        return Response.ok(updatedMedicalRecord).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        MedicalRecord medicalRecord = medicalRecordDao.getMedicalRecord(id);
        if (medicalRecord == null) {
            logger.error("Medical record not found with ID: {}", id);
            throw new UserNotFoundException("Medical record not found with ID: " + id);
        }
        medicalRecordDao.deleteMedicalRecord(id);
        return Response.ok().entity("Medical record deleted").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        return Response.ok(medicalRecordDao.getAllMedicalRecords()).build();
    }
}
