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
public class BookNotFoundException extends WebApplicationException {

    public BookNotFoundException() {
        super(Response.status(404).
        entity("Book not found").type("text/plain").build());
    }
    
    public BookNotFoundException(String message) {
        super(Response.status(404).
        entity(message).type("text/plain").build());
    }
    
    @Override
    public String getMessage(){
        
        return "Book not found";
    }
}
