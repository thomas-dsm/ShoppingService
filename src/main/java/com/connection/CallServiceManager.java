/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.connection;

import com.exception.ServiceException;
import io.github.cdimascio.dotenv.Dotenv;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tdasilvamendonca
 */
public class CallServiceManager {
    
    public Response getResponse(String host, String url, String path) throws ServiceException {
        
        Dotenv dotenv = Dotenv.load();
        String target = dotenv.get(host) + url;
        
        Client client = ClientBuilder.newClient();
        Response response = client.target(target).path(path).request(MediaType.APPLICATION_JSON_TYPE).get();
        
        if (response.getStatus() != 200){
            throw new ServiceException(Integer.toString(response.getStatus()));
        }
        
        return response;
    }
}
