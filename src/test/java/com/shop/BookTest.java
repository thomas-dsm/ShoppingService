/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.shop;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tdasilvamendonca
 */
public class BookTest {
    
    private static Book instance;
    private static Book instance2;
    
    public BookTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        instance = new Book(01, "0000000000001","livreTest", "authorTest", LocalDate.now());
        instance2 = new Book(02, "0000000000002","livreTest2", "authorTest2", LocalDate.now());
    }
    
    @AfterAll
    public static void tearDownClass() {
    }

    /**
     * Test of getId method, of class Book.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Book.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 3;

        instance2.setId(id);
        int result = instance2.getId();
        assertEquals(id, result);
    }

    /**
     * Test of getIsbn method, of class Book.
     */
    @Test
    public void testGetIsbn() {
        System.out.println("getIsbn");
        
        String expResult = "0000000000001";
        String result = instance.getIsbn();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIsbn method, of class Book.
     */
    @Test
    public void testSetIsbn() {
        System.out.println("setIsbn");
        String isbn = "0000000000003";
        
        instance2.setIsbn(isbn);
        String result = instance2.getIsbn();
        assertEquals(isbn, result);
    }

    /**
     * Test of getTitle method, of class Book.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        
        String expResult = "livreTest";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Book.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "livreTest3";

        instance2.setTitle(title);
        String result = instance2.getTitle();
        assertEquals(title, result);
    }

    /**
     * Test of getAuthor method, of class Book.
     */
    @Test
    public void testGetAuthor() {
        System.out.println("getAuthor");
 
        String expResult = "authorTest";
        String result = instance.getAuthor();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAuthor method, of class Book.
     */
    @Test
    public void testSetAuthor() {
        System.out.println("setAuthor");
        String author = "authorTest3";

        instance2.setAuthor(author);
        String result = instance2.getAuthor();
        assertEquals(author, result);
    }

    /**
     * Test of getDate method, of class Book.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Book.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        LocalDate date = LocalDate.now();

        instance.setDate(date);
        LocalDate result = instance.getDate();
        assertEquals(date, result);
    }
}
