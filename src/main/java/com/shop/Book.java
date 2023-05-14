/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

import com.exception.BookNotFoundException;
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
@Path("book")
public class Book {
    
    private static final String BOOK_TABLE = 
            "CREATE TABLE IF NOT EXISTS Book ("
            + "isbn VARCHAR(13) PRIMARY KEY NOT NULL, "
            + "title VARCHAR(100), "
            + "author VARCHAR(100), "
            + "date DATE)";
    
    @Path("appel")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku Book!";
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

    @Path("db/drop")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String setDropbd() {
        return dropTableDataBase();
    }
    
    @Path("{isbn}/isvalid")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean isValid(@PathParam("isbn") String isbn) {
        
        if(showOneBook(isbn).equals("")){
            throw new BookNotFoundException("Book with isbn : " + isbn + " doesn't exist");
        }
        
        return true;
    }
    
    private String showOneBook(String isbn) {
        try {
            Connection connection = new ConnectionManager().getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT isbn FROM Book WHERE isbn = ? LIMIT 1;");
            pstmt.setString(1, isbn );

            ResultSet rs = pstmt.executeQuery();

            String out = "";
            while (rs.next()) {
                out += "Read from DB Book: " + rs.getString("isbn");
            }

            connection.close();
            return out;
        } catch (Exception e) {
            return "There was an error: " + e.getMessage();
        }
    }
    
    private String dropTableDataBase(){
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS Book CASCADE");
            
            connection.close();
            return "Table Book Dropped";
            } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }
    
    private String showDatabase()
    {
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate(BOOK_TABLE);
            ResultSet rs = stmt.executeQuery("SELECT isbn, title, author, date FROM Book");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB Book: " + rs.getString("isbn") + " " + rs.getString("title") + " " + rs.getString("author") + " " + rs.getDate("date") + "\n";
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

            stmt.executeUpdate(BOOK_TABLE);
            stmt.executeUpdate("INSERT INTO Book (isbn, title, author, date) VALUES ('9782070541270', 'Harry Peteur', 'JK Roue Libre', to_date('2001/12/05', 'yyyy/mm/dd'))");
            stmt.executeUpdate("INSERT INTO Book (isbn, title, author, date) VALUES ('9782070541271', 'Harry Pot de fleurs', 'JK Roue Libre', to_date('2002/12/04', 'yyyy/mm/dd'))");         

            connection.close();
            return "ajout de Book a la base!";
        } catch (Exception e) {
            
          return "There was an error: " + e.getMessage();
        }
    }
}
