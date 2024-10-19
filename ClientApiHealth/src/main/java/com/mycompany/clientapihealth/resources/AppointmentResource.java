package com.mycompany.clientapihealth.resources;

import com.mycompany.clientapihealth.dao.AppointmentDao;
import com.mycompany.clientapihealth.exception.UserNotFoundException;
import com.mycompany.clientapihealth.models.Appointment;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/appointments")
public class AppointmentResource {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentResource.class);
    private AppointmentDao appointmentDao = new AppointmentDao();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointment(@PathParam("id") int id) {
        Appointment appointment = appointmentDao.getAppointment(id);
        if (appointment == null) {
            logger.error("Appointment not found with ID: {}", id);
            throw new UserNotFoundException("Appointment not found with ID: " + id);
        }
        return Response.ok(appointment).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAppointment(Appointment appointment) {
        try {
            appointmentDao.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity(appointment).build();
        } catch (Exception e) {
            logger.error("Error creating appointment: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating appointment").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") int id, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentDao.getAppointment(id);
        if (existingAppointment == null) {
            logger.error("Appointment not found with ID: {}", id);
            throw new UserNotFoundException("Appointment not found with ID: " + id);
        }
        updatedAppointment.setId(id);  // Set ID of updated appointment to match ID in URL
        appointmentDao.updateAppointment(updatedAppointment);
        return Response.ok(updatedAppointment).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAppointment(@PathParam("id") int id) {
        Appointment appointment = appointmentDao.getAppointment(id);
        if (appointment == null) {
            logger.error("Appointment not found with ID: {}", id);
            throw new UserNotFoundException("Appointment not found with ID: " + id);
        }
        appointmentDao.deleteAppointment(id);
        return Response.ok().entity("Appointment deleted").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        return Response.ok(appointmentDao.getAllAppointments()).build();
    }
}
