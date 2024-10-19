/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clientapihealth.exception;

/**
 *
 * @author johanahmedchowdhury
 */
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.core.Response;
import org.slf4j.LoggerFactory;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;

@Provider
public class UserNotFoundExceptionMapper implements ExceptionMapper <UserNotFoundException> {
    private static final Logger logger =
            LoggerFactory.getLogger(UserNotFoundExceptionMapper.class);
    
    @Override
    public Response toResponse(UserNotFoundException exception) {
        logger.error("UserNotFoundException caught: {}", exception.getMessage(),exception);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN).build();
    }
    
}
