/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shop.DAOImpl;

import com.mysql.jdbc.Connection;
import com.shop.DAO.PurchaseDAO;
import com.shop.DBManager.DBConnection;
import com.shop.model.Purchase;
import com.shop.model.Vendor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Turab Bajeer
 */
public class PurchaseDAOImpl implements PurchaseDAO{
    
    // table and column names
    private static final String TABLE_PURCHASE = "purchase";
    private static final String COLUMN_PURCHASE_ID = "purchase_id";
    private static final String COLUMN_VENDOR_ID = "vendor_id";
    private static final String COLUMN_PURCHASE_CODE = "purchase_code";
    private static final String COLUMN_PURCHASE_DATE = "purchase_date";
    private static final String COLUMN_GRAND_TOTAL = "grand_total";
    private static final String COLUMN_DISCOUNT = "discount";
    private static final String COLUMN_REMARKS = "remarks";
    private static final String COLUMN_CREATED_DATE = "created_date";
    private static final String COLUMN_CREATED_BY = "created_by";
    private static final String COLUMN_MODIFIED_DATE = "modified_date";
    private static final String COLUMN_MODIFIED_BY = "modified_by";
    private static final String COLUMN_STATUS = "status";
    
    
    //queries
    private static final String ADD_PURCHASE = "insert into "+TABLE_PURCHASE+"("+COLUMN_VENDOR_ID+","+COLUMN_PURCHASE_CODE+","+COLUMN_PURCHASE_DATE+","+COLUMN_GRAND_TOTAL+","+COLUMN_DISCOUNT+","+COLUMN_REMARKS+","+COLUMN_CREATED_DATE+","+COLUMN_CREATED_BY+","+COLUMN_STATUS+") values (?,?,?,?,?,?,?,?,1)";
    private static final String UPDATE_PURCHASE = "update "+TABLE_PURCHASE+" set "+COLUMN_VENDOR_ID+"=?, "+COLUMN_PURCHASE_CODE+"=?,"+COLUMN_PURCHASE_DATE+"=?,"+COLUMN_GRAND_TOTAL+"=?,"+COLUMN_DISCOUNT+"=?,"+COLUMN_REMARKS+"=?,"+COLUMN_MODIFIED_DATE+"=?,"+COLUMN_MODIFIED_BY+"=? where "+COLUMN_PURCHASE_ID+"=?";
    private static final String DELETE_PURCHASE = "update "+TABLE_PURCHASE+" set "+COLUMN_STATUS+"=0 where "+COLUMN_PURCHASE_ID+" =?";
    private static final String GET_PURCHASE_BY_ID = "select * from "+TABLE_PURCHASE+" where "+COLUMN_PURCHASE_ID+" =?";
    private static final String GET_PURCHASE_ID_BY_CODE = "select "+COLUMN_PURCHASE_ID+" from "+TABLE_PURCHASE+" where "+COLUMN_PURCHASE_CODE+" =? ";
    private static final String GET_ALL_PURCHASE = "select * from "+TABLE_PURCHASE;
    
    
    // Connection
    Connection conn = DBConnection.getConnection();

