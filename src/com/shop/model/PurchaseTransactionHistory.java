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
public class PurchaseTransactionHistory extends GeneralModel{
    private Integer purchaseTransactionID;
    private Account account;
    private java.util.Date purchaseDate;
    private Purchase purchase;
    private Double transactionAmount;
    

    public Integer getPurchaseTransactionID() {
        return purchaseTransactionID;
    }

    public void setPurchaseTransactionID(Integer purchaseTransactionID) {
        this.purchaseTransactionID = purchaseTransactionID;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


   
    
    
    
}
