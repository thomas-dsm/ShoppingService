/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.shop;

import java.util.List;

/**
 *
 * @author tdasilvamendonca
 */
public interface ShoppingServiceInterface {

    boolean addCustomer(Customer c);
    boolean addBook(Book b);

    List<Customer> getCustomers();
    List<Book> getBooks();

    Customer getCustomerAt(String name);
    Book getBookAt(String isbn);
}
