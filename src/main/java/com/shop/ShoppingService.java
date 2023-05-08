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

/**
 *
 * @author tdasilvamendonca
 */
@Path("shopping_service")
public class ShoppingService {

    @Path("customer/{account}/book/{isbn}/from/{Customer}/to/{Service}/corr/{corr}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean BookReq(
            @PathParam("account") String account,
            @PathParam("isbn") String isbn,
            @PathParam("Customer") String from,
            @PathParam("Service") String to,
            @PathParam("corr") String corr
    ) {
        try {
            Book b = new Book();
            Customer c = new Customer();
            
            if (
                b.isValid(isbn) &&
                c.isValid(account) && 
                from.equals("Client") &&
                to.equals("ShoppingService") &&
                corr.equals("corr")
                ){
                
                //TO-DO appel de StockService
            
                return true;
            }
        } catch (CustomerNotFoundException | BookNotFoundException ex) {
            Logger.getLogger(ShoppingService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    @Path("book/{isbn}/quantity/{quantity}/from/{Customer}/to/{Service}/corr/{corr}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean BuyReq(
            @PathParam("isbn") String isbn,
            @PathParam("quantity") String quantity,
            @PathParam("Customer") String from,
            @PathParam("Service") String to,
            @PathParam("corr") String corr
    ) {
        try {
            Book b = new Book();

            if (
                b.isValid(isbn) &&
                from.equals("Client") &&
                to.equals("WholeSalerService") &&
                corr.equals("corr")
                ){
            //TO-DO appel de WholesaleService
            
            return true;
            }
        } catch (BookNotFoundException e){
            Logger.getLogger(ShoppingService.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return false;
    }

    private int GetStockReq(String bn, String from, String to, String corr) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
