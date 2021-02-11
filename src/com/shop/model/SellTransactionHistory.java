/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.model;

import java.util.Date;

/**
 *
 * @author Turab Bajeer
 */
public class SellTransactionHistory extends GeneralModel{
    private Integer sellTransactionID;
    private Account account;
    private java.util.Date sellingDate;
    private Order order;
    private Double amount;
    

    public Integer getSellTransactionID() {
        return sellTransactionID;
    }

    public void setSellTransactionID(Integer sellTransactionID) {
        this.sellTransactionID = sellTransactionID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getSellingDate() {
        return sellingDate;
    }

    public void setSellingDate(Date sellingDate) {
        this.sellingDate = sellingDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    

    
    
    
    
}
