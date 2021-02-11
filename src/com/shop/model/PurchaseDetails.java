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
public class PurchaseDetails extends GeneralModel{
    private Integer purchaseDetailsID;
    private Purchase purchase;
    private Double total;
    private Product product;
    private Integer quantity;
    private Double purchasePrice;

    

    public Integer getPurchaseDetailsID() {
        return purchaseDetailsID;
    }

    public void setPurchaseDetailsID(Integer purchaseDetailsID) {
        this.purchaseDetailsID = purchaseDetailsID;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    
    
    
    
}
