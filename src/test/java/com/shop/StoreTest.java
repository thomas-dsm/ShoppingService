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
public class StoreTest {
    
    private static Store instance;
    private static Store instance2;
    
    public StoreTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        instance = new Store(01,"a", "Boutique1");
        instance2 = new Store(02,"a2", "Boutique2");
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getIdStore method, of class Store.
     */
    @Test
    public void testGetIdStore() {
        System.out.println("getIdStore");
        
        int expResult = 1;
        int result = instance.getIdStore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIdStore method, of class Store.
     */
    @Test
    public void testSetIdStore() {
        System.out.println("setIdStore");
        int idStore = 3;

        instance2.setIdStore(idStore);
        int result = instance2.getIdStore();
        assertEquals(idStore, result);
    }

    /**
     * Test of getAccount method, of class Store.
     */
    @Test
    public void testGetAccount() {
        System.out.println("getAccount");
        
        String expResult = "a";
        String result = instance.getAccount();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccount method, of class Store.
     */
    @Test
    public void testSetAccount() {
        System.out.println("setAccount");
        String account = "a3";
        
        instance2.setAccount(account);
        String result = instance2.getAccount();
        assertEquals(account, result);
    }

    /**
     * Test of getNameStore method, of class Store.
     */
    @Test
    public void testGetNameStore() {
        System.out.println("getNameStore");

        String expResult = "Boutique1";
        String result = instance.getNameStore();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNameStore method, of class Store.
     */
    @Test
    public void testSetNameStore() {
        System.out.println("setNameStore");
        String nameStore = "Boutique2";

        instance2.setNameStore(nameStore);
        String result = instance2.getNameStore();
        assertEquals(nameStore, result);
    }
    
}
