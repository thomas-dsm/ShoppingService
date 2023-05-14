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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tdasilvamendonca
 */
@Path("order_book")
public class OrderBook {
    
    private static final String ORDER_BOOK_TABLE = 
            "CREATE TABLE IF NOT EXISTS OrderBook ("
            + "isbn VARCHAR(13), "
            + "quantity INT, "
            + "account VARCHAR(3), "
            + "date DATE, "
            + "FOREIGN KEY (isbn) REFERENCES Book(isbn), "
            + "FOREIGN KEY (account) REFERENCES Customer(account))";
    
    
    @Path("appel")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku OrderBook!";
    }
    
    @Path("db")  
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getItbd() {
        return showDatabase();
    }
    
    @Path("db/add")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setItbd() {
        return addDatabase();
    }
    
    @Path("db/add/book/{isbn}/quantity/{quantity}/customer/{account}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setItbd(
            @PathParam("isbn") String isbn,
            @PathParam("quantity") String quantity,
            @PathParam("account") String account
            ) {
        return addDatabase(isbn, Integer.parseInt(quantity), account);
    }

    @Path("db/drop")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setDropbd() {
        return dropTableDataBase();
    }
    
    private String dropTableDataBase(){
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS OrderBook CASCADE");
            
            connection.close();
            return "Table OrderBook Dropped";
            
        } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }
    
    private String showDatabase()
    {
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(ORDER_BOOK_TABLE);
            ResultSet rs = stmt.executeQuery("SELECT isbn, quantity, account FROM OrderBook");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB OrderBook: " + rs.getString("isbn") + " " + rs.getString("quantity") + " " + rs.getString("account") + "\n";
            }

            connection.close();
            return out;
        } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }

    private String addDatabase()
    {
        try {
            Connection connection = new ConnectionManager().getConnection();
                  
            Statement stmt = connection.createStatement();

            stmt.executeUpdate(ORDER_BOOK_TABLE);
            stmt.executeUpdate("INSERT INTO OrderBook (isbn, quantity, account, date) VALUES ('9782070541270', 4, 'cl1', now())");
            stmt.executeUpdate("INSERT INTO OrderBook (isbn, quantity, account, date) VALUES ('9782070541271', 2, 'cl2', now())");         

            connection.close();
            return "ajout de OrderBook a la base!";
        } catch (Exception e) {
            
          return "There was an error: " + e.getMessage();
        }
    }

    

    private String addDatabase(String isbn, int quantity, String account) {
        try {
            Connection connection = new ConnectionManager().getConnection();
            
            PreparedStatement pstmt = connection.prepareStatement("INSERT INTO OrderBook (isbn, quantity, account, date) VALUES (?, ?, ?, ?)");
            pstmt.setString(1, isbn);
            pstmt.setInt(2, quantity);
            pstmt.setString(3, account);
            pstmt.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
            
            connection.close();
            return "ajout de OrderBook a la base!";
        } catch (Exception e) {
            
          return "There was an error: " + e.getMessage();
        }
    }
}
