/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.shop.DAO.PurchaseTransactionHistoryDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Account;
import com.shop.model.Purchase;
import com.shop.model.PurchaseTransactionHistory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class PurchaseTransactionHistoryDAOImpl implements PurchaseTransactionHistoryDAO{
    
    // table and column names
    private static final String TABLE_PURCHASE_TRANSACTION_HISTORY = "purchase_transaction_history";
    private static final String COLUMN_PURCHASE_TRANSACTION_HISTORY_ID = "purchase_transaction_history_id";
    private static final String COLUMN_ACCOUNT_ID = "account_id";
    private static final String COLUMN_PURCHASE_DATE = "purchase_date";
    private static final String COLUMN_PURCHASE_ID = "purchase_id";
    private static final String COLUMN_TRANSACTION_AMOUNT = "transaction_ammount";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_PURCHASE_TRANSACTION_HISTORY = "insert into "+TABLE_PURCHASE_TRANSACTION_HISTORY+"("+COLUMN_ACCOUNT_ID+","+COLUMN_PURCHASE_DATE+","+COLUMN_PURCHASE_ID+","+COLUMN_TRANSACTION_AMOUNT+","+COLUMN_REMARKS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,1)";
    private static final String UPDATE_PURCHASE_TRANSACTION_HISTORY = "update "+TABLE_PURCHASE_TRANSACTION_HISTORY+" set "+COLUMN_ACCOUNT_ID+"=?, "+COLUMN_PURCHASE_DATE+"=?,"+COLUMN_PURCHASE_ID+"=?,"+COLUMN_TRANSACTION_AMOUNT+"=?,"+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_PURCHASE_TRANSACTION_HISTORY_ID+"=?";
    private static final String DELETE_PURCHASE_TRANSACTION_HISTORY = "update "+TABLE_PURCHASE_TRANSACTION_HISTORY+" set "+COLUMN_STATUS+"=0 where "+COLUMN_PURCHASE_TRANSACTION_HISTORY_ID+" =?";
    private static final String GET_PURCHASE_TRANSACTION_HISTORY_BY_ID = "select * from "+TABLE_PURCHASE_TRANSACTION_HISTORY+" where "+COLUMN_PURCHASE_TRANSACTION_HISTORY_ID+" =?";
    private static final String GET_PURCHASE_TRANSACTION_HISTORY_ID_BY_PURCHASE_ID = "select "+COLUMN_PURCHASE_TRANSACTION_HISTORY_ID+" from "+TABLE_PURCHASE_TRANSACTION_HISTORY+" where "+COLUMN_PURCHASE_ID+" =? ";
    private static final String GET_ALL_PURCHASE_TRANSACTION_HISTORY = "select * from "+TABLE_PURCHASE_TRANSACTION_HISTORY;
    
    
    // Connection
    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addPurchaseTransactionHistory(PurchaseTransactionHistory purchaseTransactionHistory) {
        Integer row = null;
        try {
            
            java.sql.Date purchaseDate = new java.sql.Date(purchaseTransactionHistory.getPurchaseDate().getTime());
            java.sql.Date createdDate = new java.sql.Date(purchaseTransactionHistory.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(ADD_PURCHASE_TRANSACTION_HISTORY);
            pstmt.setInt(1, purchaseTransactionHistory.getAccount().getAccountID());
            pstmt.setDate(2, purchaseDate);
            pstmt.setInt(3, purchaseTransactionHistory.getPurchase().getPurchaseID());
            pstmt.setDouble(4, purchaseTransactionHistory.getTransactionAmount());
            pstmt.setString(5, purchaseTransactionHistory.getRemarks());
            pstmt.setDate(6, createdDate);
            pstmt.setInt(7, purchaseTransactionHistory.getCreatedBy());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updatePurchaseTransactionHistory(PurchaseTransactionHistory purchaseTransactionHistory) {
        Integer row = null;
        try {
            
            java.sql.Date purchaseDate = new java.sql.Date(purchaseTransactionHistory.getPurchaseDate().getTime());
            java.sql.Date modifiedDate = new java.sql.Date(purchaseTransactionHistory.getModifiedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PURCHASE_TRANSACTION_HISTORY);
            pstmt.setInt(1, purchaseTransactionHistory.getAccount().getAccountID());
            pstmt.setDate(2, purchaseDate);
            pstmt.setInt(3, purchaseTransactionHistory.getPurchase().getPurchaseID());
            pstmt.setDouble(4, purchaseTransactionHistory.getTransactionAmount());
            pstmt.setString(5, purchaseTransactionHistory.getRemarks());
            pstmt.setDate(6, modifiedDate);
            pstmt.setInt(7, purchaseTransactionHistory.getModifiedBy());
            pstmt.setInt(8, purchaseTransactionHistory.getPurchaseTransactionID());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deletePurchaseTransactionHistory(Integer purchaseTransactionHistoryID) {
        Integer row = null;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(DELETE_PURCHASE_TRANSACTION_HISTORY);
            pstmt.setInt(1, purchaseTransactionHistoryID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public PurchaseTransactionHistory getPurchaseTransactionHistoryByID(Integer purchaseTransactionHistoryID) {
        PurchaseTransactionHistory purchaseTransactionHistory = null;
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PURCHASE_TRANSACTION_HISTORY_BY_ID);
            pstmt.setInt(1, purchaseTransactionHistoryID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                purchaseTransactionHistory = new PurchaseTransactionHistory();
                
                Purchase purchase = new Purchase();
                purchase.setPurchaseID(rs.getInt(COLUMN_PURCHASE_ID));
                
                Account account = new Account();
                account.setAccountID(rs.getInt(COLUMN_ACCOUNT_ID));
                
                purchaseTransactionHistory.setPurchaseTransactionID(rs.getInt(COLUMN_PURCHASE_TRANSACTION_HISTORY_ID));
                purchaseTransactionHistory.setAccount(account);
                purchaseTransactionHistory.setPurchaseDate(rs.getDate(COLUMN_PURCHASE_DATE));
                purchaseTransactionHistory.setPurchase(purchase);
                purchaseTransactionHistory.setTransactionAmount(rs.getDouble(COLUMN_TRANSACTION_AMOUNT));
                purchaseTransactionHistory.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                purchaseTransactionHistory.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                purchaseTransactionHistory.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                purchaseTransactionHistory.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                purchaseTransactionHistory.setRemarks(rs.getString(COLUMN_REMARKS));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return purchaseTransactionHistory;
    }

    @Override
    public Integer getPurchaseTransactionHistoryIDByPurchaseID(Integer purchaseID) {
        Integer id = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PURCHASE_TRANSACTION_HISTORY_ID_BY_PURCHASE_ID);
            pstmt.setInt(1, purchaseID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_PURCHASE_TRANSACTION_HISTORY_ID);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<PurchaseTransactionHistory> getAllPurchaseTransactionHistory() {
        List<PurchaseTransactionHistory> allPurchaseTransactionHistorys = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_PURCHASE_TRANSACTION_HISTORY);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                PurchaseTransactionHistory purchaseTransactionHistory = new PurchaseTransactionHistory();
                
                Purchase purchase = new Purchase();
                purchase.setPurchaseID(rs.getInt(COLUMN_PURCHASE_ID));
                
                Account account = new Account();
                account.setAccountID(rs.getInt(COLUMN_ACCOUNT_ID));
                
                purchaseTransactionHistory.setPurchaseTransactionID(rs.getInt(COLUMN_PURCHASE_TRANSACTION_HISTORY_ID));
                purchaseTransactionHistory.setAccount(account);
                purchaseTransactionHistory.setPurchaseDate(rs.getDate(COLUMN_PURCHASE_DATE));
                purchaseTransactionHistory.setPurchase(purchase);
                purchaseTransactionHistory.setTransactionAmount(rs.getDouble(COLUMN_TRANSACTION_AMOUNT));
                purchaseTransactionHistory.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                purchaseTransactionHistory.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                purchaseTransactionHistory.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                purchaseTransactionHistory.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                purchaseTransactionHistory.setRemarks(rs.getString(COLUMN_REMARKS));
                
                allPurchaseTransactionHistorys.add(purchaseTransactionHistory);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return allPurchaseTransactionHistorys;
    }
    
}
