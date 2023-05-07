/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shop;

/**
 *
 * @author tdasilvamendonca
 */
public class Store {
    
    private int idStore;
    private String account;
    private String nameStore;

    public Store() {
    }

    public Store(int idStore, String account, String nameStore) {
        this.idStore = idStore;
        this.account = account;
        this.nameStore = nameStore;
    }

    public int getIdStore() {
        return idStore;
    }

    public void setIdStore(int idStore) {
        this.idStore = idStore;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNameStore() {
        return nameStore;
    }

    public void setNameStore(String nameStore) {
        this.nameStore = nameStore;
    }
}
