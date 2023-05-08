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
public class CustomerTest extends JerseyTest{
    
    @Override
    protected Application configure() {
        return new ResourceConfig(Customer.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("customer/appel").request().get(String.class);

        assertEquals("Hello, Heroku Customer!", responseMsg);
    }

    @Test
    public void testSetDropbd() {
        final String responseMsg = target().path("customer/db/drop").request().get(String.class);

        assertEquals("Table Customer Dropped", responseMsg);
    } 

    @Test
    public void testSetItbd() {
        final String responseMsg = target().path("customer/db/add").request().get(String.class);

        assertEquals("ajout de Customer a la base!", responseMsg);
    }

    @Test
    public void testGetItbd() {
        final String responseMsg = target().path("customer/db").request().get(String.class);

        String expResult = "Hello!\n" +
                "Read from DB Customer: cl1 Da Silva Mendonca Thomas\n" +
                "Read from DB Customer: cl2 Coudour Adrien\n";
        
        assertEquals(expResult, responseMsg);
    }

    
    @Test
    public void testIsValid() throws Exception {
        
        final boolean responseMsg = target().path("customer/cl1/isvalid").request().get(Boolean.class);

        assertTrue(responseMsg);
    }
    
    @Test
    public void testIsNotValid() throws Exception {
        
        final boolean responseMsg = target().path("customer/cl3/isvalid").request().get(Boolean.class);

        assertFalse(responseMsg);
    }
}
