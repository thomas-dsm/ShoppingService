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

    private Connection getConnection() throws Exception {
        // Class.forName("org.postgresql.Driver");
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

    private String showDatabase()
    {
        try {
            Connection connection = getConnection();

            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Book (isbn INT PRIMARY KEY NOT NULL, title VARCHAR, author VARCHAR, date timestamp)");
            stmt.executeUpdate("INSERT INTO Book VALUES (1L, \"Harry Pot de fleurs\", \"JK roue libre\", now())");
            ResultSet rs = stmt.executeQuery("SELECT b FROM Book");

            String out = "Hello!\n";
            while (rs.next()) {
                out += "Read from DB: " + rs.getTimestamp("b") + "\n";
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
          stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Book (isbn INT PRIMARY KEY NOT NULL, title VARCHAR, author VARCHAR, date timestamp)");
          stmt.executeUpdate("INSERT INTO Book VALUES (1L, \"Harry Pot de fleurs\", \"JK roue libre\", now())");
          return "ajouter a la base";
        } catch (Exception e) {
          return "There was an error: " + e.getMessage();
        }
    }
}
