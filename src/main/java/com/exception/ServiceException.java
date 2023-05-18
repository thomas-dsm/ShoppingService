/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author tdasilvamendonca
 */
public class ServiceException extends WebApplicationException {
    
    public ServiceException() {
        super(Response.status(404).
        entity("Book invalid").type("text/plain").build());
    }
    
    public ServiceException(String message) {
        super(Response.status(404).
        entity(message).type("text/plain").build());
    }
    
    public String getMessage(String service){
        
        Response resp = this.getResponse();
        
        return "Service " + service + " error : " + resp.getStatus() + "isbn invalid";
    }
}
