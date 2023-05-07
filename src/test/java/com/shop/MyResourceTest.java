package com.shop;

import java.time.LocalDate;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;


public class MyResourceTest extends JerseyTest {

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(MyResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("myresource/appel").request().get(String.class);

        assertEquals("Hello, Heroku!", responseMsg);
    }
    
    @Test
    public void testSetDropbd() {
        final String responseMsg = target().path("myresource/db/drop").request().get(String.class);

        assertEquals("Table Dropped", responseMsg);
    } 
    
    @Test
    public void testSetItbd() {
        final String responseMsg = target().path("myresource/db/add").request().get(String.class);

        assertEquals("ajouter a la base!", responseMsg);
    }
    
    @Test
    public void testGetItbd() {
        final String responseMsg = target().path("myresource/db").request().get(String.class);

        String expResult = "Hello!\n" +
                "Read from DB Book: 9782070541270 Harry Peteur JK Roue Libre 2001-12-05\n" +
                "Read from DB Book: 9782070541271 Harry Pot de fleurs JK Roue Libre 2002-12-04\n";
        
        assertEquals(expResult, responseMsg);
    }
}
