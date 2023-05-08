/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

import com.exception.CustomerNotFoundException;
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
@Path("customer")
public class Customer {
    
    @Path("appel")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku Customer!";
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
    public boolean isValid(@PathParam("isbn") String account) throws CustomerNotFoundException{
        
        if(showOneCustomer(account).equals("Read from DB Customer: " + account)){
            return true;
        }
        
        return false;
    }
    
    private String showOneCustomer(String account) throws CustomerNotFoundException{
        try {
            Connection connection = new ConnectionManager().getConnection();

            PreparedStatement pstmt = connection.prepareStatement("SELECT account FROM Customer WHERE account = ? LIMIT 1;");
            pstmt.setString(1, account );

            ResultSet rs = pstmt.executeQuery();

            String out = "";
            while (rs.next()) {
                out += "Read from DB Customer: " + rs.getString("account");
            }
            
            if(out.equals("")){
                throw new CustomerNotFoundException("Customer, " + account + ", is not found");
            }

            return out;
        } catch (Exception e) {
            return "There was an error: " + e.getMessage();
        }
    }
    
    private String dropTableDataBase(){
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS Customer");
            
            connection.close();
            return "Table Customer Dropped";
            } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }
    
    private String showDatabase()
    {
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Customer (account VARCHAR(3) UNIQUE, name VARCHAR(100), firstname VARCHAR(100))");
            ResultSet rs = stmt.executeQuery("SELECT account, name, firstname FROM Customer");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB Customer: " + rs.getString("account") +  " " +rs.getString("name") + " " + rs.getString("firstname") + "\n";
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

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Customer (account VARCHAR(3) UNIQUE, name VARCHAR(100), firstname VARCHAR(100))");
            stmt.executeUpdate("INSERT INTO Customer (account, name, firstname) VALUES ('cl1', 'Da Silva Mendonca', 'Thomas')");
            stmt.executeUpdate("INSERT INTO Customer (account, name, firstname) VALUES ('cl2', 'Coudour', 'Adrien')");         
            
            connection.close();
            return "ajout de Customer a la base!";
        } catch (Exception e) {
            
          return "There was an error: " + e.getMessage();
        }
    }
}
