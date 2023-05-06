package com.shop;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
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
            Connection connection = getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS Book");
        
            return "Table Dropped";
            } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }
    
        private Connection getConnection() throws Exception {
        // Class.forName("org.postgresql.Driver");
        String dbUrl = "jdbc:postgresql://ec2-34-193-110-25.compute-1.amazonaws.com:5432/d1jltmbva2bnt0";
        String username = "phvzkuazxuyeoa";
        String password = "caf84127a1b42ab4cc9c92d1860fd04b610f934efeeefb7a7fd6a4b177731872";

        return DriverManager.getConnection(dbUrl, username, password);
    }
    
    private String showDatabase()
    {
        try {
            Connection connection = getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Book (id INT PRIMARY KEY NOT NULL, isbn VARCHAR(13) UNIQUE, title VARCHAR(100), author VARCHAR(100), date DATE)");
            //stmt.executeUpdate("INSERT INTO Book (id, isbn, title, author, date) VALUES (01, '9782070541270', 'Harry Peteur', 'JK Roue Libre', now())");
            ResultSet rs = stmt.executeQuery("SELECT isbn, title, author, date FROM Book");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB: " + rs.getString("isbn") + " " + rs.getString("title") + " " + rs.getString("author") + " " + rs.getDate("date") + "\n";
            }

            return out;
        } catch (Exception e) {
            
            return "There was an error: " + e.getMessage();
        }
    }

    private String addDatabase()
    {
        try {
            Connection connection = getConnection();
                  
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Book (id INT PRIMARY KEY NOT NULL, isbn VARCHAR UNIQUE, title VARCHAR, author VARCHAR, date timestamp)");
            stmt.executeUpdate("INSERT INTO Book (id, isbn, title, author, date) VALUES (01, '9782070541270', 'Harry Peteur', 'JK Roue Libre', now())");
            //stmt.executeUpdate("INSERT INTO Book (id, isbn, title, author, date) VALUES (02, '9782070541271', 'Harry Pot de fleurs', 'JK Roue Libre', now())");            
            
            return "ajouter a la base!";
        } catch (Exception e) {
            
          return "There was an error: " + e.getMessage();
        }
    }
}
