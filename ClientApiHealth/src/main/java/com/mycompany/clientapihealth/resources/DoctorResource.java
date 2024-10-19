package com.mycompany.clientapihealth.resources;

import com.mycompany.clientapihealth.dao.DoctorDao;
import com.mycompany.clientapihealth.exception.UserNotFoundException;
import com.mycompany.clientapihealth.models.Doctor;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/doctors")
public class DoctorResource {

    private static final Logger logger = LoggerFactory.getLogger(DoctorResource.class);
    private DoctorDao doctorDao = new DoctorDao();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctor(@PathParam("id") int id) {
        Doctor doctor = doctorDao.getDoctor(id);
        if (doctor == null) {
            logger.error("Doctor not found with ID: {}", id);
            throw new UserNotFoundException("Doctor not found with this ID: " + id);
        }
        return Response.ok(doctor).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        List<Doctor> doctors = doctorDao.getAllDoctors();
        return Response.ok(doctors).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createDoctor(Doctor doctor) {
        try {
            doctorDao.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(doctor).build();
        } catch (Exception e) {
            logger.error("Error creating doctor: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating doctor").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") int id, Doctor updatedDoctor) {
        Doctor existingDoctor = doctorDao.getDoctor(id);
        if (existingDoctor == null) {
            logger.error("Doctor not found with ID: {}", id);
            throw new UserNotFoundException("Doctor not found with ID: " + id);
        }
        updatedDoctor.setId(id);  
        doctorDao.updateDoctor(updatedDoctor);
        return Response.ok(updatedDoctor).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteDoctor(@PathParam("id") int id) {
        Doctor doctor = doctorDao.getDoctor(id);
        if (doctor == null) {
            logger.error("Doctor not found with ID: {}", id);
            throw new UserNotFoundException("Doctor not found with ID: " + id);
        }
        doctorDao.deleteDoctor(id);
        return Response.ok().entity("Doctor deleted").build();
    }
}
