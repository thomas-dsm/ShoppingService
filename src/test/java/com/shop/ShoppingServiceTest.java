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
public class ShoppingServiceTest extends JerseyTest{
    
    @Override
    protected Application configure() {
        return new ResourceConfig(ShoppingService.class);
    }
    
    @Test
    public void testBookReq() {
        final boolean responseMsg = target().path("shopping_service/customer/cl1/book/9782070541270/from/Client/to/ShoppingService/corr/corr").request().get(Boolean.class);

        assertTrue(responseMsg);
    }

    @Test
    public void testBuyReq() {
        final boolean responseMsg = target().path("shopping_service/book/9782070541270/quantity/5/from/Client/to/WholeSalerService/corr/corr").request().get(Boolean.class);

        assertTrue(responseMsg);
    }
}
