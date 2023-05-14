/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

import com.exception.BookNotFoundException;
import com.exception.CustomerNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;
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
                
                //TO-DO appel de StockService
                int stock = 0;
                
                String text = "There are " + stock +" Book(s) : " + isbn;
                return Response.status(200).type("text/plain").entity(text).build();
            }
        } catch (BookNotFoundException | CustomerNotFoundException ex) {
            Logger.getLogger(ShoppingService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(404).type("text/plain").entity(ex.getMessage()).build();
        }
        
        return null;
    }
    
    @Path("book/{isbn}/quantity/{quantity}/corr/{corr}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response BuyReq(
            @PathParam("isbn") String isbn,
            @PathParam("quantity") String quantity,
            @PathParam("corr") String corr
    ) {
        try {
            Book b = new Book();
            Customer c = new Customer();
            OrderBook order = new OrderBook();
                        
            if(Integer.parseInt(quantity) <= 0){
                return Response.status(400).type("text/plain").entity("bad quantity").build();
            }
            
            if (b.isValid(isbn) && c.isValid(corr)){
                
                //TO-DO appel de WholesaleService ou StockService

                order.setItbd(isbn, quantity, corr);
                
                String text = "Command of " + quantity + " Book(s) (" + isbn + ") has been created";
                return Response.status(200).type("text/plain").entity(text).build();
            }
        } catch (BookNotFoundException | CustomerNotFoundException ex) {
            Logger.getLogger(ShoppingService.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(404).type("text/plain").entity(ex.getMessage()).build();
        }
        
        return null;
    }
}
