/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

import com.connection.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tdasilvamendonca
 */
@Path("shopping_service")
public class ShoppingService implements ShoppingServiceInterface {

    private List<Customer> customers = new ArrayList<Customer>();
    private List<Book> books = new ArrayList<Book>();

    public ShoppingService() {
            customers.add(new Customer(01, "Da Silva Mendonca", "Thomas"));
            customers.add(new Customer(02, "Coudour", "Adrien"));
            
            books.add(new Book(01, "9782070541270", "Harry Peteur", "JK Roue Libre", LocalDate.parse("2001-12-01")));
            books.add(new Book(02, "9782070541271", "Harry Pot de fleurs", "JK Roue Libre", LocalDate.parse("2002-12-04")));
    }
    
    @Path("books")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getItBooks() {
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Book (id INT PRIMARY KEY NOT NULL, isbn VARCHAR(13) UNIQUE, title VARCHAR(100), author VARCHAR(100), date DATE)");
            ResultSet rs = stmt.executeQuery("SELECT isbn, title, author, date FROM Book");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB Book: " + rs.getString("isbn") + " " + rs.getString("title") + " " + rs.getString("author") + " " + rs.getDate("date") + "\n";
            }

            return out;
        } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }

    @Path("book/{isbn}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getItBook (@PathParam("isbn") String isbn) {
        try {
            Connection connection = new ConnectionManager().getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT isbn, title, author, date FROM Book WHERE isbn = ? LIMIT 1;");
            pstmt.setString(1, isbn );

            ResultSet rs = pstmt.executeQuery();

            String out = "";
            while (rs.next()) {
                out += "Read from DB Book: " + rs.getString("isbn") + " " + rs.getString("title") + " " + rs.getString("author") + " " + rs.getDate("date");
            }

            return out;
        } catch (Exception e) {

            return "There was an error: " + e.getMessage();
        }
    }
    
    @Path("book/{isbn}/quantity/{quantity}/from/{Customer}/to/{Service}/corr/{corr}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean BuyReq(
            @PathParam("isbn") String isbn,
            @PathParam("quantity") String quantity,
            @PathParam("Customer") String from,
            @PathParam("Service") String to,
            @PathParam("corr") String corr
    ) {
        //TO-DO appel de WholesaleService
        return true;
    }

    @Path("store/{account}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getItStore (@PathParam("account") String account) {
        try {
            Connection connection = new ConnectionManager().getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT account FROM Store WHERE account = ? LIMIT 1;");
            pstmt.setString(1, account );

            ResultSet rs = pstmt.executeQuery();

            String out = "";
            while (rs.next()) {
                out += "Read from DB Store: " + rs.getString("account");
            }

            return out;
        } catch (Exception e) {

            return "There was an error: " + e.getMessage();
        }
    }

    @Path("store/{account}/book/{isbn}/from/{Customer}/to/{Service}/corr/{corr}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean BookReq(
            @PathParam("account") String account,
            @PathParam("isbn") String isbn,
            @PathParam("Customer") String from,
            @PathParam("Service") String to,
            @PathParam("corr") String corr
    ) {
        /*
        if (getItStore(account).equals("")){
            return false;
        }*/

        if (getItBook(isbn).equals("")){
            return false;
        }

        //TO-DO appel de StockService
        return true;
    }

    @Path("customers")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getItCustomers() {
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Customer (id INT PRIMARY KEY NOT NULL, name VARCHAR(100), firstname VARCHAR(100))");
            ResultSet rs = stmt.executeQuery("SELECT name, firstname FROM Customer");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB Customer: " + rs.getString("name") + " " + rs.getString("firstname") + "\n";
            }

            return out;
        } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }
    
    @Override
    public boolean addCustomer(Customer c) {
        
        return customers.add(c);
    }

    @Override
    public boolean addBook(Book b) {
        return books.add(b);
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Customer getCustomerAt(String name) {
        for (Customer customer : customers) {
            if (name.equals(customer.getName())) return customer;
        }
        return null;
    }

    @Override
    public Book getBookAt(String isbn) {
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) return book;
        }
        return null;
    }
}
