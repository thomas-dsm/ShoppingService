/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.shop;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tdasilvamendonca
 */
public class ShoppingServiceTest extends JerseyTest{
    
    @Override
    protected Application configure() {
        return new ResourceConfig(ShoppingService.class);
    }
    
    @Test
    public void testBookReq() {
        final Response output = target().path("shopping_service/book/9782070541270/customer/cl1").request().get();

        assertEquals(200, output.getStatus());
    }
    
    @Test
    public void testBookReqNotFoundBook() {
        final Response output = target().path("shopping_service/book/9782070541273/customer/cl1").request().get();

        assertEquals(404, output.getStatus());
    }
    
    @Test
    public void testBookReqNotFoundCustomer() {
        final Response output = target().path("shopping_service/book/9782070541270/customer/cl3").request().get();

        assertEquals(404, output.getStatus());
    }

    @Test
    public void testBuyReq() {
        final Response output = target().path("shopping_service/book/9782070541270/quantity/5/corr/cl1").request().get();

        assertEquals(200, output.getStatus());
    }
    
    @Test
    public void testBuyReqNotFoundBook() {
        final Response output = target().path("shopping_service/book/9782070541273/quantity/5/corr/cl1").request().get();

        assertEquals(404, output.getStatus());
    }
    
    @Test
    public void testBuyReqNotFoundCustomer() {
        final Response output = target().path("shopping_service/book/9782070541270/quantity/5/corr/cl3").request().get();

        assertEquals(404, output.getStatus());
    }
    
    @Test
    public void testBuyReqQuantityZero() {
        final Response output = target().path("shopping_service/book/9782070541270/quantity/0/corr/cl1").request().get();

        assertEquals(400, output.getStatus());
    }
}
