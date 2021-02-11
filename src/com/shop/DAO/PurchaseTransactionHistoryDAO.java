/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAO;

import com.shop.model.PurchaseTransactionHistory;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public interface PurchaseTransactionHistoryDAO {
    public Integer addPurchaseTransactionHistory(PurchaseTransactionHistory purchaseTransactionHistory);
    public Integer updatePurchaseTransactionHistory(PurchaseTransactionHistory purchaseTransactionHistory);
    public Integer deletePurchaseTransactionHistory(Integer purchaseTransactionHistoryID);
    public PurchaseTransactionHistory getPurchaseTransactionHistoryByID(Integer purchaseTransactionHistoryID);
    public Integer getPurchaseTransactionHistoryIDByPurchaseID(Integer purchaseTransactionHistoryID);
    public List<PurchaseTransactionHistory> getAllPurchaseTransactionHistory();
    
}
