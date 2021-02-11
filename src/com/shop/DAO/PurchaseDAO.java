/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.Purchase;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface PurchaseDAO {
    public Integer addPurchase(Purchase purchase);
    public Integer updatePurchase(Purchase purchase);
    public Integer deletePurchase(Integer vendorID);
    public Purchase getPurchaseByID(Integer vendorID);
    public Integer getPurchaseIDByName(String purchaseName);
    public List<Purchase> getAllPurchase();
    
}
