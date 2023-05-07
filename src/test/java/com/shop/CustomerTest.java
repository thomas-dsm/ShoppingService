/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.shop;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tdasilvamendonca
 */
public class CustomerTest {
    
    private static Customer instance;
    private static Customer instance2;

    public CustomerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        instance = new Customer(01,"name1", "fn1");
        instance2 = new Customer(02,"name2", "fn2");

    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of getIdCustomer method, of class Customer.
     */
    @Test
    public void testGetIdCustomer() {
        System.out.println("getIdCustomer");

        int expResult = 1;
        int result = instance.getIdCustomer();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdCustomer method, of class Customer.
     */
    @Test
    public void testSetIdCustomer() {
        System.out.println("setIdCustomer");
        int idCustomer = 3;

        instance2.setIdCustomer(idCustomer);
        int result = instance2.getIdCustomer();
        assertEquals(idCustomer, result);
    }

    /**
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        String expResult = "name1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Customer.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "name3";

        instance2.setName(name);
        String result = instance2.getName();
        assertEquals(name, result);
    }

    /**
     * Test of getFirstname method, of class Customer.
     */
    @Test
    public void testGetFirstname() {
        System.out.println("getFirstname");

        String expResult = "fn1";
        String result = instance.getFirstname();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstname method, of class Customer.
     */
    @Test
    public void testSetFirstname() {
        System.out.println("setFirstname");
        String firstname = "fn3";
        
        instance2.setFirstname(firstname);
        String result = instance2.getFirstname();
        assertEquals(firstname, result);
    }
}
