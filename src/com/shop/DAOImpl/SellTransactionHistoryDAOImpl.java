/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.shop.DAO.SellTransactionHistoryDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Account;
import com.shop.model.Order;
import com.shop.model.SellTransactionHistory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class SellTransactionHistoryDAOImpl implements SellTransactionHistoryDAO{
    
    // table and column names
    private static final String TABLE_SELL_TRANSACTION_HISTORY = "sell_transaction_history";
    private static final String COLUMN_SELL_TRANSACTION_HISTORY_ID = "sell_transaction_history_id";
    private static final String COLUMN_ACCOUNT_ID = "account_id";
    private static final String COLUMN_SELLING_DATE = "selling_date";
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_AMOUNT = "amount";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_SELL_TRANSACTION_HISTORY = "insert into "+TABLE_SELL_TRANSACTION_HISTORY+"("+COLUMN_ACCOUNT_ID+","+COLUMN_SELLING_DATE+","+COLUMN_ORDER_ID+","+COLUMN_AMOUNT+","+COLUMN_REMARKS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,1)";
    private static final String UPDATE_SELL_TRANSACTION_HISTORY = "update "+TABLE_SELL_TRANSACTION_HISTORY+" set "+COLUMN_ACCOUNT_ID+"=?, "+COLUMN_SELLING_DATE+"=?,"+COLUMN_ORDER_ID+"=?,"+COLUMN_AMOUNT+"=?,"+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_SELL_TRANSACTION_HISTORY_ID+"=?";
    private static final String DELETE_SELL_TRANSACTION_HISTORY = "update "+TABLE_SELL_TRANSACTION_HISTORY+" set "+COLUMN_STATUS+"=0 where "+COLUMN_SELL_TRANSACTION_HISTORY_ID+" =?";
    private static final String GET_SELL_TRANSACTION_HISTORY_BY_ID = "select * from "+TABLE_SELL_TRANSACTION_HISTORY+" where "+COLUMN_SELL_TRANSACTION_HISTORY_ID+" =?";
    private static final String GET_SELL_TRANSACTION_HISTORY_ID_BY_ORDER_ID = "select "+COLUMN_SELL_TRANSACTION_HISTORY_ID+" from "+TABLE_SELL_TRANSACTION_HISTORY+" where "+COLUMN_ORDER_ID+" =? ";
    private static final String GET_ALL_SELL_TRANSACTION_HISTORY = "select * from "+TABLE_SELL_TRANSACTION_HISTORY;
    
    
    // Connection
    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addSellTransactionHistory(SellTransactionHistory purchaseTransactionHistory) {
        Integer row = null;
        try {
            
            java.sql.Date sellingDate = new java.sql.Date(purchaseTransactionHistory.getSellingDate().getTime());
            java.sql.Date createdDate = new java.sql.Date(purchaseTransactionHistory.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(ADD_SELL_TRANSACTION_HISTORY);
            pstmt.setInt(1, purchaseTransactionHistory.getAccount().getAccountID());
            pstmt.setDate(2, sellingDate);
            pstmt.setInt(3, purchaseTransactionHistory.getOrder().getOrderID());
            pstmt.setDouble(4, purchaseTransactionHistory.getAmount());
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
    public Integer updateSellTransactionHistory(SellTransactionHistory purchaseTransactionHistory) {
        Integer row = null;
        try {
            
            java.sql.Date purchaseDate = new java.sql.Date(purchaseTransactionHistory.getSellingDate().getTime());
            java.sql.Date modifiedDate = new java.sql.Date(purchaseTransactionHistory.getModifiedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_SELL_TRANSACTION_HISTORY);
            pstmt.setInt(1, purchaseTransactionHistory.getAccount().getAccountID());
            pstmt.setDate(2, purchaseDate);
            pstmt.setInt(3, purchaseTransactionHistory.getOrder().getOrderID());
            pstmt.setDouble(4, purchaseTransactionHistory.getAmount());
            pstmt.setString(5, purchaseTransactionHistory.getRemarks());
            pstmt.setDate(6, modifiedDate);
            pstmt.setInt(7, purchaseTransactionHistory.getModifiedBy());
            pstmt.setInt(8, purchaseTransactionHistory.getSellTransactionID());
            
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteSellTransactionHistory(Integer sellTransactionHistoryID) {
        Integer row = null;
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(DELETE_SELL_TRANSACTION_HISTORY);
            pstmt.setInt(1, sellTransactionHistoryID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public SellTransactionHistory getSellTransactionHistoryByID(Integer sellTransactionHistoryID) {
        SellTransactionHistory sellTransactionHistory = null;
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_SELL_TRANSACTION_HISTORY_BY_ID);
            pstmt.setInt(1, sellTransactionHistoryID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                sellTransactionHistory = new SellTransactionHistory();
                
                Order order = new Order();
                order.setOrderID(rs.getInt(COLUMN_ORDER_ID));
                
                Account account = new Account();
                account.setAccountID(rs.getInt(COLUMN_ACCOUNT_ID));
                
                sellTransactionHistory.setSellTransactionID(rs.getInt(COLUMN_SELL_TRANSACTION_HISTORY_ID));
                sellTransactionHistory.setAccount(account);
                sellTransactionHistory.setSellingDate(rs.getDate(COLUMN_SELLING_DATE));
                sellTransactionHistory.setOrder(order);
                sellTransactionHistory.setAmount(rs.getDouble(COLUMN_AMOUNT));
                sellTransactionHistory.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                sellTransactionHistory.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                sellTransactionHistory.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                sellTransactionHistory.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                sellTransactionHistory.setRemarks(rs.getString(COLUMN_REMARKS));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return sellTransactionHistory;
    }

    @Override
    public Integer getSellTransactionHistoryIDByOrderID(Integer orderId) {
        Integer id = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_SELL_TRANSACTION_HISTORY_ID_BY_ORDER_ID);
            pstmt.setInt(1, orderId);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_SELL_TRANSACTION_HISTORY_ID);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<SellTransactionHistory> getAllSellTransactionHistory() {
        List<SellTransactionHistory> allSellTransactionHistorys = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_SELL_TRANSACTION_HISTORY);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                SellTransactionHistory sellTransactionHistory = new SellTransactionHistory();
                
                Order order = new Order();
                order.setOrderID(rs.getInt(COLUMN_ORDER_ID));
                
                Account account = new Account();
                account.setAccountID(rs.getInt(COLUMN_ACCOUNT_ID));
                
                sellTransactionHistory.setSellTransactionID(rs.getInt(COLUMN_SELL_TRANSACTION_HISTORY_ID));
                sellTransactionHistory.setAccount(account);
                sellTransactionHistory.setSellingDate(rs.getDate(COLUMN_SELLING_DATE));
                sellTransactionHistory.setOrder(order);
                sellTransactionHistory.setAmount(rs.getDouble(COLUMN_AMOUNT));
                sellTransactionHistory.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                sellTransactionHistory.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                sellTransactionHistory.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                sellTransactionHistory.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                sellTransactionHistory.setRemarks(rs.getString(COLUMN_REMARKS));
                
                allSellTransactionHistorys.add(sellTransactionHistory);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return allSellTransactionHistorys;
    }
    
}
