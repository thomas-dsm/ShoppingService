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
public class OrderBookTest extends JerseyTest{
    
    @Override
    protected Application configure() {
        return new ResourceConfig(OrderBook.class);
    }
    
    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("order_book/appel").request().get(String.class);

        assertEquals("Hello, Heroku OrderBook!", responseMsg);
    }

    @Test
    public void testSetDropbd() {
        final String responseMsg = target().path("order_book/db/drop").request().get(String.class);

        assertEquals("Table OrderBook Dropped", responseMsg);
    } 

    @Test
    public void testSetItbd() {
        final String responseMsg = target().path("order_book/db/add").request().get(String.class);

        assertEquals("ajout de OrderBook a la base!", responseMsg);
    }

    @Test
    public void testGetItbd() {
        final String responseMsg = target().path("order_book/db").request().get(String.class);

        String expResult = "Hello!\n" +
                "Read from DB OrderBook: 9782070541270 4 cl1\n" +
                "Read from DB OrderBook: 9782070541271 2 cl2\n" +
                "Read from DB OrderBook: 9782070541270 20 cl1\n" +
                "Read from DB OrderBook: 9782070541270 5 cl1\n";
        
        assertEquals(expResult, responseMsg);
    }
}
