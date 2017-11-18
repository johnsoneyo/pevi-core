/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.models.dto;

/**
 *
 * @author johnson3yo
 */
public class AnonymousOrder {

    private String fname;
    private String lname;
    private String phone;
    private String email;
    private String address;
    private OrderDTO[] orders;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderDTO[] getOrders() {
        return orders;
    }

    public void setOrders(OrderDTO[] orders) {
        this.orders = orders;
    }
    
    

}
