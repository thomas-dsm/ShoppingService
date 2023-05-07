/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connection;

import java.sql.DriverManager;

/**
 *
 * @author tdasilvamendonca
 */
public class ConnectionManager {
    
    public java.sql.Connection getConnection() throws Exception {

        String dbUrl = "jdbc:postgresql://ec2-34-193-110-25.compute-1.amazonaws.com:5432/d1jltmbva2bnt0";
        String username = "phvzkuazxuyeoa";
        String password = "caf84127a1b42ab4cc9c92d1860fd04b610f934efeeefb7a7fd6a4b177731872";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
