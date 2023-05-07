package com.shop;

import com.connection.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @Path("appel")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
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
    
    private String dropTableDataBase(){
        try {
            Connection connection = new ConnectionManager().getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS Book");
            stmt.executeUpdate("DROP TABLE IF EXISTS Customer");
            stmt.executeUpdate("DROP TABLE IF EXISTS Store");
            
            return "Table Dropped";
            } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }
    
    private String showDatabase()
    {
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

    private String addDatabase()
    {
        try {
            Connection connection = new ConnectionManager().getConnection();
                  
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Book (id INT PRIMARY KEY NOT NULL, isbn VARCHAR UNIQUE, title VARCHAR, author VARCHAR, date timestamp)");
            stmt.executeUpdate("INSERT INTO Book (id, isbn, title, author, date) VALUES (01, '9782070541270', 'Harry Peteur', 'JK Roue Libre', to_date('2001/12/05', 'yyyy/mm/dd'))");
            stmt.executeUpdate("INSERT INTO Book (id, isbn, title, author, date) VALUES (02, '9782070541271', 'Harry Pot de fleurs', 'JK Roue Libre', to_date('2002/12/04', 'yyyy/mm/dd'))");         
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Customer (idCustomer INT PRIMARY KEY NOT NULL, name VARCHAR(100), firstname VARCHAR(100))");
            stmt.executeUpdate("INSERT INTO Customer (idCustomer, name, firstname) VALUES (01, 'Da Silva Mendonca', 'Thomas')");
            stmt.executeUpdate("INSERT INTO Customer (idCustomer, name, firstname) VALUES (02, 'Coudour', 'Adrien')");         
            
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Store (idStore INT PRIMARY KEY NOT NULL, account VARCHAR(100), nameStore VARCHAR(100))");
            stmt.executeUpdate("INSERT INTO Store (idStore, account, nameStore) VALUES (01, 'a', 'Boutique Nord')");
            stmt.executeUpdate("INSERT INTO Store (idStore, account, nameStore) VALUES (02, 'a2', 'Boutique Sud')");         
            
            return "ajouter a la base!";
        } catch (Exception e) {
            
          return "There was an error: " + e.getMessage();
        }
    }
}