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
public class CustomerNotFoundException extends WebApplicationException {

    public CustomerNotFoundException(String message) {
        super(Response.status(404).
        entity(message).type("text/plain").build());
    }
    
    public CustomerNotFoundException() {
        super(Response.status(404).
        entity("Customer not found").type("text/plain").build());
    }
    
    @Override
    public String getMessage(){
        
        return "Customer not found";
    }
}
