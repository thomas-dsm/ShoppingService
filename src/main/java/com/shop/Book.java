/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

import java.time.LocalDate;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author tdasilvamendonca
 */
@Path("book")
public class Book {
    
    private Long isbn;
    private String title;
    private String author;
    private LocalDate date;

    public Book() {
    }

    public Book(Long isbn, String title, String author, LocalDate date) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.date = date;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
