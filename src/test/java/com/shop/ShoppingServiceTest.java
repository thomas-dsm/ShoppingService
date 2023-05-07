/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.shop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tdasilvamendonca
 */
public class ShoppingServiceTest {
    
    private static ShoppingService instance;
    
    public ShoppingServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        instance = new ShoppingService();
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }
    
    /**
     * Test of addCustomer method, of class ShoppingService.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        Customer c = new Customer(01,"name1", "fn1");

        boolean expResult = true;
        boolean result = instance.addCustomer(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of addBook method, of class ShoppingService.
     */
    @Test
    public void testAddBook() {
        System.out.println("addBook");
        Book b = null;

        boolean expResult = true;
        boolean result = instance.addBook(b);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCustomers method, of class ShoppingService.
     */
    @Test
    public void testGetCustomers() {
        System.out.println("getCustomers");

        List<Customer> expResult = new ArrayList<Customer>();
        expResult.add(new Customer(01, "Da Silva Mendonca", "Thomas"));
        expResult.add(new Customer(02, "Coudour", "Adrien"));
        
        List<Customer> result = instance.getCustomers();
        
        for (int i = 0; i < expResult.size(); i++){
            assertEquals(expResult.get(i).getIdCustomer(), result.get(i).getIdCustomer());
            assertEquals(expResult.get(i).getName(), result.get(i).getName());
            assertEquals(expResult.get(i).getFirstname(), result.get(i).getFirstname());
        }
    }

    /**
     * Test of getBooks method, of class ShoppingService.
     */
    @Test
    public void testGetBooks() {
        System.out.println("getBooks");

        List<Book> expResult = new ArrayList<Book>();
        expResult.add(new Book(01, "9782070541270", "Harry Peteur", "JK Roue Libre", LocalDate.parse("2001-12-01")));
        expResult.add(new Book(02, "9782070541271", "Harry Pot de fleurs", "JK Roue Libre", LocalDate.parse("2002-12-04")));
    
        List<Book> result = instance.getBooks();
        
        for (int i = 0; i < expResult.size(); i++){
            assertEquals(expResult.get(i).getId(), result.get(i).getId());
            assertEquals(expResult.get(i).getIsbn(), result.get(i).getIsbn());
            assertEquals(expResult.get(i).getTitle(), result.get(i).getTitle());
            assertEquals(expResult.get(i).getAuthor(), result.get(i).getAuthor());
            assertEquals(expResult.get(i).getDate(), result.get(i).getDate());
        }
    }

    /**
     * Test of getCustomerAt method, of class ShoppingService.
     */
    @Test
    public void testGetCustomerAt() {
        System.out.println("getCustomerAt");
        String name = "Da Silva Mendonca";

        Customer expResult = new Customer(01, "Da Silva Mendonca", "Thomas");
        Customer result = instance.getCustomerAt(name);
        
        assertEquals(expResult.getIdCustomer(), result.getIdCustomer());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getFirstname(), result.getFirstname());
    }

    /**
     * Test of getBookAt method, of class ShoppingService.
     */
    @Test
    public void testGetBookAt() {
        System.out.println("getBookAt");
        String isbn = "9782070541270";

        Book result = instance.getBookAt(isbn); 
        Book expResult = new Book(01, "9782070541270", "Harry Peteur", "JK Roue Libre", LocalDate.parse("2001-12-01"));
        
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.getIsbn(), result.getIsbn());
        assertEquals(expResult.getTitle(), result.getTitle());
        assertEquals(expResult.getAuthor(), result.getAuthor());
        assertEquals(expResult.getDate(), result.getDate());
    }

    /**
     * Test of getItBooks method, of class ShoppingService.
     */
    @Test
    public void testGetItBooks() {
        System.out.println("getItBooks");

        String expResult = "Hello!\n" +
                "Read from DB Book: 9782070541270 Harry Peteur JK Roue Libre 2001-12-05\n" +
                "Read from DB Book: 9782070541271 Harry Pot de fleurs JK Roue Libre 2002-12-04\n";
        
        String result = instance.getItBooks();
        
        assertEquals(expResult, result);

    }

    /**
     * Test of getItCustomers method, of class ShoppingService.
     */
    @Test
    public void testGetItCustomers() {
        System.out.println("getItCustomers");

        String expResult = "Hello!\n" +
                        "Read from DB Customer: Da Silva Mendonca Thomas\n" +
                        "Read from DB Customer: Coudour Adrien\n";
        
        String result = instance.getItCustomers();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getItBook method, of class ShoppingService.
     */
    @Test
    public void testGetItBook() {
        System.out.println("getItBook");

        String isbn = "9782070541270";
        String expResult = "Read from DB Book: 9782070541270 Harry Peteur JK Roue Libre 2001-12-05";

        String result = instance.getItBook(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test of getItStore method, of class ShoppingService.
     */
    @Test
    public void testGetItStore() {
        System.out.println("getItStore");

        String account = "a";

        String expResult = "Read from DB Store: a";
        String result = instance.getItStore(account);
        assertEquals(expResult, result);
    }

    /**
     * Test of BookReq method, of class ShoppingService.
     */
    @Test
    public void testBookReq() {
        System.out.println("BookReq");
        String account = "a";
        String isbn = "9782070541270";
        String from = "Client";
        String to = "S";
        String corr = "{account}";

        boolean expResult = true;
        boolean result = instance.BookReq(account, isbn, from, to, corr);
        assertEquals(expResult, result);
    }

    /**
     * Test of BuyReq method, of class ShoppingService.
     */
    @Test
    public void testBuyReq() {
        System.out.println("BuyReq");
        String isbn = "9782070541270";
        String quantity = "2";
        String from = "Client";
        String to = "S";
        String corr = "{a}";

        boolean expResult = true;
        boolean result = instance.BuyReq(isbn, quantity, from, to, corr);
        assertEquals(expResult, result);
    }
}
