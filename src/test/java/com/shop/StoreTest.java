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
public class StoreTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(Store.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("store/appel").request().get(String.class);

        assertEquals("Hello, Heroku Store!", responseMsg);
    }

    @Test
    public void testSetDropbd() {
        final String responseMsg = target().path("store/db/drop").request().get(String.class);

        assertEquals("Table Store Dropped", responseMsg);
    } 

    @Test
    public void testSetItbd() {
        final String responseMsg = target().path("store/db/add").request().get(String.class);

        assertEquals("ajout de Store a la base!", responseMsg);
    }

    @Test
    public void testGetItbd() {
        final String responseMsg = target().path("store/db").request().get(String.class);

        String expResult = "Hello!\n" +
                "Read from DB Store: 123456789 Boutique Nord\n" +
                "Read from DB Store: 987654321 Boutique Sud\n";
        
        assertEquals(expResult, responseMsg);
    }
}