    @Override
    public Integer addPurchase(Purchase purchase) {
        Integer row = null;
        
        try {
            
            java.sql.Date purchaseDate = new java.sql.Date(purchase.getPurchaseDate().getTime());
            java.sql.Date createdDate = new java.sql.Date(purchase.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(ADD_PURCHASE);
            pstmt.setInt(1, purchase.getVendor().getVendorID());
            pstmt.setString(2, purchase.getPurchaseCode());
            pstmt.setDate(3, purchaseDate);
            pstmt.setDouble(4, purchase.getGrandTotal());
            pstmt.setDouble(5, purchase.getDiscount());
            pstmt.setString(6, purchase.getRemarks());
            pstmt.setDate(7, createdDate);
            pstmt.setInt(8, purchase.getCreatedBy());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer updatePurchase(Purchase purchase) {
        Integer row = null;
        
        try {
            
            java.sql.Date purchaseDate = new java.sql.Date(purchase.getPurchaseDate().getTime());
            java.sql.Date createdDate = new java.sql.Date(purchase.getCreatedDate().getTime());
            
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_PURCHASE);
            pstmt.setInt(1, purchase.getVendor().getVendorID());
            pstmt.setString(2, purchase.getPurchaseCode());
            pstmt.setDate(3, purchaseDate);
            pstmt.setDouble(4, purchase.getGrandTotal());
            pstmt.setDouble(5, purchase.getDiscount());
            pstmt.setString(6, purchase.getRemarks());
            pstmt.setDate(7, createdDate);
            pstmt.setInt(8, purchase.getCreatedBy());
            pstmt.setInt(9, purchase.getPurchaseID());
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deletePurchase(Integer purchaseID) {
        Integer row = null;
        
        try {
            
            PreparedStatement pstmt = conn.prepareStatement(DELETE_PURCHASE);
            pstmt.setInt(1, purchaseID);
            
            row = pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Purchase getPurchaseByID(Integer purchaseID) {
        Purchase purchase = null;
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PURCHASE_BY_ID);
            pstmt.setInt(1, purchaseID);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                purchase = new Purchase();
                
                Vendor vendor = new Vendor();
                vendor.setVendorID(rs.getInt(COLUMN_VENDOR_ID));
                
                purchase.setPurchaseID(rs.getInt(COLUMN_PURCHASE_ID));
                purchase.setVendor(vendor);
                purchase.setPurchaseCode(rs.getString(COLUMN_PURCHASE_CODE));
                purchase.setPurchaseDate(rs.getDate(COLUMN_PURCHASE_DATE));
                purchase.setGrandTotal(rs.getDouble(COLUMN_GRAND_TOTAL));
                purchase.setDiscount(rs.getDouble(COLUMN_DISCOUNT));
                purchase.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                purchase.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                purchase.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                purchase.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                purchase.setRemarks(rs.getString(COLUMN_REMARKS));
                
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return purchase;
    }

    @Override
    public Integer getPurchaseIDByName(String purchaseName) {
        Integer id = null;
        ResultSet rs = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_PURCHASE_ID_BY_CODE);
            pstmt.setString(1, purchaseName);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
            
                id = rs.getInt(COLUMN_PURCHASE_ID);
            }
            
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Purchase> getAllPurchase() {
        List<Purchase> allPurchase = new ArrayList<>();
        ResultSet rs = null;
        
        try {
            PreparedStatement pstmt = conn.prepareStatement(GET_ALL_PURCHASE);
            
            rs = pstmt.executeQuery();
            
            while(rs.next()){
            
                Purchase purchase = new Purchase();
                
                Vendor vendor = new Vendor();
                vendor.setVendorID(rs.getInt(COLUMN_VENDOR_ID));
                
                purchase.setPurchaseID(rs.getInt(COLUMN_PURCHASE_ID));
                purchase.setVendor(vendor);
                purchase.setPurchaseCode(rs.getString(COLUMN_PURCHASE_CODE));
                purchase.setPurchaseDate(rs.getDate(COLUMN_PURCHASE_DATE));
                purchase.setGrandTotal(rs.getDouble(COLUMN_GRAND_TOTAL));
                purchase.setDiscount(rs.getDouble(COLUMN_DISCOUNT));
                purchase.setCreatedDate(rs.getDate(COLUMN_CREATED_DATE));
                purchase.setCreatedBy(rs.getInt(COLUMN_CREATED_BY));
                purchase.setModifiedDate(rs.getDate(COLUMN_MODIFIED_DATE));
                purchase.setModifiedBy(rs.getInt(COLUMN_MODIFIED_BY));
                purchase.setRemarks(rs.getString(COLUMN_REMARKS));
                
                allPurchase.add(purchase);
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            e.printStackTrace();
        }
        
        return allPurchase;
    }
    
}
