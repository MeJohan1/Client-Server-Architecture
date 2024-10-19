package com.mycompany.clientapihealth.resources;

import com.mycompany.clientapihealth.dao.BillingDao;
import com.mycompany.clientapihealth.exception.UserNotFoundException;
import com.mycompany.clientapihealth.models.Billing;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/billing")
public class BillingResource {

    private static final Logger logger = LoggerFactory.getLogger(BillingResource.class);
    private BillingDao billingDao = new BillingDao();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBilling(@PathParam("id") int id) {
        Billing billing = billingDao.getBilling(id);
        if (billing == null) {
            logger.error("Billing record not found with ID: {}", id);
            throw new UserNotFoundException("Billing record not found with ID: " + id);
        }
        return Response.ok(billing).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBilling(Billing billing) {
        try {
            billingDao.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity(billing).build();
        } catch (Exception e) {
            logger.error("Error creating billing record: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating billing record").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("id") int id, Billing updatedBilling) {
        Billing existingBilling = billingDao.getBilling(id);
        if (existingBilling == null) {
            logger.error("Billing record not found with ID: {}", id);
            throw new UserNotFoundException("Billing record not found with ID: " + id);
        }
        updatedBilling.setId(id);  // Set ID of updated billing record to match ID in URL
        billingDao.updateBilling(updatedBilling);
        return Response.ok(updatedBilling).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBilling(@PathParam("id") int id) {
        Billing billing = billingDao.getBilling(id);
        if (billing == null) {
            logger.error("Billing record not found with ID: {}", id);
            throw new UserNotFoundException("Billing record not found with ID: " + id);
        }
        billingDao.deleteBilling(id);
        return Response.ok().entity("Billing record deleted").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        return Response.ok(billingDao.getAllBillings()).build();
    }
}
