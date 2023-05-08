/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.shop;

import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tdasilvamendonca
 */
public class BookTest extends JerseyTest{
    
    @Override
    protected Application configure() {
        return new ResourceConfig(Book.class);
    }
    
    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("book/appel").request().get(String.class);

        assertEquals("Hello, Heroku Book!", responseMsg);
    }

    @Test
    public void testSetDropbd() {
        final String responseMsg = target().path("book/db/drop").request().get(String.class);

        assertEquals("Table Book Dropped", responseMsg);
    } 

    @Test
    public void testSetItbd() {
        final String responseMsg = target().path("book/db/add").request().get(String.class);

        assertEquals("ajout de Book a la base!", responseMsg);
    }

    @Test
    public void testGetItbd() {
        final String responseMsg = target().path("book/db").request().get(String.class);

        String expResult = "Hello!\n" +
                "Read from DB Book: 9782070541270 Harry Peteur JK Roue Libre 2001-12-05\n" +
                "Read from DB Book: 9782070541271 Harry Pot de fleurs JK Roue Libre 2002-12-04\n";
        
        assertEquals(expResult, responseMsg);
    }

    @Test
    public void testIsValid() throws Exception {
        
        final boolean responseMsg = target().path("book/9782070541270/isvalid").request().get(Boolean.class);

        assertTrue(responseMsg);
    }
    
    @Test
    public void testIsNotValid() throws Exception {
        
        final boolean responseMsg = target().path("book/9782070541272/isvalid").request().get(Boolean.class);

        assertFalse(responseMsg);
    }
}
