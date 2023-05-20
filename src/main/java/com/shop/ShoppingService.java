/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

import com.connection.CallServiceManager;
import com.exception.BookNotFoundException;
import com.exception.CustomerNotFoundException;
import com.exception.ServiceException;
import io.github.cdimascio.dotenv.Dotenv;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author tdasilvamendonca
 */
@Path("shopping_service")
public class ShoppingService {
    
    @Path("book/{isbn}/customer/{account}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response BookReq(
            @PathParam("isbn") String isbn,
            @PathParam("account") String account
    ) {
        try {
            Book b = new Book();
            Customer c = new Customer();
            
            if (b.isValid(isbn) && c.isValid(account)) {
                
                Response response = new CallServiceManager().getResponse("URL_STOCK_SERVICE", "/stock_service/get/book/", isbn);

                String stock = response.readEntity(String.class);
                String text = "There are " + stock +" Book(s) : " + isbn;

                return Response.status(200).type("text/plain").entity(text).build();
            }
        } catch (BookNotFoundException | CustomerNotFoundException ex) {
            return Response.status(404).type("text/plain").entity(ex.getMessage()).build();

        } catch (ServiceException ex){
            return Response.status(404).type("text/plain").entity(ex.getMessage("StockService")).build();
        }
        
        return null;
    }
    
    @Path("book/{isbn}/quantity/{quantity}/customer/{account}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response BuyReq(
            @PathParam("isbn") String isbn,
            @PathParam("quantity") int quantity,
            @PathParam("account") String account
    ) {
        try {
            Book b = new Book();
            Customer c = new Customer();
            OrderBook order = new OrderBook();

            if(quantity <= 0){
                String text = "Quantity can't be equals to zero";
                return Response.status(400).type("text/plain").entity(text).build();
            }

            if (b.isValid(isbn) && c.isValid(account)){

                int stock = Integer.parseInt(new CallServiceManager().getResponse("URL_STOCK_SERVICE", "/stock_service/get/book/", isbn).readEntity(String.class));
                
                if (stock <= quantity){
                    sendOrderToWholeSalerService(isbn, account, quantity);
                }
                
                new CallServiceManager().getResponse("URL_STOCK_SERVICE", "/stock_service/remove/book/", isbn + "/" + quantity);

                order.setItbd(isbn, quantity, account);

                String text = "Command of " + quantity + " Book(s) (" + isbn + ") has been created";
                return Response.status(200).type("text/plain").entity(text).build();
            }
        } catch (BookNotFoundException | CustomerNotFoundException | ServiceException ex) {
            return Response.status(404).type("text/plain").entity(ex.getMessage()).build();
        }
        
        return null;
    }

    private void sendOrderToWholeSalerService(String isbn, String account, int quantity) throws ServiceException {

        float i = quantity / 5;
        int j = 0;

        String key = Dotenv.load().get("KEY_STORE");

        String command = key + "/" + isbn + "/5/" + account;

        while (j < i){
            new CallServiceManager().getResponse("URL_WHOLESALER_SERVICE", "/wholesaler_service/command/", command );
            j+=1;
        }

        new CallServiceManager().getResponse("URL_STOCK_SERVICE", "/stock_service/add/book/", isbn + "/" + j*5);
    }
}
