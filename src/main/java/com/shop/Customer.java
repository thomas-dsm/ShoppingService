/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

/**
 *
 * @author tdasilvamendonca
 */
public class Customer {
    
    private int idCustomer;
    private String name;
    private String firstname;

    public Customer() {
    }

    public Customer(int idCustomer, String name, String firstname) {
        this.idCustomer = idCustomer;
        this.name = name;
        this.firstname = firstname;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public String toString() {
        return "Customer{" + "idCustomer=" + idCustomer + ", name=" + name + ", firstname=" + firstname + '}';
    }
}
