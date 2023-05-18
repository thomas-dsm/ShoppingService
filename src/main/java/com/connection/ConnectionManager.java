/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connection;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.DriverManager;

/**
 *
 * @author tdasilvamendonca
 */
public class ConnectionManager {
    
    public java.sql.Connection getConnection() throws Exception {

        Dotenv dotenv = Dotenv.load();
        String dbUrl = dotenv.get("DATABASE_URL");
        String username = dotenv.get("DATABASE_USERNAME");
        String password = dotenv.get("DATABASE_PASSWORD");

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
